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

import static controller.Main.stageAux;
import static controller.PrincipalController.grafo;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Ponto;
import util.DuplicateException;
import util.EstacionamentoException;

/**
 * Esta classe é o controller da cena na qual o usuário irá adicionar um ponto
 * no grafo que está criando
 * 
 * @author  João Erick Barbosa Teixeira Da Silva
 * @author João Samuel Vilas Boas Góes
 */
public class TelaAddPontoController implements Initializable{
    List<MenuItem> listMenuItem;

    @FXML // fx:id="menuAddAresta"
    private MenuItem menuAddAresta; // Value injected by FXMLLoader

    @FXML // fx:id="menuRemovePonto"
    private MenuItem menuRemovePonto; // Value injected by FXMLLoader

    @FXML // fx:id="menuRemoveAresta"
    private MenuItem menuRemoveAresta; // Value injected by FXMLLoader

    @FXML // fx:id="menuAlteraPonto"
    private MenuItem menuAlteraPonto; // Value injected by FXMLLoader
    
    @FXML // fx:id="menuMenorCaminho"
    private MenuItem menuMenorCaminho; // Value injected by FXMLLoader

    @FXML // fx:id="nome"
    private Label nome; // Value injected by FXMLLoader

    @FXML // fx:id="caixaNome"
    private TextField caixaNome; // Value injected by FXMLLoader

    @FXML // fx:id="id"
    private Label id; // Value injected by FXMLLoader

    @FXML // fx:id="descricao"
    private Label descricao; // Value injected by FXMLLoader

    @FXML // fx:id="posicao"
    private Label posicao; // Value injected by FXMLLoader

    @FXML // fx:id="posicaoX"
    private TextField posicaoX; // Value injected by FXMLLoader

    @FXML // fx:id="posicaoY"
    private TextField posicaoY; // Value injected by FXMLLoader

    @FXML // fx:id="spinner"
    private Spinner<Integer> spinner; // Value injected by FXMLLoader

    @FXML // fx:id="btnSalvaPonto"
    private Button btnSalvaPonto; // Value injected by FXMLLoader

    @FXML // fx:id="bntVoltar"
    private Button bntVoltar; // Value injected by FXMLLoader
    
    @FXML // fx:id="novoPonto"
    private Button novoPonto; // Value injected by FXMLLoader
    
    @FXML // fx:id="lblAviso"
    private Label lblAviso; // Value injected by FXMLLoader

    @FXML
      /**
     * Esse metodo abre uma nova janela de criação de aresta quando o usuário aperta
     * nova aresta
     */
    void adicionaAresta(ActionEvent event) throws IOException {
        novaJanela("TelaAddAresta.fxml");
    }

    
    @FXML
       /**
     * Esse metodo salva o ponto criado pelo usuário no grafo estático
     * mas mostra um erro se o mesmo não preencher todas as lacunas da 
     * criação
     */
    void salvarPonto(ActionEvent event) throws DuplicateException, EstacionamentoException {
        System.out.println("You clicked me!");
        if(posicaoX.getText().isEmpty() || posicaoY.getText().isEmpty() || caixaNome.getText().isEmpty()){
            lblAviso.setText("É preciso preencher todos os dados!");
            btnSalvaPonto.setDisable(true);
        }
        else
            save();
    }
    
    /**
     * Esse metodo salva as arestas novas no grafo estático, mas pode gerar uma
     * exceção se ja houver um estacionamento e tentarem adicionar outro
     */
    void save() throws EstacionamentoException{
        double x, y;
        x = Double.valueOf(posicaoX.getText()).doubleValue();
        y = Double.valueOf(posicaoY.getText()).doubleValue();
        Ponto ponto = new Ponto(caixaNome.getText(),spinner.getValue(),x,y);
        
        try{
            grafo.adicionarPonto(ponto);
        }
        catch(DuplicateException d){
            lblAviso.setText(d.getMessage());
        }
        catch(EstacionamentoException e){
            lblAviso.setText(e.getMessage());
        }
        
        btnSalvaPonto.setDisable(true);
        for(MenuItem m: listMenuItem){
            m.setDisable(false);
        }
    }
    
    @FXML
    /**
     * Esse metodo abre uma nova janela de criação de ponto quando o usuário aperta
     * novo ponto
    */
    void adicionaNovoPonto(ActionEvent event) throws IOException {
        novaJanela("TelaAddPonto.fxml");
    }
    
    @FXML
    /**
     * Esse metodo abre uma nova janela para a remoção de uma aresta do grafo estático
    */
    void removeAresta(ActionEvent event) throws IOException {
        novaJanela("TelaRemoverAresta.fxml");
    }
    
    @FXML
      /**
     * Esse metodo abre uma nova janela para alteração de um ponto já adicionado
     */
    void alteraPonto(ActionEvent event) throws IOException {
        novaJanela("TelaAlterarPonto.fxml");
    }

    @FXML
    /**
     * Esse metodo abre uma nova janela para a remoção de um ponto do grafo estático
    */
    void removePonto(ActionEvent event) throws IOException {
        novaJanela("TelaRemoverPonto.fxml");
    }
    
    @FXML
     /**
     * Esse metodo abre uma nova janela para o calculo do menor caminho
    */
    void calculaMenorCaminho(ActionEvent event) throws IOException {
        novaJanela("TelaMenorCaminho.fxml");
    }
    
    
    @FXML
     /**
     * Esse metodo volta para a janela anterior
    */
    void voltar(ActionEvent event) throws IOException {
        novaJanela("TelaEscolha.fxml");
    }
    
    /**
     * metodo que abre uma nova janela apartir da string passada
     * @param nomejanela - nome da nova janela
     */
    void novaJanela(String nomejanela) throws IOException{
        stageAux.close();
        Parent root = FXMLLoader.load(getClass().getResource(nomejanela));
        stageAux = new Stage();
        
        Scene scene = new Scene(root);
        
        Image imagem = new Image("/imagem/icone.png");
        stageAux.getIcons().add(imagem);
        stageAux.setTitle("Forte Seguros");
        
        stageAux.setScene(scene);
        stageAux.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> gradesValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,3,0);
        this.spinner.setValueFactory(gradesValueFactory);
        
        listMenuItem = new ArrayList<>();
        listMenuItem.add(this.menuAddAresta);
        listMenuItem.add(this.menuAlteraPonto);
        listMenuItem.add(this.menuRemoveAresta);
        listMenuItem.add(this.menuRemovePonto);
        listMenuItem.add(this.menuMenorCaminho);
        
        
    }
}
