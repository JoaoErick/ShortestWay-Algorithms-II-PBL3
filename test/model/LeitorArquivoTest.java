/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: João Erick Barbosa Teixeira Da Silva, João Samuel Vilas Boas Góes
 * Data:  15/09/2019
 *
 * Declaro que este código foi elaborado por mim de forma individual e
 * não contém nenhum trecho de código de outro colega ou de outro autor, 
 * tais como provindos de livros e apostilas, e páginas ou documentos 
 * eletrônicos da Internet. Qualquer trecho de código de outra autoria que
 * uma citação para o  não a minha está destacado com  autor e a fonte do
 * código, e estou ciente que estes trechos não serão considerados para fins
 * de avaliação. Alguns trechos do código podem coincidir com de outros
 * colegas pois estes foram discutidos em sessões tutorias.
 */
package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import util.DuplicateException;
import util.LeitorException;


public class LeitorArquivoTest {
        private LeitorArquivo leitor;
        private Grafo grafo;
    
    /**
     * Método que é executado primeiramente para inicializar os objetos que
     * serão utilizados nos testes a seguir.
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        leitor = new LeitorArquivo("Pontos.txt","Arestas.txt");
        grafo = new Grafo();
    }
    
    /**
     * Teste de unidade que verifica se os atributos do leitor de imagens e 
     * computadores são atribuídos corretamente.
     * @throws java.io.IOException
     */
    @Test
    public void testBasic() throws IOException {
        assertEquals("Pontos.txt", leitor.getFile().getName());
        assertNotEquals("Carros.txt", leitor.getFile().getName());
        
        assertEquals("Arestas.txt", leitor.getFile2().getName());
        assertNotEquals("Casa.txt", leitor.getFile2().getName());
    }
    
    /**
     * Teste de unidade que verifica se a leitura de imagens está sendo 
     * realizada corretamente.
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws util.DuplicadaException
     * @throws model.CapacidadeIndisponivelException
     */
    @Test
    public void testLerArquivos() throws IOException, FileNotFoundException, DuplicateException, LeitorException{
        assertEquals(0, grafo.getListaPonto().size());
        assertTrue(grafo.getListaPonto().isEmpty());
        
        assertEquals("Arquivos lidos corretamente!" , leitor.lerArquivos(grafo));
        
        assertEquals(51, grafo.getListaPonto().size());
        assertFalse(grafo.getListaPonto().isEmpty());
        
       assertEquals(200, grafo.getListaTotalAresta().size());
        assertFalse(grafo.getListaTotalAresta().isEmpty());
        
        assertEquals(10, grafo.getListaPonto().get(0).getListaAresta().size());
        assertEquals(6, grafo.getListaPonto().get(1).getListaAresta().size());
        assertEquals(8, grafo.getListaPonto().get(2).getListaAresta().size());
        assertEquals(4, grafo.getListaPonto().get(3).getListaAresta().size());
        assertEquals(14, grafo.getListaPonto().get(4).getListaAresta().size());
        assertEquals(10, grafo.getListaPonto().get(5).getListaAresta().size());
        assertEquals(14, grafo.getListaPonto().get(6).getListaAresta().size());
        assertEquals(8, grafo.getListaPonto().get(7).getListaAresta().size());
        
    }
    
}
