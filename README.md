# ws-sicredi-voting

ws-sicredi-voting é um projeto que provê microsserviços para o registro de questões a serem decididas por uma assembleia de membros de uma cooperativa, recebe, valida e processa os votos e gera o resultado das votações.

# O projeto é composto por 5 microsserviços
- Issues - responsável pelo gerenciamento das pautas a serem votadas
- Votes - responsável por receber os votos e colocá-los na fila de processamento
- Votesregistration - responsável por processar os votos da fila, validando se a votação está aberta, se o membro votante está apto e se o mesmo já não votou anteriormente
- Memberswrapper - responsável pela consulta externa para a validação do membro
- Votescount - responsável pela totalização dos votos

![image](https://user-images.githubusercontent.com/11496647/151874042-24215bd8-a8f4-4271-ab0f-03c5aa934f50.png)

## Installation

Para disponibilizar os serviços utilizados (Redis, RabbitMQ e MongoDB) existe uma pasta "docker" e dentro dela um arquivo chamado "docker-compose.yml" (bem como duas pastas para armazenamento de dados)

Para subir os containers entre na pasta "docker" e execute

```bash
docker-compose up -d
```

## Documentação

\* A documentação do microsserviço de Issues pode ser acessada em http://localhost:8089/swagger-ui.html 
