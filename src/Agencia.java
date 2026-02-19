import java.util.ArrayList;
import java.util.List;

public class Agencia {

    private String nome;
    private List<Cliente> clientes = new ArrayList<>();
    private List<Conta> contas = new ArrayList<>();
    private Gerente gerente;

    public Agencia(String nome, Gerente gerente) {
        this.nome = nome;
        this.gerente = gerente;
        gerente.setAgencia(this);
    }

    //TODO: excluir depois de criar as funções de gerência
    public void adicionaContaTeste(Conta c){
        contas.add(c);
        clientes.add(c.getTitular());
    }

    public void cadastraCliente(Cliente cliente){
        clientes.add(cliente);
        System.out.printf("""
                Cliente cadastrado com sucesso!
                Nome: %s
                CPF: %s
                """, cliente.getNome(), cliente.getCpf());
    }

    public void criaConta(Conta conta){
        contas.add(conta);
    }

    public boolean validaLoginCliente(String nomeUsuario, String senha){

        Cliente clienteEncontrado = encontraCliente(nomeUsuario);

        if (clienteEncontrado != null){
            return clienteEncontrado.validaLogin(nomeUsuario, senha);
        } else return false;
    }

    public Cliente encontraCliente(String nomeUsuarioProcurado){
        return this.clientes
                .stream()
                .filter(cliente -> cliente.getLogin().equals(nomeUsuarioProcurado))
                .findFirst()
                .orElse(null);
    }

    public Conta encontraContaParaLogin(String nomeUsuarioProcurado){
        return this.contas
                .stream()
                .filter(conta -> conta.getTitular().getLogin().equals(nomeUsuarioProcurado))
                .findFirst()
                .orElse(null);
    }

    public Conta encontraContaPorNumeroCC(String numeroCCProcurado){
        return this.contas
                .stream()
                .filter(conta -> conta.getNumeroCC().equals(numeroCCProcurado))
                .findFirst()
                .orElse(null);
    }

    public List<Cliente> clientesSemConta(){
        return this.clientes.stream().filter(cliente -> cliente.getConta() == null).toList();
    }

    public String getNome() {
        return this.nome;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }
}