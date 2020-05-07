/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: João Erick Barbosa Teixeira Da Silva, João Samuel Vilas Boas Góes
 * Data:  14/09/2019
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
package util;

import model.Aresta;
import model.Ponto;
/**
 * Esta classe implementa a exceção para elementos com o mesmo nome nas 
 * estruturas. Logo, ela contém os atributos dos elementos específicos, do 
 * tipo: Ponto e Aresta. Assim como métodos a serem explicitados
 * posteriormente. 
* 
* 
* @author João Erick Barbosa Teixeira Da Silva
* @author João Samuel Vilas Boas Góes 
*/

public class DuplicateException extends Exception {
    protected Ponto ponto;
    protected Aresta aresta;
    
    /**Método construtor que incializa os atributos com valores pré-definidos.
     * @param ponto Ponto - Contém o ponto duplicado.
     */
    public DuplicateException(Ponto ponto) {
        super();
        this.ponto = ponto;
        this.aresta = null;
    }
    
    /**Método construtor que incializa os atributos com valores pré-definidos.
     * @param aresta Aresta - Contém a aresta duplicada.
     */
    public DuplicateException(Aresta aresta) {
        super();
        this.aresta = aresta;
        this.ponto = null;
    }
    
    /**
     * Método que confere se o ponto ou aresta já existe no sistema.
     * @return String - Mensagem que informa a exceção.
     */
    @Override
    public String getMessage() {
        if(ponto != null)
            return "O Ponto: [" + ponto.getNome() + "] já existe no mapa!"; 
        else
            return "A Ligação: [" + aresta.getOrigem().getNome() + " - "+ aresta.getDestino().getNome()+ "] já existe no mapa!"; 
    }
    
    
}
