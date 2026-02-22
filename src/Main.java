//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Cliente clienteMaria = new Cliente("Maria de Lourdes", "00011122234", "maria.lourdes", "112233");
//        Cliente clienteJoao = new Cliente("João da Silva", "11122233345", "joao.silva", "223344");
//        Conta contaMaria = new Conta(TipoConta.CORRENTE,"111112","A", clienteMaria,1000,300,200);
//        Conta contaJoao = new Conta(TipoConta.CORRENTE, "111211","A", clienteJoao,200, 100, 100);

        Gerente gerente = new Gerente("Eduardo","edu","123123");
        Agencia agencia = new Agencia("A", gerente);
//        agencia.adicionaContaTeste(contaMaria);
//        agencia.adicionaContaTeste(contaJoao);
        SistemaAgencia menu = new SistemaAgencia(agencia);

        menu.exibirMenuInicial();
/*
        contaMaria.saque(100.50);
        contaJoao.saque(400);
        contaMaria.transferencia(contaJoao,200);
        contaJoao.saque(400);
        contaJoao.deposito(100);

        System.out.println(contaJoao.getHistoricoDeTransacoes());
        System.out.println(contaMaria.getHistoricoDeTransacoes());
*/
    }
}
