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
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Ponto;

/**
 * Esta classe é o controller da cena na qual o usuário irá alterar um ponto do grafo
 * estático que já existe
 * 
 * @author  João Erick Barbosa Teixeira Da Silva
 * @author João Samuel Vilas Boas Góes
 */
public class TelaAlterarPontoController implements Initializable{
    @FXML // fx:id="btnAlteraPonto"
    private Button btnAlteraPonto; // Value injected by FXMLLoader

    @FXML // fx:id="lblPonto"
    private Label lblPonto; // Value injected by FXMLLoader

    @FXML // fx:id="combo"
    private ComboBox<Ponto> combo; // Value injected by FXMLLoader
    private ObservableList<Ponto> obsPonto;

    @FXML // fx:id="bntVoltar"
    private Button bntVoltar; // Value injected by FXMLLoader

    @FXML // fx:id="btnNovaAlteracao"
    private Button btnNovaAlteracao; // Value injected by FXMLLoader
    
    @FXML // fx:id="radioEstacionamento"
    private RadioButton radioEstacionamento; // Value injected by FXMLLoader

    @FXML // fx:id="radioBanco"
    private RadioButton radioBanco; // Value injected by FXMLLoader
    
    @FXML // fx:id="radioGroup"
    private Group radioGroup; // Value injected by FXMLLoader
    
    @FXML // fx:id="grupo"
    private ToggleGroup grupo; // Value injected by FXMLLoader
    
    @FXML // fx:id="lblAviso"
    private Label lblAviso; // Value injected by FXMLLoader
    
    @FXML
     /**
     * Esse metodo abre uma nova janela de alteração de ponto quando o usuário aperta
     * alterar ponto
     */
    void alteraNovoPonto(ActionEvent event) throws IOException {
        novaJanela("TelaAlterarPonto.fxml");
    }

    @FXML
      /**
     * Esse metodo altera o ponto criada pelo usuário no grafo estático
     * mas mostra um erro se o mesmo não preencher todas as lacunas da 
     * alteração
     */
    void alterarPonto(ActionEvent event) {
         System.out.println("You clicked me!");
        if(combo.getValue() == null){
            lblAviso.setText("É preciso preencher todos os dados!");
            btnAlteraPonto.setDisable(true);
        }
        else
            save();
        
    }
    
    /**
     * Esse metodo salva os pontos novos no grafo estático
     */
    void save(){
        RadioButton escolha = (RadioButton) grupo.getSelectedToggle();
        if(escolha.getText().compareTo("Estacionamento") == 0)
            grafo.alterarPontoPartidaTo(combo.getValue());
        else
            grafo.alterarToPontoChegada(combo.getValue());
        btnAlteraPonto.setDisable(true);
    }

     @FXML
    /**
     * Esse metodo volta para a janela anterior
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
        stageAux= new Stage();
        
        Scene scene = new Scene(root);
        
        Image imagem = new Image("/imagem/icone.png");
        stageAux.getIcons().add(imagem);
        stageAux.setTitle("Forte Seguros");
        
        stageAux.setScene(scene);
        stageAux.show();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        this.obsPonto = FXCollections.observableArrayList(grafo.getListaPonto());
        combo.setItems(obsPonto);
    }
}
