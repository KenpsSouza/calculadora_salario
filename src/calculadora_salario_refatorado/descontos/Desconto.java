
package calculadora_salario_refatorado.descontos;

/**
 * Interface para cálculo de descontos.
 */
public interface Desconto {
    double calcular(double salarioBruto, int dependentes);
}