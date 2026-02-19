public class Cliente {

    private final String nome;
    private final String cpf;
    private String telefone;
    private String login;
    private String senha;
    private Conta conta;

    public Cliente(String nome, String cpf, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cpf = cpf;
    }

    public boolean validaLogin(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getLogin() {
        return login;
    }

    public void setTelefone(String telefone) {
        //TODO: Adaptar para pedir login e senha antes de alterar
        this.telefone = telefone;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        /*
        No futuro, o ideal seria criar um service para gerenciar a criação
         de contas e vinculação a cada cliente, para não sobrescrever e não
         ter duas contas para o mesmo cliente
        */
        this.conta = conta;
    }
}