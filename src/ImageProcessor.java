import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

import controller.ExtendedImageProcessorController;
import controller.GUIImageProcessorController;
import controller.ImageProcessorController;
import model.BetterImageProcessor;
import model.HistogramImageProcessor;
import model.HistogramImageProcessorModel;
import view.ImageProcessorGUI;
import view.ImageProcessorGUIView;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

/**
 * Class to hold the main method for running the image processor.
 */
public class ImageProcessor {

  /**
   * Main method which builds a model and view and instantiates the controller with them.
   *
   * @param args Arguments from the command line, currently unused
   */
  public static void main(String[] args) {
    ImageProcessorController controller;
    if (args.length == 0) {
      HistogramImageProcessorModel model = new HistogramImageProcessor();
      ImageProcessorGUIView view = new ImageProcessorGUI();
      controller = new GUIImageProcessorController(model, view);
    } else {
      BetterImageProcessor model = new BetterImageProcessor();
      ImageProcessorView view = new ImageProcessorTextView(System.out);
      Readable input;
      if (args.length > 1 && args[0].equals("-file")) {
        try {
          String filepath = args[1];
          Path file = Path.of(filepath);
          String content = Files.readString(file) + " q";
          input = new StringReader(content);
        } catch (IOException e) {
          throw new IllegalArgumentException("Unable to read instruction file: " + e.getMessage());
        }
      } else if (args.length == 1 && args[0].equals("-text")) {
        input = new InputStreamReader(System.in);
      }
      else {
        throw new IllegalArgumentException("Invalid command line arguments");
      }
      controller = new ExtendedImageProcessorController(model, view, input);
    }
    controller.runImageProcessor();
  }
}
