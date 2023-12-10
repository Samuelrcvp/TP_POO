enum EVeiculo{
    CARRO(50d, 10000d, 10000d),
    VAN(60d,10000d, 12000d),
    FURGAO(80d, 10000d, 12000d),
    CAMINHAO(250d, 20000d, 20000d);

    double capacidadeMaximaTanque;
    double manutencaoPeriodica;
    double manutencaoTrocaPecas;

    EVeiculo(double capacidadeMaximaTanque, double manutencaoPeriodica, double manutencaoTrocaPecas){
        this.capacidadeMaximaTanque = capacidadeMaximaTanque;
        this.manutencaoPeriodica = manutencaoPeriodica;
        this.manutencaoTrocaPecas = manutencaoTrocaPecas;
        
    }
}