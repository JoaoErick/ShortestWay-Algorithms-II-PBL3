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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Aresta;
import model.Ponto;
import util.DuplicateException;

/**
 * Esta classe é o controller da cena na qual o usuário irá adicionar uma aresta
 * no grafo que está criando.
 * 
 * @author  João Erick Barbosa Teixeira Da Silva
 * @author João Samuel Vilas Boas Góes
 */
public class TelaAddArestaController implements Initializable{
    @FXML // fx:id="bntVoltar"
    private Button bntVoltar; // Value injected by FXMLLoader
    
    @FXML // fx:id="btnSalvaAresta"
    private Button btnSalvaAresta; // Value injected by FXMLLoader

    @FXML // fx:id="origem"
    private Label origem; // Value injected by FXMLLoader

    @FXML // fx:id="destino"
    private Label destino; // Value injected by FXMLLoader

    @FXML // fx:id="combo1"
    private ComboBox<Ponto> combo1; // Value injected by FXMLLoader
    private ObservableList<Ponto> obsPonto;

    @FXML // fx:id="combo2"
    private ComboBox<Ponto> combo2; // Value injected by FXMLLoader
    
    @FXML // fx:id="lblDuracao"
    private Label lblDuracao; // Value injected by FXMLLoader

    @FXML // fx:id="spinnerTempo"
    private Spinner<Integer> spinnerTempo; // Value injected by FXMLLoader
   
    @FXML
    private Button novoAresta;
    
    @FXML // fx:id="lblAviso"
    private Label lblAviso; // Value injected by FXMLLoader

    @FXML
     /**
     * Esse metodo abre uma nova janela quando o usuário aperta
     * nova aresta
     */
    void adicionaNovaAresta(ActionEvent event) throws IOException {
        novaJanela("TelaAddAresta.fxml");
    }
    
    @FXML
     /**
     * Esse metodo salva a aresta criada pelo usuário no grafo estático
     * mas mostra um erro se o mesmo não preencher todas as lacunas da 
     * criação
     */
    void salvarAresta(ActionEvent event) {
        System.out.println("You clicked me!");
        if(combo1.getValue() == null|| combo2.getValue() == null){
            lblAviso.setText("É preciso preencher todos os dados!");
            btnSalvaAresta.setDisable(true);
        }
        else
            save();
    }
    
    /**
     * Esse metodo salva as arestas novas no grafo estático
     */
    void save(){
        Aresta aresta;
        Aresta aresta2;
        
        try{
            aresta = new Aresta(combo1.getValue(), combo2.getValue(), spinnerTempo.getValue());
            aresta2 = new Aresta(combo2.getValue(), combo1.getValue(), spinnerTempo.getValue());
            grafo.getListaTotalAresta().add(aresta);
            grafo.getListaTotalAresta().add(aresta2);
        }
        catch(DuplicateException e){
            lblAviso.setText(e.getMessage());
        }
        
        btnSalvaAresta.setDisable(true);
    }

    @FXML
     /**
     * Esse metodo abre uma janela para adicionar um ponto
     * @param event - evento gerado quando o botão de adicionar tela é pressionado 
     */
    void voltar(ActionEvent event) throws IOException {
        novaJanela("TelaAddPonto.fxml");
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
        SpinnerValueFactory<Integer> gradesValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1440,10);
        this.spinnerTempo.setValueFactory(gradesValueFactory2);
        
        this.obsPonto = FXCollections.observableArrayList(grafo.getListaPonto());
        combo1.setItems(obsPonto);
        combo2.setItems(obsPonto);
        
        
    }
}
