
package calculadora_salario_refatorado.descontos;

/**
 * Classe para cálculo de descontos de benefícios (VT, VA, VR).
 */
public class DescontoBeneficio implements Desconto {
    private final double percentual;
    private final double valorFixo;

    public DescontoBeneficio(double percentual, double valorFixo) {
        this.percentual = percentual;
        this.valorFixo = valorFixo;
    }

    @Override
    public double calcular(double salarioBruto, int dependentes) {
        return (salarioBruto * percentual) + valorFixo;
    }
}