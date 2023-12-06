import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TanqueTest {

    @Test
    public void testAbastecerComTanqueVazio() {
        Tanque tanque = new Tanque(50.0, 0.0);
        double litrosAbastecidos = tanque.abastecer(20.0);
        assertEquals(20.0, litrosAbastecidos, 0.01);
        assertEquals(20.0, tanque.getCapacidadeAtual(), 0.01);
    }

    @Test
    public void testAbastecerComTanqueCheio() {
        Tanque tanque = new Tanque(50.0, 50.0);
        double litrosAbastecidos = tanque.abastecer(30.0);
        assertEquals(50d, litrosAbastecidos, 0.01);
        assertEquals(50d, tanque.getCapacidadeAtual(), 0.01);
    }

    @Test
    public void testAbastecerComTanqueParcialmenteCheio() {
        Tanque tanque = new Tanque(50.0, 25.0);
        double litrosAbastecidos = tanque.abastecer(20.0);
        assertEquals(20d, litrosAbastecidos, 0.01);
        assertEquals(45d, tanque.getCapacidadeAtual(), 0.01);
    }

    @Test
    public void testAutonomiaMaxima() {
        Tanque tanque = new Tanque(60.0, 40.0);
        assertEquals(60.0 * 8.2, tanque.autonomiaMaxima(), 0.01);
    }

    @Test
    public void testAutonomiaAtual() {
        Tanque tanque = new Tanque(60.0, 30.0);
        assertEquals(30.0 * 8.2, tanque.autonomiaAtual(), 0.01);
    }
}
