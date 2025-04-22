## 💻 Calculadora de Salário Líquido
Este projeto em Java é uma calculadora de salário líquido que solicita informações do usuário e calcula os descontos de INSS, Imposto de Renda, benefícios como plano de saúde, vale transporte, alimentação e refeição, levando em conta também o número de dependentes.

 ## 🛠️ Funcionalidades
Entrada interativa de dados do usuário via console;

Cálculo automático do desconto de INSS conforme faixas;

Cálculo do IR com base em número de dependentes;

Cálculo de benefícios opcionais (plano de saúde, VT, VR, VA);

Exibição detalhada dos descontos e do salário líquido final.

Abaixo está o fluxograma detalhado do funcionamento:

## 📊 Diagrama de Fluxo

```mermaid
flowchart TD
    Start([Início])
    Start --> InputSalario[/Inserir salário bruto/]
    InputSalario --> InputDependentes[/Informar número de dependentes/]
    InputDependentes --> InputBeneficios[/Selecionar benefícios utilizados: VT, VA, VR, Plano de Saúde/]
    
    InputBeneficios --> CalculoINSS[Calcular desconto do INSS]
    CalculoINSS --> CalculoIRRF[Calcular desconto do IRRF]
    CalculoIRRF --> SomaBeneficios[Calcular descontos de benefícios: VT, VA, VR, Plano de Saúde]
    
    SomaBeneficios --> SomaDescontos[Somar todos os descontos]
    SomaDescontos --> CalculoLiquido[Calcular salário líquido]
    CalculoLiquido --> ExibirResumo[/Exibir resumo detalhado com percentual de desconto/]
    ExibirResumo --> Fim([Fim]) 




