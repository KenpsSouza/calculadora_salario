
package calculadora_salario_refatorado;

/**
 * Classe que encapsula os dados do funcionário.
 */
public class Funcionario {
    private double salarioBruto;
    private int dependentes;
    private String planoSaude;
    private boolean usaVT;
    private boolean usaVA;
    private boolean usaVR;

    public Funcionario(double salarioBruto, int dependentes, String planoSaude, boolean usaVT, boolean usaVA, boolean usaVR) {
        this.salarioBruto = salarioBruto;
        this.dependentes = dependentes;
        this.planoSaude = planoSaude;
        this.usaVT = usaVT;
        this.usaVA = usaVA;
        this.usaVR = usaVR;
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public int getDependentes() {
        return dependentes;
    }

    public String getPlanoSaude() {
        return planoSaude;
    }

    public boolean isUsaVT() {
        return usaVT;
    }

    public boolean isUsaVA() {
        return usaVA;
    }

    public boolean isUsaVR() {
        return usaVR;
    }
}