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
package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Grafo;
import model.Ponto;

/**
 * Esta classe implementa o algoritmo de Dijkstra, utilizado para calcular o 
 * menor caminho entre pontos. Junto ao algoritmo, foram utilizados dois métodos
 * para que sejam feitas duas buscas e o resultado dessas duas buscas sejam 
 * concatenados. A classe contém apenas um atributo para calcular o tempo total 
 * do percurso, maior parte das variáveis são locais.  
 * Os métodos serão explicitados posteriormente.
 * 
 * Exemplo de uso:
 *
 * Dijkstra dijkstra = new Dijkstra();
 * 
 * @author  João Erick Barbosa Teixeira Da Silva
 * @author João Samuel Vilas Boas Góes
 */
public class Dijkstra {
    private int tempoTotal;
    
    public Dijkstra(){
        this.tempoTotal = 0;
    }
    
    public int getTempoTotal(){
        return tempoTotal;
    }
    
    /**Método utiliza o algoritmo de Dijkstra duas vezes para calcular o menor 
     * caminho entre o ponto de partida e o de coleta, assim como o ponto de 
     * coleta e o de chegada. Além calcular o tempo total percorrido.
     * @param grafo Grafo - Contém o grafo a ser manipulado na busca.
     * @param coleta Ponto - Contém o ponto de coleta.
     * @param chegada Ponto - Contém o ponto de chegada.
     * @return Retorna a lista de pontos pertencentes ao menor caminho entre o 
     * estacionamento, coleta e banco.
     * @throws NotFoundException - Exceção em caso de não encontrar um estacionamento.
     * @throws MenorCaminhoException  - Exceção em caso de pelo menos uma das 
     * listas de arestas dos pontos passados esteja vazia.
     */
    public List<Ponto> calcularRota(Grafo grafo, Ponto coleta, Ponto chegada) throws NotFoundException, MenorCaminhoException{
          tempoTotal = 0;  
          List<Ponto> lista = new ArrayList<>();
          List<Ponto> lista2 = new ArrayList<>();
          Ponto partida = null;
          for(int i = 0; i<grafo.getListaPonto().size();i++){
              if(grafo.getListaPonto().get(i).getIdentificacao() == 0) //Garante que a busca comece pelo estacionamento.
                  partida = grafo.getListaPonto().get(i);
          }
          if(partida == null)
              throw new NotFoundException(0);
          if(partida.getListaAresta().isEmpty() || coleta.getListaAresta().isEmpty() || chegada.getListaAresta().isEmpty())
              throw new MenorCaminhoException();
          
          lista = encontrarMenorCaminho(grafo, partida, coleta);
          
          int distanciaTotal1 = 0;
          for(int i = 0; i<lista.size();i++){
              if(i+1 == lista.size())                   //Salva a distância total do ponto de partida até a coleta.
                  distanciaTotal1 = lista.get(i).getDistancia();
          }
          
          tempoTotal = distanciaTotal1;
          lista2 = encontrarMenorCaminho(grafo, coleta, chegada);
          
          for(int i = 1; i<lista2.size();i++){  //Concatena as duas buscas, encontrando a lista de menor caminho total.
              lista.add(lista2.get(i));
              if(i+1 == lista2.size())
                  tempoTotal += lista2.get(i).getDistancia();
          }
          
          for(int i = 0; i<lista.size();i++){
              if(lista.get(i).equals(coleta))   //Põe de volta a distância da coleta que foi reiniciada para fazer a segunda busca.
                  lista.get(i).setDistancia(distanciaTotal1);
          }
          return lista;
    }
    
    /**Algoritmo de Dijkstra que calcula o menor caminho entre dois pontos.
     * @param grafo Grafo - Contém o grafo a ser manipulado.
     * @param ponto1 Ponto - Contém o ponto inicial da busca.
     * @param ponto2 Ponto - Contém o ponto final da busca.
     * @return Retorna a lista de pontos pertencentes ao menor caminho.
     */
    public List<Ponto> encontrarMenorCaminho(Grafo grafo, Ponto ponto1, Ponto ponto2){
        reiniciarAtributos(grafo);   //Reinicia o estado dos atributos dos pontos do grafo.
        
        Ponto proximoPonto = new Ponto();
        Ponto atual = new Ponto();
        Ponto pontoAuxiliar = new Ponto();
        List<Ponto> listaMenorCaminho = new ArrayList<>();
        List<Ponto> listaNaoChecados = new ArrayList<>();
        
        int i = 0;
        while(i < grafo.getListaPonto().size()){
            if(grafo.getListaPonto().get(i).equals(ponto1))
                grafo.getListaPonto().get(i).setDistancia(0);
            else
                grafo.getListaPonto().get(i).setDistancia(9999); //"9999" representa uma distância infinita.
            
            listaNaoChecados.add(grafo.getListaPonto().get(i));
            i++;
        }
        Collections.sort(listaNaoChecados);
        
        listaMenorCaminho.add(ponto1);
        
        while(!listaNaoChecados.isEmpty()){
            
            atual = listaNaoChecados.get(0);
            int j = 0;
            while(j < atual.getListaAresta().size()){
                proximoPonto = atual.getListaAresta().get(j).getDestino();
                
                //Altera as distâncias dos pontos vizinhos(9999) para a distância do ponto atual mais o peso associado a ele.
                if(proximoPonto.getChecagem() == false){
                    if(proximoPonto.getDistancia() >  atual.getDistancia() + atual.getListaAresta().get(j).getTempoDuracao()){
                        proximoPonto.setDistancia(atual.getDistancia() + atual.getListaAresta().get(j).getTempoDuracao());
                        proximoPonto.setAnterior(atual);
                        
                        //Cria uma lista do menor caminho a partir dos pontos anteriores.
                        if(ponto2 == proximoPonto){
                            listaMenorCaminho.clear();
                            pontoAuxiliar = proximoPonto;
                            listaMenorCaminho.add(proximoPonto);
                            while(pontoAuxiliar.getAnterior()!=null){
                                listaMenorCaminho.add(pontoAuxiliar.getAnterior());
                                pontoAuxiliar = pontoAuxiliar.getAnterior();
                            }
                        }
                    }
                }
                Collections.sort(listaMenorCaminho);
                j++;
            }
        atual.setPontoToChecado();
        listaNaoChecados.remove(atual);
        
        Collections.sort(listaNaoChecados);
        }
        return listaMenorCaminho;
    }
    
    /**Método que reinicia os estados dos atributos para cada busca pelo menor caminho.
     * @param grafo Grafo - Contém o grafo onde alguns dos atributos da lista de pontos
     * precisam ser reiniciados.
     */
    public void reiniciarAtributos(Grafo grafo){
        for(Ponto p: grafo.getListaPonto()){
            p.setDistancia(0);
            p.setAnterior(null);
            p.setChecagem(false);
        }
    }
            
}
