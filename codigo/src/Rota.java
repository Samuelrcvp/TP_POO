import java.text.SimpleDateFormat;
import java.util.Date;

public class Rota {
    private double quilometragem;
    private Date data;

    public Rota(double quilometragem, Date data) {
        this.quilometragem = quilometragem;
        this.data = data;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public Date getData() {
        return data;
    }

    public String relatorio() {
        StringBuilder aux = new StringBuilder("Relatório: ");
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        aux.append("Quilometragem: "+ quilometragem +" km, ");
        aux.append("Data: "+ formatoData.format(data));

        return aux.toString();
    }
}
