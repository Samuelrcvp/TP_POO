/**
 * A classe `Van` representa um tipo específico de veículo, sendo um subtipo de `Veiculo`.
 */
public class Van extends Veiculo {

    /**
     * Construtor da classe `Van`.
     * @param placa A placa da van.
     * @param combustivel O tipo de combustível utilizado pela van.
     */
    public Van(String placa, ECombustivel combustivel) {
        // Chama o construtor da classe pai (Veiculo) passando as informações específicas para inicializar uma van.
        super(EVeiculo.VAN, placa, combustivel);
    }
}