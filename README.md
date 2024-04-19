# E-COMMERCE com Spring Boot

Este é um projeto de E-COMMERCE desenvolvido em Java utilizando o framework Spring Boot. Ele inclui funcionalidades como autenticação com Spring Security e geração de tokens JWT, além de integração com o Lombok para reduzir a verbosidade do código.

## Funcionalidades Principais

- **Autenticação e Autorização**: Utiliza o Spring Security para proteger os endpoints da aplicação. Suporta diferentes papéis de usuário, incluindo ADMIN, VENDEDOR e CLIENTE.
- **Geração de Tokens JWT**: Implementa a geração de tokens JWT para autenticação e autorização de usuários.
- **Entidades e Relacionamentos**: O projeto inclui as seguintes entidades e seus relacionamentos:
  - **Usuário**: Representa os usuários da aplicação, com suporte para diferentes papéis.
  - **Produto**: Descreve os produtos disponíveis no E-COMMERCE.
  - **Avaliação**: Permite que os usuários avaliem os produtos.
  - **Carrinho**: Mantém o estado do carrinho de compras dos usuários.
  - **Categoria**: Categoriza os produtos disponíveis.
  - **Endereço**: Armazena os endereços dos usuários para fins de entrega.
  - **Vendas**: Registra as transações de vendas realizadas na plataforma.
- **Método de Busca de Produto**: Implementa um método de busca de produtos para facilitar a localização de itens desejados pelos usuários.

## Pré-requisitos

- Java 8 ou superior
- Maven
- Banco de dados compatível com o Spring Boot (por exemplo, MySQL, PostgreSQL)

- Acesse a aplicação em `http://localhost:8080`.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir um pull request para correções de bugs, melhorias de código ou novas funcionalidades.
