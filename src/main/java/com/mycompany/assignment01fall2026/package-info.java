/**
 * A JavaFX typing tutor built for Assignment 01 of Program Development in a
 * Graphical Environment.
 *
 * <p>The package is split into a small model and a thin user interface:</p>
 *
 * <ul>
 *   <li>{@link com.mycompany.assignment01fall2026.TypingTexts} supplies the
 *       practice lines.</li>
 *   <li>{@link com.mycompany.assignment01fall2026.TypingSession} tracks which
 *       line is on screen and what the user has typed against it.</li>
 *   <li>{@link com.mycompany.assignment01fall2026.TypingStats} counts correct
 *       and incorrect keystrokes and supports undoing the last one.</li>
 *   <li>{@link com.mycompany.assignment01fall2026.VirtualKey} and
 *       {@link com.mycompany.assignment01fall2026.VirtualKeyboard} draw the
 *       on-screen keyboard and light up the key being held down.</li>
 *   <li>{@link com.mycompany.assignment01fall2026.TypingTutorPane} wires the
 *       keyboard events to the session, and
 *       {@link com.mycompany.assignment01fall2026.App} opens the window.</li>
 * </ul>
 *
 * @author Kian Dehghani
 */
package com.mycompany.assignment01fall2026;
