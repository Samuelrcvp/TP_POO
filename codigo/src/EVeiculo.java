/**
 * Enumeração `EVeiculo` representa os tipos de veículos disponíveis, cada um com sua capacidade máxima de tanque
 * e valores de manutenção periódica e troca de peças.
 */
enum EVeiculo {
    CARRO(50d, 10000d, 10000d),
    VAN(60d, 10000d, 12000d),
    FURGAO(80d, 10000d, 12000d),
    CAMINHAO(250d, 20000d, 20000d);

    double capacidadeMaximaTanque;
    double manutencaoPeriodica;
    double manutencaoTrocaPecas;

    /**
     * Construtor da enumeração `EVeiculo`.
     * @param capacidadeMaximaTanque A capacidade máxima do tanque do veículo.
     * @param manutencaoPeriodica O valor de manutenção periódica do veículo.
     * @param manutencaoTrocaPecas O valor de manutenção para a troca de peças do veículo.
     */
    EVeiculo(double capacidadeMaximaTanque, double manutencaoPeriodica, double manutencaoTrocaPecas) {
        this.capacidadeMaximaTanque = capacidadeMaximaTanque;
        this.manutencaoPeriodica = manutencaoPeriodica;
        this.manutencaoTrocaPecas = manutencaoTrocaPecas;
    }
}