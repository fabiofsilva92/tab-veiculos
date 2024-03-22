Tema: API de registro de veículos.

//TODO implementar features demonstrando principios SOLID

//TODO implementar simulação para a infraestrutura AWS

//TODO implementar script para subir aplicações

//TODO implementar ms para consumir topico kafka


AWS para Infraestrutura:

EC2: Hospedar a aplicação Java.
RDS: Gerenciar o banco de dados PostgreSQL ou MySQL. // AINDA A DECIDIR
S3: Armazenar arquivos exportados contendo dados modificados.
Lambda: Funções Lambda para processar e exportar os dados alterados para o S3, ativadas por eventos do Kafka/Kinesis.


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
            "name": "Post veiculos",
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
            "name": "all veiculos",
            "request": {
                "method": "GET",
                "header": []
            },
            "response": []
        },
        {
            "name": "veiculo by id",
            "request": {
                "method": "GET",
                "header": []
            },
            "response": []
        },
        {
            "name": "update veiculos",
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
            "name": "Patch veiculos",
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
            "name": "Delete veiculos",
            "request": {
                "method": "DELETE",
                "header": [],
                "url": {
                    "raw": "localhost:8080/veiculos/1",
                    "host": [
                        "localhost"
                    ],
                    "port": "8080",
                    "path": [
                        "veiculos",
                        "1"
                    ]
                }
            },
            "response": []
        },
        {
            "name": "Get veiculo by marca e ano",
            "request": {
                "method": "GET",
                "header": [],
                "url": {
                    "raw": "localhost:8080/veiculos?marca=&ano=2019",
                    "host": [
                        "localhost"
                    ],
                    "port": "8080",
                    "path": [
                        "veiculos"
                    ],
                    "query": [
                        {
                            "key": "marca",
                            "value": ""
                        },
                        {
                            "key": "ano",
                            "value": "2019"
                        }
                    ]
                }
            },
            "response": []
        },
        {
            "name": "Get qntd by marca",
            "request": {
                "method": "GET",
                "header": []
            },
            "response": []
        },
        {
            "name": "Get qntd by decada",
            "request": {
                "method": "GET",
                "header": [],
                "url": {
                    "raw": "localhost:8080/veiculos?ano=2012",
                    "host": [
                        "localhost"
                    ],
                    "port": "8080",
                    "path": [
                        "veiculos"
                    ],
                    "query": [
                        {
                            "key": "ano",
                            "value": "2012"
                        }
                    ]
                }
            },
            "response": []
        }
    ]
}
