import static org.junit.Assert.assertTrue;
import controller.ImageProcessorController;
import controller.ImageProcessorControllerImpl;
import java.io.IOException;
import java.io.StringReader;
import model.ImageProcessorModel;
import org.junit.Test;

import view.ImageProcessorTextView;
import view.ImageProcessorView;

/**
 * Abstract test for all ImageProcessor controllers.
 */
public abstract class AbstractImageProcessorControllerTest {

  protected ImageProcessorView view;
  protected String renderMenu;

  /**
   * AbstractImageProcessorControllerTest constructor.
   */
  public AbstractImageProcessorControllerTest() {
    this.view = new ImageProcessorTextView(new StringBuilder());
    this.renderMenu = "Enter one of the following commands: \n" +
        "q or quit : quits the program\n" +
        "load filepath imageName : loads an image with a chosen name\n" +
        "brighten increment imageName newImageName : brightens a chosen image" +
        " by a chosen amount\n" +
        "vertical-flip imageName newImageName : flips a chosen image " +
        "vertically\n" +
        "horizontal-flip imageName newImageName : flips a chosen image " +
        "horizontally\n" +
        "grey-scale component imageName newImageName : creates a greyscale" +
        " version of a chosen image based on a given component (red, green, blue, luma," +
        " value, or intensity)\n" +
        "save filepath imageName : save a chose image to a specified" +
        " filepath";
  }

  /**
   * Create a model to be used for the controller.
   * @return the model to be used.
   */
  protected abstract ImageProcessorModel createModel();

  /**
   * Mock for the controller class to check that the user input and output is what is expected.
   *
   * @param interactions the input and output for the controller.
   * @return whether or not the given inputs return the given outputs.
   */
  protected abstract boolean testRun(Interaction ... interactions);

  /**
   * Mock for the controller class to check for when a bad view is used.
   *
   * @param interactions the input and output for the controller.
   */
  protected abstract void testRunBadView(Interaction ... interactions);

  /**
   * To throw IOE exceptions for testing purposes.
   */
  protected class BadAppendableTest implements Appendable {

    @Override
    public Appendable append(CharSequence csq) throws IOException {
      throw new IOException("Transmission failed");
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
      throw new IOException("Transmission failed");
    }

    @Override
    public Appendable append(char c) throws IOException {
      throw new IOException("Transmission failed");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModelConstructor() {
    ImageProcessorController invalidController = new ImageProcessorControllerImpl(
        null, view, new StringReader(""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullViewConstructor() {
    ImageProcessorController invalidController = new ImageProcessorControllerImpl(
        this.createModel(), null, new StringReader(""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullReadableConstructor() {
    ImageProcessorController invalidController = new ImageProcessorControllerImpl(
        this.createModel(), view, null);
  }

  @Test(expected = IllegalStateException.class)
  public void testRenderBoardBadAppendable() {
    testRunBadView(
        Interaction.inputs("load q"),
        Interaction.prints(""));
  }

  @Test(expected = IllegalStateException.class)
  public void testRenderBoardBadAppendableNoInputs() {
    testRunBadView();
  }

  @Test(expected = IllegalStateException.class)
  public void testRanOutOfInputs() {
    testRun(Interaction.inputs("load images/koala.ppm koala"));
  }

  @Test
  public void testQuitFirstInput() {
    assertTrue(testRun(
        Interaction.inputs("q"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testInvalidCommand() {
    assertTrue(testRun(
        Interaction.inputs("randomCommand QUIT"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Invalid command: randomCommand"),
        Interaction.prints("Please enter a new valid command."),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testLoadInvalidImage() {
    assertTrue(testRun(
        Interaction.inputs("load images/kojoad.ppm invalid quit"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("images/kojoad.ppm not found!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testLoadValidImage() {
    assertTrue(testRun(
        Interaction.inputs("load images/koala.ppm koala Q"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testLoadOverwriteImage() {
    assertTrue(testRun(
        Interaction.inputs("load images/koala.ppm FlippedKoala Q"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testBrightenInvalidImage() {
    assertTrue(testRun(
        Interaction.inputs("brighten 10 invalidImage koala-brighter QuIT"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Image cannot be found"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testBrightenValidImage() {
    assertTrue(testRun(
        Interaction.inputs("load images/koala.ppm koala "
            + "brighten 10 koala koala-brighter q"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Brighten Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testInvalidCommandThenBrightenValidImage() {
    assertTrue(testRun(
        Interaction.inputs("random load images/koala.ppm koala "
            + "brighten 10 koala koala-brighter q"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Invalid command: random"),
        Interaction.prints("Please enter a new valid command."),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Brighten Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testVerticalFlipInvalidImage() {
    assertTrue(testRun(
        Interaction.inputs("vertical-flip invalidImage koala-vertical QuIT"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Image cannot be found"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testVerticalFlipValidImage() {
    assertTrue(testRun(
        Interaction.inputs("random load images/koala.ppm koala "
            + "vertical-flip koala koala-vertical q"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Invalid command: random"),
        Interaction.prints("Please enter a new valid command."),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Vertical Flip Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testHorizontalFlipInvalidImage() {
    assertTrue(testRun(
        Interaction.inputs("horizontal-flip invalidImage koala-horizontal QuIT"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Image cannot be found"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testHorizontalFlipValidImage() {
    assertTrue(testRun(
        Interaction.inputs("random load images/koala.ppm koala "
            + "horizontal-flip koala koala-horizontal q"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Invalid command: random"),
        Interaction.prints("Please enter a new valid command."),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Horizontal Flip Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testHorizontalFlipThenVerticalFlipValidImage() {
    assertTrue(testRun(
        Interaction.inputs("random load images/koala.ppm koala "
            + "horizontal-flip koala koala-horizontal "
            + "vertical-flip koala-horizontal koala-horizontal-vertical q"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Invalid command: random"),
        Interaction.prints("Please enter a new valid command."),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Horizontal Flip Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Vertical Flip Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }



  @Test
  public void testRedComponentInvalidImage() {
    assertTrue(testRun(
        Interaction.inputs("grey-scale red invalidImage koala-red-greyscale QuIT"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Image cannot be found"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testRedComponentValidImage() {
    assertTrue(testRun(
        Interaction.inputs("random load images/koala.ppm koala "
            + "grey-scale red koala koala-red-greyscale q"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Invalid command: random"),
        Interaction.prints("Please enter a new valid command."),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Greyscale Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testBlueComponentInvalidImage() {
    assertTrue(testRun(
        Interaction.inputs("grey-scale blue invalidImage koala-blue-greyscale QuIT"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Image cannot be found"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testBlueComponentValidImage() {
    assertTrue(testRun(
        Interaction.inputs("random load images/koala.ppm koala "
            + "grey-scale blue koala koala-blue-greyscale q"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Invalid command: random"),
        Interaction.prints("Please enter a new valid command."),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Greyscale Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testGreenComponentInvalidImage() {
    assertTrue(testRun(
        Interaction.inputs("grey-scale GReen invalidImage koala-green-greyscale QuIT"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Image cannot be found"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testGreenComponentValidImage() {
    assertTrue(testRun(
        Interaction.inputs("random load images/koala.ppm koala "
            + "grey-scale GreEn koala koala-green-greyscale q"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Invalid command: random"),
        Interaction.prints("Please enter a new valid command."),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Greyscale Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testValueComponentInvalidImage() {
    assertTrue(testRun(
        Interaction.inputs("grey-scale VaLue invalidImage koala-value-greyscale QuIT"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Image cannot be found"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testValueComponentValidImage() {
    assertTrue(testRun(
        Interaction.inputs("random load images/koala.ppm koala "
            + "grey-scale vAlue koala koala-value-greyscale q"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Invalid command: random"),
        Interaction.prints("Please enter a new valid command."),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Greyscale Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testIntensityComponentInvalidImage() {
    assertTrue(testRun(
        Interaction.inputs("grey-scale intensity invalidImage koala-intensity-greyscale QuIT"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Image cannot be found"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testIntensityComponentValidImage() {
    assertTrue(testRun(
        Interaction.inputs("random load images/koala.ppm koala "
            + "grey-scale inTenSity koala koala-intensity-greyscale q"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Invalid command: random"),
        Interaction.prints("Please enter a new valid command."),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Greyscale Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testLumaComponentInvalidImage() {
    assertTrue(testRun(
        Interaction.inputs("grey-scale LuMa invalidImage koala-luma-greyscale QuIT"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Image cannot be found"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testLumaComponentValidImage() {
    assertTrue(testRun(
        Interaction.inputs("random load images/koala.ppm koala "
            + "grey-scale luma koala koala-luma-greyscale q"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Invalid command: random"),
        Interaction.prints("Please enter a new valid command."),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Greyscale Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void saveInvalidImage() {
    assertTrue(testRun(
        Interaction.inputs("load images/koala.ppm koala "
            + "save images/koala koala quit"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Invalid filepath"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void saveValidImage() {
    assertTrue(testRun(
        Interaction.inputs("load images/koala.ppm koala "
            + "save images/koala.ppm koala quit"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Save Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void saveValidImageToAnotherImage() {
    assertTrue(testRun(
        Interaction.inputs("load images/koala.ppm koala "
            + "horizontal-flip koala koala-horizontal "
            + "save images/koala.ppm koala-horizontal quit"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Horizontal Flip Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Save Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }
}
