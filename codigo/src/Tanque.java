public class Tanque {

    /* Atributos */
    
    private final double CONSUMO;
    private double capacidadeMaxima; 
    private double capacidadeAtual;

    /* Construtores */

    /* Recebe por parâmetro a capacidade maxima do tanque e a capacidade atual */
    public Tanque(double capacidadeMaxima, double CONSUMO){
        this.capacidadeMaxima = capacidadeMaxima;
        this.capacidadeAtual = 0;
        this.CONSUMO = CONSUMO;
    }

    /* Métodos */

    public double abastecer(double litros){

        double tanqueDisponivel = (capacidadeMaxima - capacidadeAtual);

        if (litros <= tanqueDisponivel) {

            capacidadeAtual += litros;
            return litros;

        }else if(tanqueDisponivel != 0){
            capacidadeAtual = capacidadeMaxima;
            return tanqueDisponivel;
        }
        return 0; 
    }

    public double autonomiaMaxima(){
        return capacidadeMaxima * CONSUMO;

    }

    public double autonomiaAtual(){
        return capacidadeAtual * CONSUMO;

    }

    public boolean percorrerRota(Rota rota){
        double quilometragem = rota.getQuilometragem();
        boolean podePercorrer = autonomiaAtual() >= quilometragem;
        if (podePercorrer) {
            
            this.capacidadeAtual = (capacidadeAtual - (quilometragem / CONSUMO));
        }
        return podePercorrer;
    }

    public double getCapacidadeAtual(){
        return capacidadeAtual;
    }

}
