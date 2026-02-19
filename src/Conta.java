import java.util.ArrayList;
import java.util.List;

public class Conta {

    private final TipoConta tipo;
    private final String numeroCC;
    private final String agencia;
    private final Cliente titular;
    private double saldo;
    private double limiteSaque;
    private double limiteTransferencia;
    private List<RegistroDeTransacao> historicoDeTransacoes = new ArrayList<RegistroDeTransacao>();

    public Conta(TipoConta tipo, String numeroCC, String agencia,
                 Cliente titular, double saldo, double limiteSaque,
                 double limiteTransferencia) {
        this.tipo = tipo;
        this.numeroCC = numeroCC;
        this.agencia = agencia;
        this.titular = titular;
        this.saldo = saldo;
        this.limiteSaque = limiteSaque;
        this.limiteTransferencia = limiteTransferencia;
        this.titular.setConta(this);
    }

    //TODO: Adicionar forma de validar que os valores não sejam negativos nas operações
    //Verifica se o valor a sacar está dentro do limite definido,
    // e se o saldo é suficiente, e retorna o saldo após a operação
    public double saque(double valorSacado){
        if (valorSacado > this.saldo) {
            System.out.println(this.titular.getNome() + ": Saldo insuficiente!");
            return this.saldo;
        } else if (valorSacado >= this.limiteSaque) {
            System.out.println(this.titular.getNome() + ": Ops. O valor informado ultrapassa o Limite de Saque da conta. O limite para saques é R$" + limiteSaque );
            return this.saldo;
        } else {
            this.saldo -= valorSacado;
            this.adicionarAoHistorico(new RegistroDeSaque(valorSacado,this));
            System.out.println(this.titular.getNome() + ": Saque de R$" + valorSacado + " concluído. Saldo atual: R$" + this.saldo);
            return this.saldo;
        }
    }

    //Adiciona o valor depositado ao saldo
    public double deposito(double valorDepositado){
        this.saldo += valorDepositado;
        adicionarAoHistorico(new RegistroDeDeposito(valorDepositado, this));
        System.out.println(this.titular.getNome() + ": Depósito concluído! Saldo atual: R$" + this.saldo);
        return this.saldo;
    }

    /*
    Verifica se o saldo é suficiente e se o valor está abaixo do limite de
     tranferência, e então deduz o valor deste saldo e chama o métdo receber
     transferência na conta destino
    */
    public double transferencia(Conta contaDestino,double valorTransferido){
        if (valorTransferido > this.saldo) {
            System.out.println(this.titular.getNome() + ": Saldo insuficiente!");
            return this.saldo;
        } else if (valorTransferido > this.limiteTransferencia) {
            System.out.println(this.titular.getNome() + ": Ops. O valor informado ultrapassa o Limite de" +
                    " Transferência da conta. O limite para transferências é R$" + limiteTransferencia );
            return this.saldo;
        } else {
            this.saldo -= valorTransferido;
            this.adicionarAoHistorico(new RegistroDeTransferenciaEfetuada(valorTransferido, this, contaDestino));
            contaDestino.receberTransferencia(this,valorTransferido);
            System.out.println(this.titular.getNome() + ": Transferência de R$" + valorTransferido + " para "
                    + contaDestino.getTitular().getNome() + " concluída. Saldo atual: R$" + this.saldo);
            return this.saldo;
        }
    }
    //Adiciona o valor ransferido ao saldo, a partir no métdo "transferencia" de outra conta
    private double receberTransferencia(Conta contaOrigem, double valorTransferido){
        this.saldo += valorTransferido;
        this.adicionarAoHistorico(new RegistroDeTransferenciaRecebida(valorTransferido, this, contaOrigem));
        System.out.println(this.titular.getNome() + ": Transferência de R$" + valorTransferido + " recebida de "
                + contaOrigem.getTitular().getNome() + ". Saldo atual: R$" + this.saldo);
        return this.saldo;
    }

    public void mostrarSaldo(){
        System.out.println(this.titular.getNome() + ": Seu saldo atual é de R$" + this.saldo);
    }

    //Adiciona o registro de transacao à arraylist historico de transações
    public void adicionarAoHistorico(RegistroDeTransacao registro){
        this.historicoDeTransacoes.add(registro);
    }

    public TipoConta getTipo() {
        return tipo;
    }

    public String getNumeroCC() {
        return numeroCC;
    }

    public String getAgencia() {
        return agencia;
    }

    public Cliente getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimiteSaque() {
        return limiteSaque;
    }

    public double getLimiteTransferencia() {
        return limiteTransferencia;
    }

    public void setLimiteSaque(double limiteSaque) {
        this.limiteSaque = limiteSaque;
    }

    public void setLimiteTransferencia(double limiteTransferencia) {
        this.limiteTransferencia = limiteTransferencia;
    }

    public String getHistoricoDeTransacoes() {
        StringBuilder historicoEscrito = new StringBuilder();

        historicoDeTransacoes.forEach(transacao ->{
           historicoEscrito.append(transacao.getRegistroImpresso());
        });

        return "\n Histórico de " + this.titular.getNome() +": \n" + historicoEscrito.toString() + " \n\nSaldo final: R$" + this.saldo;
    }
}