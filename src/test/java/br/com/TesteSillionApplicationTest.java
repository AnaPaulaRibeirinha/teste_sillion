package br.com;

import br.com.testeSillion.service.UrlFilterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.HashMap;

class TesteSillionApplicationTest {

    @Mock
    UrlFilterService urlFilterService = new UrlFilterService();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnContent() throws Exception {
        Mockito.when(urlFilterService.searchContentFromUrl(Mockito.anyString())).thenReturn("resultado");

        String content = urlFilterService.searchContentFromUrl("busca");
        Assertions.assertEquals("resultado", content);

    }

    @Test
    void shouldReturnResultPhrase() throws Exception {
        Mockito.when(urlFilterService.searchContentFromUrl(Mockito.anyString())).thenReturn("resultado");
        Mockito.when(urlFilterService.countContentFromUrl(Mockito.anyString(), Mockito.anyString())).thenCallRealMethod();

        String content = urlFilterService.searchContentFromUrl("busca");
        String phrase = "resultado";
        int counterPhrases = urlFilterService.countContentFromUrl(content,phrase);
        Assertions.assertEquals("resultado", content);
        Assertions.assertEquals(1, counterPhrases);
    }

    @Test
    void shouldReturnEmptyResultPhrase() throws Exception {
        Mockito.when(urlFilterService.searchContentFromUrl(Mockito.anyString())).thenReturn("resultado");
        Mockito.when(urlFilterService.countContentFromUrl(Mockito.anyString(), Mockito.anyString())).thenCallRealMethod();

        String content = urlFilterService.searchContentFromUrl("busca");
        String phrase = "nenhum resultado";
        int counterPhrases = urlFilterService.countContentFromUrl(content,phrase);
        Assertions.assertEquals("resultado", content);
        Assertions.assertEquals(0, counterPhrases);
    }

    @Test
    void shouldReturnResultPhraseAndWords() throws Exception {
        Mockito.when(urlFilterService.searchContentFromUrl(Mockito.anyString())).thenReturn("resultado da pesquisa");
        Mockito.when(urlFilterService.countEachWordContentFromUrl(Mockito.any(), Mockito.anyString())).thenCallRealMethod();

        HashMap<String, Integer> wordCount = new HashMap<>();

        String content = urlFilterService.searchContentFromUrl("busca");
        String phrase = "resultado da pesquisa";
        String[] words = phrase.split(" ");

        wordCount = urlFilterService.countEachWordContentFromUrl(words, content);
        Assertions.assertEquals("resultado da pesquisa", content);
        Assertions.assertNotNull(wordCount);
    }


}
