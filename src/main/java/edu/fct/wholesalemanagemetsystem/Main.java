package edu.fct.wholesalemanagemetsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Login");
        stage.setScene(scene);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        stage.show();

        stage.setX((dimension.width/2) - (stage.getWidth()/2));
        stage.setY((dimension.height/2) - (stage.getHeight()/2));
    }

    public static void main(String[] args) {
        launch();
    }
}