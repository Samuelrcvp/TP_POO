import java.util.List;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A classe abstrata `Veiculo` serve como base para outros tipos específicos de veículos, como Carro, Van, Furgão e Caminhão.
 */
abstract class Veiculo {

    private EVeiculo tipoVeiculo;
    private final int MAX_ROTAS = 30;
    private double capacidadeMaxima;
    private String placa;
    private List<Rota> rotasPercoridas;
    private int quantRotas;
    Tanque tanque;
    private double totalReabastecido;
    private double totalManutencoes;

    /**
     * Construtor da classe `Veiculo`.
     * @param tipoVeiculo O tipo de veículo.
     * @param placa A placa do veículo.
     * @param combustivel O tipo de combustível utilizado pelo veículo.
     */
    public Veiculo(EVeiculo tipoVeiculo, String placa, ECombustivel combustivel) {

        this.tipoVeiculo = tipoVeiculo;
        capacidadeMaxima = tipoVeiculo.capacidadeMaximaTanque;
        this.placa = placa;
        rotasPercoridas = new ArrayList<>();
        quantRotas = 0;
        tanque = new Tanque(capacidadeMaxima, combustivel.consumoMedio, combustivel.precoMedio);
        totalReabastecido = 0;
        totalManutencoes = 0;

    }

    public double totalGasto(){
        return tanque.getTotalGastoGasolina() + totalManutencoes;
    }

    public void SomarManutencoes(double manutencoes){
        totalManutencoes += manutencoes;
    }

    private double autonomiaMaxima() {
        return tanque.autonomiaMaxima();
    }

    private Rota addRota(double quilometragem, Date data) throws ParseException {

        Rota rota = new Rota(quilometragem, data);
        if (quantRotas < MAX_ROTAS && autonomiaMaxima() >= rota.getQuilometragem()) {
            
            quantRotas++;

            return rota;
        }
        return rota;
    }

    /**
     * Abastece o veículo com a quantidade especificada de litros.
     * @param litros A quantidade de litros a ser abastecida.
     * @return A quantidade de litros realmente abastecida (pode ser menor se o tanque estiver cheio).
     */
    public double abastecer(double litros) {
        double valorAbastecido = tanque.abastecer(litros);
        totalReabastecido += valorAbastecido;
        return valorAbastecido;
    }

    /**
     * Calcula a quilometragem percorrida no mês atual.
     * @return A quilometragem percorrida no mês atual.
     */
    public double kmNoMes() {
        Calendar hoje = Calendar.getInstance();
        int mesAtual = hoje.get(Calendar.MONTH);

        return rotasPercoridas.stream()
                .filter(rota -> {
                    Calendar dataRota = Calendar.getInstance();

                    dataRota.setTime(rota.getData());
                    return dataRota.get(Calendar.MONTH) == mesAtual;
                })
                .mapToDouble(rota -> rota.getQuilometragem())
                .sum();
    }

    /**
     * Calcula a quilometragem total percorrida.
     * @return A quilometragem total percorrida.
     */
    public double kmTotal() {
        return rotasPercoridas.stream()
            .mapToDouble(rota -> rota.getQuilometragem())
            .sum();
    }

    /**
     * Percorre uma rota com base na quilometragem e data fornecidas.
     * @param quilometragem A quilometragem da rota.
     * @param data A data da rota.
     * @return `true` se a rota puder ser percorrida, `false` caso contrário.
     * @throws ParseException
     */
    public boolean percorrerRota(double quilometragem, Date data) throws ParseException {
        
        Rota rota = addRota(quilometragem, data);
        if (tanque.percorrerRota(rota) && rota != null) {
            rotasPercoridas.add(rota);
            return true;
        }
        return false;
    }

    /**
     * Percorre uma rota com base na quilometragem e data fornecidas.
     * @param rota a rota a ser percorrida
     * @return `true` se a rota puder ser percorrida, `false` caso contrário.
     * @throws ParseException
     */
    public boolean percorrerRota(Rota rota) throws ParseException{
        
        if (tanque.percorrerRota(rota) && rota != null) {
            rotasPercoridas.add(rota);
            return true;
        }
        return false;
    }

    /**
     * Verifica se a placa do veículo corresponde à placa fornecida.
     * @param placa A placa a ser verificada.
     * @return `true` se a placa corresponder, `false` caso contrário.
     */
    public boolean placaCorresponde(String placa){
        return this.placa.equals(placa);
    }

    /**
     * Apaga todas as rotas percorridas pelo veículo.
     */
    public void apagarRotas() {
        rotasPercoridas.clear();
    }

    /**
     * Obtém a quantidade total de rotas do veículo.
     * @return A quantidade total de rotas.
     */
    public int getQuantRotas(){
        return quantRotas;
    }

    /**
     * Obtém a quantidade total de rotas percorridas pelo veículo.
     * @return A quantidade total de rotas percorridas.
     */
    public int getQuantRotasPercorridas(){
        return rotasPercoridas.size();
    }

    /**
     * Obtém o tipo de veículo.
     * @return O tipo de veículo.
     */
    public EVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        DecimalFormat formatarDouble = new DecimalFormat("#.##");
        StringBuilder aux = new StringBuilder();
        aux.append("\n=============== VEÍCULO ===============");
        aux.append("\nPlaca: "+ placa);
        aux.append("\nTanque Maxímo: "+ formatarDouble.format(capacidadeMaxima) + " | Tanque Atual: "+ formatarDouble.format(tanque.getCapacidadeAtual()));
        aux.append("\nTotal já abastecido: "+ formatarDouble.format(totalReabastecido));
        aux.append("\nRotas percorridas: "+ rotasPercoridas.size());
        aux.append("\nQuilometragem total: "+ formatarDouble.format(kmTotal()));
        aux.append("\nQuilometragem do mês: "+ formatarDouble.format(kmNoMes()));
        aux.append("\nProxima manutenção periódica daqui a "+ (tipoVeiculo.manutencaoPeriodica - kmNoMes())+ " km");
        aux.append("\nProxima troca de peças daqui a "+ (tipoVeiculo.manutencaoTrocaPecas - kmNoMes()) + " km");
        aux.append("\nDespeza total: "+ moeda.format(totalGasto()));
        aux.append("\n");

        return aux.toString();
    }
}
