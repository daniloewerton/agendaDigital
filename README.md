<h1>Agenda Digital</h1>

<p>Projeto pessoal de uma agenda digital de contatos online, onde é possível através de usuários cadastrados, inserir contatos em suas agendas, e cada contato pode conter endereços e números de telefone.</p>

<p>API criada para estudo de boas práticas. Link do deploy: https://agendadigitaldw.herokuapp.com/</p>

<h1>Estrutura dos diretórios e classes</h1>

* Controllers: Endpoints dispóníveis para utilização das funcionalidades da API;

* DTO: Classes que representam o corpo de resposta das requisições;

* Entities: Classes de domínio que representam os objetos da aplicação e as tabelas e atributos no banco de dados;

* Exception: Tratamento de excessões das classes dos services;

* Repositories: Classes que conectam a aplicação ao banco de dados;

* Services: Classes que representam as regras de negócio da aplicação.

<h2>Pasta Resources</h2>

* application.properties: Arquivo base de propriedades do projeto.

* application-dev.properties: Arquivo com propriedades do ambiente de desenvolvimento, que é executado localmente com o SGBD Postgresql.

* application-prod.properties: Arquivo com propriedades do ambiente produção que seta variáveis ambientes de conexão com o servidor de hospedagem da API.

<h1>Endpoints Disponíveis</h1>

<h2>Usuários</h2>

<h3>Endereço Raíz: /usuario</h3>
<br>

* <code>/registrarUsuario - </code> Método POST: Cadastra um novo cliente usando os campos adequados conforme exemplo abaixo:

~~~
    {
        "nome": "Danilo Ewerton",
        "sobrenome": "Pereira Freire",
        "email": "daniloewerton@gmail.com",
        "senha": "senhasegura"
    }
~~~

* <code>/consultarUsuario - </code> Método GET: Lista todos os usuários cadastrados na aplicação.

* <code>/atualizarUsuario - </code> Método PUT: Atualiza os dados de um usuário previamente cadastrado informando seu número de ID:

~~~
	{
        "id": 1,
        "nome": "Danilo Ewerton",
        "sobrenome": "Pereira Freire",
        "email": "daniloewerton@gmail.com",
        "senha": "senhadificil123"
    }
~~~

* <code>/removerUsuario/{id}</code> - Método DELETE: Remove um usuário previamente cadastrado informando seu número de ID:

<h2>Contatos</h2>

<h3>Endereço Raíz: /contatos</h3>
<br>

* <code>/registrarContato</code> - Método POST: Cadastra um novo contato para um determinado usuário usando os campos adequados conforme exemplo abaixo:

~~~
    {
        "nome": "Mayara Silva",
        "sobrenome": "Oliveira",
        "email": "mayara@gmail.com",
        "id_usuario": 1
    }
~~~

* <code>/consultarContato/?id_usuario=numeroId</code> - Método GET: Consulta os contatos de um usuário informando o ID do usuário através da URL.

* <code>/atualizarContato/{id} - </code> Método PUT: Atualiza os dados de um contato previamente cadastrado informando os seus dados atualizados no corpo da requisição, e o ID do usuário através da URL.

~~~
    {
        "id": 1,
        "nome": "Lucas Costa",
        "sobrenome": "da Cunha",
		"email": "lucas@gmail.com"
    }
~~~

* <code>/removerContato/{id}</code> - Método DELETE: Remove um contato previamente cadastrado informando seu número de ID.

<h2>Endereços</h2>

<h3>Endereço Raíz: /endereco</h3>
<br>

* <code>/registrarEndereco/{id}</code> - Método POST: Cadastra um novo endereço para um determinado contato usando os campos adequados no corpo da requisição, e o ID do contato na URL, conforme exemplo abaixo:

~~~
    {
        "rua": "João Lucena Suassuna",
        "numero": "800",
        "complemento": "Próximo ao Instituto Federal",
        "bairro": "Centro",
        "cep": "53-789-180",
        "cidade": "Campina Grande",
        "uf": "PB"
    }
~~~ 

* <code>/consultaEndereco/{id}</code> - Método GET: Consulta endereço de um contato previamente cadastrado informando seu número de ID na URL.

* <code>/alterarEndereco/{id} - </code> Método PUT: Altera um endereço de um contato previamente cadastrado informando os dados do endereço no corpo da requisição, e o número de ID do contato na URL.

~~~
    {
        "id": 1,
        "rua": "Maria Alves dos Santos",
        "numero": "7856",
        "complemento": "Perto da praia das águas molhadas",
        "bairro": "Camboinhas",
        "cep": "567-029-000",
        "cidade": "João Pessoa",
        "uf": "PB"
    }
~~~

* <code>/removerEndereco/{id}</code> - Método DELETE: Remove um endereço previamente cadastrado informando seu número de ID.

<h2>Números</h2>

<h3>Endereço Raíz: /numeros</h3>
<br>

* <code>/registrarNumero/{id}</code> - Método POST: Cadastra um número para um contato previamente cadastrado informando o número do ID do contato na URL:

~~~
    {
        "numero": "083 9 8778-5245"
    }
~~~

* <code>consultaNumeros/?id=numeroId</code> - Método GET: Consulta os números cadastrados para um contato previamente cadastrado informando o número do ID do contato na URL da requisição.

* <code>/atualizarNumero/{id_c}</code> - Método PUT: Atualiza o número de um contato previamente cadastrado informando o número no corpo da requisição e o ID do contato na URL.

~~~
    {
        "id": 1,
        "numero": "083 9 9103-8153"
    }
~~~

* <code>/removerNumero</code> - Método DELETE: Remove um número de um contato previamente cadastrado informando o Id do número e do contato na URL da requisição.

~~~
    /removerNumero/?id_cont=1&id_n=10
~~~

<h1>Tecnologias utilizadas</h1>

* Java 11;

* Spring Boot 2.6.4;

* Spring Data JPA 2.6.4;

* Postgresql 14.

<h1>Autoria do projeto</h1>

Projeto desenvolvido por Danilo Ewerton - Linkedin (https://www.linkedin.com/in/daniloewerton/)
