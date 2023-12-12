import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TanqueTest {

    @Test
    public void testAbastecerComTanqueVazio() {
        Tanque tanque = new Tanque(EVeiculo.CARRO.capacidadeMaximaTanque, ECombustivel.GASOLINA.consumoMedio, ECombustivel.GASOLINA.precoMedio);
        double litrosAbastecidos = tanque.abastecer(20.0);
        assertEquals(20.0, litrosAbastecidos, 0.01);
        assertEquals(20.0, tanque.getCapacidadeAtual(), 0.01);
    }

    @Test
    public void testAbastecerComTanqueCheio() {
        Tanque tanque = new Tanque(EVeiculo.CARRO.capacidadeMaximaTanque, ECombustivel.GASOLINA.consumoMedio, ECombustivel.GASOLINA.precoMedio);
        tanque.abastecer(50.0);
        double litrosAbastecidos = tanque.abastecer(20.0);
        
        assertEquals(0d, litrosAbastecidos, 0.01);
        assertEquals(50d, tanque.getCapacidadeAtual(), 0.01);
    }

    @Test
    public void testAbastecerComTanqueParcialmenteCheio() {
        Tanque tanque = new Tanque(EVeiculo.CARRO.capacidadeMaximaTanque, ECombustivel.GASOLINA.consumoMedio, ECombustivel.GASOLINA.precoMedio);
        tanque.abastecer(25);
        double litrosAbastecidos = tanque.abastecer(20.0);
        assertEquals(20d, litrosAbastecidos, 0.01);
        assertEquals(45d, tanque.getCapacidadeAtual(), 0.01);
    }

    @Test
    public void testAutonomiaMaxima() {
        Tanque tanque = new Tanque(EVeiculo.CARRO.capacidadeMaximaTanque, ECombustivel.GASOLINA.consumoMedio, ECombustivel.GASOLINA.precoMedio);
        assertEquals(50d * 10d, tanque.autonomiaMaxima(), 0.01);
    }

    @Test
    public void testAutonomiaAtual() {
        Tanque tanque = new Tanque(EVeiculo.CARRO.capacidadeMaximaTanque, ECombustivel.GASOLINA.consumoMedio, ECombustivel.GASOLINA.precoMedio);
        tanque.abastecer(30);
        assertEquals(30.0 * 10d, tanque.autonomiaAtual(), 0.01);
    }
}
