import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A classe `Rota` representa uma rota percorrida por um veículo, armazenando informações como quilometragem e data.
 */
public class Rota {
    private double quilometragem;
    private Date data;

    /**
     * Construtor da classe `Rota`.
     * @param quilometragem A quilometragem percorrida na rota.
     * @param data A data em que a rota foi percorrida.
     */
    public Rota(double quilometragem, Date data) {
        this.quilometragem = quilometragem;
        this.data = data;
    }

    /**
     * Obtém a quilometragem da rota.
     * @return A quilometragem da rota.
     */
    public double getQuilometragem() {
        return quilometragem;
    }

    /**
     * Obtém a data da rota.
     * @return A data da rota.
     */
    public Date getData() {
        return data;
    }

    /**
     * Gera um relatório da rota, incluindo informações sobre a quilometragem e a data.
     * @return Uma string contendo o relatório da rota.
     */
    public String relatorio() {
        StringBuilder aux = new StringBuilder("Relatório: ");
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        aux.append("Quilometragem: " + quilometragem + " km, ");
        aux.append("Data: " + formatoData.format(data));

        return aux.toString();
    }
}