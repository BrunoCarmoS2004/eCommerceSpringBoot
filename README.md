# E-COMMERCE com Spring Boot

Este é um projeto de E-COMMERCE desenvolvido em Java utilizando o framework Spring Boot. Ele inclui funcionalidades como autenticação com Spring Security e geração de tokens JWT, além de integração com o Lombok para reduzir a verbosidade do código.

##Front End em angamento! Veja as mais novas publicações sobre no meu Linkedin:https://www.linkedin.com/posts/bruno-carmo-dev_front-end-angular-para-o-projeto-back-end-activity-7200303579679641601-_Kp_?utm_source=share&utm_medium=member_desktop

Template: https://codepen.io/anandaprojapati/pen/GmrwYE?editors=1100
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

### Para usar banco de dados local basta ir em eCormmerce\src\main\resources\application.properties. É nescessário adicionar um endereço para a criação do usuario
- Retire as "#" das linhas 3 a 7 e se necessario mude o nome do banco de dados para um de sua preferencia.
- Adicione "#" nas linhas 10 a 16 para desvincular o projeto ao banco de dados MySql.
- Inicie o aplicativo
- O banco de dados escolhido digite INSERT INTO ENDERECO VALUES(ID,"CIDADE","ESTADO","PAIS");
- Após isso basta criar as entidades.
- ### Para Usar outros bancos de dados é nescessário adicionar um endereço para a criação do usuario, siga esse passo a passo para adicionar um endereço:
- Vá para eCormmerce\src\main\resources\application.properties
- Caso queira mude o banco de dados na linha 10
- Inicie o aplicativo
- O banco de dados escolhido digite INSERT INTO ENDERECO VALUES(ID,"CIDADE","ESTADO","PAIS");
- Após isso basta criar as entidades.
