import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;
import view.ImageProcessorTextView;
import view.ImageProcessorView;

/**
 * To test the ImageProcessorTextView class.
 */
public class ImageProcessorTextViewTest {

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
  public void testNullDestination() {
    ImageProcessorView invalid = new ImageProcessorTextView(null);
  }

  @Test(expected = IOException.class)
  public void testRenderMessageBadAppendable() throws IOException {
    Appendable badAppendable = new BadAppendableTest();
    ImageProcessorView badView = new ImageProcessorTextView(badAppendable);
    badView.renderMessage("Bad appendable.");
  }

  @Test(expected = IOException.class)
  public void testRenderMenuBadAppendable() throws IOException {
    Appendable badAppendable = new BadAppendableTest();
    ImageProcessorView badView = new ImageProcessorTextView(badAppendable);
    badView.renderMenu();
  }

  @Test
  public void testRenderMessage() throws IOException {
    Appendable append = new StringBuilder();
    ImageProcessorView testAppendable = new ImageProcessorTextView(append);
    testAppendable.renderMessage("");
    assertEquals(append.toString(), "");
    testAppendable.renderMessage(null);
    assertEquals(append.toString(), "");
    testAppendable.renderMessage("hello");
    assertEquals(append.toString(), "hello");
    testAppendable.renderMessage(" world");
    assertEquals(append.toString(), "hello world");
  }

  @Test
  public void testRenderMenu() throws IOException {
    Appendable append = new StringBuilder();
    ImageProcessorView testAppendable = new ImageProcessorTextView(append);
    testAppendable.renderMenu();
    assertEquals("Enter one of the following commands: \n" +
            "q or quit : quits the program\n" +
            "load filepath imageName : loads an image with a chosen name\n" +
            "brighten increment imageName newImageName : brightens a chosen image by a" +
            " chosen amount\n" +
            "vertical-flip imageName newImageName : flips a chosen image vertically\n" +
            "horizontal-flip imageName newImageName : flips a chosen image horizontally\n" +
            "grey-scale component imageName newImageName : creates a greyscale version of a" +
            " chosen image based on a given component" +
            " (red, green, blue, luma, value, or intensity)\n" +
            "save filepath imageName : save a chose image to a specified filepath\n",
            append.toString());
  }
}
