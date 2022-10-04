package controller;

/**
 * This interface represents a way to take in user input for an Image Processor to do
 * operations on the images.
 */
public interface ImageProcessorController {

  /**
   * Run operations based on the user inputs and provide feedback for the user.
   *
   * @throws IllegalStateException if unable to read input or transmit output
   */
  void runImageProcessor() throws IllegalStateException;

}
