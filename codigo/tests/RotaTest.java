import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RotaTest {

    @Test
    public void testRelatorio() throws ParseException {
        double quilometragemEsperada = 100.5;
        String dataString = "01/01/2023";
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Date dataEsperada = formatoData.parse(dataString);

        Rota rota = new Rota(quilometragemEsperada, dataEsperada);

        String relatorioEsperado = "Relatório: Quilometragem: 100.5 km, Data: 01/01/2023";
        assertEquals(relatorioEsperado, rota.relatorio());
    }

    @Test
    public void testGetQuilometragem() {
        double quilometragemEsperada = 50.0;
        Rota rota = new Rota(quilometragemEsperada, new Date());

        assertEquals(quilometragemEsperada, rota.getQuilometragem(), 0.001);
    }

    @Test
    public void testGetData() {
        Date dataEsperada = new Date();
        Rota rota = new Rota(0.0, dataEsperada);

        assertEquals(dataEsperada, rota.getData());
    }
}
