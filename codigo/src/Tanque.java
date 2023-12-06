public class Tanque {

    /* Atributos */
    
    private final double CONSUMO = 8.2;
    private double capacidadeMaxima; 
    private double capacidadeAtual;

    /* Construtores */

    /* Recebe por parâmetro a capacidade maxima do tanque e a capacidade atual */
    public Tanque(double capacidadeMaxima, double capacidadeAtual){
        this.capacidadeMaxima = capacidadeMaxima;
        this.capacidadeAtual = capacidadeAtual;
    }

    /* Métodos */

    public double abastecer(double litros){

        double tanqueDisponivel = (capacidadeMaxima - capacidadeAtual);

        if (litros <= tanqueDisponivel) {

            capacidadeAtual += litros;
            return litros;

        }else {
            capacidadeAtual = capacidadeMaxima;
            return capacidadeAtual;
        }
    }

    public double autonomiaMaxima(){
        return capacidadeMaxima * CONSUMO;

    }

    public double autonomiaAtual(){
        return capacidadeAtual * CONSUMO;

    }

    public double getCapacidadeAtual(){
        return capacidadeAtual;
    }
    public void setCapacidadeAtual(double value){
        this.capacidadeAtual = value;
    }

    public double getConsumo(){
        return CONSUMO;
    }
}
