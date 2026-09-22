package com.mycompany.assignment01fall2026;

import javafx.css.PseudoClass;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.input.KeyCode;

/**
 * A single key of the on-screen keyboard.
 *
 * <p>A virtual key is an ordinary JavaFX {@link Button} that remembers which
 * physical {@link KeyCode} it stands for and that can be lit up while that
 * physical key is held down. The lit state is exposed as the CSS pseudo-class
 * {@code :key-down} so the appearance lives in the stylesheet rather than in
 * Java code.</p>
 *
 * <p>Virtual keys never take keyboard focus: focus has to stay on the scene so
 * that every key press reaches the application's own event handlers.</p>
 *
 * @author Kian Dehghani
 */
public class VirtualKey extends Button {

    /** Width in pixels of a standard one-unit key such as a letter key. */
    public static final double KEY_UNIT = 44.0;

    private static final PseudoClass KEY_DOWN = PseudoClass.getPseudoClass("key-down");

    private final KeyCode code;

    /**
     * Creates a key for the given code.
     *
     * @param code    the physical key this button stands for
     * @param caption the text shown on the key face
     * @param units   the width of the key in standard key widths, where
     *                {@code 1.0} is the width of a letter key
     */
    public VirtualKey(KeyCode code, String caption, double units) {
        super(caption);
        this.code = code;
        getStyleClass().add("virtual-key");
        setFocusTraversable(false);
        setMinWidth(Region.USE_PREF_SIZE);
        setPrefWidth(KEY_UNIT * units);
    }

    /**
     * Returns the physical key this button stands for.
     *
     * @return the key code, never {@code null}
     */
    public KeyCode getCode() {
        return code;
    }

    /**
     * Lights the key up or returns it to its normal appearance.
     *
     * @param down {@code true} while the physical key is held down
     */
    public void setKeyDown(boolean down) {
        pseudoClassStateChanged(KEY_DOWN, down);
    }
}
