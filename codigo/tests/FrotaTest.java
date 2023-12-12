import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FrotaTest {

    @Test
    public void testRelatorioFrota() throws ParseException {
        Frota frota = new Frota();

        Veiculo veiculo1 = new Carro("ABC123", ECombustivel.ALCOOL);
        Veiculo veiculo2 = new Van("XYZ789", ECombustivel.GASOLINA);
        Veiculo veiculo3 = new Furgao("DEF789", ECombustivel.GASOLINA);
        Veiculo veiculo4 = new Caminhao("GHI789", ECombustivel.DIESEL);

        String dataString = "01/01/2023";
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Date dataEsperada = formatoData.parse(dataString);
        Rota rota1 = new Rota(43, dataEsperada);
        Rota rota2 = new Rota(80, dataEsperada);

        veiculo1.abastecer(100);
        veiculo2.abastecer(100);
        veiculo3.abastecer(100);
        veiculo4.abastecer(100);

        veiculo1.percorrerRota(rota1);


        veiculo2.percorrerRota(rota1);

        veiculo2.percorrerRota(rota2);

        veiculo2.percorrerRota(rota2);


        veiculo3.percorrerRota(rota2);

        veiculo4.percorrerRota(rota2);

        frota.adicionarVeiculo(veiculo1);
        frota.adicionarVeiculo(veiculo2);
        frota.adicionarVeiculo(veiculo3);
        frota.adicionarVeiculo(veiculo4);

        String expectedRelatorio = "================ Relatório Frota ================ "
        + "\n\nFrota possui 4 veículos"
        + "\n\nQuilometragem total percorrida da frota: 406 Km"
        + "\n\nVeículo com maior quilometragem da frota: " + veiculo2.toString()
        + "\n\nVeículo com maior quilometragem média da frota: " + veiculo3.toString()
        + "\n===============================================";

        assertEquals(expectedRelatorio, frota.relatorioFrota());
    }

    
    @Test
    public void testLocalizarVeiculoExistente() {
        Frota frota = new Frota();
        Veiculo veiculo1 = new Carro("ABC123", ECombustivel.ALCOOL);
        Veiculo veiculo2 = new Van("XYZ789", ECombustivel.GASOLINA);
        frota.adicionarVeiculo(veiculo1);
        frota.adicionarVeiculo(veiculo2);

        Veiculo encontrado = frota.localizarVeiculo("ABC123");
        assertEquals(veiculo1, encontrado);
    }

    @Test
    public void testLocalizarVeiculoNaoExistente() {
        Frota frota = new Frota();
        Veiculo veiculo1 = new Carro("ABC123", ECombustivel.ALCOOL);
        Veiculo veiculo2 = new Van("XYZ789", ECombustivel.GASOLINA);
        frota.adicionarVeiculo(veiculo1);
        frota.adicionarVeiculo(veiculo2);

        Veiculo encontrado = frota.localizarVeiculo("GHI789");
        assertNull(encontrado);
    }

    @Test
    public void testQuilometragemTotal() throws ParseException {
        Frota frota = new Frota();
        Veiculo veiculo1 = new Carro("ABC123", ECombustivel.ALCOOL);
        Veiculo veiculo2 = new Van("XYZ789", ECombustivel.GASOLINA);

        String dataString = "01/01/2023";
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Date dataEsperada = formatoData.parse(dataString);
        Rota rota1 = new Rota(43, dataEsperada);
        Rota rota2 = new Rota(80, dataEsperada);

        veiculo1.abastecer(100);
        veiculo2.abastecer(100);
        veiculo1.percorrerRota(rota1);
        veiculo2.percorrerRota(rota2);
        
        frota.adicionarVeiculo(veiculo1);
        frota.adicionarVeiculo(veiculo2);

        assertEquals(123, frota.quilometragemTotal(), 0.01);
    }

    @Test
    public void testMaiorKmTotal() throws ParseException {
        Frota frota = new Frota();
        Veiculo veiculo1 = new Carro("ABC123", ECombustivel.ALCOOL);
        Veiculo veiculo2 = new Van("XYZ789", ECombustivel.GASOLINA);

        frota.adicionarVeiculo(veiculo1);
        frota.adicionarVeiculo(veiculo2);

        String dataString = "01/01/2023";
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Date dataEsperada = formatoData.parse(dataString);
        Rota rota1 = new Rota(43, dataEsperada);
        Rota rota2 = new Rota(80, dataEsperada);

        veiculo1.abastecer(100);
        veiculo2.abastecer(100);
        veiculo1.percorrerRota(rota1);
        veiculo2.percorrerRota(rota2);

        assertEquals(veiculo2, frota.maiorKmTotal());
    }

    @Test
    public void testMaiorKmMedia() throws ParseException {
        Frota frota = new Frota();
        Veiculo veiculo1 = new Furgao("DEF789", ECombustivel.GASOLINA);
        Veiculo veiculo2 = new Caminhao("GHI789", ECombustivel.DIESEL);
        frota.adicionarVeiculo(veiculo1);
        frota.adicionarVeiculo(veiculo2);

        String dataString = "01/01/2023";
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Date dataEsperada = formatoData.parse(dataString);
        Rota rota1 = new Rota(43, dataEsperada);
        Rota rota2 = new Rota(80, dataEsperada);

        veiculo1.abastecer(100);
        veiculo2.abastecer(100);
        veiculo1.percorrerRota(rota1);
        veiculo2.percorrerRota(rota2);

        assertEquals(veiculo2, frota.maiorKmMedia());
    }
}
