# 🥖 Sistema Padaria Web - Refatorado 

Este projeto faz parte do **Projeto Integrador** do curso de Desenvolvimento de Sistemas. O objetivo desta etapa foi a refatoração completa de um sistema Desktop (Java Swing) para uma arquitetura desacoplada e preparada para o ambiente Web.

## 🚀 Tecnologias Utilizadas
* **Java JDK 17** (ou a versão que você está usando)
* **MySQL 8.0** (Banco de Dados)
* **JDBC** (Java Database Connectivity)
* **NetBeans IDE**

## 🏗️ Arquitetura e Padrões (SOLID)
O projeto foi reestruturado seguindo princípios de design de software para garantir manutenibilidade:

* **Padrão DAO (Data Access Object):** Toda a lógica de persistência foi isolada em classes específicas, separando o SQL das regras de negócio.
* **Princípio da Responsabilidade Única (SRP):** Cada classe tem uma única razão para mudar.
* **Desacoplamento:** Remoção total de dependências de interface gráfica (`javax.swing`) no núcleo do sistema, permitindo a portabilidade para Servlets/JSP.

## 📁 Estrutura de Pacotes
* `sistemapadaria.model`: Classes de entidade (POJOs).
* `sistemapadaria.dao`: Classes de acesso a dados e comandos SQL.
* `sistemapadaria.util`: Utilitários (Conexão com Banco de Dados).
* `sistemapadaria.teste`: Classe com método `main` para validação do sistema.

## 🔧 Como rodar o projeto
1. Clone este repositório.
2. Importe o projeto no **NetBeans**.
3. Certifique-se de que o driver do **MySQL Connector/J** está nas bibliotecas do projeto.
4. Execute o script SQL (localizado na pasta `sql` ou no seu Workbench) para criar o banco `sistemapadaria`.
5. Configure as credenciais de acesso em `sistemapadaria.util.Conexao`.
6. Execute a classe `TesteSistema.java` para validar a conexão.

---
✨ *Projeto desenvolvido por **Giselle Leão de Oliveira** como parte da formação técnica em Desenvolvimento de Sistemas.*
