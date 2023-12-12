/**
 * A classe `Furgao` representa um tipo específico de veículo, sendo um subtipo de `Veiculo`.
 */
public class Furgao extends Veiculo {

    /**
     * Construtor da classe `Furgao`.
     * @param placa A placa do furgão.
     * @param combustivel O tipo de combustível utilizado pelo furgão.
     */
    public Furgao(String placa, ECombustivel combustivel) {
        // Chama o construtor da classe pai (Veiculo) passando as informações específicas para inicializar um furgão.
        super(EVeiculo.VAN, placa, combustivel);
    }
}