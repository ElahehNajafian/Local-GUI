package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Local;

public class App extends Application
{

    @Override
    public void start(Stage primaryStage)
    {
        // Domain Model
        Local local = new Local();

        // View
        BorderPane root = new RootBorderPane(local);
        Scene scene = new Scene(root, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Local manager");
        // primaryStage.setResizable(false);
        // primaryStage.setFullScreen(true);
        primaryStage.show();
    }

}
