package view;

import java.util.List;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import model.IHistogram;

/**
 * Represents a GUI view that supports all previous functionality of the view
 * to display messages but uses a GUI for the user to interact with.
 */
public interface ImageProcessorGUIView extends ImageProcessorView {
  /**
   * Set the listener for any actions.
   */
  void setListener(ActionListener listener);

  /**
   * Display this view.
   */
  void display();

  /**
   * Render an image to the display in the GUI, provided with a BufferedImage.
   * @param image a BufferedImage representing the image to be displayed
   */
  void displayImage(BufferedImage image);

  /**
   * AgGetter for the OpenButton to allow the controller to handle fileIO.
   * @return A Component representing the button where the file opening menu pops up from
   */
  Component getOpenButton();

  /**
   * A getter for the SaveButton to allow the controller to handle fileIO.
   * @return A Component representing the button where the file saving menu pops up from
   */
  Component getSaveButton();

  /**
   * A getter to get the value in the text box for brighten, allowing users to brighten
   * by a custom amount.
   * @return A string of what the user has typed
   */
  String getBrightenValue();

  /**
   * A method to set the histograms for the image either 1 if its grayscale or 4 if its color.
   * @param histograms A list of Histogram objects to be stored in the view
   */
  void setHistograms(List<IHistogram> histograms);
}
