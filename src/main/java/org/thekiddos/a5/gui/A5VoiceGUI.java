package org.thekiddos.a5.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class A5VoiceGUI extends Application {
    @Override
    public void start( Stage stage ) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader( A5VoiceGUI.class.getResource( "view.fxml" ) );
        Scene scene = new Scene( fxmlLoader.load() );
        stage.setTitle( "A5 Cipher!" );
        stage.setScene( scene );
        stage.show();
    }

    public static void main( String[] args ) {
        launch();
    }
}
