package org.example;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String url;
        do {
            System.out.println("Insira o URL válido: ");
            url = scanner.nextLine();
        } while (!isValidUrl(url));

        System.out.println("Insira a frase para procurar: ");
        String frase = scanner.nextLine();

        Document document = Jsoup.connect(url).get();
        String text = document.body().text();

        int totalFrase = contarOcorrencias(text, frase);
        System.out.println("\"" + frase + "\" -> repete " + totalFrase + " vezes");


        Map<String, Integer> contagemPalavras = contarPalavras(frase, text);

        for (Map.Entry<String, Integer> entry : contagemPalavras.entrySet()) {
            System.out.println("\"" + entry.getKey() + "\" -> repete " + entry.getValue() + " vezes");
        }
    }

    protected static int contarOcorrencias(String texto, String alvo) {
        if (alvo == null || texto == null || alvo.isEmpty() || texto.isEmpty())
            return 0;

        int contador = 0;
        int index = texto.indexOf(alvo);

        while (index != -1) {
            contador++;
            index = texto.indexOf(alvo, index + 1);
        }

        return contador;
    }

    protected static Map<String, Integer> contarPalavras(String frase, String texto) {
        if (frase == null || texto == null || frase.isEmpty() || texto.isEmpty())
            return new HashMap<>();

        Map<String, Integer> contagem = new HashMap<>();
        String[] palavras = frase.split("\\s+");

        for (String palavra : palavras) {
            contagem.put(palavra, contarOcorrencias(texto, palavra));
        }

        return contagem;
    }

    public static boolean isValidUrl(String url) {
        try {
            Jsoup.connect(url).get();
            return true;
        } catch (Exception e) {
            System.out.println("URL inválida!");
            return false;
        }
    }
}