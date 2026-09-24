package com.mycompany.assignment01fall2026;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point of the Typing Tutor application.
 *
 * <p>The class builds the primary window around a {@link TypingTutorPane},
 * attaches the stylesheet and gives the pane keyboard focus so that key events
 * reach the tutor as soon as the window opens.</p>
 *
 * @author Kian Dehghani
 */
public class App extends Application {

    private static final double WINDOW_WIDTH = 900;
    private static final double WINDOW_HEIGHT = 620;

    /**
     * Builds and shows the main window.
     *
     * @param stage the primary stage supplied by the JavaFX runtime
     */
    @Override
    public void start(Stage stage) {
        TypingTutorPane tutor = new TypingTutorPane();
        Scene scene = new Scene(tutor, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add(App.class.getResource("styles.css").toExternalForm());

        stage.setTitle("Typing Tutor");
        stage.setScene(scene);
        stage.setMinWidth(WINDOW_WIDTH);
        stage.setMinHeight(WINDOW_HEIGHT);
        stage.show();
        tutor.requestFocus();
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments, unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
