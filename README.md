# controle-de-gastos
Projeto pessoal para cadastro e controle de gastos mensais. <hr>

## Motivação
Aplicação de conhecimentos em POO, conexão com banco de dados MySQL utilizando a biblioteca padrão Java de acesso a dados (JDBC).

## Como executar
1. Instale [MySQL](https://downloads.mysql.com/archives/installer/) versão **8.0.28**
2. Importe no MySQL o script **create_db.sql**
3. Altere no arquivo **db.properties** as informações referentes ao seu servidor MySQL (Porta,usuário e senha)
4. Utilize o pacote *br.com.thgyb.test* para realizar as operações de CRUD das entidades **Categoria** e **Despesa**

## Pacotes

* **br.com.tgyn.conexao**</br>
Disponibilização, controle e fechamento de conexões com o banco de dados.

* **br.com.thgyn.dao**</br>
Definições e implementações de interfaces com métodos para realização de operações CRUD.

* **br.com.thgyn.enums**</br>
Enums utilizados na aplicação.

* **br.com.thgyn.exceptions**</br>
Exceções personalizadas da aplicação.

* **br.com.thgyn.modelo.entidades**</br>
Entidades da aplicação.

* **br.com.thgyn.modelo.services.impl**</br>
Implementações das interfaces de serviço da aplicação.

* **br.com.thgyn.modelo.services**</br>
Interfaces para implementação de serviços.

* **br.com.thgyn.test**</br>
Implementação dos testes das operações CRUD.

* **br.com.thgyn.utils**</br>
Classes utilitárias.

* **br.com.thgyn.validadores**</br>
Definição de interfaces de validação de objetos.

* **br.com.thgyn.validadores.impl**</br>
Implementação das interfaces de validação de objetos.

## Outros Arquivos..
* **db.properties**: Responsável por armazenar propriedades para a conexão com o banco de dados MySQL
* **create_db.sql**: Script para criação do banco de dados da aplicação MySQL.