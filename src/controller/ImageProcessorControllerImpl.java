package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.ImageProcessorModel;
import model.Light;
import model.Pixel;
import view.ImageProcessorView;

/**
 * This class represents a controller for the Image Processor that can take user input and call the
 * appropriate operations on the view and model.
 */
public class ImageProcessorControllerImpl implements ImageProcessorController {

  protected final ImageProcessorModel model;
  protected final ImageProcessorView view;
  protected Scanner scan;

  /**
   * ImageProcessorControllerImpl constructor that takes a readable to pass to the scanner.
   *
   * @param model the model to be used.
   * @param view  the view for the image processor.
   * @param rd    the input to be used.
   */
  public ImageProcessorControllerImpl(ImageProcessorModel model,
      ImageProcessorView view, Readable rd) {
    if (model == null || view == null || rd == null) {
      throw new IllegalArgumentException("Null parameters");
    }
    this.model = model;
    this.view = view;
    this.scan = new Scanner(rd);
  }

  /**
   * Get the next string input.
   *
   * @return the next input as a string
   * @throws IllegalStateException if there are no more inputs
   */
  protected String getNextString() throws IllegalStateException {
    if (scan.hasNext()) {
      return scan.next();
    } else {
      throw new IllegalStateException("No more inputs");
    }
  }

  /**
   * Get the next integer input.
   *
   * @return the next integer input
   * @throws IllegalStateException if there are no more inputs
   */
  private int getNextInt() {
    if (scan.hasNext()) {
      return scan.nextInt();
    } else {
      throw new IllegalStateException("No more inputs");
    }
  }

  /**
   * Save the given image at the given filepath.
   *
   * @param filepath  the location of the new image
   * @param imageName the name of the image to be saved
   * @throws IllegalArgumentException if the filepath is not a ppm file.
   * @throws IllegalStateException    if the model cannot be converted to a ppm file.
   */
  protected void saveImage(String filepath, String imageName)
      throws IllegalArgumentException, IllegalStateException {
    if (!filepath.toLowerCase().endsWith(".ppm")) {
      throw new IllegalArgumentException("Invalid filepath");
    }
    try {
      String str = this.model.convertToPPM(imageName);
      BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
      writer.write(str);
      writer.close();
    } catch (IOException e) {
      throw new IllegalStateException("Model cannot be converted into a ppm file: "
          + e.getMessage());
    }
  }

  /**
   * To process the command entered by the user and do operations given the command.
   *
   * @param command the command to be processed
   * @return whether or not the command was to quit.
   * @throws IOException if writing to the view fails.
   */
  protected boolean processCommand(String command) throws IOException {
    String filepath;
    String imageName;
    String newImageName;
    switch (command.toLowerCase()) {
      case "q":
      case "quit":
        this.view.renderMessage("Quit image processor\n");
        return true;
      case "load":
        filepath = this.getNextString();
        imageName = this.getNextString();
        try {
          List<List<Pixel>> image = ImageUtil.readFile(filepath);
          this.model.loadImage(imageName, image);
          this.view.renderMessage("Load Operation Successful!\n");
        } catch (IllegalArgumentException iae) {
          System.out.print(iae);
          this.view.renderMessage(iae.getMessage() + "\n");
        }
        break;
      case "brighten":
        int increment = this.getNextInt();
        imageName = this.getNextString();
        newImageName = this.getNextString();
        try {
          this.model.brighten(increment, imageName, newImageName);
          this.view.renderMessage("Brighten Operation Successful!\n");
        } catch (IllegalArgumentException iae) {
          this.view.renderMessage(iae.getMessage() + "\n");
        }
        break;
      case "vertical-flip":
        imageName = this.getNextString();
        newImageName = this.getNextString();
        try {
          this.model.flipImage(true, imageName, newImageName);
          this.view.renderMessage("Vertical Flip Operation Successful!\n");
        } catch (IllegalArgumentException iae) {
          this.view.renderMessage(iae.getMessage() + "\n");
        }
        break;
      case "horizontal-flip":
        imageName = this.getNextString();
        newImageName = this.getNextString();
        try {
          this.model.flipImage(false, imageName, newImageName);
          this.view.renderMessage("Horizontal Flip Operation Successful!\n");
        } catch (IllegalArgumentException iae) {
          this.view.renderMessage(iae.getMessage() + "\n");
        }
        break;
      case "grey-scale":
        String component = this.getNextString();
        imageName = this.getNextString();
        newImageName = this.getNextString();
        try {
          this.model.greyscaleComponent(Light.fromString(component), imageName, newImageName);
          this.view.renderMessage("Greyscale Operation Successful!\n");
        } catch (IllegalArgumentException iae) {
          this.view.renderMessage(iae.getMessage() + "\n");
        }
        break;
      case "save":
        filepath = this.getNextString();
        imageName = this.getNextString();
        try {
          this.saveImage(filepath, imageName);
          this.view.renderMessage("Save Operation Successful!\n");
        } catch (IllegalArgumentException | IllegalStateException e) {
          this.view.renderMessage(e.getMessage() + "\n");
        }
        break;
      default:
        this.view.renderMessage("Invalid command: " + command
            + "\nPlease enter a new valid command.\n");
        break;
    }
    return false;
  }

  /**
   *
   * @throws IllegalStateException
   */
  @Override
  public void runImageProcessor() throws IllegalStateException {
    boolean processorQuit = false;
    try {
      while (!processorQuit) {
        this.view.renderMenu();
        try {
          String command = this.getNextString();
          processorQuit = this.processCommand(command);
        } catch (InputMismatchException e) {
          this.view.renderMessage("Invalid command: Please enter a new valid command.\n");
        }
      }
    } catch (IOException e) {
      throw new IllegalStateException("IOException" + e);
    }
  }
}
