
package calculadora_salario_refatorado.descontos;

/**
 * Classe para cálculo do desconto de plano de saúde.
 */
public class DescontoPlanoSaude implements Desconto {
    private final double valor;

    public DescontoPlanoSaude(double valor) {
        this.valor = valor;
    }

    @Override
    public double calcular(double salarioBruto, int dependentes) {
        return valor;
    }
}