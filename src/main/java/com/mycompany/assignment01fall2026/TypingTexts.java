package com.mycompany.assignment01fall2026;

import java.util.List;

/**
 * Supplies the fixed set of practice lines used by the typing tutor.
 *
 * <p>The texts are the six sample lines given in the assignment handout. The
 * class is a pure constant holder: it has no state and cannot be instantiated.</p>
 *
 * @author Kian Dehghani
 */
public final class TypingTexts {

    private static final List<String> TEXTS = List.of(
            "Try typing this text. Do it as quickly and accurately as you can.",
            "Next type another line of input data.",
            "The quick brown fox jumps over the lazy dog.",
            "Five big quacking zephyrs jolt my wax bed.",
            "Sympathizing would fix Quaker objectives.",
            "A large fawn jumped quickly over white zinc boxes.");

    private TypingTexts() {
    }

    /**
     * Returns the practice lines in the order they should be presented.
     *
     * @return an unmodifiable list of practice lines, never empty
     */
    public static List<String> all() {
        return TEXTS;
    }

    /**
     * Returns how many practice lines are available.
     *
     * @return the number of practice lines
     */
    public static int count() {
        return TEXTS.size();
    }
}
