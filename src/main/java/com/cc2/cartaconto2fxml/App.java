package com.cc2.cartaconto2fxml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import model.Conto;
import model.Intestatario;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    final private static String FILE_PATH = "./.intestatari"; // path del file
    final private static String FILE2_PATH = "./.conti";
    public static Map<String, Intestatario> intestatari = loadIntestatari(new File(FILE_PATH));
    public static Map<String, Conto> conti = loadConti(new File(FILE2_PATH));

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void stop() {
        saveIntestatari(intestatari, new File(FILE_PATH));
        saveConti(conti, new File(FILE2_PATH));
        System.out.println("Intestatari saved on exit: " + intestatari.toString());
        System.out.println("Conti saved on exit: " + conti.toString());
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
     
    private static Map<String, Intestatario> loadIntestatari(final File f) {
        try {
            if (!f.exists()) {
                f.createNewFile();
                return new HashMap<>();
            }
            
            if (!f.canRead()) {
                return new HashMap<>();
            }
            
            final ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(f));
            final Map<String, Intestatario> intestatario = (Map<String, Intestatario>) inputStream.readObject();
//            System.out.println("Intestatari: " + intestatario);
            
            return intestatario;

        } catch (final IOException | ClassNotFoundException ex) {
        }

        return new HashMap<>();
    }
    
    // serializzazione dei Libri in un file 
    private static void saveIntestatari(final Map<String, Intestatario> intestatari, final File f) {
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            
            if (!f.canWrite()) {
                return;
            }
            
            final ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(f));
            outputStream.writeObject(intestatari);
        } catch (final IOException ex) {
        }
    }
    
    private static Map<String, Conto> loadConti(final File f) {
        try {
            if (!f.exists()) {
                f.createNewFile();
                return new HashMap<>();
            }
            
            if (!f.canRead()) {
                return new HashMap<>();
            }
            
            final ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(f));
            final Map<String, Conto> conto = (Map<String, Conto>) inputStream.readObject();
//            System.out.println("Intestatari: " + intestatario);
            
            return conto;

        } catch (final IOException | ClassNotFoundException ex) {
        }

        return new HashMap<>();
    }
    
    private static void saveConti(final Map<String, Conto> conti, final File f) {
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            
            if (!f.canWrite()) {
                return;
            }
            
            final ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(f));
            outputStream.writeObject(conti);
        } catch (final IOException ex) {
        }
    }

}