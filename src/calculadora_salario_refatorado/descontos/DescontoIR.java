
package calculadora_salario_refatorado.descontos;

/**
 * Classe para cálculo do desconto de IR.
 */
public class DescontoIR implements Desconto {
    @Override
    public double calcular(double salarioBruto, int dependentes) {
        double baseIR = salarioBruto - (dependentes * 189.59);
        if (baseIR <= 1903.98) {
            return 0;
        } else if (baseIR <= 2826.65) {
            return (baseIR * 0.075) - 142.80;
        } else if (baseIR <= 3751.05) {
            return (baseIR * 0.15) - 354.80;
        } else if (baseIR <= 4664.68) {
            return (baseIR * 0.225) - 636.13;
        } else {
            return (baseIR * 0.275) - 869.36;
        }
    }
}