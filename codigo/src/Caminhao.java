public class Caminhao extends Veiculo{

    /**
     * Construtor da classe `Caminhao`.
     * @param placa A placa do caminhão.
     * @param combustivel O tipo de combustível utilizado pelo caminhão.
     */
    public Caminhao(String placa, ECombustivel combustivel) {
        // Chama o construtor da classe pai (Veiculo) passando as informações específicas para inicializar um caminhão.
        super(EVeiculo.CARRO, placa, combustivel);
    }
}