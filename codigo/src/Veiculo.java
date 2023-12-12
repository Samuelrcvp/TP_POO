import java.util.List;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

abstract class Veiculo {

    private EVeiculo tipoVeiculo;
    private final int MAX_ROTAS = 30;
    private double capacidadeMaxima;
    private String placa;
    private List<Rota> rotasPercoridas;
    private int quantRotas;
    Tanque tanque;
    private double totalReabastecido;

    public Veiculo(EVeiculo tipoVeiculo, String placa, ECombustivel combustivel) {

        this.tipoVeiculo = tipoVeiculo;
        capacidadeMaxima = tipoVeiculo.capacidadeMaximaTanque;
        this.placa = placa;
        rotasPercoridas = new ArrayList<>();
        quantRotas = 0;
        tanque = new Tanque(capacidadeMaxima, combustivel.consumoMedio);
        totalReabastecido = 0;
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

    public double abastecer(double litros) {
        double valorAbastecido = tanque.abastecer(litros);
        totalReabastecido += valorAbastecido;
        return valorAbastecido;
    }

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

    public double kmTotal() {
        return rotasPercoridas.stream()
            .mapToDouble(rota -> rota.getQuilometragem())
            .sum();
    }

    public boolean percorrerRota(double quilometragem, Date data) throws ParseException {
        
        Rota rota = addRota(quilometragem, data);
        if (tanque.percorrerRota(rota) && rota != null) {
            rotasPercoridas.add(rota);
            return true;
        }
        return false;
    }

    public boolean percorrerRota(Rota rota) throws ParseException{
        
        if (tanque.percorrerRota(rota) && rota != null) {
            rotasPercoridas.add(rota);
            return true;
        }
        return false;
    }

    public boolean placaCorresponde(String placa){
        return this.placa.equals(placa);
    }

    public void apagarRotas() {
        rotasPercoridas.clear();
    }

    public int getQuantRotas(){
        return quantRotas;
    }

    public int getQuantRotasPercorridas(){
        return rotasPercoridas.size();
    }

    public EVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    @Override
    public String toString(){
        DecimalFormat formatarDouble = new DecimalFormat("#.##");
        StringBuilder aux = new StringBuilder();
        aux.append("\n=============== VEÍCULO ===============");
        aux.append("\nPlaca: "+ placa);
        aux.append("\nTanque Maxímo: "+ formatarDouble.format(capacidadeMaxima) + " | Tanque Atual: "+ formatarDouble.format(tanque.getCapacidadeAtual()));
        aux.append("\nTotal já abastecido: "+ formatarDouble.format(totalReabastecido));
        aux.append("\nRotas percorridas: "+ rotasPercoridas.size());
        aux.append("\nQuilometragem total: "+ formatarDouble.format(kmTotal()));
        aux.append("\nQuilometragem do mês: "+ formatarDouble.format(kmNoMes()));
        aux.append("\n");

        return aux.toString();
    }
}
