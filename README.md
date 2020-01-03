# avaliacao-cast-backend

Api rest de cursos e categorias

Descrição Criar uma aplicação web integrando as tecnologias Java/Spring Boot (Backend) e Angular 6+ (Frontend), de forma que seja possível realizar operações de pesquisa, inclusão, alteração (opcional) e exclusão (opcional) (CRUD) de cursos para turmas de formação da Castgroup.

Tecnologias utilizadas

Linguagem Java
Banco de dados MySQL
Spring Boot
Spring WEB
Spring Data JPA
Angular
PrimeNG
HTML
CSS
Apache Tomcat
Maven
Script de criação de banco de dados e usuário

CREATE DATABASE avaliacao;
CREATE USER 'phcruz'@'localhost' IDENTIFIED BY 'phcruz';
GRANT ALL PRIVILEGES ON * . * TO 'phcruz'@'localhost';
Observações: Documento detalhado e explicativo de telas encontra-se na pasta "Docs". O script para a criação da base de dados, usuário e perfil de acesso/privilégios encontra-se na pasta "Scripts". A criação das tabelas é delegada a aplicação por meio do hibernate. A aplicação está configurada para utilização do banco de dados MySQL, configuração esta que encontra-se no arquivo application.properties do projeto. Caso essas linhas sejam comentadas, a aplicação utilizará por default o banco de dados em memória H2. As regras gerais do desafio encontra-se na pasta "Docs".

Para desenvolvimento foi utilizada a IDE STS, rodando a aplicação com o Tomcat embarcado do Spring Boot na porta padrão 8080. Os Mapeamentos são:

http://localhost:8080/categorias/ -> para Categorias
http://localhost:8080/cursos/ -> para Cursos
O projeto consiste na integração da API com a aplicação web que consome esta API e que encontra-se no endereço:

https://github.com/phcruz/avaliacao-cast-frontend
