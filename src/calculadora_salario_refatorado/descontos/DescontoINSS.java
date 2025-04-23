
package calculadora_salario_refatorado.descontos;

/**
 * Classe para cálculo do desconto de INSS.
 */
public class DescontoINSS implements Desconto {
    @Override
    public double calcular(double salarioBruto, int dependentes) {
        if (salarioBruto <= 1302.00) {
            return salarioBruto * 0.08;
        } else if (salarioBruto <= 2571.00) {
            return salarioBruto * 0.09;
        } else if (salarioBruto <= 3856.00) {
            return salarioBruto * 0.11;
        } else {
            double desconto = salarioBruto * 0.14;
            return Math.min(desconto, 570.88);
        }
    }
}