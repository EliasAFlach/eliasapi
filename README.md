Relatório da Feature 1: Microsserviço de Interesses
a) Apresentação da Funcionalidade
A funcionalidade escolhida para a expansão do projeto foi a criação de um Sistema de Fila de Interesse, desenvolvido como um microsserviço independente (interesse-service).

Lógica e Integração: Em um sistema de adoção, muitos adotantes em potencial procuram por animais com características específicas (espécie, porte, sexo, idade). Este novo microsserviço permite que os usuários registrem esses interesses de forma persistente. Quando o serviço principal (adocao-service) cadastra um novo animal, ele notifica o interesse-service, que por sua vez executa uma lógica para encontrar todos os usuários cujas preferências são compatíveis ("match") com o novo animal. Isso cria uma ponte proativa entre os animais que chegam ao abrigo e as famílias que os esperam, otimizando o processo de adoção.

b) Principais Classes Implementadas
A arquitetura desta nova funcionalidade foi centrada em um novo microsserviço, com as seguintes classes principais:

Interesse.java (Modelo): A nova entidade JPA que representa o perfil de interesse de um usuário. Ela armazena critérios como especie, porte, sexo e idadeMaximaEmMeses, além de um pessoaId para vincular ao usuário do serviço principal.

FilaDeInteresseService.java (Serviço): O coração da lógica de negócio. Esta classe é responsável por registrar novos interesses e, principalmente, por conter o método buscarInteressesCompativeis(AnimalDTO animal), que foi inteiramente desenvolvido seguindo o ciclo TDD (Test-Driven Development).

EventoController.java (Controller): Atua como a porta de entrada para a comunicação entre os microsserviços. Ele expõe um endpoint que "ouve" os eventos de "novo animal cadastrado" enviados pelo adocao-service.

c) Cenários de Teste Unitário (TDD)
O desenvolvimento do FilaDeInteresseService foi guiado pela metodologia TDD. Abaixo estão descritos 4 dos cenários de teste implementados, ilustrando o ciclo Red-Green-Refactor.

buscarInteressesCompativeis_CenarioSemInteresses: Deve retornar lista vazia quando não há interesses ativos

Objetivo do Teste: Garantir que o método de busca retorne uma lista vazia de forma segura, sem erros, quando não há nenhum interesse ativo no banco de dados. Este foi o primeiro teste criado (fase RED), que nos forçou a criar o método com o retorno mais simples possível para fazê-lo passar (fase GREEN).
buscarInteressesCompativeis_CenarioComMatchDePorte: Deve encontrar interesse compatível com a espécie e o porte do AnimalDTO

Objetivo do Teste: Validar o cenário de sucesso principal. O teste cria um Interesse com critérios específicos e um AnimalDTO compatível. Ele falhou inicialmente (fase RED), o que guiou a implementação da lógica principal de filtros no serviço para que o teste passasse (fase GREEN).
buscarInteressesCompativeis_CenarioSemMatchDeEspecie: Não deve encontrar interesse quando a espécie do animal for diferente

Objetivo do Teste: Garantir a precisão dos filtros, testando um cenário negativo. O teste verifica se um interesse por um gato não é retornado ao buscar por um cachorro. Isso ajuda a refinar a lógica de negócio (parte do ciclo REFACTOR) e garantir que apenas "matches" corretos sejam retornados.
registrarInteresse_DeveLancarExcecao_QuandoInteresseNulo: Deve lançar exceção ao tentar registrar um interesse nulo

Objetivo do Teste: Validar o comportamento do sistema em cenários de erro. Utilizando assertThrows, o teste assegura que o método registrarInteresse lança a exceção IllegalArgumentException corretamente ao receber dados inválidos (nulos), confirmando a robustez do serviço.
Relatório da Feature 3: Evoluindo a Arquitetura do Projeto
Nesta fase do projeto, meu foco foi dar um grande passo na organização e na segurança da minha API de adoção. Em vez de apenas adicionar código, eu refatorei a estrutura para torná-la mais profissional, modular e escalável.

1. Organizando a Casa: A Arquitetura em Módulos
Conforme o projeto crescia, percebi que misturar tudo em um lugar só (lógica de negócio, acesso a dados, comunicação externa) estava deixando o código confuso e difícil de manter. Para resolver isso, "quebrei" a aplicação em três módulos principais, como se fossem gavetas especializadas de uma cômoda:

common-domain (O Dicionário do Projeto): Esta é a gaveta mais importante. Aqui guardei o "coração" do meu negócio: as entidades JPA (Pessoa, Animal, Adocao, etc.) e os Enums. Elas são puras, contendo apenas a estrutura dos dados, sem nenhuma "gordura" de validação ou de API.

external-api-client (O Diplomata): A única função deste módulo é conversar com o mundo exterior. Ele contém meus clientes Feign (como o que consulta o interesse-service) e os DTOs necessários para entender as respostas dessas APIs. Se eu precisar mudar de um serviço de CEP para outro no futuro, só preciso mexer nesta gaveta.

main-application (O Cérebro): Este é o módulo principal, o único que de fato é uma aplicação executável. Ele contém toda a minha lógica de negócio (Services), os Controllers que expõem minha API, e os Repositories que falam com o banco. Ele usa as "peças" dos outros dois módulos para fazer o trabalho pesado.

2. Criando uma Fronteira Segura com DTOs
Para proteger meu sistema e criar um "contrato" claro com quem usa minha API, implementei o padrão DTO (Data Transfer Object) de forma rigorosa.

Request DTOs (O Segurança na Porta): Pense nos Request DTOs (ex: PessoaRequest) como o segurança na porta da festa. Eles recebem os dados que vêm de fora e são os únicos que fazem a validação (@NotBlank, @CPF, etc.). Se os dados não estiverem no formato correto, eles nem entram. Isso impede que dados "sujos" cheguem até o meu modelo de negócio.

Response DTOs (O Cartão de Visita): Já os Response DTOs (ex: PessoaResponse) são o "cartão de visita" que a minha API entrega. Eu decidi exatamente quais informações mostrar. Por exemplo, na resposta de um funcionário, nunca envio de volta a senha ou o CPF. Isso me dá total controle sobre os dados que saem, garantindo segurança e clareza para o cliente.

3. Juntando as Peças: O Fluxo de Dados e o Enriquecimento
Com as responsabilidades bem divididas, o fluxo de uma requisição ficou muito mais organizado.

Os Mappers (Os Tradutores): Para fazer a "tradução" entre os DTOs da fronteira e as minhas entidades internas, criei classes Mapper dedicadas (ex: PessoaMapper). Elas são as tradutoras oficiais do sistema, convertendo um PessoaRequest em uma Pessoa, e uma Pessoa em um PessoaResponse.

Orquestração em Ação (Exemplo do CEP): O melhor exemplo de como tudo isso funciona junto é o cadastro de uma Pessoa. O fluxo é o seguinte:

O Controller recebe um PessoaRequest só com os dados básicos e um CEP.
Ele passa a bola para o PessoaService.
O PessoaService (o meu "maestro") pega o CEP e usa o external-api-client para buscar o endereço completo na API do ViaCEP (através do outro microsserviço).
Com o endereço em mãos, ele "enriquece" os dados da pessoa, monta o objeto Pessoa completo e o salva no banco.
O serviço devolve a entidade salva para o Controller.
O Controller, por fim, usa o Mapper para criar um PessoaResponse bonito e completo, e o envia de volta para o cliente.
No final, o cliente da API pediu algo simples (um cadastro com CEP) e recebeu de volta uma resposta completa e inteligente, sem nunca saber da complexidade e da comunicação entre serviços que aconteceram nos bastidores.

4. Estratégia de Segurança (Feature 4)
Para garantir a segurança da API, foi implementado o Spring Security com autenticação HTTP Basic e autorização baseada em roles. A aplicação é stateless, não utilizando sessões.

Estrutura de Roles
Foram definidas duas roles (papéis) de usuário:

ROLE_USER: Um usuário padrão com permissões de acesso limitadas.
ROLE_ADMIN: Um administrador com acesso total às funcionalidades do sistema.
Regras de Autorização
As permissões de acesso aos endpoints foram configuradas de forma granular, aplicando o Princípio do Menor Privilégio:

Método HTTP	Padrão da URL	Permissão Exigida
GET	/api/v1/**	ROLE_USER ou ROLE_ADMIN
POST	/api/v1/**	ROLE_ADMIN
PUT	/api/v1/**	ROLE_ADMIN
PATCH	/api/v1/**	ROLE_ADMIN
DELETE	/api/v1/**	ROLE_ADMIN
Essa configuração garante que usuários comuns (USER) possam apenas visualizar os dados, enquanto apenas administradores (ADMIN) possam criar, modificar ou excluir recursos.

Princípios de Segurança Aplicados
Princípio do Menor Privilégio: Justificado pela restrição de acesso da role USER apenas a operações de leitura (GET), garantindo que eles tenham somente as permissões estritamente necessárias.
Segurança por Padrão: A regra anyRequest().authenticated() garante que qualquer endpoint futuro que não seja explicitamente liberado ou configurado será bloqueado por padrão, exigindo no mínimo um usuário autenticado.
