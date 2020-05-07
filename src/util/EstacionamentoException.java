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
 * Esta classe implementa a exceção para quando se tenta adicionar mais de um
 * estacionamento. 
 * @author João Erick Barbosa Teixeira Da Silva
 * @author João Samuel Vilas Boas Góes 
*/
public class EstacionamentoException extends Exception{
    
    public EstacionamentoException(){}

    @Override
    public String getMessage() {
        return "Não é possível adicionar pois já existe um estacionamento!";
    }
    
    
}
