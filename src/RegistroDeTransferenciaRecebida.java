public class RegistroDeTransferenciaRecebida extends RegistroDeTransacao{

    private final Conta contaOrigem;

    public RegistroDeTransferenciaRecebida(double valor, Conta contaMae, Conta contaOrigem) {
        super(valor, contaMae);
        this.contaOrigem = contaOrigem;
    }

    @Override
    public TipoTransacao getTipo() {
        return null;
    }

    @Override
    public String getRegistroImpresso() {
        return ("\n" + this.getHora() + ": Transferência de     | "
                + contaOrigem.getTitular().getNome() + " | R$" + this.getValor());
    }
}