/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: João Erick Barbosa Teixeira Da Silva, João Samuel Vilas Boas Góes
 * Data:  17/09/2019
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
package controller;

import java.util.ArrayList;
import java.util.List;
import model.Aresta;
import model.Grafo;
import model.LeitorArquivo;
import util.Dijkstra;

/**
 * Esta classe possui os atributos staticos que o aplicativo utilizará 
 * em sua execução
 * 
 * @author  João Erick Barbosa Teixeira Da Silva
 * @author João Samuel Vilas Boas Góes
 */
public class PrincipalController {
    public static Grafo grafo = new Grafo();
    public static LeitorArquivo leitor = new LeitorArquivo();
    public static Dijkstra dijkstra = new Dijkstra();
}
