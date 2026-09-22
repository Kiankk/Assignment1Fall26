package com.mycompany.assignment01fall2026;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * An on-screen keyboard built from {@link VirtualKey} buttons.
 *
 * <p>The layout covers the part of a keyboard a typing tutor needs: the number
 * row, the three letter rows, both shift keys and the space bar. Keys that the
 * assignment allows to be left out, such as the function keys, the arrow keys
 * and the numeric keypad, are deliberately absent so that pressing one of them
 * can be reported as unhandled.</p>
 *
 * <p>A code may map to more than one button, which is what lets the left and
 * right shift keys light up together.</p>
 *
 * @author Kian Dehghani
 */
public class VirtualKeyboard extends VBox {

    private final Map<KeyCode, List<VirtualKey>> keysByCode = new EnumMap<>(KeyCode.class);

    /**
     * Builds the keyboard and lays its rows out from top to bottom.
     */
    public VirtualKeyboard() {
        getStyleClass().add("virtual-keyboard");
        setSpacing(6);
        setAlignment(Pos.CENTER);
        getChildren().add(numberRow());
    }

    private HBox numberRow() {
        return row(
                key(KeyCode.BACK_QUOTE, "`", 1),
                key(KeyCode.DIGIT1, "1", 1),
                key(KeyCode.DIGIT2, "2", 1),
                key(KeyCode.DIGIT3, "3", 1),
                key(KeyCode.DIGIT4, "4", 1),
                key(KeyCode.DIGIT5, "5", 1),
                key(KeyCode.DIGIT6, "6", 1),
                key(KeyCode.DIGIT7, "7", 1),
                key(KeyCode.DIGIT8, "8", 1),
                key(KeyCode.DIGIT9, "9", 1),
                key(KeyCode.DIGIT0, "0", 1),
                key(KeyCode.MINUS, "-", 1),
                key(KeyCode.EQUALS, "=", 1),
                key(KeyCode.BACK_SPACE, "Backspace", 2));
    }

    private VirtualKey key(KeyCode code, String caption, double units) {
        VirtualKey virtualKey = new VirtualKey(code, caption, units);
        keysByCode.computeIfAbsent(code, unused -> new ArrayList<>()).add(virtualKey);
        return virtualKey;
    }

    private HBox row(VirtualKey... keys) {
        HBox box = new HBox(6, keys);
        box.getStyleClass().add("key-row");
        box.setAlignment(Pos.CENTER);
        return box;
    }

    /**
     * Reports whether this keyboard has a button for the given code.
     *
     * @param code the code of a physical key
     * @return {@code true} if the key is represented on screen
     */
    public boolean handles(KeyCode code) {
        return keysByCode.containsKey(code);
    }

    /**
     * Lights up every button that stands for the given code.
     *
     * @param code the code of the physical key being held down
     */
    public void press(KeyCode code) {
        setKeyDown(code, true);
    }

    /**
     * Returns every button that stands for the given code to its normal
     * appearance.
     *
     * @param code the code of the physical key being released
     */
    public void release(KeyCode code) {
        setKeyDown(code, false);
    }

    /**
     * Returns every button to its normal appearance.
     *
     * <p>This is used when the window loses focus, because key releases that
     * happen while another window is active never reach this application.</p>
     */
    public void releaseAll() {
        keysByCode.values().forEach(keys -> keys.forEach(key -> key.setKeyDown(false)));
    }

    private void setKeyDown(KeyCode code, boolean down) {
        List<VirtualKey> keys = keysByCode.get(code);
        if (keys != null) {
            keys.forEach(key -> key.setKeyDown(down));
        }
    }
}
