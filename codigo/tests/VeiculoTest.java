import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import java.util.Date;


public class VeiculoTest {

    private Veiculo veiculo;
    private Rota rota1;
    private Rota rota2;

    @Before
    public void setUp() {
        veiculo = new Veiculo("ABC1234", 60.0, 30.0);
        rota1 = new Rota(20.0, new Date());
        rota2 = new Rota(40.0, new Date());
    }

    @Test
    public void testAddRota() {
        assertTrue(veiculo.addRota(rota1));
        assertTrue(veiculo.addRota(rota2));
        assertFalse(veiculo.addRota(new Rota(500.0, new Date())));
    }

    @Test
    public void testPercorrerRota(){
        veiculo.percorrerRota(rota1);
        assertEquals(27.57, veiculo.tanque.getCapacidadeAtual(),0.01);
    }

    @Test
    public void testAbastecer() {
        double litrosAbastecidos = veiculo.abastecer(10.0);

        assertEquals(10.0, litrosAbastecidos, 0.01);
        assertEquals(40.0, veiculo.tanque.getCapacidadeAtual(), 0.01);
    }

    @Test
    public void testKmNoMes() {
        veiculo.percorrerRota(rota1);
        assertEquals(20.0, veiculo.kmNoMes(), 0.01);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Rota rotaForaDoMes = new Rota(30.0, cal.getTime());
        veiculo.percorrerRota(rotaForaDoMes);

        assertEquals(20.0, veiculo.kmNoMes(), 0.01);
    }

    @Test
    public void testKmTotal() {
        veiculo.percorrerRota(rota1);
        veiculo.percorrerRota(rota2);
        assertEquals(60.0, veiculo.kmTotal(), 0.01);
    }

    @Test
    public void testIniciarNovoMes() {
        veiculo.percorrerRota(rota1);
        veiculo.iniciarNovoMes();
        assertEquals(0, veiculo.getQuantRotas());
    }

    @Test
    public void testPlacaCorresponde() {
        assertTrue(veiculo.placaCorresponde("ABC1234"));
        assertFalse(veiculo.placaCorresponde("XYZ5678"));
    }

    @Test
    public void testEquals() {
        Veiculo veiculo2 = new Veiculo("ABC1234", 60.0, 30.0);
        Veiculo veiculo3 = new Veiculo("XYZ5678", 60.0, 30.0);

        assertTrue(veiculo.equals(veiculo2));
        assertFalse(veiculo.equals(veiculo3));
    }

    @Test
    public void testToString() {
        // Configuração do cenário

        veiculo.addRota(rota1);
        veiculo.percorrerRota(rota1);
        veiculo.addRota(rota2);
        veiculo.percorrerRota(rota2);
        veiculo.abastecer(20.0);
        veiculo.abastecer(10.0);

        // Teste do método toString
        String expectedToString = "\n=============== VEÍCULO ==============="
                + "\nPlaca: ABC1234"
                + "\nTanque Maxímo: 60 | Tanque Atual: 52,68"
                + "\nTotal já abastecido: 30"
                + "\nRotas adicionadas: 2"
                + "\nRotas percorridas: 2"
                + "\nQuilometragem total: 60"
                + "\nQuilometragem do mês: 60"
                + "\n";
        assertEquals(expectedToString, veiculo.toString());
    }
}