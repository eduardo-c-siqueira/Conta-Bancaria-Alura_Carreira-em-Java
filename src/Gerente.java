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

    public void cadastrarCliente(String nome, String cpf, String login, String senha){
        this.agencia.cadastraCliente(new Cliente(nome, cpf, login, senha));
    }

    public void criarConta(TipoConta tipo, String numeroCC, String agencia, Cliente titular,
                           double saldo, double limiteSaque, double limiteTransferencia){

        this.agencia.criaConta(new Conta(tipo, numeroCC, agencia, titular, saldo,
                limiteSaque, limiteTransferencia));

    }

    public boolean validaLogin(String nomeUsuario, String senha){
        return nomeUsuario.equals(this.nomeUsuario) && senha.equals(this.senha);
    }

    public String getNome() {
        return this.nome;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }
}
