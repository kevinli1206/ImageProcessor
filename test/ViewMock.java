import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.swing.JMenuItem;
import model.IHistogram;
import view.ImageProcessorGUIView;

/**
 * A Mock of the view to use for testing purposes.
 */
public class ViewMock implements ImageProcessorGUIView {

  private ActionListener listener;

  private final Appendable out;

  /**
   * MockView constructor.
   *
   * @param out the appendable to write messages to.
   */
  public ViewMock(Appendable out) {
    this.out = out;
  }

  @Override
  public void setListener(ActionListener controller) {
    this.listener = Objects.requireNonNull(controller);
  }

  /**
   * Blur the image.
   */
  public void blur() {
    this.listener.actionPerformed(
        new ActionEvent(new JMenuItem(), ActionEvent.ACTION_PERFORMED, "Blur"));
  }

  /**
   * Sharpen the image.
   */
  public void sharpen() {
    this.listener.actionPerformed(
        new ActionEvent(new JMenuItem(), ActionEvent.ACTION_PERFORMED, "Sharpen"));
  }

  /**
   * Sepia the given image
   */
  public void sepia() {
    this.listener.actionPerformed(
        new ActionEvent(new JMenuItem(), ActionEvent.ACTION_PERFORMED, "Sepia"));
  }

  /**
   * Greyscale the given image to red.
   */
  public void greyRed() {
    this.listener.actionPerformed((
        new ActionEvent(new JMenuItem(), ActionEvent.ACTION_PERFORMED, "Grayscale Red")));
  }

  /**
   * Greyscale the given image to blue.
   */
  public void greyBlue() {
    this.listener.actionPerformed((
        new ActionEvent(new JMenuItem(), ActionEvent.ACTION_PERFORMED, "Grayscale Blue")));
  }

  /**
   * Greyscale the given image to green.
   */
  public void greyGreen() {
    this.listener.actionPerformed((
        new ActionEvent(new JMenuItem(), ActionEvent.ACTION_PERFORMED, "Grayscale Green")));
  }

  /**
   * Greyscale the given image to luma.
   */
  public void greyLuma() {
    this.listener.actionPerformed((
        new ActionEvent(new JMenuItem(), ActionEvent.ACTION_PERFORMED, "Grayscale Luma")));
  }

  /**
   * Greyscale the given image to intensity.
   */
  public void greyIntensity() {
    this.listener.actionPerformed((
        new ActionEvent(new JMenuItem(), ActionEvent.ACTION_PERFORMED, "Grayscale Intensity")));
  }

  /**
   * Greyscale the given image to value.
   */
  public void greyValue() {
    this.listener.actionPerformed((
        new ActionEvent(new JMenuItem(), ActionEvent.ACTION_PERFORMED, "Grayscale Value")));
  }

  /**
   * Horizontally flip the image.
   */
  public void horizontalFlip() {
    this.listener.actionPerformed((
        new ActionEvent(new JMenuItem(), ActionEvent.ACTION_PERFORMED, "Flip Horizontal")));
  }

  /**
   * Vertically flip the image.
   */
  public void verticalFlip() {
    this.listener.actionPerformed((
        new ActionEvent(new JMenuItem(), ActionEvent.ACTION_PERFORMED, "Flip Vertical")));
  }

  /**
   * Brighten the image.
   */
  public void brighten() {
    this.listener.actionPerformed((
        new ActionEvent(new JMenuItem(), ActionEvent.ACTION_PERFORMED, "Brighten")));
  }

  /**
   * Open the image.
   */
  public void open() {
    this.listener.actionPerformed(
        new ActionEvent(new JMenuItem(), ActionEvent.ACTION_PERFORMED, "Open file"));
  }

  /**
   * Save the image.
   */
  public void save() {
    this.listener.actionPerformed(
        new ActionEvent(new JMenuItem(), ActionEvent.ACTION_PERFORMED, "Save file"));
  }

  // don't do anything in the below methods because it is a mock
  @Override
  public void display() {

  }

  @Override
  public void displayImage(BufferedImage image) {

  }

  @Override
  public Component getOpenButton() {
    return null;
  }

  @Override
  public Component getSaveButton() {
    return null;
  }

  @Override
  public String getBrightenValue() {
    return null;
  }

  @Override
  public void setHistograms(List<IHistogram> histograms) {

  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.out.append(message);
  }

  @Override
  public void renderMenu() {

  }


}
