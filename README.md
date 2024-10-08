# Vinoteca Microservices Project

Este projeto contém diversos microsserviços para gerenciar pedidos, vinhos, notas fiscais, e mais. Todos os serviços estão configurados para serem executados em contêineres Docker utilizando o `docker-compose`.

## Pré-requisitos

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Passo a Passo para Iniciar o Projeto


### 1. Alterar permissões e executar o `build.sh`

Antes de iniciar o projeto, é necessário criar os pacotes JAR para cada microsserviço. Isso pode ser feito utilizando o script `build.sh`.

1. No terminal, navegue até a pasta raiz do projeto.
2. Altere as permissões do script `build.sh` para torná-lo executável:

   ```bash
   chmod +x build.sh
   ```

3. Execute o script para gerar os pacotes JAR dos microsserviços:

   ```bash
   ./build.sh
   ```


### 2. Iniciar o docker-compose
Depois de gerar os pacotes JAR, você pode subir todos os serviços utilizando o `docker-compose.yml`.

1. Execute o seguinte comando para subir os contêineres:

```bash
docker-compose up -d --build
```

Esse comando vai subir todos os serviços, incluindo os microsserviços, RabbitMQ, e MongoDB. Certifique-se de que o Docker está em execução no seu ambiente.


### 3. Verificar os Serviços

Depois de configurar o RabbitMQ, todos os serviços estarão rodando e se comunicando. Você pode acessar os endpoints dos microsserviços pelo gateway na porta 9999 (por exemplo, [http://localhost:9999/vinho](http://localhost:9999/vinho)).


### 4. Documentação e Swagger

A documentação foi gerada pelo Swagger e pode ser acessada em: [http://localhost:9999/docs](http://localhost:9999/docs)

### 5. Debug e Logs

Para visualizar os logs de execução dos serviços, utilize:

```bash
docker-compose logs -f
```

Este comando permite monitorar os logs e verificar qualquer erro ou comportamento inesperado.


## Conclusão

Com estas etapas, você terá todos os microsserviços rodando em contêineres Docker e comunicando-se entre si por meio do Eureka e RabbitMQ. Se tiver qualquer problema, revise as permissões, o build dos pacotes e a configuração dos serviços no RabbitMQ.
