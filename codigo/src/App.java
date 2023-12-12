import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
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

    private static int menu(String nomeArquivo) throws FileNotFoundException {
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

    private static void gerarVeiculosComRotasRandom(int qntDe4Veiculos, int qntRotasPorVeiculo) {
        Random random = new Random();

        for (int i = 0; i < qntDe4Veiculos; i++) {
            String placa = "ABC" + (random.nextInt(9000) + 1000); 
            ECombustivel combustivel = ECombustivel.values()[random.nextInt(ECombustivel.values().length)];

            Carro carro = new Carro(placa, combustivel);
            Van van = new Van(placa, combustivel);
            Furgao furgao = new Furgao(placa, combustivel);
            Caminhao caminhao = new Caminhao(placa, combustivel);
            frota.adicionarVeiculo(carro);
            frota.adicionarVeiculo(van);
            frota.adicionarVeiculo(furgao);
            frota.adicionarVeiculo(caminhao);

            List<Rota> rotas = geraRotaRandom(qntRotasPorVeiculo);
            for(Rota rota: rotas){
                carro.addRota(rota);
                van.addRota(rota);
                furgao.addRota(rota);
                caminhao.addRota(rota);
            }
        }
    }
    
    private static List<Rota> geraRotaRandom(int qnt){
        Random random = new Random();
        List<Rota> dados = new ArrayList<>(qnt);
        
        for (int i = 0; i < qnt; i++) {
            double quilometragem = random.nextDouble(20d, 450d);
            Date data = new Date();
            Rota rota = new Rota(quilometragem, data);
            dados.add(rota);
        }
        return dados;
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
                 pausa();
            } catch (Exception e) {
                System.out.println("Formato de data inválido. Rota não percorrida.");
                 pausa();
            }
        } else {
            System.out.println("Veículo não encontrado.");
             pausa();
        }
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

    private static ECombustivel escolherCombustivel(){
        String nomeArq = "codigo/menuCombustiveis";
        ECombustivel combustivelEscolhido = null;
        int opcao = -1;
        while(opcao!=0 || combustivelEscolhido == null){
            try {
                    limparTela();
                    opcao = menu(nomeArq);
                    switch(opcao){
                    case 1: {
                        limparTela();
                        return combustivelEscolhido = ECombustivel.ALCOOL;

                    }
                    case 2: {
                        limparTela();
                        return combustivelEscolhido = ECombustivel.GASOLINA;

                    }
                    case 3: {
                        limparTela();
                        return combustivelEscolhido = ECombustivel.DIESEL;

                    }
                    case 0:{
                        return combustivelEscolhido;
                    }

                    default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
            }catch (FileNotFoundException erro) {
            System.out.println("Arquivo de Menu não encontrado " + erro);
            }catch (NumberFormatException erro) {
            System.out.println("Opção inválida. Escolha um número ");
            pausa();
            }
        }
        return combustivelEscolhido;
    }


    /* -------------------------------------------------------------------------------------------------------------- */

    private static Veiculo criarVeiculo(String placa, ECombustivel combustivel) {

        teclado = new Scanner(System.in); 
        String nomeArq = "codigo/menuVeiculos";
        Veiculo veiculo = null;
        int opcao = -1;
        while(opcao!=0 || veiculo == null){
            try {
                    limparTela();
                    opcao = menu(nomeArq);
                    switch(opcao){
                    case 1: {
                        limparTela();
                        return veiculo = new Carro(placa, combustivel);

                    }
                    case 2: {
                        limparTela();
                        return veiculo = new Van(placa, combustivel);

                    }
                    case 3: {
                        limparTela();
                        return veiculo = new Furgao(placa, combustivel);

                    }
                    case 4: {
                        limparTela();
                        return veiculo = new Caminhao(placa, combustivel);

                    }
                    case 0:{
                        return veiculo;
                    }

                    default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
            }catch (FileNotFoundException erro) {
            System.out.println("Arquivo de Menu não encontrado" + erro);
            }catch (NumberFormatException erro) {
                System.out.println("Opção inválida. Escolha um número ");
                pausa();
            }
        }
        
        return veiculo;      
    }

    public static void main(String[] args) {
         teclado = new Scanner(System.in);
        String nomeArq = "codigo/menuPrincipal";
        int opcao = -1;
        while(opcao!=0 || opcao==-1){
        try {
                gerarVeiculosComRotasRandom(5,3);
                limparTela();
                opcao = menu(nomeArq);
                switch(opcao){
                case 1: {
                    limparTela();
                    System.out.println("Digite a placa do novo veículo:");
                    String placa = teclado.nextLine();

                    ECombustivel combustivelEscolhido = escolherCombustivel();
                    if (combustivelEscolhido != null) {
                        Veiculo veiculoCriado = criarVeiculo(placa, combustivelEscolhido);
                        
                        if (veiculoCriado != null && combustivelEscolhido != null) {
                            limparTela();
                            System.out.println("Veículo criado com sucesso!");
                            frota.adicionarVeiculo(veiculoCriado);
                            pausa();
                        }else{
                            limparTela();
                            System.out.println("Erro ao criar veículo, tente novamente");
                            pausa();
                        }
                    }    
                }break;
                case 2: {
                    limparTela();
                    abastecerVeiculo();
                }break;
                case 3: {
                    limparTela();
                    adicionarRota();
                }break;
                case 4: {
                    limparTela();
                    percorrerRota();
                }break;
                case 5:{
                    limparTela();
                    relatorioFrota();
                }break;
                case 6:{
                    limparTela();
                    iniciarNovoMes();
                }break;
                case 0:{
                    System.out.println("Saindo...");
                }break;
                default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        catch (FileNotFoundException erro) {
        System.out.println("Arquivo de Menu não encontrado" + erro);
        }catch (NumberFormatException erro) {
            System.out.println("Opção inválida. Escolha um número ");
            pausa();
        }}
        teclado.close();
    }
}


/*import java.util.Date;

 class App{
    public static void main(String[] args){
        Veiculo veiculo = new Carro("GFS1234", ECombustivel.GASOLINA);
        System.out.println(veiculo);
        Rota rota1 = new Rota(20.0, new Date());
        Rota rota2 = new Rota(40.0, new Date());
        veiculo.addRota(rota1);
        veiculo.addRota(rota2);
        veiculo.abastecer(50);
        veiculo.percorrerRota(rota1);
        veiculo.percorrerRota(rota2);
       System.out.println(veiculo);
        veiculo.abastecer(20.0);
        veiculo.abastecer(10.0);
         System.out.println(veiculo);
    }
} */