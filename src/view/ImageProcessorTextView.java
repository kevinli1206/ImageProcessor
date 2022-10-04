package view;

import java.io.IOException;

/**
 * Represents a way to display the command line instructions as text to the user.
 */
public class ImageProcessorTextView implements ImageProcessorView {

  private final Appendable destination;

  /**
   * ImageProcessorTextView constructor with the given appendable.
   * @param destination the place where the view shows outputs to the user.
   * @throws IllegalArgumentException if the given destination is null
   */
  public ImageProcessorTextView(Appendable destination) {
    if (destination == null) {
      throw new IllegalArgumentException("Null destination");
    }
    this.destination = destination;
  }


  @Override
  public void renderMessage(String message) throws IOException {
    if (message == null) {
      return;
    }
    try {
      this.destination.append(message);
    } catch (IOException e) {
      throw new IOException("Transmission failed");
    }
  }

  @Override
  public void renderMenu() throws IOException {
    this.destination.append("Enter one of the following commands: \n");
    this.destination.append("q or quit : quits the program\n");
    this.destination.append("load filepath imageName : loads an image with a chosen name\n");
    this.destination.append("brighten increment imageName newImageName : brightens a chosen image" +
            " by a chosen amount\n");
    this.destination.append("vertical-flip imageName newImageName : flips a chosen image " +
            "vertically\n");
    this.destination.append("horizontal-flip imageName newImageName : flips a chosen image " +
            "horizontally\n");
    this.destination.append("grey-scale component imageName newImageName : creates a greyscale" +
            " version of a chosen image based on a given component (red, green, blue, luma," +
            " value, or intensity)\n");
    this.destination.append("save filepath imageName : save a chose image to a specified" +
            " filepath\n");
  }
}
