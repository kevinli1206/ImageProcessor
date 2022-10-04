import static org.junit.Assert.assertEquals;

import controller.GUIImageProcessorController;
import model.HistogramImageProcessor;
import model.HistogramImageProcessorModel;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the GUIImageProcessorController class.
 */
public class GUIImageProcessorControllerTest {

  private HistogramImageProcessorModel model;
  private ViewMock view;
  private Appendable append;

  @Before
  public void setUp() {
    model = new HistogramImageProcessor();
    append = new StringBuilder();
    view = new ViewMock(append);
    GUIImageProcessorController controller = new GUIImageProcessorController(model, view);
    view.setListener(controller);
  }

  @Test
  public void testOpen() {
    view.open();
    assertEquals(append.toString(), "Load Operation Successful!\n");
  }

  @Test
  public void testSepia() {
    view.open();
    view.sepia();
    assertEquals(append.toString(), "Load Operation Successful!\n"
        + "Sepia Operation Successful!\n");
  }

  @Test
  public void testBlur() {
    view.open();
    view.blur();
    assertEquals(append.toString(), "Load Operation Successful!\n"
        + "Blur Operation Successful!\n");
  }

  @Test
  public void testSharpen() {
    view.open();
    view.sharpen();
    assertEquals(append.toString(), "Load Operation Successful!\n"
        + "Sharpen Operation Successful!\n");
  }

  @Test
  public void testGreyRed() {
    view.open();
    view.greyRed();
    assertEquals(append.toString(), "Load Operation Successful!\n"
        + "Greyscale Operation Successful!\n");
  }

  @Test
  public void testGreyBlue() {
    view.open();
    view.greyBlue();
    assertEquals(append.toString(), "Load Operation Successful!\n"
        + "Greyscale Operation Successful!\n");
  }

  @Test
  public void testGreyGreen() {
    view.open();
    view.greyGreen();
    assertEquals(append.toString(), "Load Operation Successful!\n"
        + "Greyscale Operation Successful!\n");
  }

  @Test
  public void testGreyLuma() {
    view.open();
    view.greyLuma();
    assertEquals(append.toString(), "Load Operation Successful!\n"
        + "Greyscale Operation Successful!\n");
  }

  @Test
  public void testGreyIntensity() {
    view.open();
    view.greyIntensity();
    assertEquals(append.toString(), "Load Operation Successful!\n"
        + "Greyscale Operation Successful!\n");
  }

  @Test
  public void testGreyValue() {
    view.open();
    view.greyValue();
    assertEquals(append.toString(), "Load Operation Successful!\n"
        + "Greyscale Operation Successful!\n");
  }

  @Test
  public void testHorizontalFlip() {
    view.open();
    view.horizontalFlip();
    assertEquals(append.toString(), "Load Operation Successful!\n"
        + "Horizontal Flip Operation Successful!\n");
  }

  @Test
  public void testVerticalFlip() {
    view.open();
    view.verticalFlip();
    assertEquals(append.toString(), "Load Operation Successful!\n"
        + "Vertical Flip Operation Successful!\n");
  }

  @Test
  public void testBrighten() {
    view.open();
    view.brighten();
    assertEquals(append.toString(), "Load Operation Successful!\n"
        + "Do not include letters in the Brighten By box");
  }

  @Test
  public void testSave() {
    view.open();
    view.save();
    assertEquals(append.toString(), "Load Operation Successful!\n"
        + "Save Operation Successful!\n");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSepia() {
    view.sepia();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBlur() {
    view.blur();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSharpen() {
    view.sharpen();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGreyRed() {
    view.greyRed();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGreyGreen() {
    view.greyGreen();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGreyBlue() {
    view.greyBlue();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGreyLuma() {
    view.greyLuma();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGreyIntensity() {
    view.greyIntensity();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGreyValue() {
    view.greyValue();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidHorizontalFlip() {
    view.horizontalFlip();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidVerticalFlip() {
    view.verticalFlip();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBrighten() {
    view.brighten();
  }

}
