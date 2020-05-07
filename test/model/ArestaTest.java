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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.DuplicateException;


public class ArestaTest { 
    private Ponto p1,p2,p3;
    private Aresta a1,a2,a3;
    
    
    
    @Before
    public void setUp() throws DuplicateException {
        p1 = new Ponto("cruzamento1", 1 , 4.8 , 9.9);
        p2 = new Ponto("cruzamento2", 1 , 9.5 , 8.4);
        p3 = new Ponto("cruzamento3", 1 , 5.2 , 3.2);
        
        a1 = new Aresta(p1,p2,6);
        a2 = new Aresta(p1,p3,9);
    }
    
    
    @Test
    public void TestBasic(){
        
        assertEquals(p1, a1.getOrigem());
        assertEquals(p2, a1.getDestino());
        assertEquals(6 ,a1.getTempoDuracao());
        
        assertEquals(p1, a2.getOrigem());
        assertEquals(p3, a2.getDestino());
        assertEquals(9 ,a2.getTempoDuracao());
    }
    
    
    @Test
    public void TestEquals() throws DuplicateException{
        Aresta temp = new Aresta(p3,p2,6);
        assertFalse(temp.equals(a1));
        
        temp.setTempoDuracao(7);
        assertFalse(temp.equals(a1));
    }
    
}
