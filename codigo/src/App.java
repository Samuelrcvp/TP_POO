import java.io.File;
import java.util.Date;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner teclado;
    static Frota frota = new Frota();
    static List<Rota> rotas = new ArrayList<>();

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        System.out.println("Enter para continuar.");
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

    public static void main(String[] args) throws FileNotFoundException {
         teclado = new Scanner(System.in);
        String nomeArq = "codigo/menuPrincipal";
        int opcao = -1;
        while(opcao!=0){
            limparTela();
            opcao = menu(nomeArq);
            switch(opcao){
            case 1: {
                System.out.println("Placa do veículo: ");
                String placa = teclado.nextLine(); 
                System.out.println("Capacidade do tanque: ");
                double capacidadeMaxima = teclado.nextDouble(); 
                System.out.print("Capacidade atual do tanque: ");
                double capacidadeAtual = teclado.nextDouble();
                Veiculo veiculo = new Veiculo(placa, capacidadeMaxima, capacidadeAtual);
                frota.adicionarVeiculo(veiculo);
                System.out.println("Veículo Criado e adicionado à frota!");
                pausa();
            }break;
            case 2: {
                System.out.println("Placa do veículo que deseja abastecer: ");
                String placa = teclado.nextLine();
                Veiculo veiculo = frota.localizarVeiculo(placa);
                if (veiculo != null) {
                    System.out.print("Litros a serem abastecidos: ");
                    double litros = teclado.nextDouble();
                    veiculo.abastecer(litros);
                    System.out.println("Abastecimento finalizado!");
                }
                else{
                    System.out.println("Veículo não encontrado");
                }
            }break;
            case 3: {
                System.out.println("Placa do veículo que deseja adicionar uma rota: ");
                String placa = teclado.nextLine();
                Veiculo veiculo = frota.localizarVeiculo(placa);
                if (veiculo != null) {
                    System.out.println("Quilometragem da rota: ");
                    double quilometragem = teclado.nextDouble();
                    System.out.println("Data da rota(dd/MM/yyyy): ");
                    String dataString = teclado.next();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date data;
                    try {
                        data = dateFormat.parse(dataString);
                    } 
                    catch (ParseException e) {
                        System.out.println("Formato de data inválido. Certifique-se de usar o formato dd/MM/yyyy.");
                        break;
                    }
                    Rota rota = new Rota(quilometragem, data);
                    rotas.add(rota);
                }
                else{
                    System.out.println("Veículo não encontrado");
                }
            }break;
            case 4: {
                System.out.println("Placa do veículo que deseja percorrer a rota: ");
                String placa = teclado.nextLine();
                Veiculo veiculo = frota.localizarVeiculo(placa);
                if (veiculo != null) {
                    System.out.println("Rotas disponíveis para o veículo:");
                    for (int i = 0; i < rotas.size(); i++) {
                        System.out.println("Número " + i + ": " + rotas.get(i).getQuilometragem() + " km, Data: " + rotas.get(i).getData());
                    }

                    System.out.print("Rota que deseja percorrer (informe o número): ");
                    int numeroRota = teclado.nextInt();
                    teclado.nextLine();

                    if (numeroRota >= 0 && numeroRota < rotas.size()) {
                        veiculo.percorrerRota(rotas.get(numeroRota));
                        System.out.println("Veículo percorreu a rota com sucesso!");
                    } else {
                        System.out.println("Número de rota inválido. Por favor, escolha um número de rota válido.");
                    }
                } else {
                    System.out.println("Veículo não encontrado");
                }                
            }break;
            case 5:{
                System.out.println(frota.relatorioFrota());
            }break;
            case 6:{
                System.out.println("Placa do veículo: ");
                String placa = teclado.nextLine();
                Veiculo veiculo = frota.localizarVeiculo(placa);
                if (veiculo != null) {
                veiculo.iniciarNovoMes();
                System.out.println("Novo mês iniciado, rotas apagadas.");
                }
                else{
                        System.out.println("Veículo não encontrado");
                    }
            }break;
            default: {
                System.out.println("Opção inválida");
            }break;
        }
        teclado.close();
        }
    }
}
