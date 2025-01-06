package br.com.testeSillion.main;

import br.com.testeSillion.service.UrlFilterService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TesteSillionApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UrlFilterService urlFilterService = new UrlFilterService();
        String url = "";
        String phrase = "";
        String[] words = null;
        String content = "";
        int counterPhrases = 0;

        HashMap<String, Integer> wordCount = new HashMap<>();

        try {
            System.out.println("Digite a URL:");
            url = scanner.nextLine();
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao tentar validar a URL digitada");
        }

        try {
            System.out.println("Digite a frase a ser buscada:");
            phrase = scanner.nextLine();
            words = phrase.split(" ");
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao tentar validar a frase digitada");
        }

        try {
            content = urlFilterService.searchContentFromUrl(url);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao tentar buscar a URL");
        }

        try {
            counterPhrases = urlFilterService.countContentFromUrl(content,phrase);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao tentar encontrar a contagem da frase inteira");
        }

        try {
            wordCount = urlFilterService.countEachWordContentFromUrl(words, content);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao tentar contar cada palavra");
        }

        try {
            urlFilterService.showResult(counterPhrases, phrase, wordCount);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao apresentar os resultados da pesquisa");
        }
    }
}
