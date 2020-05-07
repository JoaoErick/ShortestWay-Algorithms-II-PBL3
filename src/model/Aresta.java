/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: João Erick Barbosa Teixeira Da Silva, João Samuel Vilas Boas Góes
 * Data:  02/09/2019
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

import util.DuplicateException;

/**
 * Esta classe implementa características gerais de uma Aresta. Logo, ela
 *  contém os atributos necessários para a existência de uma aresta, que são: ponto 
 * de origem, ponto de destino e o peso associado a ela(tempo). Assim como 
 * métodos a serem explicitados posteriormente.
 * 
 * Exemplo de uso:
 *
 * Aresta aresta = new Aresta(Ponto1, Ponto2, 20);
 * 
 * @author  João Erick Barbosa Teixeira Da Silva
 * @author João Samuel Vilas Boas Góes
 */


public class Aresta {
    private Ponto origem;
    private Ponto destino;
    private int tempoDuracao;

    /**Método construtor que inicializa os atributos com valores solicitados.
     * @param origem - ponto de origem da aresta
     * @param destino - ponto de destino da aresta
     * @param tempoDuracao - valor da aresta que representa seu tempo de duração
     * @throws util.DuplicateException - Exceção para arestas duplicadas.
     */
    public Aresta(Ponto origem, Ponto destino, int tempoDuracao) throws DuplicateException {
        this.origem = origem;
        this.destino = destino;
        this.tempoDuracao = tempoDuracao;
        origem.addAresta(this);
        destino.addAresta(this);
    }
    
    /**
     * Sobrecarga do método construtor sem inicialização de atributos.
     */
    public Aresta(){}

  /**
   * Este método retorna o ponto de origem
   * @return origem - ponto de origem da aresta
  */
    public Ponto getOrigem() {
        return origem;
    }

    /**
    * Este método altera o ponto de origem, mas retorna uma exceção caso ela ja exista
     * @param origem Ponto - Contém o novo ponto de origem.
     * @throws util.DuplicateException - Exceção para pontos duplicados.
    */
    public void setOrigem(Ponto origem) throws DuplicateException {
        if(this.origem != origem){
            Ponto antigo = this.origem;
            this.origem = origem;
            if(origem != null)
                origem.addAresta(this);
            if(antigo != null)
                antigo.removeAresta(this);
        }
    }

    /**
     * Este método retorna o ponto de destino.
     * @return Ponto - Ponto de destino.
    */
    public Ponto getDestino() {
        return destino;
    }

    /**
    * Este método altera o ponto de destino, mas retorna uma exceção caso ela ja exista
     * @param destino - ponto de destino da aresta
     * @throws util.DuplicateException - Exceção para pontos duplicados.
    */
    public void setDestino(Ponto destino) throws DuplicateException {
        if(this.destino != destino){
            Ponto antigo = this.destino;
            this.destino = destino;
            if(destino != null)
                destino.addAresta(this);
            if(antigo != null)
                antigo.removeAresta(this);
        }
    }

    /**
     * Este método retorna o tempo de duração
     * @return tempoDuração - tempo de duração
    */
    public int getTempoDuracao() {
        return tempoDuracao;
    }

    /**
     * Este método altera o tempo de duração
     * @param tempoDuracao - tempo de duração
    */
    public void setTempoDuracao(int tempoDuracao) {
        this.tempoDuracao = tempoDuracao;
    }
    
    /**Sobrescrita do método "equals();" que compara o atributos desta classe 
     * com os solicitados no parâmetro através do objeto.
     * @param obj Aresta - Contém a aresta a ser comparada.
     * @return boolean - true(todos os dados são iguais) e 
     * false(todos são diferentes).
     */
    public boolean equals(Aresta obj){
       return this.origem.equals(obj.getOrigem()) && this.destino.equals(obj.getDestino())
               && this.tempoDuracao == obj.getTempoDuracao();
    }
    
    /**
     * Método que transforma o nome dos pontos de origem e destino em uma string.
     * @return String - string formada pelos dois pontos.
     */
    @Override
    public String toString() {
        return origem.getNome() + " - " + destino.getNome();
    }
    
    
    
    
}
