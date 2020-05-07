/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: João Erick Barbosa Teixeira Da Silva, João Samuel Vilas Boas Góes
 * Data:  10/09/2019
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

import java.io.*;
import util.DuplicateException;
import util.LeitorException;
/**
 * Esta classe implementa características gerais de um leitor de Pontos e 
 * Arestas. Logo, ela contém os atributos de necessários de um leitor como 
 * File e referências para a lista de arestas, estrutura que guardará os dados 
 * de parte dessa leitura.
 * Os métodos serão explicitados posteriormente.
 * 
 * Exemplo de uso:
 *
 * LeitorArquivo leitor = new LeitorArquivo("arquivo1.txt", "arquivo2.txt");
 * 
 * @author  João Erick Barbosa Teixeira Da Silva
 * @author João Samuel Vilas Boas Góes
 */

public class LeitorArquivo {
    private File file, file2;
    
    /**Método construtor que incializa os atributos com valores solicitados.
     * @param arq String - Obtém o nome do primeiro arquivo a ser lido.
     * @param arq2 String - Obtém o nome do segundo arquivo a ser lido.
     * @throws java.io.IOException - Exceção de entrada e saída de leitura.
     */
    public LeitorArquivo(String arq, String arq2) throws IOException{
        this.file = new File(arq);
        file.createNewFile();
        
        this.file2 = new File(arq2);
        file2.createNewFile();
    }

    public LeitorArquivo() {
        file = null;
        file2 = null;
    }
    
    /**Método que obtém o objeto do primeiro arquivo a ser lido.
     * @return File - Objeto do primeiro arquivo.
     */
    public File getFile() {
        return file;
    }
    
    /**Método que obtém o objeto do segundo arquivo a ser lido.
     * @return File - Objeto do segundo arquivo.
     */
    public File getFile2() {
        return file2;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setFile2(File file2) {
        this.file2 = file2;
    }
    
    
    
    public String lerArquivos(Grafo grafo) throws FileNotFoundException, DuplicateException, LeitorException{
        if(file.getName().compareTo("Pontos.txt")== 0 && file2.getName().compareTo("Arestas.txt")==0)
            return leiaPontos_e_Arestas(grafo);
        else if(file2.getName().compareTo("Pontos.txt")== 0 && file.getName().compareTo("Arestas.txt")==0){
            File temp = file;
            file = file2;
            file2 = temp;
            return leiaPontos_e_Arestas(grafo);
        }
        else
            throw new LeitorException();
    }
    
    /**Método que realiza a leitura do arquivo de imagens e do arquivo de 
     * computadores, e salva cada uma leituras em suas respectivas estruturas.
     * @return boolean - [false] = não foi possível ler e adicionar | 
     * [true] = foi possível ler e adicionar.
     * @throws FileNotFoundException
     * @throws DuplicadaException 
     * @throws model.CapacidadeIndisponivelException 
     */
    private String leiaPontos_e_Arestas(Grafo grafo) throws FileNotFoundException, DuplicateException {
        String mensagem = "Arquivos lidos corretamente!";
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String leitura1,leitura2;
            Ponto ponto1 = null, ponto2 = null;
            
            while((leitura1 = br.readLine()) != null){
                String[] s = leitura1.split(",");
                
                leitura2 = s[0];
                int ident = Integer.valueOf(s[1]).intValue();
                int posicaoX = Integer.valueOf(s[2]).intValue();
                int posicaoY = Integer.valueOf(s[3]).intValue();
                Ponto ponto = new Ponto(leitura2,ident,posicaoX,posicaoY);
                grafo.getListaPonto().add(ponto);
            }
            br.close();
            
            br = new BufferedReader(new FileReader(file2));
            while((leitura1 = br.readLine()) != null){
                
                String[] s = leitura1.split(",");
                for(int i = 0;i<grafo.getListaPonto().size(); i++){
                    if(s[0].equals(grafo.getListaPonto().get(i).getNome()))
                        ponto1 = grafo.getListaPonto().get(i);
                    else if(s[1].equals(grafo.getListaPonto().get(i).getNome()))
                        ponto2 = grafo.getListaPonto().get(i);
                }
                
                int tempo = Integer.valueOf(s[2]).intValue();
                Aresta aresta = new Aresta(ponto1, ponto2, tempo);
                Aresta aresta2 = new Aresta(ponto2, ponto1, tempo);
                grafo.getListaTotalAresta().add(aresta);//talvez remover
                grafo.getListaTotalAresta().add(aresta2);//talvez remover
            }
            
            br.close();
            return mensagem;
            
        }
        catch(IOException e){
            mensagem = e.getMessage();
        }
        return mensagem;
    }
   
}
