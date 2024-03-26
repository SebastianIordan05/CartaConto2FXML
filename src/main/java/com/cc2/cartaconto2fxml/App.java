package com.cc2.cartaconto2fxml;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import model.Conto;
import model.Intestatario;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 640, 480);
        stage.setTitle("CartaConto2FXML");
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void stop() {
        Intestatario.saveIntestatari(Intestatario.intestatari, new File(Intestatario.FILE_PATH));
        Conto.saveConti(Conto.conti, new File(Conto.FILE2_PATH));
        System.out.println("Intestatari saved on exit: " + Intestatario.intestatari.toString());
        System.out.println("Conti saved on exit: " + Conto.conti.toString());
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}