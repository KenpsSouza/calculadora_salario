package calculadora_salario;

import java.util.Scanner;

public class CalculadoraSalarioComMenu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. Calcular salário líquido");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    calcularSalarioLiquido(sc);
                    break;
                case 2:
                    System.out.println("Encerrando o programa. Até mais!");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        sc.close();
    }

    // Método separado para os cálculos
    public static void calcularSalarioLiquido(Scanner sc) {
        System.out.print("\nInforme o salário bruto: ");
        double salarioBruto = sc.nextDouble();

        System.out.print("Número de dependentes (ou 0 se não tiver): ");
        int dependentes = sc.nextInt();
        sc.nextLine(); // limpa buffer

        System.out.print("Plano de Saúde (B = Básico | A = Avançado | N = Nenhum): ");
        String planoSaude = sc.nextLine().toUpperCase().trim();
        while (!planoSaude.equals("B") && !planoSaude.equals("A") && !planoSaude.equals("N")) {
            System.out.print("Opção inválida! Digite B, A ou N: ");
            planoSaude = sc.nextLine().toUpperCase().trim();
        }

        System.out.print("Usa Vale Transporte? (S/N): ");
        String vTransporte = sc.nextLine().toUpperCase();

        System.out.print("Usa Vale Alimentação? (S/N): ");
        String vAlimentacao = sc.nextLine().toUpperCase();

        System.out.print("Usa Vale Refeição? (S/N): ");
        String vRefeicao = sc.nextLine().toUpperCase();

        // Desconto INSS
        double descontoINSS;
        if (salarioBruto <= 1302.00) {
            descontoINSS = salarioBruto * 0.08;
        } else if (salarioBruto <= 2571.00) {
            descontoINSS = salarioBruto * 0.09;
        } else if (salarioBruto <= 3856.00) {
            descontoINSS = salarioBruto * 0.11;
        } else {
            descontoINSS = salarioBruto * 0.14;
            if (descontoINSS > 570.88) {
                descontoINSS = 570.88;
            }
        }

        // IR
        double baseIR = salarioBruto - (dependentes * 189.59);
        double descontoIR;

        if (baseIR <= 1903.98) {
            descontoIR = 0;
        } else if (baseIR <= 2826.65) {
            descontoIR = (baseIR * 0.075) - 142.80;
        } else if (baseIR <= 3751.05) {
            descontoIR = (baseIR * 0.15) - 354.80;
        } else if (baseIR <= 4664.68) {
            descontoIR = (baseIR * 0.225) - 636.13;
        } else {
            descontoIR = (baseIR * 0.275) - 869.36;
        }

        // Outros descontos
        double descontoVT = vTransporte.equals("S") ? salarioBruto * 0.06 : 0;
        double descontoVA = vAlimentacao.equals("S") ? 200.0 : 0;
        double descontoVR = vRefeicao.equals("S") ? 250.0 : 0;

        double descontoPlano = 0;
        if (planoSaude.equals("B")) {
            descontoPlano = 150.0;
        } else if (planoSaude.equals("A")) {
            descontoPlano = 300.0;
        }

        double totalDescontos = descontoINSS + descontoIR + descontoVT + descontoVA + descontoVR + descontoPlano;
        double percentualDesconto = (totalDescontos / salarioBruto) * 100;
        double salarioLiquido = salarioBruto - totalDescontos;

        // Saída
        System.out.println("\n===============================================");
        System.out.printf(" Desconto do INSS => R$ %.2f\n", descontoINSS);
        System.out.printf(" Base para IR => R$ %.2f\n", baseIR);
        System.out.printf(" Desconto do IR => R$ %.2f\n", descontoIR);
        System.out.printf(" Plano de Saúde => R$ %.2f\n", descontoPlano);
        System.out.printf(" Vale Transporte => R$ %.2f\n", descontoVT);
        System.out.printf(" Vale Alimentação => R$ %.2f\n", descontoVA);
        System.out.printf(" Vale Refeição => R$ %.2f\n", descontoVR);
        System.out.println("===============================================");
        System.out.println("                 RESUMO FINAL");
        System.out.printf(" Salário bruto => R$ %.2f\n", salarioBruto);
        System.out.printf(" Total de descontos => R$ %.2f\n", totalDescontos);
        System.out.printf(" Percentual de desconto => %.2f%%\n", percentualDesconto);
        System.out.printf(" Salário líquido => R$ %.2f\n", salarioLiquido);
    }
}
