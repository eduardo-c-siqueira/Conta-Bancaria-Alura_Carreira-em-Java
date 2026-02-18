public class RegistroDeTransferenciaEfetuada extends RegistroDeTransacao{

    private final Conta contaDestino;

    public RegistroDeTransferenciaEfetuada(double valor, Conta contaMae, Conta contaDestino) {
        super(valor, contaMae);
        this.contaDestino = contaDestino;
    }

    @Override
    public TipoTransacao getTipo() {
        return TipoTransacao.TRANSFERENCIA_EFETUADA;
    }

    @Override
    public String getRegistroImpresso() {
        return ("\n" + this.getHora() + ": Transferência para   | "
                + contaDestino.getTitular().getNome() + " | R$" + this.getValor());
    }
}