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
/**
 * Esta classe implementa a exceção para quando é impossivel chegar em algum
 * ponto expecifico do grafo por motivo de falta de arestas.
 * 
 * @author João Erick Barbosa Teixeira Da Silva
 * @author João Samuel Vilas Boas Góes
*/
public class MenorCaminhoException extends Exception{
    
    /**Método construtor utilizado para a instanciação dos objetos.
     */
    public MenorCaminhoException() {
    }
    
    /**Método que obtém a mensagem de aviso de exceção para o cálculo de menor 
     * caminho.
     * @return String - Mensagem que informa a exceção.
     */
    @Override
    public String getMessage() {
        return "Não é possível realizar o cálculo para esses pontos!";
    }
    
    
}
