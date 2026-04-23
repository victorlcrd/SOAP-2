# Cliente SOAP para validação de ISBN (Java)

Este projeto implementa um cliente SOAP para o serviço de validação de ISBN citado no tutorial.

## Como funciona

- `App` representa a aplicação cliente.
- `IsbnSoapStub` representa o **stub do cliente**: encapsula envio/recebimento SOAP e expõe métodos locais:
  - `isValidIsbn10(String isbn)`
  - `isValidIsbn13(String isbn)`

Assim, o código cliente invoca métodos Java locais enquanto o stub cuida dos detalhes de rede e XML.

## Executando

```bash
./gradlew run
```

## Arquivos principais

- `src/main/java/org/example/App.java`
- `src/main/java/org/example/IsbnSoapStub.java`
- `src/main/resources/wsdl/ISBNService.wsdl`
