/**
 * A classe `Tanque` representa o tanque de um veículo, mantendo informações sobre sua capacidade máxima, capacidade atual e consumo.
 */
public class Tanque {

    /* Atributos */
    
    private final double CONSUMO;
    private double capacidadeMaxima; 
    private double capacidadeAtual;
    private double precoMedioGasolina;
    private double totalGastoGasolina;

    /* Construtores */

    /**
     * Construtor da classe `Tanque`.
     * @param capacidadeMaxima A capacidade máxima do tanque.
     * @param CONSUMO O consumo do veículo (litros por quilômetro).
     * @param precoMedioGasolina O preço médio da gasolina do veículo para calculo.
     */
    public Tanque(double capacidadeMaxima, double CONSUMO, double precoMedioGasolina){
        this.capacidadeMaxima = capacidadeMaxima;
        this.capacidadeAtual = 0;
        this.CONSUMO = CONSUMO;
        this.precoMedioGasolina = precoMedioGasolina;
        totalGastoGasolina = 0;
    }

    /* Métodos */

    /**
     * Abastece o tanque com a quantidade especificada de litros.
     * @param litros A quantidade de litros a ser abastecida.
     * @return A quantidade de litros realmente abastecida (pode ser menor se o tanque estiver cheio).
     */
    public double abastecer(double litros){

        double tanqueDisponivel = (capacidadeMaxima - capacidadeAtual);

        if (litros <= tanqueDisponivel) {
            
            capacidadeAtual += litros;
            totalGastoGasolina += litros * precoMedioGasolina;
            return litros;

        }else if(tanqueDisponivel != 0){
            capacidadeAtual = capacidadeMaxima;
            return tanqueDisponivel;
        }
        return 0; 
    }

    /**
     * Calcula a autonomia máxima do veículo com base na capacidade máxima do tanque e no consumo.
     * @return A autonomia máxima em quilômetros.
     */
    public double autonomiaMaxima(){
        return capacidadeMaxima * CONSUMO;

    }

     /**
     * Calcula a autonomia atual do veículo com base na capacidade atual do tanque e no consumo.
     * @return A autonomia atual em quilômetros.
     */
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

    /**
     * Percorre uma rota, consumindo combustível com base na quilometragem da rota.
     * @param rota A rota a ser percorrida.
     * @return `true` se a rota puder ser percorrida com a autonomia atual, `false` caso contrário.
     */
    public double getCapacidadeAtual(){
        return capacidadeAtual;
    }

    /**
     * Obtém a capacidade atual do tanque.
     * @return A capacidade atual do tanque.
     */
    public double getTotalGastoGasolina(){
        return totalGastoGasolina;
    }

}
