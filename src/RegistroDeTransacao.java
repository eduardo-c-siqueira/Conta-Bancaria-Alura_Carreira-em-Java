import java.util.Date;

public abstract class RegistroDeTransacao {

    private Date hora;
    private double valor;
    private Conta contaMae;

    public RegistroDeTransacao(double valor, Conta contaMae) {
        this.valor = valor;
        this.contaMae = contaMae;
        this.hora = new Date();
    }

    public abstract TipoTransacao getTipo();

    public abstract String getRegistroImpresso();

    public Date getHora() {
        return hora;
    }

    public double getValor() {
        return valor;
    }

    public Conta getContaMae() {
        return contaMae;
    }
}