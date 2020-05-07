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
import static controller.PrincipalController.leitor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.DuplicateException;
import util.LeitorException;

/**
 * Esta classe é o controller da cena na qual o usuário irá importar um grafo já
 * existente
 * 
 * @author  João Erick Barbosa Teixeira Da Silva
 * @author João Samuel Vilas Boas Góes
 */
public class TelaLeituraController {
    @FXML // fx:id="btnselecao"
    private Button btnselecao; // Value injected by FXMLLoader

    @FXML // fx:id="btnvoltar"
    private Button btnvoltar; // Value injected by FXMLLoader

    @FXML // fx:id="btnSalvaArquivo"
    private Button btnSalvaArquivo; // Value injected by FXMLLoader

    @FXML // fx:id="lblAviso"
    private Label lblAviso; // Value injected by FXMLLoader

    @FXML
     /**
     * metodo que abre uma janela para escolher os arquivos .txt
     * para criar importar um grafo
     */
    void salvar(ActionEvent event) throws FileNotFoundException, IOException, DuplicateException, LeitorException   {
        if(leitor.getFile() == null|| leitor.getFile2() == null){
            lblAviso.setText("É preciso preencher todos os dados!");
            btnSalvaArquivo.setDisable(true);
        }
        else
            save();
    }
    
    /**
     * metodo que salva o novo grafo
     */
    void save() throws FileNotFoundException, IOException, DuplicateException{
        try{
            leitor.lerArquivos(grafo);
            novaJanela("TelaAddPonto.fxml");
        }
        catch(LeitorException e){
            lblAviso.setText(e.getMessage());
        }
        btnSalvaArquivo.setDisable(true);
    }
    
    @FXML
     /**
     * Esse metodo abrirá uma janela para o usuário escolher
     * o arquivo que ele vai importar para fazer o grafo
    */
    void selecionarTXT(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
        File f1 = fc.showOpenDialog(null);
        File f2 = fc.showOpenDialog(null);
        leitor.setFile(f1);
        leitor.setFile2(f2);
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
}
