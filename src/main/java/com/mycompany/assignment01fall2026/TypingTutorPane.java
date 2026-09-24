package com.mycompany.assignment01fall2026;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * The main user interface of the typing tutor.
 *
 * <p>The pane shows the practice line the user has to copy and a second field
 * that echoes what the user has typed so far. Both fields are read-only: the
 * response field is filled from the application's own keyboard handlers rather
 * than by the text field's built-in editing, which keeps every keystroke under
 * the control of the {@link TypingSession}.</p>
 *
 * @author Kian Dehghani
 */
public class TypingTutorPane extends BorderPane {

    private final TypingSession session = new TypingSession(TypingTexts.all());
    private final TextField promptField = new TextField();
    private final TextField responseField = new TextField();
    private final Label counterLabel = new Label();
    private final Button nextButton = new Button("Next");
    private final Button resetButton = new Button("Reset");
    private final Label keyValueLabel = new Label();
    private final Label statusLabel = new Label();
    private final VirtualKeyboard keyboard = new VirtualKeyboard();
    private final Label statsLabel = new Label();
    private final EventHandler<KeyEvent> pressedHandler = this::handleKeyPressed;
    private final EventHandler<KeyEvent> releasedHandler = this::handleKeyReleased;

    /**
     * Builds the typing tutor interface.
     */
    public TypingTutorPane() {
        setPadding(new Insets(16));
        setTop(buildTextPanel());
        setCenter(buildKeyPanel());
        setBottom(buildControlBar());
        listenForKeyEvents();
    }

    private void listenForKeyEvents() {
        sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (oldScene != null) {
                oldScene.removeEventHandler(KeyEvent.KEY_PRESSED, pressedHandler);
                oldScene.removeEventHandler(KeyEvent.KEY_RELEASED, releasedHandler);
            }
            if (newScene != null) {
                newScene.addEventHandler(KeyEvent.KEY_PRESSED, pressedHandler);
                newScene.addEventHandler(KeyEvent.KEY_RELEASED, releasedHandler);
            }
        });
    }

    private void handleKeyPressed(KeyEvent event) {
        keyboard.press(event.getCode());
        keyValueLabel.setText(keyboard.captionOf(event.getCode()));
    }

    private void handleKeyReleased(KeyEvent event) {
        keyboard.release(event.getCode());
    }

    private String describeAccuracy() {
        TypingStats stats = session.stats();
        return String.format("Correct: %d    Incorrect: %d    Accuracy: %.0f%%",
                stats.correctProperty().get(),
                stats.incorrectProperty().get(),
                stats.accuracy() * 100);
    }

    private Node buildKeyPanel() {
        Label caption = new Label("LAST KEY");
        caption.getStyleClass().add("section-label");
        keyValueLabel.getStyleClass().add("key-value-label");
        statusLabel.getStyleClass().add("status-label");

        HBox keyRow = new HBox(10, caption, keyValueLabel, statusLabel);
        keyRow.setAlignment(Pos.CENTER_LEFT);
        keyRow.setPadding(new Insets(0, 0, 12, 0));

        VBox panel = new VBox(10, keyRow, keyboard);
        panel.setAlignment(Pos.TOP_CENTER);
        return panel;
    }

    private Node buildControlBar() {
        counterLabel.getStyleClass().add("counter-label");
        counterLabel.textProperty().bind(session.positionLabel());

        nextButton.setFocusTraversable(false);
        nextButton.setOnAction(event -> session.next());

        resetButton.setFocusTraversable(false);
        resetButton.setOnAction(event -> session.reset());

        statsLabel.getStyleClass().add("stats-label");
        statsLabel.textProperty().bind(Bindings.createStringBinding(
                this::describeAccuracy,
                session.stats().correctProperty(),
                session.stats().incorrectProperty()));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox bar = new HBox(10, counterLabel, nextButton, resetButton, spacer, statsLabel);
        bar.setAlignment(Pos.CENTER_LEFT);
        bar.setPadding(new Insets(14, 0, 0, 0));
        return bar;
    }

    private Node buildTextPanel() {
        Label title = new Label("Typing Tutor");
        title.getStyleClass().add("title-label");

        Label promptCaption = new Label("TEXT TO TYPE");
        promptCaption.getStyleClass().add("section-label");
        promptField.getStyleClass().add("prompt-field");
        promptField.setEditable(false);
        promptField.setFocusTraversable(false);
        promptField.textProperty().bind(session.currentTextProperty());

        Label responseCaption = new Label("YOUR TYPING");
        responseCaption.getStyleClass().add("section-label");
        responseField.getStyleClass().add("response-field");
        responseField.setEditable(false);
        responseField.setFocusTraversable(false);
        responseField.textProperty().bind(session.responseProperty());

        VBox panel = new VBox(6, title, promptCaption, promptField, responseCaption, responseField);
        panel.setPadding(new Insets(0, 0, 14, 0));
        return panel;
    }
}
