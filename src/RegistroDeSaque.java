public class RegistroDeSaque extends RegistroDeTransacao {

    public RegistroDeSaque(double valor, Conta contaMae) {
        super(valor, contaMae);
    }

    @Override
    public TipoTransacao getTipo() {
        return TipoTransacao.SAQUE;
    }

    @Override
    public String getRegistroImpresso() {
        return ("\n" + this.getHora() + ": Saque     | R$" + this.getValor());
    }
}
