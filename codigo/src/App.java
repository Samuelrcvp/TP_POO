import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.io.FileNotFoundException;
import java.text.ParseException;
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
        limparTela();
    }

    private static int menu(String nomeArquivo){
        limparTela();
        int opcao = -1;
        do{
            try{
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
                opcao = Integer.parseInt(teclado.nextLine());
                leitor.close();
                
            }catch(FileNotFoundException erro){
                System.out.println("Erro ao ler arquivo menu.\n"+ erro);
            }
        }while(opcao == -1);
        return opcao;
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

    private static Veiculo localizarVeiculoPorPlaca() {
        String placa;
        do {
            System.out.println("Digite a placa do veículo:");
            placa = teclado.nextLine().trim();
            if (placa.isEmpty()) {
                System.out.println("Placa não pode ser vazia. Tente novamente.\n");
            }
        } while (placa.isEmpty());
        limparTela();
        Veiculo veiculo = frota.localizarVeiculo(placa);
        if (veiculo == null) {
            System.out.println("Veículo não encontrado");
            pausa();
            limparTela();
        }
        return veiculo;
    }
    

    private static void abastecerVeiculo() {

        Veiculo veiculo = localizarVeiculoPorPlaca();
        
        try {
            if (veiculo != null) {
                System.out.println("Digite a quantidade de litros a abastecer:");
                double litros = Double.parseDouble(teclado.nextLine());
                
                double litrosAbastecidos = veiculo.abastecer(litros);
                if (litrosAbastecidos > 0) {
                    System.out.println("Foram abastecidos "+ litrosAbastecidos +" litros");
                }else{
                    System.out.println("Não foi possível abastecer, tanque cheio.");
                };
            }
            pausa();
        }catch (NumberFormatException erro) {
        System.out.println("Opção inválida.");
        pausa();
        }      
    }

    /* private static void adicionarRota() {

        Veiculo veiculo = localizarVeiculoPorPlaca();
        try {
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
            }
            pausa();
        }catch (NumberFormatException erro) {
        System.out.println("Opção inválida.");
        pausa();
        }         
    } */

    private static void relatorioFrota() {
        limparTela();
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
            }catch (NumberFormatException erro) {
                System.out.println("Opção inválida. Escolha um número ");
                pausa();
            }
        }
        
        return veiculo;      
    }

    private static String verificarPlacaExiste(){

        String placa;
        do{
            System.out.println("Digite a placa do novo veículo:");
            placa = teclado.nextLine().trim();
            
            if (placa.isEmpty()) {
                limparTela();
                System.out.println("Placa não pode ser vazia");
                pausa();
            }
        }while(placa.isEmpty());
        limparTela();

        Veiculo veiculo = frota.localizarVeiculo(placa);
        if(veiculo != null){
           limparTela();
           System.out.println("Placa já existe na frota");
           pausa();
        }
        return placa;
    }

    public static void main(String[] args) {
         teclado = new Scanner(System.in);
        String nomeArq = "codigo/menuPrincipal";
        int opcao = -1;
        while(opcao!=0 || opcao==-1){
        try {
                /* gerarVeiculosComRotasRandom(5,3); */

                opcao = menu(nomeArq);
                switch(opcao){
                case 1: {
                    limparTela();
                    String placa = verificarPlacaExiste();
                    if (placa != null) {
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
                    }
                    
                }break;
                case 2: {
                    limparTela();
                    abastecerVeiculo();
                }break;
                case 3: {
                    limparTela();
                    Veiculo veiculo = localizarVeiculoPorPlaca(); 
                    try{       
                        System.out.println("Digite a quilometragem da rota:");
                        double quilometragem = Double.parseDouble(teclado.nextLine());

                        System.out.println("Digite a data da rota (formato dd/MM/yyyy):");
                        String dataString = teclado.next();
                        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
                        
                        Date data = formatoData.parse(dataString);     
                        if (veiculo != null) {
                            veiculo.percorrerRota(quilometragem, data);
                            
                            System.out.println("Rota percorrida com sucesso!");
                        }
            
                    }catch(ParseException erro){
                        System.out.println("Formato de data inválido. Rota não percorrida: "+erro);
                        pausa();
                    }  
                }break;
                case 4: {
                    limparTela();
                    relatorioFrota();
                }break;
                case 5:{
                    limparTela();
                    iniciarNovoMes();
                }break;
                case 6:{
                    limparTela();
                    Veiculo veiculo = localizarVeiculoPorPlaca();
                    if (veiculo != null) {
                        System.out.println(veiculo.toString());
                        pausa();
                    }
                }break;
                case 0:{
                    System.out.println("Saindo...");
                }break;
                default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }catch (NumberFormatException erro) {
            System.out.println("Opção inválida. Escolha um número ");
            pausa();
            limparTela();
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