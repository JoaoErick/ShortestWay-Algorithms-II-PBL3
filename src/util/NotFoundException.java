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

import java.util.List;
import model.Aresta;
import model.Ponto;

/**
 * Esta classe implementa a exceção para quando um ponto ou aresta não 
 * se encontra na lista; lista de pontos ou ligações encontram-se vazias; ou quando o estacionamento não for encontrado.
 * @author João Erick Barbosa Teixeira Da Silva
 * @author João Samuel Vilas Boas Góes
*/

public class NotFoundException extends Exception {
    protected Ponto ponto;
    protected Aresta aresta;
    protected int identificacao;
    protected List<Ponto> listaPonto;
    protected List<Aresta> listaAresta;
    
    
    
    /**Método construtor que incializa os atributos com valores pré-definidos.
     * @param ponto Ponto - Contém o ponto não encontrado.
     */
    public NotFoundException(Ponto ponto) {
        this.ponto = ponto;
        this.aresta = null;
    }
    
    /**Método construtor que incializa os atributos com valores pré-definidos.
     * @param aresta Aresta - Contém a aresta não encontrada.
     */
    public NotFoundException(Aresta aresta) {
        this.aresta = aresta;
        this.ponto = null;
    }
    
    /**Método construtor que incializa os atributos com valores pré-definidos.
     * @param listaPonto - Contém a lista de pontos vazia.
     */
    public NotFoundException(List<Ponto> listaPonto) {
        this.listaPonto = listaPonto;
        this.listaAresta = null;
    }
    
    /**Método construtor que incializa os atributos com valores pré-definidos.
     * @param id - Contém a identificação do ponto não encontrado.
     */
    public NotFoundException(int id) {
       this.identificacao = id;
    }
    
    /**Método construtor que incializa os atributos com valores pré-definidos.
     */
    public NotFoundException() {
        this.listaAresta = null;
        this.listaPonto = null;
    }
    
    /**
     * Método que retorna uma mensagem, informando que determinado objeto não 
     * foi encontrado.
     * @return String - Mensagem que informa a exceção.
     */
    @Override
    public String getMessage() {
        if(ponto != null)
            return "O Ponto: [" + ponto.getNome() + "] não foi encontrado!"; 
        else if(aresta != null)
            return "A Ligação: [" + aresta.getOrigem().getNome() + " - "+ aresta.getDestino().getNome()+ "] não foi encontrada!"; 
        else if(listaPonto != null)
            return "A lista de pontos encontra-se vazia!";
        else if(identificacao == 0)
            return "O estacionamento não foi encontrado!";    
        else
            return "A lista de ligações encontra-se vazia!";
    }
    
    
    
}
