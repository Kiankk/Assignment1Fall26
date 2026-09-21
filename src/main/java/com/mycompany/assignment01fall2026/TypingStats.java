package com.mycompany.assignment01fall2026;

import java.util.ArrayDeque;
import java.util.Deque;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;

/**
 * Counts how many keystrokes a user has typed correctly and incorrectly.
 *
 * <p>Every recorded keystroke is also pushed onto an undo stack so that a
 * backspace can withdraw the most recent keystroke and keep the totals in step
 * with the text actually shown in the response field.</p>
 *
 * <p>The counters are exposed as JavaFX properties so that user-interface
 * labels can bind to them instead of being refreshed by hand.</p>
 *
 * @author Kian Dehghani
 */
public class TypingStats {

    private final ReadOnlyIntegerWrapper correct = new ReadOnlyIntegerWrapper(this, "correct", 0);
    private final ReadOnlyIntegerWrapper incorrect = new ReadOnlyIntegerWrapper(this, "incorrect", 0);
    private final Deque<Boolean> history = new ArrayDeque<>();

    /**
     * Records one keystroke.
     *
     * @param wasCorrect {@code true} if the character matched the expected one
     */
    public void record(boolean wasCorrect) {
        history.push(wasCorrect);
        if (wasCorrect) {
            correct.set(correct.get() + 1);
        } else {
            incorrect.set(incorrect.get() + 1);
        }
    }

    /**
     * Withdraws the most recently recorded keystroke, if there is one.
     *
     * @return {@code true} if a keystroke was withdrawn, {@code false} if no
     *         keystrokes had been recorded
     */
    public boolean undo() {
        Boolean wasCorrect = history.poll();
        if (wasCorrect == null) {
            return false;
        }
        if (wasCorrect) {
            correct.set(correct.get() - 1);
        } else {
            incorrect.set(incorrect.get() - 1);
        }
        return true;
    }

    /**
     * Clears both counters and the undo history.
     */
    public void reset() {
        history.clear();
        correct.set(0);
        incorrect.set(0);
    }

    /**
     * Returns the share of keystrokes that were correct.
     *
     * @return a value between {@code 0.0} and {@code 1.0}; {@code 1.0} when no
     *         keystrokes have been recorded yet
     */
    public double accuracy() {
        int total = correct.get() + incorrect.get();
        return total == 0 ? 1.0 : (double) correct.get() / total;
    }

    /**
     * Returns the number of correct keystrokes as an observable property.
     *
     * @return the read-only correct-keystroke count
     */
    public ReadOnlyIntegerProperty correctProperty() {
        return correct.getReadOnlyProperty();
    }

    /**
     * Returns the number of incorrect keystrokes as an observable property.
     *
     * @return the read-only incorrect-keystroke count
     */
    public ReadOnlyIntegerProperty incorrectProperty() {
        return incorrect.getReadOnlyProperty();
    }
}
