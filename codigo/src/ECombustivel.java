enum ECombustivel{
    ALCOOL(7d,3.29),
    GASOLINA(10d,5.19),
    DIESEL(4d,6.09);
    double consumoMedio;
    double precoMedio;

    ECombustivel(double consumoMedio, double precoMedio){
        this.precoMedio=precoMedio;
        this.consumoMedio = consumoMedio;
    }
}