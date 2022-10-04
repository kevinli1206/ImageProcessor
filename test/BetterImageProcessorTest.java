import org.junit.Assert;
import org.junit.Test;
import model.BetterImageProcessor;
import model.BetterImageProcessorModel;
import model.ImageProcessorModel;
import model.RGBPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A child class of the AbstractImageProcessorModelTest class whose job it is to test any
 * methods that are implemented differently from the BetterImageProcessor, in this case there are
 * 4 new methods to test, getBufferedImage, sharpen, blur, and sepia.
 */
public class BetterImageProcessorTest extends AbstractImageProcessorModelTest {
  BetterImageProcessorModel model1;

  @Override
  public ImageProcessorModel createModel() {
    this.model1 = new BetterImageProcessor();
    return new BetterImageProcessor();
  }

  /**
   * Verifies the getBufferedImage retrieves the correct BufferedImage by testing the RGB value
   * at each pixel.
   */
  @Test
  public void testGetBufferedImage() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    Assert.assertEquals(-65264,
            this.model1.getBufferedImage("twoByTwo").getRGB(0, 0));
    Assert.assertEquals(-16503517,
            this.model1.getBufferedImage("twoByTwo").getRGB(0, 1));
    Assert.assertEquals(-16512924,
            this.model1.getBufferedImage("twoByTwo").getRGB(1, 0));
    Assert.assertEquals(-12498069,
            this.model1.getBufferedImage("twoByTwo").getRGB(1, 1));
    this.model1.loadImage("threeByTwo", this.threeByTwo);
    Assert.assertEquals(-39590,
            this.model1.getBufferedImage("threeByTwo").getRGB(0, 0));
    Assert.assertEquals(-12425125,
            this.model1.getBufferedImage("threeByTwo").getRGB(0, 1));
    Assert.assertEquals(-12949668,
            this.model1.getBufferedImage("threeByTwo").getRGB(1, 0));
    Assert.assertEquals(-12883933,
            this.model1.getBufferedImage("threeByTwo").getRGB(1, 1));
    Assert.assertEquals(-12685729,
            this.model1.getBufferedImage("threeByTwo").getRGB(2, 0));
    Assert.assertEquals(-13762375,
            this.model1.getBufferedImage("threeByTwo").getRGB(2, 1));
  }

  @Test
  public void testGetBufferedImageThrowsExceptionWhenImageDoesNotExist() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    try {
      this.model1.getBufferedImage("twoByOne");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Image cannot be found", e.getMessage());
    }
    this.model1.getBufferedImage("twoByTwo");
  }

  @Test
  public void testSharpenThrowsExceptionWhenImageDoesNotExist() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    try {
      this.model1.sharpenImage("twoByOne", "anything");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Image cannot be found", e.getMessage());
    }
    this.model1.sharpenImage("twoByTwo", "anything");
  }

  @Test
  public void testBlurThrowsExceptionWhenImageDoesNotExist() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    try {
      this.model1.blurImage("twoByOne", "anything");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Image cannot be found", e.getMessage());
    }
    this.model1.blurImage("twoByTwo", "anything");
  }

  @Test
  public void testSepiaThrowsExceptionWhenImageDoesNotExist() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    try {
      this.model1.sepiaImage("twoByOne", "anything");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Image cannot be found", e.getMessage());
    }
    this.model1.sepiaImage("twoByTwo", "anything");
  }

  @Test
  public void testSharpen() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    assertEquals(new RGBPixel(255, 1, 16),
            this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
            this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
            this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
            this.model1.getPixelAt(1, 1, "twoByTwo"));
    this.model1.sharpenImage("twoByTwo", "sharpTwoByTwo");
    assertEquals(new RGBPixel(255, 33, 76),
            this.model1.getPixelAt(0, 0, "sharpTwoByTwo"));
    assertEquals(new RGBPixel(85, 38, 139),
            this.model1.getPixelAt(0, 1, "sharpTwoByTwo"));
    assertEquals(new RGBPixel(85, 66, 90),
            this.model1.getPixelAt(1, 0, "sharpTwoByTwo"));
    assertEquals(new RGBPixel(130, 88, 144),
            this.model1.getPixelAt(1, 1, "sharpTwoByTwo"));
    assertEquals(new RGBPixel(255, 1, 16),
            this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
            this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
            this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
            this.model1.getPixelAt(1, 1, "twoByTwo"));
  }

  @Test
  public void testBlur() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    assertEquals(new RGBPixel(255, 1, 16),
            this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
            this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
            this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
            this.model1.getPixelAt(1, 1, "twoByTwo"));
    this.model1.blurImage("twoByTwo", "blurTwoByTwo");
    assertEquals(new RGBPixel(68, 11, 27),
            this.model1.getPixelAt(0, 0, "blurTwoByTwo"));
    assertEquals(new RGBPixel(41, 14, 42),
            this.model1.getPixelAt(0, 1, "blurTwoByTwo"));
    assertEquals(new RGBPixel(41, 21, 30),
            this.model1.getPixelAt(1, 0, "blurTwoByTwo"));
    assertEquals(new RGBPixel(33, 25, 44),
            this.model1.getPixelAt(1, 1, "blurTwoByTwo"));
    assertEquals(new RGBPixel(255, 1, 16),
            this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
            this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
            this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
            this.model1.getPixelAt(1, 1, "twoByTwo"));
  }

  @Test
  public void testSepia() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    assertEquals(new RGBPixel(255, 1, 16),
            this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
            this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
            this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
            this.model1.getPixelAt(1, 1, "twoByTwo"));
    this.model1.sepiaImage( "twoByTwo", "sepiaTwoByTwo");
    assertEquals(new RGBPixel(104, 92, 72),
            this.model1.getPixelAt(0, 0, "sepiaTwoByTwo"));
    assertEquals(new RGBPixel(27, 24, 18),
            this.model1.getPixelAt(0, 1, "sepiaTwoByTwo"));
    assertEquals(new RGBPixel(43, 38, 30),
            this.model1.getPixelAt(1, 0, "sepiaTwoByTwo"));
    assertEquals(new RGBPixel(103, 92, 72),
            this.model1.getPixelAt(1, 1, "sepiaTwoByTwo"));
    assertEquals(new RGBPixel(255, 1, 16),
            this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
            this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
            this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
            this.model1.getPixelAt(1, 1, "twoByTwo"));
  }

}
