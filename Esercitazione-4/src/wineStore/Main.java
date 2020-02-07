package wineStore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for the application
 *
 * @author Mattia Ricci mattia.ricci1@studenti.unipr.it
 * @author Gaspare Riccardo Lo Bue gasparericcardo.lobue@studenti.unipr.it
 */
public class Main extends Application {
    Stage stage = new Stage();
    Scene scene;

    @Override
    public void start(Stage stage) throws Exception{

        this.scene = new Scene(FXMLLoader.load(getClass().getResource("/user_form.fxml")));
        this.stage.setTitle("Welcome");
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
