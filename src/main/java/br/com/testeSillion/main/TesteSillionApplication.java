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

        while(true) {
            System.out.println("Digite a URL:");
            url = scanner.nextLine().trim();
            if(!url.isEmpty()) {
                break;
            } else {
                System.out.println("A url não pode ser vazia.");
            }
        }

        while (true) {
            System.out.println("Digite a frase a ser buscada:");
            phrase = scanner.nextLine().trim();
            if(!phrase.isEmpty()) {
                break;
            } else {
                System.out.println("A frase não pode ser vazia.");
            }
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
