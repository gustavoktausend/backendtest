<h1>Teste para desenvolvedor(a) backend</h1>
<b>Data de versão do teste: 28/08/2020</b>

<h2>Introdução</h2>

<p>Este teste é composto pela construção de um backend REST simples, composto por dois endpoints.</p>

<h2>Tecnologia</h2>

<p>O teste deve ser desenvolvido em <b>Spring Boot 2.3.3</b>, em projeto padrão Maven. 
Sugestão: criar utilizando https://start.spring.io/ </p>

<h2>Modelo de dados</h2>

<p>Persistidos:</p>
<p>Pais (id, nome, sigla, gentilico)</p>
<p>Token (id, token, login, expiracao (timestamp), administrador (boolean) )</p>
<p>Usuario (id, login, senha, nome, administrador (boolean))</p>

<p>Para resposta de requisições apenas (em memória):</p>
<p>UsuarioAutenticado (login, nome, token, administrador (boolean), autenticado (boolean))</p>

<h2>Endpoints</h2>

<p><b>/usuario</b> - ações relativa a objetos Usuario</p>
Ação
Descrição
Método
Parâmetros
Retorno
/autenticar
Autentica o usuário - consulta a tabela de usuários por um registro com os dados passados. 
POST
login - login do usuário
senha - senha do usuário
Objeto UsuarioAutenticado indicando se o usuário foi autenticado, e em caso positivo, com o token gerado na autenticação, incluindo também a informação se o usuário é administrador.
/renovar-ticket
Renova um ticket gerado anteriormente
GET
token- token de autenticação gerado anteriormente
true se reautenticou o token, false se não conseguiu reautenticar (token não existe, por exemplo)


/pais - ações relativas a objetos Pais
Ação
Descrição
Método
Parâmetros
Retorno
/listar
Lista os países cadastrados
GET
token - token de autenticação gerado anteriormente
Lista dos países ou HTTP_ERROR 401 se não autenticado
/salvar
Inclui/altera um país cadastrado. Se recebe um Pais com id 0, inclui um novo país, caso contrário altera o país com id informado
POST
token- token de autenticação gerado anteriormente, passado na URL
corpo da requisição (body em formato JSON) - entidade Pais
O objeto pais salvo atualizado ou HTTP_ERROR 401 se não autenticado ou o usuário não é administrador
/pesquisar
Retorna os países cujo nome contém o texto informado (case insensitive)
GET
token - token de autenticação gerado anteriormente
filtro - texto a ser considerado para o filtro
Lista dos países ou HTTP_ERROR 401 se não autenticado
/excluir
Remove o pais de id informado
GET
token - token de autenticação gerado anteriormente
id - id do país a excluir
true se excluiu, false se não encontrou ou HTTP_ERROR 401 se não autenticado ou o usuário não é administrador

A fim de facilitar o entendimento, um exemplo de implementação do backend está disponível em: https://mng-teste-backend.herokuapp.com/swagger-ui.html


Dados iniciais

O backend deve vir com os seguintes dados iniciais ao ser carregado:

insert into usuario(id,login,senha,nome,administrador) values (SEQ_USUARIO.NEXTVAL, 'convidado', 'manager', 'Usuário convidado', 0);
insert into usuario(id,login,senha,nome,administrador) values (SEQ_USUARIO.NEXTVAL, 'admin', 'suporte', 'Gestor', 1);
insert into pais(id,nome,sigla,gentilico) values (SEQ_PAIS.NEXTVAL, 'Brasil', 'BR', 'Brasileiro');
insert into pais(id,nome,sigla,gentilico) values (SEQ_PAIS.NEXTVAL, 'Argentina', 'AR', 'Argentino');
insert into pais(id,nome,sigla,gentilico) values (SEQ_PAIS.NEXTVAL, 'Alemanha', 'AL', 'Alemão');

Os tipos/nomes de campos são meramente ilustrativos. Não é obrigatório que a inserção seja via SQL.


Funcionamento da autenticação e tokens

Ao solicitar a autenticação do usuário, para gerar um token, o sistema deve:
    1. Conferir login/senha
    2. Gerar objeto Token, com um valor de token gerado de alguma forma dinâmica, que não seja previsível
    3. Persistir o objeto Token
    4. O token deve expirar em 5 minutos a partir da sua geração

Ao solicitar a renovação de um token:
    1. O sistema deve validar a existência do token
    2. O sistema deve atualizar a expiração do mesmo para 5 minutos no futuro

Infraestrutura

Utilizar Swagger para publicar a interface rest e disponibilizar navegação via Swagger-UI (tal como exemplo publicado).
Utilizar banco de dados H2 ou HQSQL.
Persistência via JPA.


Bibliografia sugerida
    • https://projectlombok.org/
    • https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api


Apresentação do resultado

Os fontes da aplicação desenvolvida devem ser disponibilizados em github/bitbucket ou outro serviço de versionamento, tagged como versao_1_0_0.
