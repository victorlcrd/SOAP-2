package org.example;

public class App {
    public static void main(String[] args) {
        IsbnSoapStub isbnClient = new IsbnSoapStub();

        try {
            System.out.printf("ISBN-13 9780306406157 válido? %s%n", isbnClient.isValidIsbn13("9780306406157"));
            System.out.printf("ISBN-13 9780306406158 válido? %s%n", isbnClient.isValidIsbn13("9780306406158"));
            System.out.printf("ISBN-10 0306406152 válido? %s%n", isbnClient.isValidIsbn10("0306406152"));
            System.out.printf("ISBN-10 0306406153 válido? %s%n", isbnClient.isValidIsbn10("0306406153"));
        } catch (RuntimeException ex) {
            System.err.println("Falha ao acessar o serviço SOAP de ISBN.");
            System.err.println(ex.getMessage());
        }
    }
}
