package br.com.testeSillion.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class UrlFilterService {

    public UrlFilterService() {
    }

    public String searchContentFromUrl(String url) throws Exception {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (RuntimeException e) {
            throw new RuntimeException("Ocorreu um erro ao tentar buscar a URL");
        }

    }

    public int countContentFromUrl(String content, String target) throws Exception {

        try {
            content = content.toLowerCase();
            target = target.toLowerCase();

            int counter = 0;
            int index = content.indexOf(target);
            while (index != -1) {
                counter++;
                index = content.indexOf(target, index + target.length());
            }
            return counter;
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao tentar encontrar a contagem da frase inteira.");
        }
    }

    public HashMap<String, Integer> countEachWordContentFromUrl(String[] words, String content) throws Exception {
        HashMap<String, Integer> wordsCounts = new HashMap<>();
        try {
            if (!(words.length == 1)) {
                for (String word : words) {
                    wordsCounts.put(word, countContentFromUrl(content, word));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao tentar contar cada palavra.");
        }
        return wordsCounts;
    }

    public void showResult(Integer counterPhrases, String phrase, HashMap<String, Integer> wordCount ) {
        try {
            System.out.println("Resultados:");
            System.out.println(phrase + " => " + counterPhrases + " vezes");
            for (Map.Entry<String, Integer> e : wordCount.entrySet()) {
                System.out.println(e.getKey() + " => repete " + e.getValue() + " vezes");
            }
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao apresentar os resultados da pesquisa.");
        }
    }
}
