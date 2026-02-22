import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Agencia {

    private String nome;
    private List<Cliente> clientes;
    private List<Conta> contas;
    private List<String> numerosCCUsados;
    private Gerente gerente;

    public Agencia(String nome, Gerente gerente) {
        this.nome = nome;
        this.gerente = gerente;
        gerente.setAgencia(this);
        clientes = new ArrayList<>();
        contas = new ArrayList<>();
        numerosCCUsados = new ArrayList<>();
    }

    //TODO: excluir depois de criar as funções de gerência
    void adicionaContaTeste(Conta c){
        contas.add(c);
        clientes.add(c.getTitular());
    }

    void cadastraCliente(Cliente cliente){
        clientes.add(cliente);
        System.out.printf("""
                Cliente cadastrado com sucesso!
                Nome: %s
                CPF: %s
                """, cliente.getNome(), cliente.getCpf());
    }

    boolean criaConta(Conta conta){
        if(!numerosCCUsados.contains(conta.getNumeroCC())){
            numerosCCUsados.add(conta.getNumeroCC());
            contas.add(conta);
            return true;
        } else {
            return false;
        }
    }

    boolean validaLoginCliente(String nomeUsuario, String senha){

        Cliente clienteEncontrado = encontraCliente(nomeUsuario);

        if (clienteEncontrado != null){
            return clienteEncontrado.validaLogin(nomeUsuario, senha);
        } else return false;
    }

    Cliente encontraCliente(String nomeUsuarioProcurado){
        return this.clientes
                .stream()
                .filter(cliente -> cliente.getLogin().equals(nomeUsuarioProcurado))
                .findFirst()
                .orElse(null);
    }

    Conta encontraContaParaLogin(String nomeUsuarioProcurado){
        return this.contas
                .stream()
                .filter(conta -> conta.getTitular().getLogin().equals(nomeUsuarioProcurado))
                .findFirst()
                .orElse(null);
    }

    Conta encontraContaPorNumeroCC(String numeroCCProcurado){
        return this.contas
                .stream()
                .filter(conta -> conta.getNumeroCC().equals(numeroCCProcurado))
                .findFirst()
                .orElse(null);
    }

    List<Cliente> clientesSemConta(){
        return this.clientes.stream().filter(cliente -> cliente.getConta() == null).toList();
    }

    String gerarNumeroCC(){
        if (this.contas.size()<50000) {
            while (true){
                String numeroGerado = Integer.toString(ThreadLocalRandom.current().nextInt(10000, 100000));
                if(!numerosCCUsados.contains(numeroGerado)){
                    return numeroGerado;
                }
            }
        } else return null;
    }

    String getNome() {
        return this.nome;
    }

    Gerente getGerente() {
        return gerente;
    }

    void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }
}