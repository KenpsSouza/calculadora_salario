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
