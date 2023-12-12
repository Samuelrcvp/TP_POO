import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


public class VeiculoTest {

    private Veiculo veiculo;
    private Veiculo veiculo2;
    private Rota rota1;
    private Rota rota2;

    @Before
    public void setUp() {
        veiculo = new Carro("ABC1234", ECombustivel.GASOLINA);
        veiculo2 = new Carro("EFG9876", ECombustivel.GASOLINA);
        rota1 = new Rota(20.0, new Date());
        rota2 = new Rota(40.0, new Date());
        veiculo.abastecer(100);
        veiculo2.abastecer(5);
        
    }

    @Test
    public void testPercorrerRota() throws ParseException{
        veiculo.percorrerRota(rota1);
        assertEquals(48.0, veiculo.tanque.getCapacidadeAtual(),0.01);
    }

    @Test
    public void testAbastecer() {
        double litrosAbastecidos = veiculo2.abastecer(10.0) + veiculo2.abastecer(10.0);

        assertEquals(20.0, litrosAbastecidos, 0.01);
        assertEquals(25.0, veiculo2.tanque.getCapacidadeAtual(), 0.01);
    }

    @Test
    public void testKmNoMes() throws ParseException {
        veiculo.percorrerRota(rota1);
        assertEquals(20.0, veiculo.kmNoMes(), 0.01);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Rota rotaForaDoMes = new Rota(30.0, cal.getTime());
        veiculo.percorrerRota(rotaForaDoMes);

        assertEquals(20.0, veiculo.kmNoMes(), 0.01);
    }

    @Test
    public void testKmTotal() throws ParseException {
        veiculo.percorrerRota(rota1);
        veiculo.percorrerRota(rota2);
        assertEquals(60.0, veiculo.kmTotal(), 0.01);
    }

    @Test
    public void testPlacaCorresponde() {
        assertTrue(veiculo.placaCorresponde("ABC1234"));
        assertFalse(veiculo.placaCorresponde("XYZ5678"));
    }


    @Test
    public void testToString() throws ParseException {
        // Configuração do cenário

        Veiculo veiculo3 = new Carro("GFS1234", ECombustivel.GASOLINA);

        veiculo3.abastecer(45);

        veiculo3.percorrerRota(rota1);//20
        veiculo3.percorrerRota(rota2);//40
        veiculo3.abastecer(20.0);
        veiculo3.abastecer(10.0);

        // Teste do método toString
        String expectedToString = "\n=============== VEÍCULO ==============="
                + "\nPlaca: GFS1234"
                + "\nTanque Maxímo: 50 | Tanque Atual: 50"
                + "\nTotal já abastecido: 56"
                + "\nRotas percorridas: 2"
                + "\nQuilometragem total: 60"
                + "\nQuilometragem do mês: 60"
                + "\nProxima manutenção periódica daqui a 10000.0 km"
                + "\nProxima troca de peças daqui a 10000.0 km"
                + "\nDespeza total: R$ 233,55"
                + "\n";
        assertEquals(expectedToString, veiculo3.toString());
    }
}