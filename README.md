# Documentação para o desafio técnico da empresa Elotech

### Objetivo:
Desenvolver uma aplicação de gestão de biblioteca que permita o cadastro de livros, usuários, empréstimos, devoluções e forneça recomendações de livros.
A aplicação deve utilizar um banco de dados relacional com relacionamento entre tabelas e uma API REST para as operações CRUD.
Há duas questões bônus: uma para integração com a API do Google Books e outra para o desenvolvimento de um frontend em Reacrt ou Angular.

## Baixe a imagem docker na versão 0.0.2 do projeto no link abaixo:
https://hub.docker.com/repository/docker/lucaspontesdev/biblioteca-elo/general

### Comandos:
Baixar a imagem:
```
docker pull lucaspontesdev/biblioteca-elo:0.0.2
```
Rodar a imagem: 
```
docker run -p 8080:8080 lucaspontesdev/biblioteca-elo:0.0.2
```
### [Swagger - doc dos controllers](http://localhost:8080/swagger-ui/index.html)