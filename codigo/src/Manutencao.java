public class Manutencao{

    Frota frota;

    public double DistanciaProximaManutencaoPeriodica(String placa){
       Veiculo veiculo = frota.localizarVeiculo(placa);
       double kmTotal = veiculo.kmTotal();
       double kmManutencao = veiculo.getTipoVeiculo().manutencaoPeriodica;
       if (kmTotal >= kmManutencao) {
            return 0d;
       }
       return kmManutencao - kmTotal;
    }

    public double DistanciaProximaTrocaDePecas(String placa){
        Veiculo veiculo = frota.localizarVeiculo(placa);
        double kmTotal = veiculo.kmTotal();
        double kmTrocaPecas = veiculo.getTipoVeiculo().manutencaoTrocaPecas;
        if (kmTotal >= kmTrocaPecas) {
            return 0d;
        }
        return kmTrocaPecas - kmTotal;
    }
}