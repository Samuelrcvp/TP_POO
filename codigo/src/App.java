import java.io.File;
import java.util.Date;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class App {
    static Scanner teclado;
    private static Frota frota = new Frota();

    /**
     * Limpa a tela do console.
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Pausa a execução do programa até que o usuário pressione Enter.
     */
    static void pausa() {
        System.out.println("\nEnter para continuar.");
        teclado.nextLine();
        limparTela();
    }

    /**
     * Exibe um menu a partir de um arquivo e retorna a opção escolhida pelo usuário.
     * 
     * @param nomeArquivo Nome do arquivo contendo as opções do menu.
     * @return Opção escolhida pelo usuário.
     */
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

    /**
     * Localiza um veículo na frota com base na placa inserida pelo usuário.
     * 
     * @return Veículo encontrado ou nulo se não encontrado.
     */
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
    
    /**
     * Abastece um veículo com base na placa inserida pelo usuário e na quantidade de litros.
     */
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

    /**
    * Exibe o relatório da frota na tela e pausa a execução do programa.
    */
    private static void relatorioFrota() {
        limparTela();
        System.out.println(frota.relatorioFrota());
        pausa();
    }

    /**
    * Inicia um novo mês na frota, resetando a quilometragem mensal para todos os veículos.
    */
    private static void iniciarNovoMes() {
        frota.iniciarNovoMes();
        System.out.println("Novo mês iniciado. Quilometragem mensal resetada para todos os veículos.");
        pausa();
    }

    /**
    * Captura e manda o valor da manutenção feita para o método de somar
    */
    private static void realizarManutencao(){

        Veiculo veiculo = localizarVeiculoPorPlaca();
        
        try {
            if (veiculo != null) {
                System.out.println("Digite o valor da manutenção ou troca de peças:");
                double precoManutencao = Double.parseDouble(teclado.nextLine());
                
                veiculo.SomarManutencoes(precoManutencao);
            }
        }catch (NumberFormatException erro) {
        System.out.println("Opção inválida.");
        pausa();
        }      
    }

    /**
    * Permite que o usuário escolha o tipo de combustível a ser utilizado.
    * 
    * @return Tipo de combustível escolhido pelo usuário.
    */
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


    /**
     * Cria um novo veículo com base na escolha do usuário.
     * 
     * @param placa Placa do novo veículo.
     * @param combustivel Combustível escolhido para o novo veículo.
     * @return Novo veículo criado.
     */
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

    /**
    * Verifica se a placa digitada para um novo veículo já existe na frota.
    * 
    * @return Placa do novo veículo após verificação.
    */
    private static String verificarPlacaExiste(){

        String placa = null;
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
           return null;
        }
        return placa;
    }

    /**
     * Método para gerar dados de exemplo para a execução mais rápida do programa
     * 
     */
     private static void gerarDadosParaExemplo() throws ParseException{
        Veiculo veiculo1 = new Carro("GFS1234", ECombustivel.GASOLINA);
        Veiculo veiculo2 = new Caminhao("ABC1234", ECombustivel.DIESEL);
        frota.adicionarVeiculo(veiculo1);
        frota.adicionarVeiculo(veiculo2);

        veiculo1.abastecer(40);
        veiculo2.abastecer(60);

        veiculo1.percorrerRota(50, new Date());
        veiculo1.percorrerRota(70, new Date());

        veiculo2.percorrerRota(100, new Date());
        veiculo2.percorrerRota(50, new Date());
    }

    /**
     * Método principal que inicia a execução do programa.
     * 
     * @param args Argumentos da linha de comando (não utilizados neste caso).
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
         teclado = new Scanner(System.in);
        String nomeArq = "codigo/menuPrincipal";
        int opcao = -1;
        gerarDadosParaExemplo();
        while(opcao!=0 || opcao==-1){
        try {
                

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
                case 7:{
                    limparTela();
                    realizarManutencao();
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