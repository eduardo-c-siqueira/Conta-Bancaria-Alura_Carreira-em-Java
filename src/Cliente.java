public class Cliente {

    private final String nome;
    private final String cpf;
    private String telefone;
    private String login;
    private String senha;

    public Cliente(String nome, String cpf, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cpf = cpf;
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

    public void setTelefone(String telefone) {
        //TODO: Adaptar para pedir login e senha antes de alterar
        this.telefone = telefone;
    }

    public boolean validaLogin(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }
}
