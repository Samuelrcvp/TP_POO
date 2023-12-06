import java.util.List;
import java.util.Objects;
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
        quantRotas = rotas.size();
        tanqueMax = capacidadeMaxima;
        tanqueAtual = tanque.getCapacidadeAtual();
        totalReabastecido = 0;
        
    }

    public boolean addRota(Rota rota) {
        if (quantRotas < MAX_ROTAS && autonomiaMaxima() >= rota.getQuilometragem()) {
            rotas.add(rota);
            return true;
        }
        return false;
    }

    private double autonomiaMaxima() {
        return tanqueMax * CONSUMO;
    }

    private double autonomiaAtual() {
        return tanqueAtual * CONSUMO;
    }

    public double abastecer(double litros) {
        double abastecido = tanque.abastecer(litros);
        totalReabastecido += abastecido;
        return abastecido;
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
        }
    }

    public void iniciarNovoMes() {
        rotas.clear();
    }

    public boolean placaCorresponde(String placa){
        return this.placa.equals(placa);
    }

    public int getQuantRotasPercorridas(){
        return rotasPercoridas.size();
    }

    public double getTotalReabastecido(){
        return totalReabastecido;
    }

    @Override
    public String toString(){
        StringBuilder aux = new StringBuilder("\nVEÍCULO: ");
        aux.append("\nPlaca: "+ placa);
        for (Rota rotaPercorrida : rotasPercoridas) {
            aux.append("\nRotas Percorridas: "+ rotaPercorrida.relatorio());
        }
        
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
