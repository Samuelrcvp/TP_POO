import java.util.List;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * A classe `Frota` representa uma coleção de veículos e fornece funcionalidades para gerar relatórios e gerenciar a frota.
 */
public class Frota {

    private List<Veiculo> veiculos;
    private int tamanhoFrota;

    /**
     * Construtor da classe `Frota`.
     */
    public Frota() {
        veiculos = new ArrayList<>();
        tamanhoFrota = 0;
    }

    /**
     * Gera um relatório da frota, incluindo informações sobre o número de veículos, quilometragem total,
     * veículo com maior quilometragem total e veículo com maior quilometragem média.
     * @return Uma string contendo o relatório da frota.
     */
    public String relatorioFrota() {
        DecimalFormat formatarDouble = new DecimalFormat("#.##");
        StringBuilder aux = new StringBuilder("================ Relatório Frota ================ ");
        aux.append("\n\nFrota possui " + tamanhoFrota + " veículos");
        aux.append("\n\nQuilometragem total percorrida da frota: " + formatarDouble.format(quilometragemTotal()) + " Km");
        aux.append("\n\nVeículo com maior quilometragem da frota: " + maiorKmTotal());
        aux.append("\n\nVeículo com maior quilometragem média da frota: " + maiorKmMedia());
        aux.append("\n===============================================");

        return aux.toString();
    }

    /**
     * Localiza um veículo na frota com base na placa.
     * @param placa A placa do veículo a ser localizado.
     * @return O veículo correspondente à placa, ou null se não for encontrado.
     */
    public Veiculo localizarVeiculo(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.placaCorresponde(placa))
                return veiculo;
        }
        return null;
    }

    /**
     * Calcula a quilometragem total percorrida por todos os veículos da frota.
     * @return A quilometragem total percorrida.
     */
    public double quilometragemTotal() {
        return veiculos.stream()
            .mapToDouble(Veiculo::kmTotal)
            .sum();
    }

    /**
     * Localiza o veículo com a maior quilometragem total na frota.
     * @return O veículo com a maior quilometragem total, ou null se a frota estiver vazia.
     */
    public Veiculo maiorKmTotal() {
        return veiculos.stream()
            .max(Comparator.comparing(Veiculo::kmTotal))
            .orElse(null);
    }

    /**
     * Localiza o veículo com a maior quilometragem média na frota.
     * @return O veículo com a maior quilometragem média, ou null se a frota estiver vazia.
     */
    public Veiculo maiorKmMedia() {
        return veiculos.stream()
            .max(Comparator.comparing(veiculo -> veiculo.kmTotal() / veiculo.getQuantRotasPercorridas()))
            .orElse(null);
    }

    /**
     * Inicia um novo mês, apagando as rotas percorridas por todos os veículos da frota.
     */
    public void iniciarNovoMes() {
        for (Veiculo veiculo : veiculos) {
            veiculo.apagarRotas();
        }
    }

    /**
     * Adiciona um veículo à frota.
     * @param veiculo O veículo a ser adicionado à frota.
     */
    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
        tamanhoFrota++;
    }
}