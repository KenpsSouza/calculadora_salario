package calculadora_salario;

import java.util.Scanner;

public class CalculadoraSalario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(" Olá! Vamos calcular a composição do salário líquido. Informe o salário bruto: ");
        double salarioBruto = sc.nextDouble();

        System.out.println(" Você possui dependentes? Caso afirmativo, informe o número de dependentes. Caso contrário, digite 0: ");
        int dependentes = sc.nextInt();        
        sc.nextLine(); // --> Limpa  para não interferir com o próximo nextLine()

        System.out.println(" Informe o Plano de Saúde: (B para Básico, A para Avançado ou N para Nenhum): ");
        String planoSaude = sc.nextLine().toUpperCase().trim(); // Converte p maiúsculo e o trim retira espaços em branco
        // Validação  para o usuário informar um valor válido
        while (!planoSaude.equals("B") && !planoSaude.equals("A") && !planoSaude.equals("N")) {
            System.out.println("Opção inválida! Por favor, informe: B para Básico, A para Avançado ou N para Nenhum");
            planoSaude = sc.nextLine().toUpperCase().trim(); 
        }

        System.out.println(" Usa Vale Transporte? (digite: S para Sim): ");
        String vTransporte = sc.nextLine().toUpperCase();

        System.out.println(" Usa Vale Alimentação? (digite: S para Sim): ");
        String vAlimentacao = sc.nextLine().toUpperCase();

        System.out.println(" Usa Vale Refeição? (digite: S para Sim): ");
        String vRefeicao = sc.nextLine().toUpperCase();

        // Cálculo do desconto INSS baseado no salário bruto
        double descontoINSS;
        if (salarioBruto <= 1302.00) {
            descontoINSS = salarioBruto * 0.08; // 8%  até 1302
        } else if (salarioBruto <= 2571.00) {
            descontoINSS = salarioBruto * 0.09; // 9%  até 2571
        } else if (salarioBruto <= 3856.00) {
            descontoINSS = salarioBruto * 0.11; // 11%  até 3856
        } else {
            descontoINSS = salarioBruto * 0.14; // 14% acima de 3856
            // para o desconto não ultrapassar 570.88, tudo que passar desse valor será = 570.88
            if (descontoINSS > 570.88) {
                descontoINSS = 570.88;
            }    
        }

        // Cálculo do desconto do IR, levando em consideração os dependentes
        double baseIR = salarioBruto - (dependentes * 189.59); // Reduz a base de cálculo do IR conforme o número de dependentes
        double descontoIR;

        // Faixas  Imposto de Renda
        if (baseIR <= 1903.98) {
            descontoIR = 0; // Isenção de IR até 1903.98
        } else if (baseIR <= 2826.65) {
            descontoIR = (baseIR * 0.075) - 142.80; // Desconto de 7.5% com dedução
        } else if (baseIR <= 3751.05) {
            descontoIR = (baseIR * 0.15) - 354.80; // Desconto de 15% com dedução
        } else if (baseIR <= 4664.68) {
            descontoIR = (baseIR * 0.225) - 636.13; // Desconto de 22.5% com dedução
        } else {
            descontoIR = (baseIR * 0.275) - 869.36; // Desconto de 27.5% com dedução
        }

        // Cálculos dos descontos (Vale Transporte, Alimentação e Refeição)
        double descontoVT = vTransporte.equals("S") ? salarioBruto * 0.06 : 0; // 6% desconto do VT se sim
        double descontoVA = vAlimentacao.equals("S") ? 200.0 : 0; // Desconto de 200 no VA se sim
        double descontoVR = vRefeicao.equals("S") ? 250.00 : 0; // Desconto de 250 no VR se sim

        // Desconto do plano de saúde, dependendo do plano ou sem plano algum
        double descontoPlano = 0;  
        if (planoSaude.equals("B")) {
            descontoPlano = 150.0;
        } else if (planoSaude.equals("A")) {
            descontoPlano = 300.0; 
        } else if (planoSaude.equals("N")) {
            descontoPlano = 0;
        }

        // Somar todos os descontos
        double totalDescontos = descontoINSS + descontoIR + descontoVT + descontoVA + descontoVR + descontoPlano;

        // Cálculo do percentual de descontos sobre o salário bruto
        double percentualDesconto = (totalDescontos / salarioBruto) * 100;
        
        // Cálculo do salário líquido 
        double salarioLiquido = salarioBruto - totalDescontos;

        System.out.println("\n===============================================");
        System.out.printf(" Desconto do INSS => R$ %.2f\n", descontoINSS);
        System.out.printf(" Salário base para IR => R$ %.2f\n", baseIR);        
        System.out.printf(" Desconto do IR => R$ %.2f\n", descontoIR);
        System.out.printf(" Desconto do Plano de Saúde => R$ %.2f\n", descontoPlano);
        System.out.printf(" Desconto de Vale Transporte => R$ %.2f\n", descontoVT);
        System.out.printf(" Desconto de Vale Alimentação => R$ %.2f\n", descontoVA);
        System.out.printf(" Desconto de Vale Refeição => R$ %.2f\n", descontoVR);
        System.out.println("===============================================");
        System.out.println("                 RESUMO FINAL");
        System.out.printf("\n Salário bruto => R$ %.2f\n", salarioBruto);
        System.out.println("");
        System.out.printf(" Percentual de desconto => %.2f%%\n", percentualDesconto);
        System.out.println("");
        System.out.printf(" Salário líquido => R$ %.2f\n", salarioLiquido);

        sc.close();
    }
}
