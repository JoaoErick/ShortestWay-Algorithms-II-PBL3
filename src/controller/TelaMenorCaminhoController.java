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
import static controller.PrincipalController.dijkstra;
import static controller.PrincipalController.grafo;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Ponto;
import util.MenorCaminhoException;
import util.NotFoundException;

/**
 * Esta classe é o controller da cena na qual o usuário receber a informação
 * de qual é o menor caminho
 * 
 * @author  João Erick Barbosa Teixeira Da Silva
 * @author João Samuel Vilas Boas Góes
 */
public class TelaMenorCaminhoController implements Initializable {
    
    @FXML // fx:id="btncalcula"
    private Button btncalcula; // Value injected by FXMLLoader
    private ObservableList<Ponto> obsPonto;
    private ObservableList<Ponto> obsPonto2;
    @FXML // fx:id="combo2"
    private ComboBox<Ponto> combo2; // Value injected by FXMLLoader

    @FXML // fx:id="combo1"
    private ComboBox<Ponto> combo1; // Value injected by FXMLLoader

    @FXML // fx:id="bntVoltar"
    private Button bntVoltar; // Value injected by FXMLLoader

    @FXML // fx:id="novoMenorCaminho"
    private Button novoMenorCaminho; // Value injected by FXMLLoader

    @FXML // fx:id="lblexibicao"
    private Label lblexibicao; // Value injected by FXMLLoader
    
    @FXML // fx:id="paneResults"
    private Pane paneResults; // Value injected by FXMLLoader
    
    @FXML // fx:id="paneCalculate"
    private Pane paneCalculate; // Value injected by FXMLLoader

    @FXML // fx:id="textAreaResult"
    private TextArea textAreaResult; // Value injected by FXMLLoader
    
    @FXML // fx:id="lblAviso"
    private Label lblAviso; // Value injected by FXMLLoader

    @FXML
     /**
     * metodo que abre uma nova janela calcular o menor caminho
     */
    void calculaNovoMenorCaminho(ActionEvent event) throws IOException {
        novaJanela("TelaMenorCaminho.fxml");
    }

    @FXML
     /**
     * Esse metodo chama o metodo de calcular o menor caminho, mas se o
     * usuário não preencger todos os dados ele o notifica e não mostra
     */
    void calcularMenorCaminho(ActionEvent event) throws NotFoundException, MenorCaminhoException {
        if(combo1.getValue() == null|| combo2.getValue() == null){
            lblAviso.setText("É preciso preencher todos os dados!");
            btncalcula.setDisable(true);
        }
        else
            show();
        
    }
    
    /**
     * Esse metodo calcula e mostra na tela o menor caminho
     */
    void show() throws NotFoundException, MenorCaminhoException{
        String rota = "";
        Ponto coleta = new Ponto();
        Ponto banco = new Ponto();
        List<Ponto> menorCaminho = new ArrayList<>(); 
        
        for(int i = 0; i<grafo.getListaPonto().size();i++){
            if(combo1.getValue().equals(grafo.getListaPonto().get(i)))
                coleta = grafo.getListaPonto().get(i);
            if(combo2.getValue().equals(grafo.getListaPonto().get(i)))
                banco = grafo.getListaPonto().get(i);
        }
        
        try{
            menorCaminho = dijkstra.calcularRota(grafo, coleta, banco);
        }
        catch(NotFoundException | MenorCaminhoException e){
            lblAviso.setText(e.getMessage());
            btncalcula.setDisable(true);
        }
        rota = menorCaminho.get(0).getNome();
        for(int i = 1; i<menorCaminho.size();i++){
            rota = rota + " -> " + menorCaminho.get(i).getNome();
        }
        
        paneCalculate.setVisible(false);
        paneResults.setVisible(true);
        textAreaResult.setText(rota);
        lblexibicao.setText(dijkstra.getTempoTotal()+" minutos");
    
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
        List<Ponto> listaTemp = new ArrayList<>();
        for(int i= 0; i<grafo.getListaPonto().size();i++){
            if(grafo.getListaPonto().get(i).getIdentificacao() == 2)
                listaTemp.add(grafo.getListaPonto().get(i));
        }
        this.obsPonto = FXCollections.observableArrayList(listaTemp);
        combo1.setItems(obsPonto);
        
        listaTemp.clear();
        for(int i= 0; i<grafo.getListaPonto().size();i++){
            if(grafo.getListaPonto().get(i).getIdentificacao() == 3)
                listaTemp.add(grafo.getListaPonto().get(i));
        }
        this.obsPonto = FXCollections.observableArrayList(listaTemp);
        combo2.setItems(obsPonto);
        
        
    }
}
