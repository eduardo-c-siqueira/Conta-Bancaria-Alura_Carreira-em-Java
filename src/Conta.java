import java.util.ArrayList;
import java.util.List;

public class Conta {

    private final TipoConta tipo;
    private final String numeroCC;
    private final String agencia;
    private final Cliente titular;
    private NivelConta nivel;
    private double saldo;
    private double limiteSaque;
    private double limiteTransferencia;
    private List<RegistroDeTransacao> historicoDeTransacoes = new ArrayList<RegistroDeTransacao>();

    public Conta(TipoConta tipo, String numeroCC, String agencia,
                 Cliente titular, NivelConta nivel) {
        this.tipo = tipo;
        this.numeroCC = numeroCC;
        this.agencia = agencia;
        this.titular = titular;
        this.nivel = nivel;
        this.saldo = 0;

        this.limiteSaque = switch (nivel){
            case BASIC -> 100.00;
            case STANDARD -> 500.00;
            case STANDARD_PLUS -> 1000.00;
            case PREMIUM -> 2000.00;
            case PLATINUM -> 5000.00;
        };

        this.limiteTransferencia = switch (nivel){
            case BASIC -> 500.00;
            case STANDARD -> 1000.00;
            case STANDARD_PLUS -> 2000.00;
            case PREMIUM -> 25000.00;
            case PLATINUM -> 100000.00;
        };

        this.titular.setConta(this);
    }

    //Verifica se o valor a sacar está dentro do limite definido,
    // e se o saldo é suficiente, e retorna o saldo após a operação
    boolean saque(double valorSacado){
        if (valorSacado > this.saldo) {
            System.out.println(this.titular.getNome() + ": Saldo insuficiente!");
            return false;
        } else if (valorSacado >= this.limiteSaque) {
            System.out.println(this.titular.getNome() + ": Ops. O valor informado ultrapassa o Limite de Saque da conta. O limite para saques é R$" + limiteSaque );
            return false;
        } else if (valorSacado > 0){
            this.saldo -= valorSacado;
            this.adicionarAoHistorico(new RegistroDeSaque(valorSacado,this));
            System.out.println(this.titular.getNome() + ": Saque de R$" + valorSacado + " concluído. Saldo atual: R$" + this.saldo);
            return true;
        } else {
            System.out.println("Houve um problema inesperado. Por favor, tente novamente.");
            return false;
        }
    }

    //Adiciona o valor depositado ao saldo
    boolean deposito(double valorDepositado){
        if (valorDepositado >0) {
            this.saldo += valorDepositado;
            adicionarAoHistorico(new RegistroDeDeposito(valorDepositado, this));
            System.out.println(this.titular.getNome() + ": Depósito concluído! Saldo atual: R$" + this.saldo);
            return true;
        } else {
            System.out.println("Houve um problema inesperado. Por Favor, tente novamente");
            return false;
        }
    }

    /*
    Verifica se o saldo é suficiente e se o valor está abaixo do limite de
     tranferência, e então deduz o valor deste saldo e chama o métdo receber
     transferência na conta destino
    */
    boolean transferencia(Conta contaDestino,double valorTransferido){
        if (valorTransferido > this.saldo) {
            System.out.println(this.titular.getNome() + ": Saldo insuficiente!");
            return false;
        } else if (valorTransferido > this.limiteTransferencia) {
            System.out.println(this.titular.getNome() + ": Ops. O valor informado ultrapassa o Limite de" +
                    " Transferência da conta. O limite para transferências é R$" + limiteTransferencia );
            return false;
        } else if (valorTransferido >0){
            this.saldo -= valorTransferido;
            this.adicionarAoHistorico(new RegistroDeTransferenciaEfetuada(valorTransferido, this, contaDestino));
            contaDestino.receberTransferencia(this, valorTransferido);
            System.out.println(this.titular.getNome() + ": Transferência de R$" + valorTransferido + " para "
                    + contaDestino.getTitular().getNome() + " concluída. Saldo atual: R$" + this.saldo);
            return true;
        } else {
            System.out.println("Houve um problema inesperado");
            return false;
        }
    }
    //Adiciona o valor ransferido ao saldo, a partir no métdo "transferencia" de outra conta
    private boolean receberTransferencia(Conta contaOrigem, double valorTransferido){
        if (valorTransferido > 0) {
            this.saldo += valorTransferido;
            this.adicionarAoHistorico(new RegistroDeTransferenciaRecebida(valorTransferido, this, contaOrigem));
            System.out.println(this.titular.getNome() + ": Transferência de R$" + valorTransferido + " recebida de "
                    + contaOrigem.getTitular().getNome() + ". Saldo atual: R$" + this.saldo);
            return true;
        } else {
            System.out.printf("Ocorreu um problema inesperado ao receber uma tranferência. Por favor, " +
                    "entre em contato com seu gerente.");
            return false;
        }
    }

    void mostrarSaldo(){
        System.out.println(this.titular.getNome() + ": Seu saldo atual é de R$" + this.saldo);
    }

    //Adiciona o registro de transacao à arraylist historico de transações
    private void adicionarAoHistorico(RegistroDeTransacao registro){
        this.historicoDeTransacoes.add(registro);
    }

    TipoConta getTipo() {
        return tipo;
    }

    String getNumeroCC() {
        return numeroCC;
    }

    String getAgencia() {
        return agencia;
    }

    Cliente getTitular() {
        return titular;
    }

    double getSaldo() {
        return saldo;
    }

    double getLimiteSaque() {
        return limiteSaque;
    }

    double getLimiteTransferencia() {
        return limiteTransferencia;
    }

    void setLimiteSaque(double limiteSaque) {
        this.limiteSaque = limiteSaque;
    }

    void setLimiteTransferencia(double limiteTransferencia) {
        this.limiteTransferencia = limiteTransferencia;
    }

    String getHistoricoDeTransacoes() {
        StringBuilder historicoEscrito = new StringBuilder();

        historicoDeTransacoes.forEach(transacao ->{
           historicoEscrito.append(transacao.getRegistroImpresso());
        });

        return "\n Histórico de " + this.titular.getNome() +": \n" + historicoEscrito.toString() + " \n\nSaldo final: R$" + this.saldo;
    }

    NivelConta getNivel() {
        return nivel;
    }
}