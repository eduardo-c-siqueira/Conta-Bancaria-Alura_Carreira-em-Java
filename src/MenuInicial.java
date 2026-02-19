import java.util.List;
import java.util.Scanner;

public class MenuInicial {

    private Agencia agencia;
    private Conta conta;

    private boolean usuarioLogado = false;
    private boolean gerenteLogado = false;
    private final Scanner sc = new Scanner(System.in);
    private final String menuInicial = """
            \n-----------------------------------------------------
            Escolha uma das opções para prosseguir
            
            1 - Área do usuário
            2 - Área do gestor
            3 - Sair
            """;
    private final String menuCliente = """
            \n---------------------------------------------
            Escolha uma das opções para prosseguir
            
            1 - Saque
            2 - Depósito
            3 - Transferência
            4 - Histórico de Transações
            5 - Log Out
            """;
    private final String menuGestor = """
            \n---------------------------------------------
            Escolha uma das opções para prosseguir
            
            1 - Cadastrar novo cliente
            2 - Criar nova conta
            3 - Sair
            """;

    public MenuInicial(Agencia agencia) {
        this.agencia = agencia;
    }

    public void exibirMenuInicial(){
        while(true){
            System.out.println(menuInicial);
            String opcaoEscolhida = sc.nextLine().trim();

                switch (opcaoEscolhida){

                    case "1" -> {
                        //Área do usuário
                        exibirTelaLoginCliente();
                    }
                    case "2" -> {
                        //Área do Gestor
                        this.exibirTelaLoginGestor();
                    }
                    case "3" -> {
                        //Sair
                        System.out.println("Encerrando...");
                        System.exit(0);
                    }

                    default -> System.out.println("Opção Inválida!");
                }

        }

    }

    private void exibirTelaLoginCliente(){
        //TODO: Esta etapa precisaria de várias otimizações, quando aprender melhor sobre serviços
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Por favor, digite o nome de usuario:");
        String nomeDigitado = sc.nextLine();
        System.out.println("Por favor, digite a senha:");
        String senhaDigitada = sc.nextLine();
        if (agencia.validaLoginCliente(nomeDigitado,senhaDigitada)){
            this.conta = this.agencia.encontraContaParaLogin(nomeDigitado);
            this.gerenteLogado = false;
            this.usuarioLogado = true;
            this.exibirMenuCliente();
        } else {
            System.out.println("Usuário ou senha inválida!");
        }
    }

    private void exibirMenuCliente(){
        if (this.usuarioLogado){

            System.out.printf("""
                    \n-----------------------------------
                    Cliente logado: %s
                    Conta: %s
                    Agência: %s""",
                    conta.getTitular().getNome(),
                    conta.getNumeroCC(),
                    conta.getAgencia());

            do {
                System.out.println(menuCliente);
                String opcaoEscolhida = sc.nextLine();

                switch (opcaoEscolhida) {

                    case "1" -> {
                        //Saque
                        System.out.println("Quanto deseja sacar?");
                        try {
                            double valorASacar = Double.parseDouble(sc.nextLine());
                            this.conta.saque(valorASacar);
                        } catch (NumberFormatException e) {
                            System.out.println("Valor inválido!");
                        }
                    }
                    case "2" -> {
                        //Depósito
                        System.out.println("Quanto deseja depositar?");
                        try {
                            double valorADepositar = Double.parseDouble(sc.nextLine());
                            this.conta.deposito(valorADepositar);
                        } catch (NumberFormatException e) {
                            System.out.println("Valor inválido!");
                        }
                    }
                    case "3" -> {
                        //Transferência
                        Conta contaDestino;
                        double valorATransferir;
                        while (true){
                            System.out.println("Digite o número da conta destino ou \"q\" para sair:");
                            String resposta = sc.nextLine();

                            if (resposta.trim().equalsIgnoreCase("q")) break;

                            contaDestino = this.agencia.encontraContaPorNumeroCC(resposta);
                            if (contaDestino == null){
                                System.out.println("Conta não encontrada na agência " + this.agencia.getNome()
                                + ". Por favor, tente de novo.");
                            } else {
                                System.out.println("Digite o valor que deseja transferir para "
                                        + contaDestino.getTitular().getNome() + ", conta " + contaDestino.getNumeroCC() + ":");
                                try {
                                    valorATransferir = Double.parseDouble(sc.nextLine());
                                    this.conta.transferencia(contaDestino,valorATransferir);
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Valor inválido");
                                }
                            }
                        }
                    }
                    case "4" -> {
                        System.out.println("-----------------------------------------------");
                        System.out.println(this.conta.getHistoricoDeTransacoes());
                    }
                    case "5" -> {
                        //sair
                        System.out.println("\nSaindo...");
                        this.conta = null;
                        this.usuarioLogado = false;
                    }
                    default -> {
                        System.out.println("Opção Inválida!");
                    }
                }
            } while (usuarioLogado);
        } else {
            System.out.println("Usuário não está logado!");
        }
    }

    private void exibirTelaLoginGestor(){
        //TODO: Esta etapa precisaria de várias otimizações, quando aprender melhor sobre serviços

        System.out.println("-----------------------------------------------------------------");
        System.out.println("Gerente: Por favor, digite o nome de usuário:");
        String nomeDigitado = sc.nextLine();
        System.out.println("Gerente: Por favor, digite a senha:");
        String senhaDigitada = sc.nextLine();

        if (agencia.getGerente().validaLogin(nomeDigitado,senhaDigitada)){
            this.usuarioLogado = false;
            this.gerenteLogado = true;
            this.exibirMenuGestor();
        } else {
            System.out.println("Usuário ou senha inválida!");
        }
    }

    private void exibirMenuGestor(){
        while (this.gerenteLogado) {
            System.out.println("\nLogado como gerente " + this.agencia.getGerente().getNome());
            System.out.println(menuGestor);
            String opcaoEscolhida = sc.nextLine();

            switch (opcaoEscolhida) {

                case "1" -> {
                    //cadastrar cliente
                    System.out.println("Digite o nome do cliente:");
                    String nomeDigitado = sc.nextLine();
                    //TODO: criar validação de CPF
                    System.out.println("Digite o CPF do cliente:");
                    String cpfDigitado = sc.nextLine();
                    System.out.println("Cliente: Crie um nome de usuário para login:");
                    String loginCriado = sc.nextLine();
                    //TODO: Criar confirmação de senha
                    System.out.println("Cliente: Crie uma senha para seu acesso:");
                    String senhaCriada = sc.nextLine();
                    //TODO: Criar método para evitar cliente repetido
                    this.agencia.getGerente().cadastrarCliente(nomeDigitado, cpfDigitado, loginCriado, senhaCriada);
                }

                case "2" -> {
                    //criar conta
                    //TODO: Criar tratamento para opções inválidas

                    System.out.println("Para qual cliente deseja criar a conta?");
                    List<Cliente> clientesSemConta = this.agencia.clientesSemConta();
                    for (int i = 1; i <= clientesSemConta.size(); i++){
                        System.out.println(i + " - " + clientesSemConta.get(i - 1).getNome());
                    }
                    int opcaoDeCliente = Integer.parseInt(sc.nextLine());
                    Cliente clienteEscolhido = clientesSemConta.get(opcaoDeCliente-1);
                    System.out.println("Cliente escolhido: " + clienteEscolhido.getNome());

                    System.out.println("Escolha o tipo de conta:");
                    TipoConta[] tipos = TipoConta.values();
                    for (int i = 1; i <= tipos.length; i++){
                        System.out.println(i + " - " + tipos[i-1]);
                    };
                    int opcaoDeTipo = Integer.parseInt(sc.nextLine());
                    TipoConta tipoEscolhido = tipos[opcaoDeTipo-1];
                    System.out.println("Escolhido: " + tipoEscolhido);

                    //tipo, numero, agencia, cliente, saldo, limite saque, limite transf.

                }

                case "3" -> {
                    System.out.println("\nSaindo...");
                    this.gerenteLogado = false;
                }

                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private boolean login(String nomeUsuario, String senha){
        return this.conta.getTitular().validaLogin(nomeUsuario,senha);
    }

    private Conta getConta() {
        return conta;
    }

    private void setConta(Conta conta) {
        this.conta = conta;
    }
}
