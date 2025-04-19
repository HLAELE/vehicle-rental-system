package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        try {
            setRoot("login-view.fxml");
            stage.setTitle("Vehicle Rental System");
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load initial scene: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void setRoot(String fxml) throws IOException {
        setRoot(fxml, 800, 600);
    }

    public static void setRoot(String fxml, int width, int height) throws IOException {
        URL fxmlUrl = HelloApplication.class.getResource("/com/example/demo1/" + fxml);
        if (fxmlUrl == null) {
            throw new IOException("FXML file not found: /com/example/demo1/" + fxml);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
