package com.mycompany.assignment01fall2026;

import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;

/**
 * Holds the state of one typing practice run.
 *
 * <p>The session knows which practice line is on screen, what the user has
 * typed so far against that line, and how accurate the typing has been. The
 * user-interface classes observe this state rather than owning it, which keeps
 * the typing rules in one place and testable on their own.</p>
 *
 * @author Kian Dehghani
 */
public class TypingSession {

    private final List<String> texts;
    private final TypingStats stats = new TypingStats();
    private final ReadOnlyIntegerWrapper index = new ReadOnlyIntegerWrapper(this, "index", 0);
    private final ReadOnlyStringWrapper currentText = new ReadOnlyStringWrapper(this, "currentText");
    private final ReadOnlyStringWrapper response = new ReadOnlyStringWrapper(this, "response", "");

    /**
     * Creates a session over the supplied practice lines.
     *
     * @param texts the lines to practise, in presentation order; must not be
     *              empty
     * @throws IllegalArgumentException if {@code texts} is empty
     */
    public TypingSession(List<String> texts) {
        if (texts.isEmpty()) {
            throw new IllegalArgumentException("A typing session needs at least one practice line.");
        }
        this.texts = List.copyOf(texts);
        this.currentText.set(this.texts.get(0));
    }

    /**
     * Moves to the next practice line, wrapping back to the first line after
     * the last one, and clears the typed response.
     */
    public void next() {
        index.set((index.get() + 1) % texts.size());
        currentText.set(texts.get(index.get()));
        response.set("");
    }

    /**
     * Returns to the first practice line and clears the typed response and all
     * accuracy counters.
     */
    public void reset() {
        index.set(0);
        currentText.set(texts.get(0));
        response.set("");
        stats.reset();
    }

    /**
     * Returns the accuracy counters for this session.
     *
     * @return the session's statistics, never {@code null}
     */
    public TypingStats stats() {
        return stats;
    }

    /**
     * Returns the practice line currently on screen.
     *
     * @return the read-only current-text property
     */
    public ReadOnlyStringProperty currentTextProperty() {
        return currentText.getReadOnlyProperty();
    }

    /**
     * Returns what the user has typed against the current line.
     *
     * @return the read-only response property
     */
    public ReadOnlyStringProperty responseProperty() {
        return response.getReadOnlyProperty();
    }

    /**
     * Returns the zero-based position of the current line.
     *
     * @return the read-only index property
     */
    public ReadOnlyIntegerProperty indexProperty() {
        return index.getReadOnlyProperty();
    }

    /**
     * Returns a one-based progress description such as {@code "2 of 6"} that
     * updates whenever the current line changes.
     *
     * @return a live progress description
     */
    public StringExpression positionLabel() {
        return Bindings.format("%d of %d", index.add(1), texts.size());
    }
}
