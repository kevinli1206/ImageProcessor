import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.ExtendedImageProcessorController;
import controller.ImageProcessorController;
import java.io.StringReader;
import model.BetterImageProcessor;
import model.ImageProcessorModel;
import org.junit.Test;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

/**
 * To test the ImageProcessorControllerImpl class.
 */
public class ExtendedImageProcessorControllerImplTest extends AbstractImageProcessorControllerTest {

  /**
   * ExtendedImageProcessorControllerTest constructor.
   */
  public ExtendedImageProcessorControllerImplTest() {
    super();
  }

  @Override
  protected boolean testRun(Interaction... interactions) {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());
    StringBuilder actualOutput = new StringBuilder();
    ImageProcessorView view = new ImageProcessorTextView(actualOutput);
    ImageProcessorController controller = new ExtendedImageProcessorController(
        new BetterImageProcessor(), view, input);
    controller.runImageProcessor();
    assertEquals(expectedOutput.toString(), actualOutput.toString());
    return expectedOutput.toString().equals(actualOutput.toString());
  }

  @Override
  protected void testRunBadView(Interaction... interactions) {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());
    BadAppendableTest badAppendable = new BadAppendableTest();
    ImageProcessorView view = new ImageProcessorTextView(badAppendable);
    ImageProcessorController controller = new ExtendedImageProcessorController(
        new BetterImageProcessor(), view, input);
    controller.runImageProcessor();
  }

  @Override
  protected ImageProcessorModel createModel() {
    return new BetterImageProcessor();
  }

  @Test
  public void testSepiaInvalidImage() {
    assertTrue(testRun(
        Interaction.inputs("sepia invalidImage koala-sepia QuIT"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Image cannot be found"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testSepiaValidImage() {
    assertTrue(testRun(
        Interaction.inputs("load images/koala.ppm koala "
            + "sepia koala koala-sepia q"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Sepia Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testBlurInvalidImage() {
    assertTrue(testRun(
        Interaction.inputs("blur invalidImage koala-blur QuIT"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Image cannot be found"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testBlurValidImage() {
    assertTrue(testRun(
        Interaction.inputs("load images/koala.ppm koala "
            + "blur koala koala-blur q"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Blur Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testSharpenInvalidImage() {
    assertTrue(testRun(
        Interaction.inputs("sharpen invalidImage koala-sharpen QuIT"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Image cannot be found"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void testSharpenValidImage() {
    assertTrue(testRun(
        Interaction.inputs("load images/koala.ppm koala "
            + "sharpen koala koala-sharpen q"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Sharpen Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void saveValidPNGImage() {
    assertTrue(testRun(
        Interaction.inputs("load images/koala.ppm koala "
            + "save images/koala.png koala quit"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Save Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void saveValidJPGImage() {
    assertTrue(testRun(
        Interaction.inputs("load images/koala.ppm koala "
            + "save images/koala.jpg koala quit"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Save Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }

  @Test
  public void saveValidBMPImage() {
    assertTrue(testRun(
        Interaction.inputs("load images/koala.ppm koala "
            + "save images/koala.bmp koala quit"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Load Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Save Operation Successful!"),
        Interaction.prints(this.renderMenu),
        Interaction.prints("Quit image processor")));
  }
}
