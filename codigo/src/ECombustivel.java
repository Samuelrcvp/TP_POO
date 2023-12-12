/**
 * Enumeração `ECombustivel` representa os tipos de combustíveis disponíveis, cada um com seu consumo médio e preço médio.
 */
enum ECombustivel {
    ALCOOL(7d, 3.29),
    GASOLINA(10d, 5.19),
    DIESEL(4d, 6.09);

    double consumoMedio;
    double precoMedio;

    /**
     * Construtor da enumeração `ECombustivel`.
     * @param consumoMedio O consumo médio do combustível.
     * @param precoMedio O preço médio do combustível.
     */
    ECombustivel(double consumoMedio, double precoMedio) {
        this.precoMedio = precoMedio;
        this.consumoMedio = consumoMedio;
    }
}