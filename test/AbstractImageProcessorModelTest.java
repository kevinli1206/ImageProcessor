import model.ImageProcessorModel;
import controller.ImageUtil;
import model.Light;
import model.Pixel;
import model.RGBPixel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A class to test the implementation of the ImageProcessorModel.
 */
public abstract class AbstractImageProcessorModelTest {

  protected ImageProcessorModel model1;
  protected List<List<Pixel>> twoByTwo;
  protected List<List<Pixel>> threeByTwo;
  protected List<List<Pixel>> miniExample;
  protected List<List<Pixel>> koala;

  /**
   * Initialize the model to be used for testing.
   */
  @Before
  public void setUp() {
    this.model1 = this.createModel();
    this.twoByTwo = ImageUtil.readFile("images/TwoByTwo.ppm");
    this.threeByTwo = ImageUtil.readFile("images/ThreeByTwo.ppm");
    this.miniExample = ImageUtil.readFile("images/MiniExample.ppm");
  }

  public abstract ImageProcessorModel createModel();

  /**
   * Verifies the loadImage function works properly to initialize the arrayList when given a valid
   * PPM file.
   */
  @Test
  public void testLoadImage() {
    this.model1.loadImage("miniExample", this.miniExample);
    Assert.assertEquals(new RGBPixel(255, 101, 90),
        this.model1.getPixelAt(0, 0, "miniExample"));
    assertEquals(new RGBPixel(58, 103, 92),
        this.model1.getPixelAt(0, 1, "miniExample"));
    assertEquals(this.model1.getHeight("miniExample"), 4);
    assertEquals(this.model1.getWidth("miniExample"), 4);
    this.model1.loadImage("threeByTwo", this.threeByTwo);
    assertEquals(new RGBPixel(59, 104, 35),
        this.model1.getPixelAt(1, 1, "threeByTwo"));
    assertEquals(new RGBPixel(46, 0, 185),
        this.model1.getPixelAt(1, 2, "threeByTwo"));
    assertEquals(this.model1.getHeight("threeByTwo"), 2);
    assertEquals(this.model1.getWidth("threeByTwo"), 3);
  }

  /**
   * Verifies the brighten function works properly including when attempting to brighten values
   * beyond or below the bounds.
   */
  @Test
  public void testBrighten() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
    this.model1.brighten(15, "twoByTwo", "twoByTwo");
    assertEquals(new RGBPixel(255, 16, 31),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(19, 23, 115),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(19, 60, 50),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(80, 90, 122),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
    this.model1.brighten(50, "twoByTwo", "brightTwoByTwo");
    assertEquals(new RGBPixel(255, 66, 81),
        this.model1.getPixelAt(0, 0, "brightTwoByTwo"));
    assertEquals(new RGBPixel(69, 73, 165),
        this.model1.getPixelAt(0, 1, "brightTwoByTwo"));
    assertEquals(new RGBPixel(69, 110, 100),
        this.model1.getPixelAt(1, 0, "brightTwoByTwo"));
    assertEquals(new RGBPixel(130, 140, 172),
        this.model1.getPixelAt(1, 1, "brightTwoByTwo"));
    assertEquals(new RGBPixel(255, 16, 31),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(19, 23, 115),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(19, 60, 50),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(80, 90, 122),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
    this.model1.brighten(-100, "brightTwoByTwo", "darkTwoByTwo");
    assertEquals(new RGBPixel(155, 0, 0),
        this.model1.getPixelAt(0, 0, "darkTwoByTwo"));
    assertEquals(new RGBPixel(0, 0, 65),
        this.model1.getPixelAt(0, 1, "darkTwoByTwo"));
    assertEquals(new RGBPixel(0, 10, 0),
        this.model1.getPixelAt(1, 0, "darkTwoByTwo"));
    assertEquals(new RGBPixel(30, 40, 72),
        this.model1.getPixelAt(1, 1, "darkTwoByTwo"));
    assertEquals(new RGBPixel(255, 66, 81),
        this.model1.getPixelAt(0, 0, "brightTwoByTwo"));
    assertEquals(new RGBPixel(69, 73, 165),
        this.model1.getPixelAt(0, 1, "brightTwoByTwo"));
    assertEquals(new RGBPixel(69, 110, 100),
        this.model1.getPixelAt(1, 0, "brightTwoByTwo"));
    assertEquals(new RGBPixel(130, 140, 172),
        this.model1.getPixelAt(1, 1, "brightTwoByTwo"));
  }

  /**
   * Verifies that the greyscaleComponent function sets all values to red component of their pixel
   * when passed Light.RED.
   */
  @Test
  public void greyscaleComponentRed() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
    this.model1.greyscaleComponent(Light.RED, "twoByTwo", "grayTwoByTwo");
    assertEquals(new RGBPixel(255, 255, 255),
        this.model1.getPixelAt(0, 0, "grayTwoByTwo"));
    assertEquals(new RGBPixel(4, 4, 4),
        this.model1.getPixelAt(0, 1, "grayTwoByTwo"));
    assertEquals(new RGBPixel(4, 4, 4),
        this.model1.getPixelAt(1, 0, "grayTwoByTwo"));
    assertEquals(new RGBPixel(65, 65, 65),
        this.model1.getPixelAt(1, 1, "grayTwoByTwo"));
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
  }

  /**
   * Verifies that the greyscaleComponent function sets all values to green component of their pixel
   * when passed Light.GREEN.
   */
  @Test
  public void greyscaleComponentGreen() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
    this.model1.greyscaleComponent(Light.GREEN, "twoByTwo", "greenTwoByTwo");
    assertEquals(new RGBPixel(1, 1, 1),
        this.model1.getPixelAt(0, 0, "greenTwoByTwo"));
    assertEquals(new RGBPixel(8, 8, 8),
        this.model1.getPixelAt(0, 1, "greenTwoByTwo"));
    assertEquals(new RGBPixel(45, 45, 45),
        this.model1.getPixelAt(1, 0, "greenTwoByTwo"));
    assertEquals(new RGBPixel(75, 75, 75),
        this.model1.getPixelAt(1, 1, "greenTwoByTwo"));
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
  }

  /**
   * Verifies that the greyscaleComponent function sets all values to blue component of their pixel
   * when passed Light.BLUE.
   */
  @Test
  public void greyscaleComponentBlue() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
    this.model1.greyscaleComponent(Light.BLUE, "twoByTwo", "blueTwoByTwo");
    assertEquals(new RGBPixel(16, 16, 16),
        this.model1.getPixelAt(0, 0, "blueTwoByTwo"));
    assertEquals(new RGBPixel(100, 100, 100),
        this.model1.getPixelAt(0, 1, "blueTwoByTwo"));
    assertEquals(new RGBPixel(35, 35, 35),
        this.model1.getPixelAt(1, 0, "blueTwoByTwo"));
    assertEquals(new RGBPixel(107, 107, 107),
        this.model1.getPixelAt(1, 1, "blueTwoByTwo"));
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
  }

  /**
   * Verifies that the greyscaleComponent function sets all values to the maximum component of their
   * pixel when passed Light.VALUE.
   */
  @Test
  public void greyscaleComponentValue() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
    this.model1.greyscaleComponent(Light.VALUE, "twoByTwo", "valueTwoByTwo");
    assertEquals(new RGBPixel(255, 255, 255),
        this.model1.getPixelAt(0, 0, "valueTwoByTwo"));
    assertEquals(new RGBPixel(100, 100, 100),
        this.model1.getPixelAt(0, 1, "valueTwoByTwo"));
    assertEquals(new RGBPixel(45, 45, 45),
        this.model1.getPixelAt(1, 0, "valueTwoByTwo"));
    assertEquals(new RGBPixel(107, 107, 107),
        this.model1.getPixelAt(1, 1, "valueTwoByTwo"));
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
  }

  /**
   * Verifies that the greyscaleComponent function sets all values to average of their components of
   * their pixel when passed Light.INTENSITY.
   */
  @Test
  public void greyscaleComponentIntensity() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
    this.model1.greyscaleComponent(Light.INTENSITY, "twoByTwo", "intensityTwoByTwo");
    assertEquals(new RGBPixel(91, 91, 91),
        this.model1.getPixelAt(0, 0, "intensityTwoByTwo"));
    assertEquals(new RGBPixel(37, 37, 37),
        this.model1.getPixelAt(0, 1, "intensityTwoByTwo"));
    assertEquals(new RGBPixel(28, 28, 28),
        this.model1.getPixelAt(1, 0, "intensityTwoByTwo"));
    assertEquals(new RGBPixel(82, 82, 82),
        this.model1.getPixelAt(1, 1, "intensityTwoByTwo"));
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
  }

  /**
   * Verifies that the greyscaleComponent function sets all values to luma of their components of
   * their pixel when passed Light.LUMA.
   */
  @Test
  public void greyscaleComponentLuma() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
    this.model1.greyscaleComponent(Light.LUMA, "twoByTwo", "lumaTwoByTwo");
    assertEquals(new RGBPixel(56, 56, 56),
        this.model1.getPixelAt(0, 0, "lumaTwoByTwo"));
    assertEquals(new RGBPixel(14, 14, 14),
        this.model1.getPixelAt(0, 1, "lumaTwoByTwo"));
    assertEquals(new RGBPixel(36, 36, 36),
        this.model1.getPixelAt(1, 0, "lumaTwoByTwo"));
    assertEquals(new RGBPixel(75, 75, 75),
        this.model1.getPixelAt(1, 1, "lumaTwoByTwo"));
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
  }

  /**
   * Verifies that the flipImage function can flip all the rows in an image when called with true.
   */
  @Test
  public void flipImageVertical() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
    this.model1.flipImage(true, "twoByTwo", "flippedTwoByTwo");
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(1, 0, "flippedTwoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(1, 1, "flippedTwoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(0, 0, "flippedTwoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(0, 1, "flippedTwoByTwo"));
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
    this.model1.loadImage("threeByTwo", this.threeByTwo);
    assertEquals(new RGBPixel(255, 101, 90),
        this.model1.getPixelAt(0, 0, "threeByTwo"));
    assertEquals(new RGBPixel(58, 103, 92),
        this.model1.getPixelAt(0, 1, "threeByTwo"));
    assertEquals(new RGBPixel(62, 110, 95),
        this.model1.getPixelAt(0, 2, "threeByTwo"));
    assertEquals(new RGBPixel(66, 104, 91),
        this.model1.getPixelAt(1, 0, "threeByTwo"));
    assertEquals(new RGBPixel(59, 104, 35),
        this.model1.getPixelAt(1, 1, "threeByTwo"));
    assertEquals(new RGBPixel(46, 0, 185),
        this.model1.getPixelAt(1, 2, "threeByTwo"));
    this.model1.flipImage(true, "threeByTwo", "flippedThreeByTwo");
    assertEquals(new RGBPixel(255, 101, 90),
        this.model1.getPixelAt(1, 0, "flippedThreeByTwo"));
    assertEquals(new RGBPixel(58, 103, 92),
        this.model1.getPixelAt(1, 1, "flippedThreeByTwo"));
    assertEquals(new RGBPixel(62, 110, 95),
        this.model1.getPixelAt(1, 2, "flippedThreeByTwo"));
    assertEquals(new RGBPixel(66, 104, 91),
        this.model1.getPixelAt(0, 0, "flippedThreeByTwo"));
    assertEquals(new RGBPixel(59, 104, 35),
        this.model1.getPixelAt(0, 1, "flippedThreeByTwo"));
    assertEquals(new RGBPixel(46, 0, 185),
        this.model1.getPixelAt(0, 2, "flippedThreeByTwo"));
    assertEquals(new RGBPixel(255, 101, 90),
        this.model1.getPixelAt(0, 0, "threeByTwo"));
    assertEquals(new RGBPixel(58, 103, 92),
        this.model1.getPixelAt(0, 1, "threeByTwo"));
    assertEquals(new RGBPixel(62, 110, 95),
        this.model1.getPixelAt(0, 2, "threeByTwo"));
    assertEquals(new RGBPixel(66, 104, 91),
        this.model1.getPixelAt(1, 0, "threeByTwo"));
    assertEquals(new RGBPixel(59, 104, 35),
        this.model1.getPixelAt(1, 1, "threeByTwo"));
    assertEquals(new RGBPixel(46, 0, 185),
        this.model1.getPixelAt(1, 2, "threeByTwo"));
  }

  /**
   * Verifies that the flipImage function can flip all the columns in an image when called with
   * false.
   */
  @Test
  public void flipImageHorizontal() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
    this.model1.flipImage(false, "twoByTwo", "horizontalTwoByTwo");
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 1, "horizontalTwoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 0, "horizontalTwoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 1, "horizontalTwoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 0, "horizontalTwoByTwo"));
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
    this.model1.loadImage("threeByTwo", this.threeByTwo);
    assertEquals(new RGBPixel(255, 101, 90),
        this.model1.getPixelAt(0, 0, "threeByTwo"));
    assertEquals(new RGBPixel(58, 103, 92),
        this.model1.getPixelAt(0, 1, "threeByTwo"));
    assertEquals(new RGBPixel(62, 110, 95),
        this.model1.getPixelAt(0, 2, "threeByTwo"));
    assertEquals(new RGBPixel(66, 104, 91),
        this.model1.getPixelAt(1, 0, "threeByTwo"));
    assertEquals(new RGBPixel(59, 104, 35),
        this.model1.getPixelAt(1, 1, "threeByTwo"));
    assertEquals(new RGBPixel(46, 0, 185),
        this.model1.getPixelAt(1, 2, "threeByTwo"));
    this.model1.flipImage(false, "threeByTwo", "horizontalThreeByTwo");
    assertEquals(new RGBPixel(255, 101, 90),
        this.model1.getPixelAt(0, 2, "horizontalThreeByTwo"));
    assertEquals(new RGBPixel(58, 103, 92),
        this.model1.getPixelAt(0, 1, "horizontalThreeByTwo"));
    assertEquals(new RGBPixel(62, 110, 95),
        this.model1.getPixelAt(0, 0, "horizontalThreeByTwo"));
    assertEquals(new RGBPixel(66, 104, 91),
        this.model1.getPixelAt(1, 2, "horizontalThreeByTwo"));
    assertEquals(new RGBPixel(59, 104, 35),
        this.model1.getPixelAt(1, 1, "horizontalThreeByTwo"));
    assertEquals(new RGBPixel(46, 0, 185),
        this.model1.getPixelAt(1, 0, "horizontalThreeByTwo"));
    assertEquals(new RGBPixel(255, 101, 90),
        this.model1.getPixelAt(0, 0, "threeByTwo"));
    assertEquals(new RGBPixel(58, 103, 92),
        this.model1.getPixelAt(0, 1, "threeByTwo"));
    assertEquals(new RGBPixel(62, 110, 95),
        this.model1.getPixelAt(0, 2, "threeByTwo"));
    assertEquals(new RGBPixel(66, 104, 91),
        this.model1.getPixelAt(1, 0, "threeByTwo"));
    assertEquals(new RGBPixel(59, 104, 35),
        this.model1.getPixelAt(1, 1, "threeByTwo"));
    assertEquals(new RGBPixel(46, 0, 185),
        this.model1.getPixelAt(1, 2, "threeByTwo"));
  }

  /**
   * Verifies the getPixelAt function throws the correct exception when the method is called with
   * arguments not within the image bounds.
   */
  @Test
  public void getPixelAtThrowsExceptionWithInvalidArguments() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    try {
      this.model1.getPixelAt(-1, 0, "twoByTwo");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Position not in the image", e.getMessage());
    }
    try {
      this.model1.getPixelAt(0, -1, "twoByTwo");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Position not in the image", e.getMessage());
    }
    try {
      this.model1.getPixelAt(-1, -1, "twoByTwo");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Position not in the image", e.getMessage());
    }
    try {
      this.model1.getPixelAt(3, 0, "twoByTwo");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Position not in the image", e.getMessage());
    }
    try {
      this.model1.getPixelAt(0, 3, "twoByTwo");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Position not in the image", e.getMessage());
    }
    try {
      this.model1.getPixelAt(3, 3, "twoByTwo");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Position not in the image", e.getMessage());
    }
    try {
      this.model1.getPixelAt(-1, 3, "twoByTwo");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Position not in the image", e.getMessage());
    }
  }

  /**
   * Verifies getPixelAt retrieves the Pixel at the given row and column.
   */
  @Test
  public void getPixelAt() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    assertEquals(new RGBPixel(255, 1, 16),
        this.model1.getPixelAt(0, 0, "twoByTwo"));
    assertEquals(new RGBPixel(4, 8, 100),
        this.model1.getPixelAt(0, 1, "twoByTwo"));
    assertEquals(new RGBPixel(4, 45, 35),
        this.model1.getPixelAt(1, 0, "twoByTwo"));
    assertEquals(new RGBPixel(65, 75, 107),
        this.model1.getPixelAt(1, 1, "twoByTwo"));
    this.model1.loadImage("miniExample", this.miniExample);
    assertEquals(new RGBPixel(255, 101, 90),
        this.model1.getPixelAt(0, 0, "miniExample"));
    assertEquals(new RGBPixel(58, 103, 92),
        this.model1.getPixelAt(0, 1, "miniExample"));
    this.model1.loadImage("threeByTwo", this.threeByTwo);
    assertEquals(new RGBPixel(59, 104, 35),
        this.model1.getPixelAt(1, 1, "threeByTwo"));
    assertEquals(new RGBPixel(46, 0, 185),
        this.model1.getPixelAt(1, 2, "threeByTwo"));
  }

  /**
   * Verifies that the getWidth function will retrieve the width of this image.
   */
  @Test
  public void getWidth() {
    this.koala = ImageUtil.readFile("images/Koala.ppm");
    this.model1.loadImage("miniExample", this.miniExample);
    assertEquals(4, this.model1.getWidth("miniExample"));
    this.model1.loadImage("threeByTwo", this.threeByTwo);
    assertEquals(3, this.model1.getWidth("threeByTwo"));
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    assertEquals(2, this.model1.getWidth("twoByTwo"));
    this.model1.loadImage("koala", this.koala);
    assertEquals(512, this.model1.getWidth("koala"));
  }

  /**
   * Verifies that the getHeight function will retrieve the height of this image.
   */
  @Test
  public void getHeight() {
    this.koala = ImageUtil.readFile("images/Koala.ppm");
    this.model1.loadImage("miniExample", this.miniExample);
    assertEquals(4, this.model1.getHeight("miniExample"));
    this.model1.loadImage("threeByTwo", this.threeByTwo);
    assertEquals(2, this.model1.getHeight("threeByTwo"));
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    assertEquals(2, this.model1.getHeight("twoByTwo"));
    this.model1.loadImage("koala", this.koala);
    assertEquals(384, this.model1.getHeight("koala"));
  }

  /**
   * Test to verify getPPMString works to output a valid PPM file for an image in the model.
   */
  @Test
  public void testGetPPMString() {
    this.model1.loadImage("twoByTwo", this.twoByTwo);
    assertEquals("P3\n" +
        "2 2\n" +
        "255\n" +
        "255 1 16\n" +
        "4 8 100\n" +
        "4 45 35\n" +
        "65 75 107\n", this.model1.convertToPPM("twoByTwo"));
    this.model1.brighten(10, "twoByTwo", "brightTwoByTwo");
    assertEquals("P3\n" +
        "2 2\n" +
        "255\n" +
        "255 11 26\n" +
        "14 18 110\n" +
        "14 55 45\n" +
        "75 85 117\n", this.model1.convertToPPM("brightTwoByTwo"));
    this.model1.flipImage(true, "brightTwoByTwo", "flipTwoByTwo");
    assertEquals("P3\n" +
        "2 2\n" +
        "255\n" +
        "14 55 45\n" +
        "75 85 117\n" +
        "255 11 26\n" +
        "14 18 110\n", this.model1.convertToPPM("flipTwoByTwo"));
  }

}