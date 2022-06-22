package application;

import javafx.scene.control.Alert;

public abstract class AlertHelper
{
    public static void showAlert(Alert.AlertType type, String message)
    {
        System.out.println("About clicked");
        Alert alert = new Alert(type);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
