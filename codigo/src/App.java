import java.io.File;
import java.util.Date;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class App {
    static Scanner teclado;
    private static Frota frota = new Frota();

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        System.out.println("\nEnter para continuar.");
        teclado.nextLine();
    }

    public static int menu(String nomeArquivo) throws FileNotFoundException {
        limparTela();
        File arqMenu = new File(nomeArquivo);
        Scanner leitor = new Scanner(arqMenu, "UTF-8");
        System.out.println(leitor.nextLine());
        System.out.println("==============================");
        int contador = 1;
        while(leitor.hasNext()){
            System.out.println(contador + " - " + leitor.nextLine());
            contador++;
        }
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());
        leitor.close();
        return opcao;
    }

    /* public static Map<String,Veiculo> geraVeiculos(int quantidade){
        
        Map<String,Veiculo> dados = new HashMap<>(quantidade*2);
       
        for (int i = 1; i <= quantidade; i++) {
            String placa = gerarPlaca();
            double capacidadeMaxima = sorteador.nextDouble(25d, 60d);
            double capacidadeAtual = 0;
            Veiculo novo = new Veiculo(placa, capacidadeMaxima, capacidadeAtual);
            dados.put(placa, novo);
        }
        return dados;
    }

    public static String gerarPlaca() {
        StringBuilder placa = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            char letra = gerarLetraAleatoria();
            placa.append(letra);
        }

        for (int i = 0; i < 4; i++) {
            int numero = gerarNumeroAleatorio();
            placa.append(numero);
        }

        return placa.toString();
    }

    public static char gerarLetraAleatoria() {

        int codigoAscii = sorteador.nextInt(26) + 65;
        return (char) codigoAscii;
    }

    public static int gerarNumeroAleatorio() {

        return sorteador.nextInt(10);
    }

    public static List<Frota> geraFrota(int quantidade){
        List<Frota> dados = new ArrayList<>(quantidade);
        
        for (int i = 0; i < quantidade; i++) {
            int tamanhoFrota = sorteador.nextInt(1,10);
            Frota nova = new Frota(tamanhoFrota);
            dados.add(nova);
        }
        return dados;
    }
    
    public static List<Rota> geraRota(int quantidade){
        List<Rota> dados = new ArrayList<>(quantidade);
        
        for (int i = 0; i < quantidade; i++) {
            double quilometragem = sorteador.nextDouble(20d, 450d);
            Date data = gerarDataAleatoria();
            Rota nova = new Rota(quilometragem, data);
            dados.add(nova);
        }
        return dados;
    }

    public static Date gerarDataAleatoria() {
        long dataInicialMillis = System.currentTimeMillis() - 365 * 24 * 60 * 60 * 1000L;
        long dataFinalMillis = System.currentTimeMillis();

        long dataAleatoriaMillis = IntervaloMs(dataInicialMillis, dataFinalMillis);

        return new Date(dataAleatoriaMillis);
    }

    private static long IntervaloMs(long start, long end) {
        long range = end - start + 1;
        return start + (long) (range * sorteador.nextDouble());
    } */

    private static void adicionarNovoVeiculo() {

        System.out.println("Digite a placa do veículo:");
        String placa = teclado.nextLine();

        System.out.println("Digite a capacidade máxima do tanque:");
        double capacidadeMaxima = Double.parseDouble(teclado.nextLine());

        System.out.println("Digite a capacidade atual do tanque:");
        double capacidadeAtual = Double.parseDouble(teclado.nextLine());

        Veiculo novoVeiculo = new Veiculo(placa, capacidadeMaxima, capacidadeAtual);
        frota.adicionarVeiculo(novoVeiculo);

        System.out.println("\nVeículo adicionado com sucesso!");
        pausa();
    }

    private static void abastecerVeiculo() {

        System.out.println("Digite a placa do veículo:");
        String placa = teclado.nextLine();

        Veiculo veiculo = frota.localizarVeiculo(placa);

        if (veiculo != null) {
            System.out.println("Digite a quantidade de litros a abastecer:");
            double litrosAbastecidos = Double.parseDouble(teclado.nextLine());

            veiculo.abastecer(litrosAbastecidos);

            System.out.println("Abastecimento realizado com sucesso!");
        } else {
            System.out.println("Veículo não encontrado.");
        }
        pausa();
    }

    private static void adicionarRota() {

        System.out.println("Digite a placa do veículo:");
        String placa = teclado.nextLine();

        Veiculo veiculo = frota.localizarVeiculo(placa);

        if (veiculo != null) {
            System.out.println("Digite a quilometragem da rota:");
            double quilometragem = Double.parseDouble(teclado.nextLine());

            System.out.println("Digite a data da rota (formato dd/MM/yyyy):");
            String dataString = teclado.next();
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            try {
                Date data = formatoData.parse(dataString);
                Rota rota = new Rota(quilometragem, data);
                veiculo.addRota(rota);

                System.out.println("Rota adicionada com sucesso!");
            } catch (Exception e) {
                System.out.println("Formato de data inválido. Rota não adicionada.");
            }
        } else {
            System.out.println("Veículo não encontrado.");
        }
        pausa();
    }

    private static void percorrerRota() {

        System.out.println("Digite a placa do veículo:");
        String placa = teclado.nextLine();

        Veiculo veiculo = frota.localizarVeiculo(placa);

        if (veiculo != null) {
            System.out.println("Digite a quilometragem da rota:");
            double quilometragem = Double.parseDouble(teclado.nextLine());

            System.out.println("Digite a data da rota (formato dd/MM/yyyy):");
            String dataString = teclado.next();
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            try {
                Date data = formatoData.parse(dataString);
                Rota rota = new Rota(quilometragem, data);
                veiculo.percorrerRota(rota);

                System.out.println("Rota percorrida com sucesso!");
            } catch (Exception e) {
                System.out.println("Formato de data inválido. Rota não percorrida.");
            }
        } else {
            System.out.println("Veículo não encontrado.");
        }
        pausa();
    }

    private static void relatorioFrota() {
        System.out.println(frota.relatorioFrota());
        pausa();
    }

    private static void iniciarNovoMes() {
        frota.iniciarNovoMes();
        System.out.println("Novo mês iniciado. Quilometragem mensal resetada para todos os veículos.");
        pausa();
    }

    public static void main(String[] args) throws FileNotFoundException {
         teclado = new Scanner(System.in);
        String nomeArq = "codigo/menuPrincipal";
        int opcao = -1;
        while(opcao!=0){
            limparTela();
            opcao = menu(nomeArq);
            switch(opcao){
            case 1: {
                adicionarNovoVeiculo();
            }break;
            case 2: {
                abastecerVeiculo();
            }break;
            case 3: {
                adicionarRota();
            }break;
            case 4: {
                percorrerRota();
            }break;
            case 5:{
                relatorioFrota();
            }break;
            case 6:{
                iniciarNovoMes();
            }break;
            default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        teclado.close();
    }
}
