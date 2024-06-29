# Cep Service

Bem-vindo ao repositório do projeto **CepService**! Este é um simples
projeto que tem a função de integrar uma API Spring com API's de CEP
utilizando comunicação síncrona com Open Feign, além de inserir recursos
de resiliência como Circuit Breaker em caso de falhas.

### Como funciona
Após cadastrar um usuário onde ele deve fornecer apenas seu nome e o seu 
CEP(zipcode), a API deve buscar em outros serviços informações complementares
a respeito do endereço do usuário, tais como: rua, bairro, cidade, estado.

Caso o primeiro serviço esteja indisponível ou apresente alguma falha, será
ativado o circuit breaker, e outro serviço de CEP será chamado.



## Tecnologias Utilizadas

- Java 17
- Spring (Web, JPA, Validation, AOP)
- Banco H2
- Lombok
- Resilience4j

### Serviços Externos Utilizados (API's)
- <a href="https://viacep.com.br">ViaCep</a>
- <a href="https://brasilapi.com.br/docs#tag/CEP">Brasil API</a>



## Configuração e Execução
Certifique-se de ter os seguintes softwares instalados em seu ambiente
de desenvolvimento:

- Java 17
- Maven
- Docker
- MySQL

### Clonando o Repositório

```bash
git clone https://github.com/uFelps/Cep-Service.git
cd cep-service
```

### Rodando o Docker

```bash
docker build -t cep-service .
docker run -p 8080:8080 cep-service


```

## Utilizando as API's

### 1º Passo

Voce pode começar criando um novo usuário apartir de uma Request POST 
na URL: `http://localhost:8080/users/createUser`

```bash
{
    "name": "John",
    "zipcode": "01310930",
    "number": "708"
}
```
Após isso, o API irá iniciar o processo de cadastro do novo usuário, onde irá procurar o
endereço do usuário com base no seu CEP.

Caso ocorra tudo OK, a resposta dessa request será parecida com a abaixo:
```bash
{
    "id": 1,
    "name": "John",
    "zipcode": "01310930",
    "state": "SP",
    "city": "São Paulo",
    "neighborhood": "Bela Vista",
    "address": "Avenida Paulista",
    "number": "708"
}
```

Caso ocorra algum erro no primeiro serviço(ViaCep), será acionado o circuit breaker e 
chamado o metodo de fallback, que irá tentar buscar o endereço do usuário atráves do
segundo serviço(Brasil API)

Caso não seja possível encontrar o CEP em ambos os serviços por falha das API's, ou por
CEP incorreto, será retornada a seguinte resposta:
```bash
{
    "timestamp": "2024-06-15T01:12:43.513135200Z",
    "status": 503,
    "error": "Cep Service not avaliable",
    "message": "Error On Brasil API. Check if your zipcode is correct",
    "path": "/users/createUser"
}
```
