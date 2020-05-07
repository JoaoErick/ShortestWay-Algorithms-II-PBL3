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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Aresta;
import util.NotFoundException;

/**
 * Esta classe é o controller da cena na qual o usuário irá remover uma aresta
 * no grafo que está criando.
 * 
 * @author  João Erick Barbosa Teixeira Da Silva
 * @author João Samuel Vilas Boas Góes
 */
public class TelaRemoverArestaController implements Initializable{
    private ObservableList<Aresta> obsAresta;
    @FXML // fx:id="btnRemoveAresta"
    private Button btnRemoveAresta; // Value injected by FXMLLoader

    @FXML // fx:id="lblAresta"
    private Label lblAresta; // Value injected by FXMLLoader

    @FXML // fx:id="combo"
    private ComboBox<Aresta> combo; // Value injected by FXMLLoader

    @FXML // fx:id="bntVoltar"
    private Button bntVoltar; // Value injected by FXMLLoader

    @FXML // fx:id="btnNovaRemocao"
    private Button btnNovaRemocao; // Value injected by FXMLLoader

    @FXML // fx:id="lblAviso"
    private Label lblAviso; // Value injected by FXMLLoader

    @FXML
     /**
     * Esse metodo chama a janela que o usuário usa para remover uma nova
     * aresta
     */
    void adicionaNovaAresta(ActionEvent event) throws IOException {
        novaJanela("TelaRemoverAresta.fxml");
    }

    @FXML
     /**
     * Esse metodo por meio da ação de um botão chama o metodo de remover uma 
     * aresta, mas apenas se todos os dados forem preenchidos
     */
    void removerAresta(ActionEvent event) throws NotFoundException {
        System.out.println("You clicked me!");
        if(combo.getValue() == null){
            lblAviso.setText("É preciso preencher todos os dados!");
            btnRemoveAresta.setDisable(true);
        }
        else
            save();
        
    }
    
     /**
     * Esse metodo salva as arestas que foram retiradas no grafo estático
     */
    void save(){
        try{
            grafo.removerLigacao(combo.getValue());
            grafo.getListaTotalAresta().remove(combo.getValue());
        }
        catch(NotFoundException e){
            lblAviso.setText(e.getMessage());
            btnRemoveAresta.setDisable(true);
        }
        
        btnRemoveAresta.setDisable(true);
        if(grafo.getListaTotalAresta().isEmpty())
            btnNovaRemocao.setDisable(true);
    }
    
    @FXML
     /**
     * Esse metodo chama a janela que o usuário usa para adicionar uma nova
     * aresta
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
        
        this.obsAresta = FXCollections.observableArrayList(grafo.getListaTotalAresta());
        combo.setItems(obsAresta);
    }
}
