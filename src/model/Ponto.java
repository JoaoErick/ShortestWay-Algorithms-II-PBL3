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
import java.util.ArrayList;
import java.util.List;
import util.DuplicateException;
/**
 * Esta classe implementa características gerais de um Ponto. Logo, ela
contém os atributos necessários para a existência de um Ponto, que são: nome, 
* identificação,posição no espaço(x,y), e lista de arestas incidentes nele. A classe possui ainda, 
* alguns outros atributos necessários para a manipulação do algoritmo de 
* Dijkstra, são eles: distância, checagem de visitação e referência para pontos 
* anteriores a ele. 
* Foi implementada a interface Comparable nesta classe para que fosse possível 
* executar a ordenação de uma lista de pontos, criada por meio das Collections.
* Os métodos serão explicitados posteriormente.
 * 
 * Exemplo de uso:
 *
 * Ponto ponto = new Ponto("Rua Álvaro Dias",1,3.0,4.2);
 * 
 * @author  João Erick Barbosa Teixeira Da Silva
 * @author João Samuel Vilas Boas Góes
 */

public class Ponto implements Comparable<Ponto>{
    private List<Aresta> listaAresta;
    
    private String nome;
    private int identificacao;
   
    private double x, y;
    
    private int distancia;
    private Ponto anterior;
    private boolean checagem; //Verifica a situação de visitação false: não visitado; true: visitado)
    
    /**Método construtor que inicializa os atributos com valores solicitados.
     * @param nome String - Obtém o nome do computador.
     * @param id int - Obtém a identificação do ponto.
     * (0- ponto de partida, 1- cruzamento, 2- coleta, 3- Banco)
     * @param x double - Obtém o posição x do ponto.
     * @param y double - Obtém o posição y do ponto.
     */
    public Ponto(String nome, int id, double x, double y) {
        this.listaAresta = new ArrayList<>();
        this.nome = nome;
        this.identificacao = id;
        this.x = x;
        this.y = y;
        this.checagem = false;
    }
    
    /**
     * Sobrecarga do método construtor sem inicialização de atributos.
     */
    public Ponto() {
    }
    
    /**
     * Método que obtém a referência do ponto anterior.
     * @return No - Referência do ponto anterior.
     */
    public Ponto getAnterior() {
        return anterior;
    }
    
    /**
     * Método que altera a referência atual do ponto anterior, para uma 
     * solicitada. 
     * @param anterior Ponto - Contém a nova referência ao ponto anterior.
     */
    public void setAnterior(Ponto anterior) {
        this.anterior = anterior;
    }
    
    /**
     * Método que obtém a lista de arestas criada por meio da Collection List e 
     * manipulada na forma de ArrayList.
     * @return Lista de arestas.  
     */
    public List<Aresta> getListaAresta() {
        return listaAresta;
    }
    
    /**
     * Esse metodo altera a lista de arestas
     * @param arestas - Contém a nova lista de arestas.
     */
    public void setListaAresta(List<Aresta> arestas){
        this.listaAresta.addAll(arestas);
    }
    
    /**
     * Esse metodo adiciona uma aresta a lista de arestas, mas lança uma exceção se
     * a aresta ja existir na lista
     * @param aresta Aresta - Contém a aresta a ser adicionada.
     * @throws util.DuplicateException - Exceção para arestas duplicadas.
     */
    public void addAresta(Aresta aresta) throws DuplicateException{
         for(int i = 0; i<listaAresta.size();i++){
            if(listaAresta.get(i).getOrigem().getNome().compareTo(aresta.getOrigem().getNome()) == 0
                    && listaAresta.get(i).getDestino().getNome().compareTo(aresta.getDestino().getNome()) == 0){
                throw new DuplicateException(aresta);
            }
        }
        listaAresta.add(aresta);
        if(aresta.getOrigem() == null || aresta.getDestino() == null){
           if(aresta.getOrigem() == null) 
               aresta.setOrigem(this);
            else
                aresta.setDestino(this);
        }
    }
    
    /**
     * Esse metodo remove uma aresta da lista de arestas.
     * @param aresta Aresta - Contém a aresta a ser removida.
     * @throws util.DuplicateException - Exceção para aresta duplicada.
     */
    public void removeAresta(Aresta aresta) throws DuplicateException{
        listaAresta.remove(aresta);
        aresta.setOrigem(null);
        aresta.setDestino(null);
    }

    /**
     * Esse metodo altera a situação da checagem utilizada na busca.
     * @param situacao boolean - Contém a situação de visitação do ponto 
     * [false] = não visitado; [true] = visitado
     */
    public void setChecagem(boolean situacao){
        this.checagem = situacao;
    }
    
    /**
     * Esse metodo altera a situação da checagem utilizada na busca para verdadeiro.
     */
    public void setPontoToChecado(){
        this.setChecagem(true);
    }
    
    /**
     * Esse metodo retorna a situação da checagem.
     * @return boolean - Situação de visitação.
     */
    public boolean getChecagem(){
        return this.checagem;
    }
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(int identificacao) {
        this.identificacao = identificacao;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    
    @Override
    public int compareTo(Ponto ponto) {
        if(this.getDistancia() < ponto.getDistancia())
            return -1;
        else if(this.getDistancia() == ponto.getDistancia())
            return 0;
        else
            return 1;
    }
    
    /**Sobrescrita do método "equals();" que compara o atributos desta classe 
     * com os solicitados no parâmetro através do objeto.
     * @param obj Imagem - Obtém todos os dados da classe "Imagem"
     * @return boolean - true(todos os dados são iguais) e 
     * false(todos são diferentes).
     */
    public boolean equals(Ponto obj){
       return this.nome.equals(obj.getNome()) && this.identificacao == obj.getIdentificacao()
               && this.distancia == obj.getDistancia() && this.checagem == obj.getChecagem();
    }

    @Override
    public String toString() {
        return getNome();
    }
    
    
    
    
}
