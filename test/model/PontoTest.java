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

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.DuplicateException;


public class PontoTest {
    private Ponto p1,p2,p3,p4;
    private Aresta a1,a2,a3;
    
    @Before
    public void setUp() throws DuplicateException {
        p1 = new Ponto("cruzamento1", 1 , 4.5 , 9.2);
        p2 = new Ponto("cruzamento2", 1 , 9.1 , 8.7);
        p3 = new Ponto("cruzamento3", 1 , 5.3 , 3.3);
        p4 = new Ponto("cruzamento4", 1 , 2.8 , 5.7);
        
        a1 = new Aresta(p1 ,p2 ,6);
        a2 = new Aresta(p3 ,p4 ,9);
        a3 = new Aresta(p2, p3, 3);
        
    }
    
    
    
    @Test
    public void Testbasic(){
        
        assertEquals("cruzamento1" , p1.getNome());
        assertEquals(1,p1.getIdentificacao());
        assertEquals(0 , p1.getDistancia());
        assertFalse(p1.getChecagem());

    }
    
    
    @Test
    public void TestEquals(){
        Ponto temp = new Ponto("cruzamento1", 1 , 4.5 , 9.2);
        assertTrue(temp.equals(p1));
        
        temp.setNome("cruzamento0");
        assertFalse(temp.equals(p1));
        
        temp = new Ponto("cruzamento1", 1 , 4.5, 9.2);
        assertTrue(temp.equals(p1));
        
        temp.setIdentificacao(2);
        assertFalse(temp.equals(p1));
        
        temp = new Ponto("cruzamento1", 1 , 4.5, 9.2);
        assertTrue(temp.equals(p1));
        
        temp.setDistancia(5);
        assertFalse(temp.equals(p1));
        
        temp = new Ponto("cruzamento1", 1 , 4.5, 9.2);
        assertTrue(temp.equals(p1));
        
        temp.setPontoToChecado();
        assertFalse(temp.equals(p1));
    }
    
    @Test
    public void AddAresta() throws DuplicateException{
        
        assertFalse(p1.getListaAresta().isEmpty()); //não estará vazia pq quando eu instanciei as arestas eu usei esse ponto.
        assertEquals(1,p1.getListaAresta().size());
        
        p1.addAresta(a2);
        p1.addAresta(a3);
        assertEquals(3,p1.getListaAresta().size());
        
        assertEquals(a1, p1.getListaAresta().get(0));
        assertEquals(a2, p1.getListaAresta().get(1));
        assertEquals(a3, p1.getListaAresta().get(2));
        
        assertFalse(p3.getListaAresta().isEmpty());
        assertEquals(2 ,p3.getListaAresta().size());
        
        p3.addAresta(a1);
        assertEquals(3 ,p3.getListaAresta().size());
        assertEquals(a2, p3.getListaAresta().get(0));
        assertEquals(a3, p3.getListaAresta().get(1));
        assertEquals(a1, p3.getListaAresta().get(2));
 
    }
    
    
    @Test
    public void RemoveAresta() throws DuplicateException{
        assertFalse(p1.getListaAresta().isEmpty()); 
        assertEquals(1,p1.getListaAresta().size());
        
        p1.addAresta(a2);
        p1.addAresta(a3);
        assertEquals(3,p1.getListaAresta().size());
        
        p1.removeAresta(a2);
        p1.removeAresta(a3);
        assertFalse(p1.getListaAresta().isEmpty());
        assertEquals(1 ,p1.getListaAresta().size());
        
        p1.removeAresta(a1);
        assertTrue(p1.getListaAresta().isEmpty());
        assertEquals(0 ,p1.getListaAresta().size());
   
    }
    
}