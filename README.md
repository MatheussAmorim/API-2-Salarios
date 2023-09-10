# API-REST-Salarios
## **Descrição**

Este é um projeto de uma API desenvolvida em Groovy on Grails, que fornece endpoints para gerenciar reajustes salariais. A API é projetada para aceitar e retornar dados no formato JSON e utiliza o banco de dados oracle como armazenamento.

# **Pré-requisitos**
Antes de executar a API, certifique-se de ter o seguinte instalado em sua máquina:

* **[Grails](https://grails.org/download.html)** (versão 5.3.2 ou superior)
* **[Java Development Kit (JDK)](https://www.oracle.com/br/java/technologies/javase/javase8-archive-downloads.html)** (versão 8 ou 11)

Set as variáveis de ambiente`GRAILS_HOME` apontando para o diretório de instalação do Grails.

Adicione `%GRAILS_HOME%\bin;%JAVA_HOME%\bin` ao final da variável PATH.
# Execução

Para executar a API localmente, use o seguinte comando pelo Prompt no caminho do projeto:

`grails run-app`

A API estará disponível em http://localhost:8080/API2.


# Endpoints

A API possui três domínios: Cidade, funcionario e ReajusteSalarial. Ambos possuem os mesmos endpoints configurados conforme abaixo.

- `GET API2/$controller/list` - Retorna a lista dos registros.
- `GET API2/$controller/{$id}` - Retorna os detalhes de um controller específico.
- `POST API2/$controller/save` - Cria um novo registro.
- `PUT API2/$controller/update{$id}` -  Atualiza os detalhes de um registro existente.
- `DELETE API2/$controller/{$id}` - Remove um registro.
# Armazenamento
Os dados são salvos no banco Oracle, dessa forma, criando a estrutura de tabelas quando iniciar a aplicação e excluíndo quando finaliza-la.

Configuração com o banco é configurada no `application.yml` conforme abaixo.


```
 environments:
    development:    
        dataSource:        
            driverClassName: 'oracle.jdbc.OracleDriver'
            username: 'ALUNO11'
            password: 'ALUNO11'
            dbCreate: create-drop
            url: "jdbc:oracle:thin:@insoft-lnx4.insoft.local:1521:desenv01"
```
