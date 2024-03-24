Tema: API de registro de veículos.

docker run --name desafiodb -e MYSQL_ROOT_PASSWORD=pass -p 3306:3306 mysql:8.0.0

SOLID implementado nas operações de veiculos (checkup e autonomia)

INFRA AWS:
![VPJDJX~1](https://github.com/fabiofsilva92/tab-veiculos/assets/61157010/0f221291-a2e7-4b3b-9376-7b29fb43b1fe)


//TODO implementar script para subir aplicações

//TODO implementar ms para consumir topico kafka


AWS para Infraestrutura:

EC2: Hospedar a aplicação Java.
RDS: Gerenciar o banco de dados PostgreSQL ou MySQL. // AINDA A DECIDIR
Kafka: Envio dos arquivos por mensagem
Lambda: Funções Lambda para processar e exportar os dados alterados consumidos pelo kafka para o S3
S3: Armazenar arquivos exportados contendo dados modificados.



Collection POSTMAN:

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
					"raw": "{\r\n  \"tipo\": \"CARRO\",\r\n  \"veiculo\": \"veiculo_0f359f777829\",\r\n  \"marca\": \"Toyota\",\r\n  \"ano\": 2022,\r\n  \"descricao\": \"descricao_8ed39b0fd4c4\",\r\n  \"capacidadeTanque\": 50,\r\n  \"consumo\": 10,\r\n  \"autonomia\": 0.00,\r\n  \"checkup\": false\r\n}",
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
