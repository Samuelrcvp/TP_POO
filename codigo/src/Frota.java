import java.util.List;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

public class Frota {

    private List<Veiculo> veiculos;
    private int tamanhoFrota;

    public Frota() {
        veiculos = new ArrayList<>();
        tamanhoFrota = 0;
    }

    public String relatorioFrota(){
        DecimalFormat formatarDouble = new DecimalFormat("#.##");
        StringBuilder aux = new StringBuilder("================ Relatório Frota ================ ");
        aux.append("\n\nFrota possui "+ tamanhoFrota + " veículos");
        aux.append("\n\nQuilometragem total percorrida da frota: "+ formatarDouble.format(quilometragemTotal())+" Km");
        aux.append("\n\nVeículo com maior quilometragem da frota: "+ maiorKmTotal());
        aux.append("\n\nVeículo com maior quilometragem média da frota: "+ maiorKmMedia());
        aux.append("\n===============================================");

        return aux.toString();
    }
    
    public Veiculo localizarVeiculo(String placa){
        for (Veiculo veiculo : veiculos) {
            if (veiculo.placaCorresponde(placa))
                return veiculo;
        }
        return null;
    }

    public double quilometragemTotal(){
        return veiculos.stream()
            .mapToDouble(veiculo -> veiculo.kmTotal()).sum();
    }

    public Veiculo maiorKmTotal(){
        return veiculos.stream()
        .max(Comparator.comparing(veiculo -> veiculo.kmTotal()))
        .orElse(null);
    }

    public Veiculo maiorKmMedia(){
      return veiculos.stream()
            .max(Comparator.comparing(veiculo -> veiculo.kmTotal() / veiculo.getQuantRotasPercorridas()))
            .orElse(null);
    }

    public void iniciarNovoMes() {
        for(Veiculo veiculo:veiculos){
            veiculo.iniciarNovoMes();
        }
    }

    public void adicionarVeiculo(Veiculo veiculo){
        veiculos.add(veiculo);
        tamanhoFrota++;
    }
}
