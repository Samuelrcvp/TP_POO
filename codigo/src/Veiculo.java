import java.util.List;
import java.util.Objects;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Veiculo {
    private final int MAX_ROTAS = 30;
    private final double CONSUMO = 8.2;
    private String placa;
    private List<Rota> rotas;
    private List<Rota> rotasPercoridas;
    private int quantRotas;
    Tanque tanque;
    private double tanqueAtual;
    private double tanqueMax;
    private double totalReabastecido;

    public Veiculo(String placa, double capacidadeMaxima, double capacidadeAtual) {
        this.placa = placa;
        tanque = new Tanque(capacidadeMaxima, capacidadeAtual);
        rotas = new ArrayList<>();
        rotasPercoridas = new ArrayList<>();
        quantRotas = 0;
        tanqueMax = capacidadeMaxima;
        tanqueAtual = tanque.getCapacidadeAtual();
        totalReabastecido = 0;
        
    }

    public boolean addRota(Rota rota) {
        if (quantRotas < MAX_ROTAS && autonomiaMaxima() >= rota.getQuilometragem()) {
            rotas.add(rota);
            quantRotas++;
            return true;
        }
        return false;
    }

    private double autonomiaMaxima() {
        return tanque.autonomiaMaxima();
    }

    private double autonomiaAtual() {
        return tanque.autonomiaAtual();
    }

    public double abastecer(double litros) {
        double valorAbastecido = tanque.abastecer(litros);
        totalReabastecido += valorAbastecido;
        tanqueAtual = tanque.getCapacidadeAtual();
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

    public void percorrerRota(Rota rota) {
        double quilometragem = rota.getQuilometragem();
        boolean podePercorrer = autonomiaAtual() >= quilometragem;
        if (podePercorrer) {
            rotasPercoridas.add(rota);
            tanque.setCapacidadeAtual(tanqueAtual - (quilometragem / CONSUMO));
            tanqueAtual = tanque.getCapacidadeAtual();
        }
    }

    public void iniciarNovoMes() {
        rotas.clear();
        rotasPercoridas.clear();
    }

    public boolean placaCorresponde(String placa){
        return this.placa.equals(placa);
    }

    public int getQuantRotas(){
        return quantRotas;
    }

    public int getQuantRotasPercorridas(){
        return rotasPercoridas.size();
    }

    @Override
    public String toString(){
        DecimalFormat formatarDouble = new DecimalFormat("#.##");
        StringBuilder aux = new StringBuilder();
        aux.append("\n=============== VEÍCULO ===============");
        aux.append("\nPlaca: "+ placa);
        aux.append("\nTanque Maxímo: "+ formatarDouble.format(tanqueMax) + " | Tanque Atual: "+ formatarDouble.format(tanqueAtual));
        aux.append("\nTotal já abastecido: "+ formatarDouble.format(totalReabastecido));
        aux.append("\nRotas adicionadas: "+ quantRotas);
        aux.append("\nRotas percorridas: "+ rotasPercoridas.size());
        aux.append("\nQuilometragem total: "+ formatarDouble.format(kmTotal()));
        aux.append("\nQuilometragem do mês: "+ formatarDouble.format(kmNoMes()));
        aux.append("\n");

        return aux.toString();
    }
    @Override
    public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
        return false;
    }
    Veiculo veiculo = (Veiculo) obj;
    return Objects.equals(placa, veiculo.placa);
}
}
