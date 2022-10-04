package view;

import java.io.IOException;

/**
 * This interface represents a way to display the results of our image processor
 * to the user.
 */
public interface ImageProcessorView {

  /**
   * Render a specific message to the provided data destination.
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;

  /**
   * Renders the menu for the image processor with options for user input.
   */
  void renderMenu() throws IOException;
}
