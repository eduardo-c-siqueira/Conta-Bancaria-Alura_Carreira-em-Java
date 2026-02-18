public class RegistroDeDeposito extends RegistroDeTransacao{

    public RegistroDeDeposito(double valor, Conta contaMae) {
        super(valor, contaMae);
    }

    @Override
    public TipoTransacao getTipo() {
        return TipoTransacao.DEPOSITO;
    }

    @Override
    public String getRegistroImpresso(){
        return ("\n" + this.getHora() + ": Depósito     | R$" + this.getValor());
    }
}