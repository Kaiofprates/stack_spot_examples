**Exemplo de diagrama mermaid gerado a partir de Quick Command da Stack Spot**

```mermaid
flowchart TD
    A[Início] --> B[**processOrder**]
    B --> C[**checkOrderConditions**]
    C -->|Pagamento não recebido| D[Rejeitado: Pagamento]
    C -->|Inventário não disponível| E[Rejeitado: Inventário]
    C -->|Endereço não verificado| F[Rejeitado: Endereço]
    C -->|Condições atendidas| G[Aprovado]
    G --> H[**shipOrder**]
    H --> I[Enviado]
    I --> J[**deliverOrder**]
    J --> K[Entregue]
    D --> L[Fim]
    E --> L
    F --> L
    K --> L
```

