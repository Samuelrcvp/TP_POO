public class Carro extends Veiculo {

    /**
     * Construtor da classe `Carro`.
     * @param placa A placa do carro.
     * @param combustivel O tipo de combustível utilizado pelo carro.
     */
    public Carro(String placa, ECombustivel combustivel) {
        // Chama o construtor da classe pai (Veiculo) passando as informações específicas para inicializar um carro.
        super(EVeiculo.CARRO, placa, combustivel);
    }
}