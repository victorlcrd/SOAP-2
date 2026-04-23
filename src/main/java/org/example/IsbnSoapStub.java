package org.example;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Stub de cliente SOAP para o serviço ISBN.
 *
 * Encapsula os detalhes de montagem de envelope SOAP, envio HTTP e leitura da resposta,
 * expondo métodos locais equivalentes às operações remotas.
 */
public class IsbnSoapStub {
    private static final String ENDPOINT = "https://webservices.daehosting.com/services/isbnservice.wso";
    private static final String XML_NAMESPACE = "http://webservices.daehosting.com/ISBN";

    public boolean isValidIsbn10(String isbn) {
        return callBooleanOperation("IsValidISBN10", isbn, "IsValidISBN10Result");
    }

    public boolean isValidIsbn13(String isbn) {
        return callBooleanOperation("IsValidISBN13", isbn, "IsValidISBN13Result");
    }

    private boolean callBooleanOperation(String operation, String isbn, String responseTag) {
        try {
            String body = """
                    <?xml version=\"1.0\" encoding=\"utf-8\"?>
                    <soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"
                                   xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"
                                   xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">
                      <soap:Body>
                        <%s xmlns=\"%s\">
                          <sISBN>%s</sISBN>
                        </%s>
                      </soap:Body>
                    </soap:Envelope>
                    """.formatted(operation, XML_NAMESPACE, isbn, operation);

            HttpURLConnection connection = (HttpURLConnection) URI.create(ENDPOINT).toURL().openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            connection.setRequestProperty("SOAPAction", '"' + XML_NAMESPACE + "/" + operation + '"');

            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(body.getBytes(StandardCharsets.UTF_8));
            }

            int status = connection.getResponseCode();
            if (status < 200 || status >= 300) {
                throw new RuntimeException("HTTP " + status + " ao chamar operação " + operation);
            }

            try (InputStream responseStream = connection.getInputStream()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setNamespaceAware(true);
                Document document = factory.newDocumentBuilder().parse(responseStream);
                NodeList resultNodes = document.getElementsByTagNameNS(XML_NAMESPACE, responseTag);

                if (resultNodes.getLength() == 0) {
                    throw new RuntimeException("Tag de resposta não encontrada: " + responseTag);
                }

                return Boolean.parseBoolean(resultNodes.item(0).getTextContent());
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro na chamada SOAP (" + operation + "): " + ex.getMessage(), ex);
        }
    }
}
