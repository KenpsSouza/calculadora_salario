package calculadora_salario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraSalarioComInterfaceGrafica extends JFrame {

    // Componentes da interface gráfica
    private JTextField campoSalarioBruto; // Campo de texto para entrada do salário bruto
    private JTextField campoDependentes; // Campo de texto para entrada do número de dependentes
    private JComboBox<String> comboPlanoSaude; // ComboBox para seleção do plano de saúde
    private JCheckBox checkVT, checkVA, checkVR; // CheckBoxes para selecionar benefícios (VT, VA, VR)
    private JTextArea areaResultado; // Área de texto para exibir os resultados

    public CalculadoraSalarioComInterfaceGrafica() {
        // Configurações básicas da janela
        setTitle("Calculadora de Salário Líquido"); // Título da janela
        setSize(600, 700); // Tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        setLayout(new BorderLayout()); // Define o layout principal como BorderLayout

        // Painel de entrada de dados
        JPanel painelEntrada = new JPanel();
        painelEntrada.setLayout(new GridLayout(8, 2, 10, 10)); // Layout em grade com 8 linhas e 2 colunas
        painelEntrada.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margem ao redor do painel

        // Adiciona os componentes de entrada ao painel
        painelEntrada.add(new JLabel("Salário Bruto:")); // Rótulo para o campo de salário bruto
        campoSalarioBruto = new JTextField(); // Campo de texto para entrada do salário bruto
        painelEntrada.add(campoSalarioBruto);

        painelEntrada.add(new JLabel("Número de Dependentes:")); // Rótulo para o campo de dependentes
        campoDependentes = new JTextField(); // Campo de texto para entrada do número de dependentes
        painelEntrada.add(campoDependentes);

        painelEntrada.add(new JLabel("Plano de Saúde:")); // Rótulo para o ComboBox de plano de saúde
        String[] planos = {"Nenhum", "Básico", "Avançado"}; // Opções do plano de saúde
        comboPlanoSaude = new JComboBox<>(planos); // ComboBox para seleção do plano de saúde
        painelEntrada.add(comboPlanoSaude);

        // CheckBoxes para selecionar benefícios
        checkVT = new JCheckBox("Vale Transporte");
        painelEntrada.add(checkVT);

        checkVA = new JCheckBox("Vale Alimentação");
        painelEntrada.add(checkVA);

        checkVR = new JCheckBox("Vale Refeição");
        painelEntrada.add(checkVR);

        // Botão para calcular o salário líquido
        JButton botaoCalcular = new JButton("Calcular");
        botaoCalcular.setBackground(new Color(0, 153, 76)); // Cor de fundo verde
        botaoCalcular.setForeground(Color.WHITE); // Cor do texto branca
        painelEntrada.add(botaoCalcular);

        // Botão para limpar os campos
        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setBackground(new Color(204, 0, 0)); // Cor de fundo vermelha
        botaoLimpar.setForeground(Color.WHITE); // Cor do texto branca
        painelEntrada.add(botaoLimpar);

        // Adiciona o painel de entrada ao topo da janela
        add(painelEntrada, BorderLayout.NORTH);

        // Área de texto para exibir os resultados
        areaResultado = new JTextArea(20, 50); // Área de texto com 20 linhas e 50 colunas
        areaResultado.setEditable(false); // Torna a área de texto somente leitura
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Define a fonte como monoespaçada
        JScrollPane scrollPane = new JScrollPane(areaResultado); // Adiciona barra de rolagem à área de texto
        add(scrollPane, BorderLayout.CENTER); // Adiciona a área de texto ao centro da janela

        // Ações dos botões
        botaoCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularSalario(); // Chama o método para calcular o salário líquido
            }
        });

        botaoLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos(); // Chama o método para limpar os campos
            }
        });

        setVisible(true); // Torna a janela visível
    }

    // Método para calcular o salário líquido
    private void calcularSalario() {
        try {
            // Obtém os valores dos campos de entrada
            double salarioBruto = Double.parseDouble(campoSalarioBruto.getText());
            int dependentes = Integer.parseInt(campoDependentes.getText());
            String planoSaude = (String) comboPlanoSaude.getSelectedItem();

            // Verifica se os CheckBoxes estão selecionados
            boolean usaVT = checkVT.isSelected();
            boolean usaVA = checkVA.isSelected();
            boolean usaVR = checkVR.isSelected();

            ///////////////////////////////////////////////
            ///////////////////////////////////////////////
            ///////////////////////////////////////////////
            // Lógica de cálculo (mesma do código original)
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

            double descontoVT = usaVT ? salarioBruto * 0.06 : 0;
            double descontoVA = usaVA ? 200 : 0;
            double descontoVR = usaVR ? 250 : 0;

            double descontoPlano = 0;
            if (planoSaude.equals("Básico")) {
                descontoPlano = 150;
            } else if (planoSaude.equals("Avançado")) {
                descontoPlano = 300;
            }

            double totalDescontos = descontoINSS + descontoIR + descontoVT + descontoVA + descontoVR + descontoPlano;
            double salarioLiquido = salarioBruto - totalDescontos;
            double percentualDesconto = (totalDescontos / salarioBruto) * 100;

            // Exibir resultados na área de texto
            areaResultado.setText(""); // Limpa a área de texto
            areaResultado.append(String.format("Salário Bruto: R$ %.2f\n", salarioBruto));
            areaResultado.append(String.format("INSS: R$ %.2f\n", descontoINSS));
            areaResultado.append(String.format("IRRF: R$ %.2f\n", descontoIR));
            areaResultado.append(String.format("Plano de Saúde: R$ %.2f\n", descontoPlano));
            areaResultado.append(String.format("VT: R$ %.2f\n", descontoVT));
            areaResultado.append(String.format("VA: R$ %.2f\n", descontoVA));
            areaResultado.append(String.format("VR: R$ %.2f\n", descontoVR));
            areaResultado.append("\n--------------------------\n");
            areaResultado.append(String.format("Total de Descontos: R$ %.2f\n", totalDescontos));
            areaResultado.append(String.format("Percentual de Desconto: %.2f%%\n", percentualDesconto));
            areaResultado.append(String.format("Salário Líquido: R$ %.2f\n", salarioLiquido));

        } catch (NumberFormatException e) {
            // Exibe uma mensagem de erro se os valores inseridos forem inválidos
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos!");
        }
    }

    // Método para limpar os campos de entrada e a área de resultado
    private void limparCampos() {
        campoSalarioBruto.setText(""); // Limpa o campo de salário bruto
        campoDependentes.setText(""); // Limpa o campo de dependentes
        comboPlanoSaude.setSelectedIndex(0); // Reseta o ComboBox para a primeira opção
        checkVT.setSelected(false); // Desmarca o CheckBox de Vale Transporte
        checkVA.setSelected(false); // Desmarca o CheckBox de Vale Alimentação
        checkVR.setSelected(false); // Desmarca o CheckBox de Vale Refeição
        areaResultado.setText(""); // Limpa a área de texto
    }

    public static void main(String[] args) {
        // Executa a aplicação na thread de eventos do Swing
        SwingUtilities.invokeLater(() -> new CalculadoraSalarioComInterfaceGrafica());
    }
}