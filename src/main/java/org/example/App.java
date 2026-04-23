package org.example;

public class App {
    public static void main(String[] args) {
        NumberConversionSoapStub client = new NumberConversionSoapStub();
        String result = client.numberToWords(1001);
        System.out.println("1001: " + result);
    }
}
