# Cliente SOAP para NumberConversion (Java)

Este projeto mostra um cliente SOAP no estilo RPC para a operação `NumberToWords`.

## Executar

```bash
gradle run --no-daemon
```

Saída esperada no exemplo do tutorial:

```text
1001: one thousand one
```

## Arquivos principais

- `src/main/java/org/example/App.java`
- `src/main/java/org/example/NumberConversionSoapStub.java`
- `src/main/resources/wsdl/NumberConversion.wsdl`
