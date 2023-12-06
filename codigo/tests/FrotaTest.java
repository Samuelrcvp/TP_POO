import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FrotaTest {

    @Test
    public void testLocalizarVeiculoExistente() {
        Frota frota = new Frota();
        Veiculo veiculo1 = new Veiculo("ABC123", 55, 10);
        Veiculo veiculo2 = new Veiculo("DEF456", 55, 15);
        frota.adicionarVeiculo(veiculo1);
        frota.adicionarVeiculo(veiculo2);

        Veiculo encontrado = frota.localizarVeiculo("ABC123");
        assertEquals(veiculo1, encontrado);
    }

    @Test
    public void testLocalizarVeiculoNaoExistente() {
        Frota frota = new Frota();
        Veiculo veiculo1 = new Veiculo("ABC123", 55, 10);
        Veiculo veiculo2 = new Veiculo("DEF456", 50, 15);
        frota.adicionarVeiculo(veiculo1);
        frota.adicionarVeiculo(veiculo2);

        Veiculo encontrado = frota.localizarVeiculo("GHI789");
        assertNull(encontrado);
    }

    @Test
    public void testQuilometragemTotal() throws ParseException {
        Frota frota = new Frota();
        Veiculo veiculo1 = new Veiculo("ABC123", 55, 10);
        Veiculo veiculo2 = new Veiculo("DEF456", 50, 15);

        String dataString = "01/01/2023";
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Date dataEsperada = formatoData.parse(dataString);
        Rota rota1 = new Rota(43, dataEsperada);
        Rota rota2 = new Rota(80, dataEsperada);

        veiculo1.addRota(rota1);
        veiculo1.percorrerRota(rota1);
        veiculo2.addRota(rota2);
        veiculo2.percorrerRota(rota2);
        
        frota.adicionarVeiculo(veiculo1);
        frota.adicionarVeiculo(veiculo2);

        assertEquals(123, frota.quilometragemTotal(), 0.01);
    }

    @Test
    public void testMaiorKmTotal() throws ParseException {
        Frota frota = new Frota();
        Veiculo veiculo1 = new Veiculo("ABC123", 55, 10);
        Veiculo veiculo2 = new Veiculo("DEF456", 50, 15);
        frota.adicionarVeiculo(veiculo1);
        frota.adicionarVeiculo(veiculo2);

        String dataString = "01/01/2023";
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Date dataEsperada = formatoData.parse(dataString);
        Rota rota1 = new Rota(43, dataEsperada);
        Rota rota2 = new Rota(80, dataEsperada);

        veiculo1.addRota(rota1);
        veiculo1.percorrerRota(rota1);
        veiculo2.addRota(rota2);
        veiculo2.percorrerRota(rota2);

        assertEquals(veiculo2, frota.maiorKmTotal());
    }

    @Test
    public void testMaiorKmMedia() throws ParseException {
        Frota frota = new Frota();
        Veiculo veiculo1 = new Veiculo("ABC123", 55, 10);
        Veiculo veiculo2 = new Veiculo("DEF456", 50, 9);
        frota.adicionarVeiculo(veiculo1);
        frota.adicionarVeiculo(veiculo2);

        String dataString = "01/01/2023";
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Date dataEsperada = formatoData.parse(dataString);
        Rota rota1 = new Rota(43, dataEsperada);
        Rota rota2 = new Rota(80, dataEsperada);

        veiculo1.addRota(rota1);
        veiculo1.percorrerRota(rota1);
        veiculo2.addRota(rota2);
        veiculo2.percorrerRota(rota2);

        assertEquals(veiculo2, frota.maiorKmMedia());
    }
}
