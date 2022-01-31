# ws-sicredi-voting

ws-sicredi-voting é um projeto que provê microsserviços para o registro de questões a serem decididas por uma assembleia de membros de uma cooperativa, recebe, valida e processa os votos e gera o resultado das votações.

## Installation

Para disponibilizar os serviços utilizados (Redis, RabbitMQ e MongoDB) existe uma pasta "docker" e dentro dela um arquivo chamado "docker-compose.yml" (bem como duas pastas para armazenamento de dados)

Para subir os containers entre na pasta "docker" e execute

```bash
docker-compose up -d
```
