public class Gerente {

    private final String nome;
    private String nomeUsuario;
    private String senha;
    private Agencia agencia;

    public Gerente(String nome, String nomeUsuario, String senha) {
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    void cadastrarCliente(String nome, String cpf, String login, String senha){
        this.agencia.cadastraCliente(new Cliente(nome, cpf, login, senha));
    }

    boolean criarConta(TipoConta tipo, String agencia, Cliente titular,NivelConta nivel){

        String numeroCC = this.agencia.gerarNumeroCC();
        return this.agencia.criaConta(new Conta(tipo, numeroCC, agencia, titular, nivel));
    }

    boolean validaLogin(String nomeUsuario, String senha){
        return nomeUsuario.equals(this.nomeUsuario) && senha.equals(this.senha);
    }

    String getNome() {
        return this.nome;
    }

    //TODO: Talvez criar serviço de gestão de cadastro para mudar nome de usuário e senha

    Agencia getAgencia() {
        return agencia;
    }

    void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }
}