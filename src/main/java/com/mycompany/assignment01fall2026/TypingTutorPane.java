package com.mycompany.assignment01fall2026;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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

    /**
     * Builds the typing tutor interface.
     */
    public TypingTutorPane() {
        setPadding(new Insets(16));
        setTop(buildTextPanel());
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
