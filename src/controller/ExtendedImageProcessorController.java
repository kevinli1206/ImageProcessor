package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.BetterImageProcessorModel;
import view.ImageProcessorView;

/**
 * To represent an ImageProcessorController with the additional supported operations of blur,
 * sharpen, and sepia as well as allowing saving png, jpg, and bmp images.
 */
public class ExtendedImageProcessorController extends ImageProcessorControllerImpl {

  private final BetterImageProcessorModel model;

  /**
   * ImageProcessorControllerImpl constructor that takes a readable to pass to the scanner.
   *
   * @param model the model to be used.
   * @param view  the view for the image processor.
   * @param rd    the input to be used.
   */
  public ExtendedImageProcessorController(BetterImageProcessorModel model,
      ImageProcessorView view, Readable rd) {
    super(model, view, rd);
    this.model = model;
  }

  @Override
  protected boolean processCommand(String command) throws IOException {
    String imageName;
    String newImageName;
    switch (command) {
      case "blur":
        imageName = this.getNextString();
        newImageName = this.getNextString();
        try {
          this.model.blurImage(imageName, newImageName);
          this.view.renderMessage("Blur Operation Successful!\n");
        } catch (IllegalArgumentException iae) {
          this.view.renderMessage(iae.getMessage() + "\n");
        }
        break;
      case "sharpen":
        imageName = this.getNextString();
        newImageName = this.getNextString();
        try {
          this.model.sharpenImage(imageName, newImageName);
          this.view.renderMessage("Sharpen Operation Successful!\n");
        } catch (IllegalArgumentException iae) {
          this.view.renderMessage(iae.getMessage() + "\n");
        }
        break;
      case "sepia":
        imageName = this.getNextString();
        newImageName = this.getNextString();
        try {
          this.model.sepiaImage(imageName, newImageName);
          this.view.renderMessage("Sepia Operation Successful!\n");
        } catch (IllegalArgumentException iae) {
          this.view.renderMessage(iae.getMessage() + "\n");
        }
        break;
      default:
        return super.processCommand(command);
    }
    return false;
  }

  /**
   * Save the given image at the given filepath.
   *
   * @param filepath  the location of the new image
   * @param imageName the name of the image to be saved
   * @throws IllegalArgumentException if the filepath is not a ppm file.
   * @throws IllegalStateException    if the model cannot be converted to a ppm file.
   */
  @Override
  protected void saveImage(String filepath, String imageName)
      throws IllegalArgumentException, IllegalStateException {
    String lowerFile = filepath.toLowerCase();
    try {
      if (lowerFile.endsWith(".ppm")) {
        super.saveImage(filepath, imageName);
      } else if (lowerFile.endsWith(".png")) {
        BufferedImage image = this.model.getBufferedImage(imageName);
        ImageIO.write(image, "png", new File(filepath));
      } else if (lowerFile.endsWith(".jpg") || lowerFile.endsWith(".jpeg")) {
        BufferedImage image = this.model.getBufferedImage(imageName);
        ImageIO.write(image, "jpg", new File(filepath));
      } else if (lowerFile.endsWith(".bmp")) {
        BufferedImage image = this.model.getBufferedImage(imageName);
        ImageIO.write(image, "bmp", new File(filepath));
      } else {
        throw new IllegalArgumentException("Invalid filepath");
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("IOException: " + e.getMessage());
    }
  }
}
