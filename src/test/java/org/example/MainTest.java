package org.example;

import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class MainTest extends TestCase {


    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();

    @Test
    public void testMainMethod() throws Exception {
        // Entrada simulada para o Scanner
        String simulatedInput = "https://es.wikipedia.org/wiki/Pir%C3%A1mides_de_Egipto\nbloques de piedra\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Simula a execução do método main
        exit.expectSystemExitWithStatus(0);

        // Salva as entradas e saídas padrão
        ByteArrayInputStream originalIn = (ByteArrayInputStream) System.in;
        PrintStream originalOut = System.out;

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            Main.main(null);

            // Verificações
            // Substitua essas verificações com base na saída real esperada
            assertTrue(outputStream.toString().contains("\"bloques de piedra\" -> repete 2 vezes"));
            assertTrue(outputStream.toString().contains("\"de\" -> repete 358 vezes"));
            assertTrue(outputStream.toString().contains("\"piedra\" -> repete 6 vezes"));
            assertTrue(outputStream.toString().contains("\"bloques\" -> repete 7 vezes"));

            assertFalse(systemErrRule.getLog().contains("Erro"));
        } finally {
            // Restaura as entradas e saídas padrão
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }


    @Test
    public void testUrlValidation() {
        assertTrue(Main.isValidUrl("https://es.wikipedia.org/wiki/Pir%C3%A1mides_de_Egipto"));
        assertFalse(Main.isValidUrl("https://es.wikipedia.org/wiki/Pir%C3%A1mides_de_Egipt"));
        assertFalse(Main.isValidUrl("ftp://example.com"));
        assertFalse(Main.isValidUrl("not a url"));
    }


    @Test
    public void testContarPalavras() {
        String texto = "Esta é uma frase de exemplo. Exemplo de frase.";

        // Testando a contagem de palavras
        Map<String, Integer> resultado = Main.contarPalavras("frase exemplo", texto.toLowerCase());
        assertEquals(2, resultado.size());
        assertEquals(2, resultado.get("frase").intValue());
        assertEquals(2, resultado.get("exemplo").intValue());

        // Testando quando a frase contém uma palavra que não está no texto
        resultado = Main.contarPalavras("frase2 inexistente", texto);

        assertEquals(2, resultado.size());
        assertEquals(0, resultado.get("frase2").intValue());
        assertEquals(0, resultado.get("inexistente").intValue());

        // Testando quando a frase é vazia
        resultado = Main.contarPalavras("", texto);
        assertEquals(0, resultado.size());
    }

    @Test
    public void testContarOcorrencias() {
        String texto = "Isso é um exemplo. Um exemplo é isso.";

        // Testando a contagem de ocorrências
        int resultado = Main.contarOcorrencias(texto.toLowerCase(), "exemplo");
        assertEquals(2, resultado);

        // Testando quando o alvo não está no texto
        resultado = Main.contarOcorrencias(texto, "inexistente");
        assertEquals(0, resultado);

        // Testando quando o alvo é uma substring de outra palavra
        resultado = Main.contarOcorrencias(texto, "ex");
        assertEquals(2, resultado);

        // Testando quando o texto e o alvo são vazios
        resultado = Main.contarOcorrencias("", "");
        assertEquals(0, resultado);
    }
}