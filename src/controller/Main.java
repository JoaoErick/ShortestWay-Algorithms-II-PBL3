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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Esta é a classe main que é por onde o aplicativo é executado chamando a primeira cena
 * 
 * @author  João Erick Barbosa Teixeira Da Silva
 * @author João Samuel Vilas Boas Góes
 */
public class Main extends Application {
    public static Stage stageAux = new Stage(); //Objeto estático utilizado para a mudança entre as janelas.
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        stageAux = stage;
        
        Scene scene = new Scene(root);
        
        Image imagem = new Image("/imagem/icone.png");
        stage.getIcons().add(imagem);
        stage.setTitle("Forte Seguros");
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
