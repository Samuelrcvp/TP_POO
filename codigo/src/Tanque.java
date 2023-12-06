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

    /* Recebe por parâmetro os litros que serão abastecidos */
    public double abastecer(double litros){

        // Calcula a quantidade de espaço disponível no tanque
        double tanqueDisponivel = (capacidadeMaxima - capacidadeAtual);

        // Verifica se a quantidade de litros a ser abastecida cabe no tanque
        if (litros <= tanqueDisponivel) {

            // Se couber, adiciona os litros ao tanque
            capacidadeAtual += litros;
            // Retorna a quantidade de litros que foram abastecidos
            return litros;

        }
        else{

            // Se não couber, enche o tanque até sua capacidade máxima
            capacidadeAtual = capacidadeMaxima;
             // Retorna a quantidade de litros que couberam no tanque
            return tanqueDisponivel;
            
        }
    }

    /* Calcular autonomia máxima do veículo */
    public double autonomiaMaxima(){
        return capacidadeMaxima * CONSUMO;

    }

    /* Calcular autonomia atual do veículo */
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
