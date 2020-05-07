/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import util.Dijkstra;
import util.DuplicateException;
import util.EstacionamentoException;
import util.MenorCaminhoException;
import util.NotFoundException;

public class GrafoTest {
    private Grafo grafo;
    private Aresta a1, a2, a3, a4, a5,a6, a7,a8,a9,a10, a11, a12;
    private Ponto v1, v2, v3, v4, v5, v6;
    private Dijkstra dijkstra;
    
    /**
     * Este método é executado antes de cada teste de unidade (testes a seguir),
     * e serve para inicializar objetos que são utilizados nos testes.
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        grafo = new Grafo();
        dijkstra = new Dijkstra();
        
        v1 = new Ponto("Ponto de partida", 0, 30.2, 30.2);
        v2 = new Ponto("Cruzamento 1", 1, 50.4, 50.4);
        v3 = new Ponto("Ponto de chegada", 3, 72.0, 30.7);
        v4 = new Ponto("Ponto de Coleta 1", 2, 40.5, 90.2);
        v5 = new Ponto("Cruzamento 2", 1, 20, 20);
        v6 = new Ponto("Ponto de Coleta 2", 2, 21.2, 33.3);
        
        a1 = new Aresta(v1, v2, 30);
        a2 = new Aresta(v2, v4, 50);
        a3 = new Aresta(v1, v3, 20);
        a4 = new Aresta(v4, v5, 40);
        a5 = new Aresta(v3, v5, 20);
        
        
        a6 = new Aresta(v2, v1, 30);
        a7 = new Aresta(v4, v2, 50);
        a8 = new Aresta(v3, v1, 20);
        a9 = new Aresta(v5, v4, 40);
        a10 = new Aresta(v5, v3, 20);
        a11 = new Aresta(v6, v3, 10);
        a12 = new Aresta(v3, v6, 10);
    }   
    
    @Test
    public void testAdicionarPonto() throws DuplicateException, EstacionamentoException{
        assertEquals(0, grafo.getListaPonto().size());
        
        grafo.adicionarPonto(v1);
        assertEquals(v1, grafo.getListaPonto().get(0));
        assertEquals(1, grafo.getListaPonto().size());
        
        grafo.adicionarPonto(v2);
        grafo.adicionarPonto(v3);
        grafo.adicionarPonto(v4);
        grafo.adicionarPonto(v5);
        assertEquals(v2, grafo.getListaPonto().get(1));
        assertEquals(v3, grafo.getListaPonto().get(2));
        assertEquals(v4, grafo.getListaPonto().get(3));
        assertEquals(v5, grafo.getListaPonto().get(4));
        
        assertEquals(5, grafo.getListaPonto().size());
    }
    
    @Test
    public void testRemoverPonto_e_Ligacao() throws DuplicateException, NotFoundException, EstacionamentoException{
        assertEquals(0, grafo.getListaPonto().size());
        assertTrue(grafo.getListaPonto().isEmpty());
        
        grafo.adicionarPonto(v1);
        grafo.adicionarPonto(v2);
        grafo.adicionarPonto(v3);
        grafo.adicionarPonto(v4);
        grafo.adicionarPonto(v5);
        assertEquals(5, grafo.getListaPonto().size());
        assertFalse(grafo.getListaPonto().isEmpty());
        
        assertEquals(4,grafo.getListaPonto().get(0).getListaAresta().size());
        assertEquals(4,grafo.getListaPonto().get(4).getListaAresta().size());
        grafo.removerPonto(v3);
        assertEquals(2,grafo.getListaPonto().get(0).getListaAresta().size());
        assertEquals(2,grafo.getListaPonto().get(3).getListaAresta().size());
        assertEquals(4, grafo.getListaPonto().size());
        
        assertEquals(2,grafo.getListaPonto().get(0).getListaAresta().size());
        assertEquals(4,grafo.getListaPonto().get(2).getListaAresta().size());
        grafo.removerPonto(v2);
        assertEquals(0,grafo.getListaPonto().get(0).getListaAresta().size());
        assertEquals(2,grafo.getListaPonto().get(1).getListaAresta().size());
        assertEquals(3, grafo.getListaPonto().size());
        
        grafo.removerPonto(v4);
        assertEquals(2, grafo.getListaPonto().size());
        assertFalse(grafo.getListaPonto().isEmpty());
        
    }
    
    @Test
    public void testRemoverLigacao() throws NotFoundException, DuplicateException, EstacionamentoException{
        assertEquals(0, grafo.getListaPonto().size());
        assertTrue(grafo.getListaPonto().isEmpty());
        
        grafo.adicionarPonto(v1);
        grafo.adicionarPonto(v2);
        grafo.adicionarPonto(v3);
        grafo.adicionarPonto(v4);
        grafo.adicionarPonto(v5);
        assertEquals(5, grafo.getListaPonto().size());
        assertFalse(grafo.getListaPonto().isEmpty());
        
        assertEquals(4,grafo.getListaPonto().get(0).getListaAresta().size());
        assertEquals(4,grafo.getListaPonto().get(1).getListaAresta().size());
        grafo.removerLigacao(a1);
        assertEquals(3,grafo.getListaPonto().get(0).getListaAresta().size());
        assertEquals(3,grafo.getListaPonto().get(1).getListaAresta().size());
        
        assertEquals(3,grafo.getListaPonto().get(1).getListaAresta().size());
        assertEquals(4,grafo.getListaPonto().get(3).getListaAresta().size());
        grafo.removerLigacao(a2);
        assertEquals(2,grafo.getListaPonto().get(1).getListaAresta().size());
        assertEquals(3,grafo.getListaPonto().get(3).getListaAresta().size());
        
        assertEquals(3,grafo.getListaPonto().get(0).getListaAresta().size());
        assertEquals(6,grafo.getListaPonto().get(2).getListaAresta().size());
        grafo.removerLigacao(a3);
        assertEquals(2,grafo.getListaPonto().get(0).getListaAresta().size());
        assertEquals(5,grafo.getListaPonto().get(2).getListaAresta().size());
        
    
    }
    
    @Test
    public void testEncontrarMenorCaminho() throws DuplicateException, EstacionamentoException, NotFoundException, MenorCaminhoException{
        assertEquals(0, grafo.getListaPonto().size());
        assertTrue(grafo.getListaPonto().isEmpty());
        
        grafo.adicionarPonto(v1);
        grafo.adicionarPonto(v2);
        grafo.adicionarPonto(v3);
        grafo.adicionarPonto(v4);
        grafo.adicionarPonto(v5);
        grafo.adicionarPonto(v6);
        assertEquals(6, grafo.getListaPonto().size());
        assertFalse(grafo.getListaPonto().isEmpty());
        
       List<Ponto> menorCaminho = dijkstra.calcularRota(grafo, v4, v3);
       assertEquals(v1, menorCaminho.get(0));
       assertEquals(v2, menorCaminho.get(1));
       assertEquals(v4, menorCaminho.get(2));
       assertEquals(v5, menorCaminho.get(3));
       assertEquals(v3, menorCaminho.get(4));
       assertEquals(140, dijkstra.getTempoTotal());
       
       
       menorCaminho = dijkstra.calcularRota(grafo, v6, v3);
       assertEquals(v1, menorCaminho.get(0));
       assertEquals(v3, menorCaminho.get(1));
       assertEquals(v6, menorCaminho.get(2));
       assertEquals(v3, menorCaminho.get(3));
       assertEquals(40, dijkstra.getTempoTotal());
    }
    
    @Test
    public void testAlterarPontoPartida() throws DuplicateException, EstacionamentoException{
        assertEquals(0, grafo.getListaPonto().size());
        assertTrue(grafo.getListaPonto().isEmpty());
        
        grafo.adicionarPonto(v1);
        grafo.adicionarPonto(v2);
        grafo.adicionarPonto(v5);
        assertEquals(3, grafo.getListaPonto().size());
        assertFalse(grafo.getListaPonto().isEmpty());
        
        assertEquals(0, grafo.getListaPonto().get(0).getIdentificacao());
        assertEquals(1, grafo.getListaPonto().get(1).getIdentificacao());
        grafo.alterarPontoPartidaTo(v2);
        assertEquals(1, grafo.getListaPonto().get(0).getIdentificacao());
        assertEquals(0, grafo.getListaPonto().get(1).getIdentificacao());
        
        assertEquals(1, grafo.getListaPonto().get(0).getIdentificacao());
        assertEquals(0, grafo.getListaPonto().get(1).getIdentificacao());
        assertEquals(1, grafo.getListaPonto().get(2).getIdentificacao());
        grafo.alterarPontoPartidaTo(v5);
        assertEquals(1, grafo.getListaPonto().get(0).getIdentificacao());
        assertEquals(1, grafo.getListaPonto().get(1).getIdentificacao());
        assertEquals(0, grafo.getListaPonto().get(2).getIdentificacao());
       
    }
    
    @Test
    public void testAlterarPontoChegada() throws DuplicateException, EstacionamentoException{
        assertEquals(0, grafo.getListaPonto().size());
        assertTrue(grafo.getListaPonto().isEmpty());
        
        grafo.adicionarPonto(v3);
        grafo.adicionarPonto(v2);
        grafo.adicionarPonto(v5);
        assertEquals(3, grafo.getListaPonto().size());
        assertFalse(grafo.getListaPonto().isEmpty());
        
        assertEquals(3, grafo.getListaPonto().get(0).getIdentificacao());
        assertEquals(1, grafo.getListaPonto().get(1).getIdentificacao());
        grafo.alterarToPontoChegada(v2);
        assertEquals(3, grafo.getListaPonto().get(0).getIdentificacao());
        assertEquals(3, grafo.getListaPonto().get(1).getIdentificacao());
        
        assertEquals(3, grafo.getListaPonto().get(0).getIdentificacao());
        assertEquals(3, grafo.getListaPonto().get(1).getIdentificacao());
        assertEquals(1, grafo.getListaPonto().get(2).getIdentificacao());
        grafo.alterarToPontoChegada(v5);
        assertEquals(3, grafo.getListaPonto().get(0).getIdentificacao());
        assertEquals(3, grafo.getListaPonto().get(1).getIdentificacao());
        assertEquals(3, grafo.getListaPonto().get(2).getIdentificacao());
       
    }
}
