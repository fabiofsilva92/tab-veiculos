# Desafio API

O projeto é parte do desafio  tema livre do Itau, é uma aplicação Java que implementa uma API REST para gerenciamento de veículos e exportação de dados modificados no banco de dados para o Amazon S3. Este projeto utiliza Java 17 com Spring Boot para a lógica da aplicação, Docker para containerização (localmente), e integra-se com AWS SNS, SQS e S3 para processamento e armazenamento de eventos de dados.

## Recursos

- API REST desenvolvida com Spring Boot.
- Banco de dados MySQL rodando em um container Docker.
- Integração com AWS SNS e SQS para gerenciamento de eventos de dados.
- Exportação de dados modificados para o Amazon S3.
- Containerização da aplicação com Docker.

## Pré-requisitos

- [Docker](https://www.docker.com/products/docker-desktop) 

  OU

- IDE com Java 17 + Mysql

  

Será demonstrado na apresentação o passo com o requisito abaixo.

- Acesso configurado à AWS para SNS, SQS e S3.

## Configuração do Ambiente

Antes de iniciar, certifique-se de que possui docker inicializado em sua máquina, ou uma instancia mysql rodando na porta 3306.

## Instruções de Inicialização(Para testes do avaliador)

### Opção 1 - Construção e Execução com Docker através do script

1. Clone o repositório e acesse o diretório do projeto:

   ```bash
   git clone <url-do-repositorio>
   ```

2. Certifique que seu Docker esteja rodando normalmente na maquina e inicie o script disponibilizado através do seu terminal: 

   ```bash
   ./build_and_run.sh
   ```

   

O script entrará em na pasta de cada microsserviço gerará as imagens através do Dockerfile e depois usará o docker-compose para subir a aplicação com todas configurações necessárias.



### Opção 2 - Construção e Execução com Docker manualmente

1. Clone o repositório e acesse o diretório do projeto:

   ```bash
   git clone <url-do-repositorio>
   ```

2. Certifique que seu Docker esteja rodando normalmente na maquina mova para a pasta da aplicação da api e construa a partir do DockerFile: 

   ```bash
   cd desafio
   docker build -t desafio-api .
   ```

3. Volte para a pasta anterior e entre na pasta da aplicação listener e construa a partir do DockerFile: 

   ```bash
   cd ..
   cd db-listener-file-creation
   docker build -t db-listener .
   ```

4. Volte para a pasta anterior e execute o docker-compose: 

   ```bash
   cd ..
   docker-compose up
   ```



### Opção 3 - Rodar manualmente através de uma IDE

1. Clone o repositório e acesse o diretório do projeto:

   ```bash
   git clone <url-do-repositorio>
   ```

2. Suba uma instancia de banco de dados MySql na porta 3306 através do seguinte comando docker: 

   ```bash
    docker run --name desafiodb -e MYSQL_ROOT_PASSWORD=pass -p 3306:3306 mysql:8.0.0
   ```

3. Após a instancia subir, abra a aplicação desafio em sua IDE de preferencia e inicie o programa: 

   ```bash
   Ao iniciar desafio-api, a configuração padrão ja se conectará com o banco de dados e incluirá alguns dados para teste através do flyway, além das triggers e tabelas necessárias.
   A aplicação estará disponível na porta 8080
   ```

4. Após o inicio da API, inicie a aplicação db-listener-file-creation em sua IDE de preferencia: 

   ```bash
   Essa aplicação iniciará na porta 8081, com um agendador para verificar as mudanças no banco de dados da API.
   Toda mudança  no banco será registrada em um arquivo json que ficará disponível na pasta C:/tmp
   ```

   

## Instruções de Uso(Para testes do avaliador)

Após o inicio a aplicação estará disponível para testes, vale ressaltar alguns requisitos para evitar erros.

- Para a inclusão de novos veículos, temos um validator para a marca portanto deve-se atentar as marcas aceitas (não é case sensitive):

  - ```
    "AUDI", "BMW", "CHEVROLET", "FORD", "HONDA", "TOYOTA"
    ```

- Para a inclusão de novos veículos, temos um enum que controla os tipos permitidos (não é case sensitive):

    - ```
      "CARRO", "MOTO", "ONIBUS", "CAMINHAO"
      ```

- Ao atualizar, atualizar parcialmente, inserir ou deletar um recurso, os registros modificados serão salvos em um arquivo localizado localmente no C:/tmp, ja no teste da apresentação será incluído no bucket S3 da AWS.
- Temos duas operações distintas que fazem comportamentos diferentes para cada tipo de veículo, sendo essas as operações /checkup e /autonomia, tais endpoints foram criados com o intuito de demonstrar conceitos de SOLID e alterar dois campos distintos do Objeto Veiculo.



## Operações 

### Endpoints da API

| Método | Endpoint              | Descrição                                            |
| ------ | --------------------- | ---------------------------------------------------- |
| GET    | `/veiculos`           | Lista todos os veículos cadastrados.                 |
| GET    | `/veiculos/{id}`      | Obtém os detalhes de um veículo específico por ID.   |
| POST   | `/veiculos`           | Salva um novo veículo no sistema.                    |
| PUT    | `/veiculos/{id}`      | Atualiza os dados de um veículo existente por ID.    |
| PATCH  | `/veiculos/{id}`      | Atualiza parcialmente os dados de um veículo por ID. |
| DELETE | `/veiculos/{id}`      | Remove um veículo do sistema por ID.                 |
| POST   | `/veiculos/checkup`   | Realiza um checkup no veículo especificado por ID.   |
| POST   | `/veiculos/autonomia` | Calcula a autonomia do veículo especificado por ID.  |



## Documentação da API

[Swagger UI](http://localhost:8080/swagger-ui.html) está disponível para explorar e testar a API RESTful.

## Arquitetura

**Backend em Java**: Spring Boot para estruturar sua aplicação, facilitando a implementação dos endpoints do CRUD e a integração com o banco de dados.

- Microsserviço 1 - API Rest dos veículos
- Microsserviço 2 - Aplicação que verifica as mudanças do banco de dados e envia para o tópico SNS da AWS

**Banco de Dados**: MySQL para armazenamento de dados. JPA (Java Persistence API) para a abstração e gestão do banco de dados.

**Detecção de Mudanças no Banco de Dados**: Triggers no banco de dados para identificar alterações. Combinação de eventos de aplicação no Spring Boot para capturar esses triggers e processar as mudanças.

**Encaminhamento de Mensagens via SNS para SQS**: As mensagens publicadas no tópico SNS são automaticamente encaminhadas para as filas SQS configuradas como assinantes desse tópico.

**Consumo pelo Lambda e Armazenamento no S3**: As mensagens na fila SQS disparam eventos que são consumidos pela função Lambda. A função processa essas mensagens e salva os dados no Amazon S3.

**Monitoramento com CloudWatch**: O Lambda registra atividades e potenciais alertas no AWS CloudWatch para monitoramento.

### Desenho da Solução

![Desenho da solução](https://github.com/fabiofsilva92/tab-veiculos/assets/61157010/6b15145f-cc90-4f0f-8a9a-36bb6f1e960c)


### Diagrama de Sequência


![sequencia](https://github.com/fabiofsilva92/tab-veiculos/assets/61157010/00ab5812-b18b-414b-b266-7e04bafa4a0b)



## Collection Postman



```bash
{
	"info": {
		"_postman_id": "5adbfa7c-4888-49fe-a521-e3ecac958e10",
		"name": "tab-veiculos",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "32401837"
	},
	"item": [
		{
			"name": "Incluir Veículo",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n  \"tipo\": \"caminhao\",\r\n  \"veiculo\": \"deu certo?\",\r\n  \"marca\": \"Toyota\",\r\n  \"ano\": 2022,\r\n  \"descricao\": \"descricao_8ed39b0fd4c4\",\r\n  \"capacidadeTanque\": 50,\r\n  \"consumo\": 10,\r\n  \"autonomia\": 0.00,\r\n  \"checkup\": false\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "localhost:8080/veiculos",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"veiculos"
					]
				}
			},
			"response": []
		},
		{
			"name": "Realizar Checkup de Veiculo",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n  \"veiculo\": \"veiculo 11518\",\r\n  \"marca\": \"Volks\",\r\n  \"ano\": 2018,\r\n  \"descricao\": \"sdfdsf\",\r\n  \"vendido\": false\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "localhost:8080/veiculos/checkup?id=1",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"veiculos",
						"checkup"
					],
					"query": [
						{
							"key": "id",
							"value": "1"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "Calcular Autonomia de Veiculo",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n  \"veiculo\": \"veiculo 11518\",\r\n  \"marca\": \"Volks\",\r\n  \"ano\": 2018,\r\n  \"descricao\": \"sdfdsf\",\r\n  \"vendido\": false\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "localhost:8080/veiculos/autonomia?id=1",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"veiculos",
						"autonomia"
					],
					"query": [
						{
							"key": "id",
							"value": "1"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "Receber todos veículos do db",
			"request": {
				"method": "GET",
				"header": []
			},
			"response": []
		},
		{
			"name": "Buscar Veiculo por ID",
			"request": {
				"method": "GET",
				"header": []
			},
			"response": []
		},
		{
			"name": "Atualizar Veiculo",
			"request": {
				"method": "PUT",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n  \"veiculo\": \"veiculo_a2df9165dc84\",\r\n  \"marca\": \"marca_9ab5c148847a\",\r\n  \"ano\": 1992,\r\n  \"descricao\": \"UPDATE descricao_3ba51c858558\",\r\n  \"vendido\": false\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "localhost:8080/veiculos/4",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"veiculos",
						"4"
					]
				}
			},
			"response": []
		},
		{
			"name": "Atualizar parcialmente Veiculo",
			"request": {
				"method": "PATCH",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n  \"ano\": 1995\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "localhost:8080/veiculos/4",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"veiculos",
						"4"
					]
				}
			},
			"response": []
		},
		{
			"name": "Deletar Veiculo",
			"request": {
				"method": "DELETE",
				"header": [],
				"url": {
					"raw": "localhost:8080/veiculos/2",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"veiculos",
						"2"
					]
				}
			},
			"response": []
		}
	]
}
```





## Contato

Fábio Fernandes da Silva - [fabiofsilva92@gmail.com](mailto:fabiofsilva92@gmail.com)
