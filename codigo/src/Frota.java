import java.util.List;
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
        StringBuilder aux = new StringBuilder("Relatório Frota: ");
        aux.append("\nTamanho: "+ tamanhoFrota);
        aux.append("\nVeículos: "+ veiculos.toString());

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

    public void adicionarVeiculo(Veiculo veiculo){
        veiculos.add(veiculo);
        tamanhoFrota++;
    }
}
