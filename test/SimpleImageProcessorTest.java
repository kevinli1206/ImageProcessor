import model.ImageProcessorModel;
import model.SimpleImageProcessor;

/**
 * A class to test the simple implementation of the ImageProcessor.
 */
public class SimpleImageProcessorTest extends AbstractImageProcessorModelTest {

  @Override
  public ImageProcessorModel createModel() {
    return new SimpleImageProcessor();
  }

}