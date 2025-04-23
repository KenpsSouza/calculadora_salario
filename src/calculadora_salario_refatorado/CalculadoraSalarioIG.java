
package calculadora_salario_refatorado;

import calculadora_salario_refatorado.descontos.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal que implementa a interface gráfica para a calculadora de salário líquido.
 */
public class CalculadoraSalarioIG extends JFrame {

    private JTextField campoSalarioBruto;
    private JTextField campoDependentes;
    private JComboBox<String> comboPlanoSaude;
    private JCheckBox checkVT, checkVA, checkVR;
    private JTextArea areaResultado;

    public CalculadoraSalarioIG() {
        setTitle("Calculadora de Salário Líquido");
        setSize(620, 820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de entrada
        JPanel painelEntrada = new JPanel(new GridLayout(5, 2, 10, 10));
        painelEntrada.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        painelEntrada.add(new JLabel("Salário Bruto:"));
        campoSalarioBruto = new JTextField();
        painelEntrada.add(campoSalarioBruto);

        painelEntrada.add(new JLabel("Número de Dependentes:"));
        campoDependentes = new JTextField();
        painelEntrada.add(campoDependentes);

        painelEntrada.add(new JLabel("Plano de Saúde:"));
        String[] planos = {"Nenhum", "Básico", "Avançado"};
        comboPlanoSaude = new JComboBox<>(planos);
        painelEntrada.add(comboPlanoSaude);

        checkVT = new JCheckBox("Vale Transporte");
        painelEntrada.add(checkVT);

        checkVA = new JCheckBox("Vale Alimentação");
        painelEntrada.add(checkVA);

        checkVR = new JCheckBox("Vale Refeição");
        painelEntrada.add(checkVR);

        JButton botaoCalcular = new JButton("Calcular");
        JButton botaoLimpar = new JButton("Limpar");

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelBotoes.add(botaoCalcular);
        painelBotoes.add(botaoLimpar);

        painelEntrada.add(painelBotoes);

        add(painelEntrada, BorderLayout.NORTH);

        // Área de resultados
        areaResultado = new JTextArea(20, 50);
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(areaResultado);
        add(scrollPane, BorderLayout.CENTER);

        botaoCalcular.addActionListener(e -> calcularSalario());
        botaoLimpar.addActionListener(e -> limparCampos());

        setVisible(true);
    }

    private void calcularSalario() {
        try {
            double salarioBruto = Double.parseDouble(campoSalarioBruto.getText());
            int dependentes = Integer.parseInt(campoDependentes.getText());
            String planoSaude = (String) comboPlanoSaude.getSelectedItem();
            boolean usaVT = checkVT.isSelected();
            boolean usaVA = checkVA.isSelected();
            boolean usaVR = checkVR.isSelected();

            Funcionario funcionario = new Funcionario(salarioBruto, dependentes, planoSaude, usaVT, usaVA, usaVR);

            List<Desconto> descontos = new ArrayList<>();
            descontos.add(new DescontoINSS());
            descontos.add(new DescontoIR());
            if (funcionario.isUsaVT()) descontos.add(new DescontoBeneficio(0.06, 0));
            if (funcionario.isUsaVA()) descontos.add(new DescontoBeneficio(0, 200));
            if (funcionario.isUsaVR()) descontos.add(new DescontoBeneficio(0, 250));
            if (funcionario.getPlanoSaude().equals("Básico")) descontos.add(new DescontoPlanoSaude(150));
            if (funcionario.getPlanoSaude().equals("Avançado")) descontos.add(new DescontoPlanoSaude(300));

            double totalDescontos = descontos.stream()
                    .mapToDouble(d -> d.calcular(funcionario.getSalarioBruto(), funcionario.getDependentes()))
                    .sum();

            double salarioLiquido = funcionario.getSalarioBruto() - totalDescontos;
            double percentualDesconto = (totalDescontos / funcionario.getSalarioBruto()) * 100;

            areaResultado.setText("");
            areaResultado.append(String.format("Salário Bruto: R$ %.2f\n", funcionario.getSalarioBruto()));
            areaResultado.append(String.format("Total de Descontos: R$ %.2f\n", totalDescontos));
            areaResultado.append(String.format("Percentual de Desconto: %.2f%%\n", percentualDesconto));
            areaResultado.append(String.format("Salário Líquido: R$ %.2f\n", salarioLiquido));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos!");
        }
    }

    private void limparCampos() {
        campoSalarioBruto.setText("");
        campoDependentes.setText("");
        comboPlanoSaude.setSelectedIndex(0);
        checkVT.setSelected(false);
        checkVA.setSelected(false);
        checkVR.setSelected(false);
        areaResultado.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculadoraSalarioIG::new);
    }
}