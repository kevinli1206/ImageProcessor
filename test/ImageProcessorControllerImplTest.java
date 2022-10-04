import static org.junit.Assert.assertEquals;

import controller.ImageProcessorController;
import controller.ImageProcessorControllerImpl;
import java.io.StringReader;
import model.ImageProcessorModel;
import model.SimpleImageProcessor;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

/**
 * To test the ImageProcessorControllerImpl class.
 */
public class ImageProcessorControllerImplTest extends AbstractImageProcessorControllerTest {

  /**
   * ImageProcessorControllerImplTest constructor.
   */
  public ImageProcessorControllerImplTest() {
    super();
  }

  @Override
  protected ImageProcessorModel createModel() {
    return new SimpleImageProcessor();
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
    ImageProcessorController controller = new ImageProcessorControllerImpl(this.createModel(),
        view, input);
    controller.runImageProcessor();
    assertEquals(expectedOutput.toString(), actualOutput.toString());
    return expectedOutput.toString().equals(actualOutput.toString());
  }

  /**
   * Mock for the controller class to check for when a bad view is used.
   *
   * @param interactions the input and output for the controller.
   */
  protected void testRunBadView(Interaction... interactions) {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());
    BadAppendableTest badAppendable = new BadAppendableTest();
    ImageProcessorView view = new ImageProcessorTextView(badAppendable);
    ImageProcessorController controller = new ImageProcessorControllerImpl(this.createModel(),
            view, input);
    controller.runImageProcessor();
  }
}
