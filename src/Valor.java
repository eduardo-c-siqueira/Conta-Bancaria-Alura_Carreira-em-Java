import java.math.BigDecimal;

public record Valor(BigDecimal valor) {
    public Valor {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor de deve ser maior que zero");
        }
    }
}
