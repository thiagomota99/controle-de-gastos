# controle-de-gastos
Projeto pessoal para cadastro e controle de gastos mensais. <hr>

## Motiva��o
Aplica��o de conhecimentos em POO, conex�o com banco de dados MySQL utilizando a biblioteca padr�o Java de acesso a dados (JDBC).

## Como executar
1. Instale [MySQL](https://downloads.mysql.com/archives/installer/) vers�o **8.0.28**
2. Importe no MySQL o script **create_db.sql**
3. Altere no arquivo **db.properties** as informa��es referentes ao seu servidor MySQL (Porta,usu�rio e senha)
4. Utilize o pacote *br.com.thgyb.test* para realizar as opera��es de CRUD das entidades **Categoria** e **Despesa**

## Pacotes

* **br.com.tgyn.conexao**</br>
Disponibiliza��o, controle e fechamento de conex�es com o banco de dados.

* **br.com.thgyn.dao**</br>
Defini��es e implementa��es de interfaces com m�todos para realiza��o de opera��es CRUD.

* **br.com.thgyn.enums**</br>
Enums utilizados na aplica��o.

* **br.com.thgyn.exceptions**</br>
Exce��es personalizadas da aplica��o.

* **br.com.thgyn.modelo.entidades**</br>
Entidades da aplica��o.

* **br.com.thgyn.modelo.services.impl**</br>
Implementa��es das interfaces de servi�o da aplica��o.

* **br.com.thgyn.modelo.services**</br>
Interfaces para implementa��o de servi�os.

* **br.com.thgyn.test**</br>
Implementa��o dos testes das opera��es CRUD.

* **br.com.thgyn.utils**</br>
Classes utilit�rias.

* **br.com.thgyn.validadores**</br>
Defini��o de interfaces de valida��o de objetos.

* **br.com.thgyn.validadores.impl**</br>
Implementa��o das interfaces de valida��o de objetos.

## Outros Arquivos..
* **db.properties**: Respons�vel por armazenar propriedades para a conex�o com o banco de dados MySQL
* **create_db.sql**: Script para cria��o do banco de dados da aplica��o MySQL.