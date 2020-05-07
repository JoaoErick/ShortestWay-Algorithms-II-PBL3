/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: João Erick Barbosa Teixeira Da Silva, João Samuel Vilas Boas Góes
 * Data:  12/09/2019
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
import util.EstacionamentoException;
import util.NotFoundException;
/**
 * Esta classe implementa características gerais de uma Grafo Logo, ela
 *  contém os atributos necessários para a existência de uma grafo, que são: pontos e 
 * arestas. Assim como métodos a serem explicitados posteriormente.
 * 
 * @author  João Erick Barbosa Teixeira Da Silva
 * @author João Samuel Vilas Boas Góes
 */

public class Grafo {
    private List<Ponto> listaPonto;
    private List<Aresta> listaTotalAresta;
    
    /**Método construtor que inicializa os atributos com valores solicitados.
     */
    public Grafo(){
        this.listaPonto = new ArrayList<>();
        this.listaTotalAresta = new ArrayList<>();
    }
           
    /**
     * Este metodo retorna a lista de pontos
     * @return listaPonto - lista de pontos do grafo.
     */
    public List<Ponto> getListaPonto(){
        return listaPonto;
    }

    /**
     * Este metodo retorna a lista de arestas
     * @return listaTotalAresta - lista de arestas do grafo.
     */
    public List<Aresta> getListaTotalAresta() {
        return listaTotalAresta;
    }
    
    /**
     * Este metodo adiciona um novo ponto ao grafo, mas ele lança exceções se
     * ja existir um estacionamento ou se o ponto ja existe.
     * 
     * @param ponto - ponto que será adicionado
     * @throws util.DuplicateException - Exceção para pontos duplicados.
     * @throws util.EstacionamentoException - Exceção para quando já existe um 
     * estacionamento.
    */
    public void adicionarPonto(Ponto ponto) throws DuplicateException, EstacionamentoException{
        if(ponto.getIdentificacao() == 0){
            for(Ponto p: listaPonto){
                if(p.getIdentificacao() == 0)
                    throw new EstacionamentoException();
            }
        }
        for(int i = 0; i<listaPonto.size();i++){
            if(listaPonto.get(i).getNome().compareTo(ponto.getNome()) == 0)
                throw new DuplicateException(ponto);
        }
        listaPonto.add(ponto);
    }
    
    /**
     * remove um ponto do grafo, mas se ele não existir
     * dispara uma exceção
     * 
     * @param ponto - ponto que será removido
     * @throws util.NotFoundException - Exceção para lista de pontos vazia ou 
     * ponto não encontrado.
    */
    public void removerPonto(Ponto ponto) throws NotFoundException{
        Ponto aux = null;
        Aresta temp;
        
        for(int i =0; i<listaPonto.size(); i++){
            if(listaPonto.get(i).equals(ponto))
                aux = listaPonto.get(i);
        }
        
        if(listaPonto.isEmpty())
            throw new NotFoundException(listaPonto);
        else if(aux == null)
            throw new NotFoundException(ponto);
        
        int j = 0;
        while(j < aux.getListaAresta().size()){
            temp = aux.getListaAresta().get(j);
            if(temp.getDestino().equals(aux) == false)
                aux.getListaAresta().get(j).getDestino().getListaAresta().remove(temp);
                listaTotalAresta.remove(temp);
            if(temp.getOrigem().equals(aux) == false)
                aux.getListaAresta().get(j).getOrigem().getListaAresta().remove(temp);
                 listaTotalAresta.remove(temp);
            j++;
        }
        listaPonto.remove(aux);
    }
    
    /**
     * Este metodo remove uma aresta, mas se ele não existir
     * dispara uma exceção
     * @param aresta - aresta que será removida do grafo
     * @throws util.NotFoundException - Exceção para lista de arestas vazia ou 
     * aresta não encontrada.
    */
    public void removerLigacao(Aresta aresta) throws NotFoundException{
        Aresta aux = null;
        int contador = 0, indice1 = 0, indice2 = 0;
        for(int i = 0; i<listaPonto.size(); i++){
            for(int j = 0; j<listaPonto.get(i).getListaAresta().size(); j++){
                contador++;
                if(listaPonto.get(i).getListaAresta().get(j).equals(aresta))
                    aux = listaPonto.get(i).getListaAresta().get(j);
            }
        }
        if(contador == 0)
            throw new NotFoundException();
        if(aux == null)
            throw new NotFoundException(aresta); 
        for(int i = 0; i<listaPonto.size(); i++){
            for(int j = 0; j<listaPonto.get(i).getListaAresta().size(); j++){
                if(listaPonto.get(i).getListaAresta().get(j).equals(aresta))
                    listaPonto.get(i).getListaAresta().remove(j);
            }
        }
        listaTotalAresta.remove(aux);
    }
    
    
    /**
     * Este metodo remove altera o ponto de partida, no qual o carro forte partirá.
     * dispara uma exceção
     * @param ponto - ponto que será o novo ponto de partida
    */
    public void alterarPontoPartidaTo(Ponto ponto){ 
        for(int i = 0; i<listaPonto.size(); i++){
            if(listaPonto.get(i).getIdentificacao() == 0)
                listaPonto.get(i).setIdentificacao(ponto.getIdentificacao());
            else if(listaPonto.get(i).equals(ponto))
                listaPonto.get(i).setIdentificacao(0);
        }
    }
    
    /**
     * Este metodo remove altera o ponto de chegada, no qual o carro forte chegará
     * dispara uma exceção
     * @param ponto - ponto que será a nova chegada
    */
    public void alterarToPontoChegada(Ponto ponto){
        for(int i = 0; i<listaPonto.size(); i++){
            if(listaPonto.get(i).equals(ponto))
                listaPonto.get(i).setIdentificacao(3);
        }
    }
    
    
}
