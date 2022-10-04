import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import controller.ImageUtil;
import model.Pixel;
import model.RGBPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A test class to test the ImageUtil static class, specifically testing the loadImage function.
 */
public class ImageUtilTest {

  @Test
  public void testLoadImageThrowsExceptionWhenFileDoesNotExist() {
    try {
      ImageUtil.readFile("");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("IOException: Can't read input file!", e.getMessage());
    } try {
      ImageUtil.readFile("not_a_file.docx");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("IOException: Can't read input file!", e.getMessage());
    } try {
      ImageUtil.readFile("no_file");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("IOException: Can't read input file!", e.getMessage());
    }
  }

  /**
   * Verifies that the loadImage function throws the correct exception when the
   * file contains blank lines.
   */
  @Test
  public void testLoadImageThrowsExceptionWhenFileContainsBlankLines() {
    try {
      ImageUtil.readFile("images/MiniExampleWithBlankLines.ppm");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid PPM file: plain RAW file should not have blank lines",
              e.getMessage());
    } try {
      ImageUtil.readFile("images/TwoByTwoWithBlankLines.ppm");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid PPM file: plain RAW file should not have blank lines",
              e.getMessage());
    }
  }

  /**
   * Verifies that the loadImage function throws the correct exception when the file is
   * not a PPM file.
   */
  @Test
  public void testLoadImageThrowsExceptionWhenPPMDoesNotBeginWithP3() {
    try {
      ImageUtil.readFile("images/MiniExampleNoHeader.ppm");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid PPM file: plain RAW file should begin with P3",
              e.getMessage());
    } try {
      ImageUtil.readFile("images/InvalidPPM.ppm");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid PPM file: plain RAW file should begin with P3",
              e.getMessage());
    }
  }

  /**
   * Verifies the loadImage function works properly to initialize the arrayList when given a
   * valid PPM file.
   */
  @Test
  public void testLoadImage() {
    List<List<Pixel>> image = ImageUtil.readFile("images/MiniExample.ppm");
    Assert.assertEquals(new RGBPixel(255, 101, 90),
            image.get(0).get(0));
    assertEquals(new RGBPixel(58, 103, 92),
            image.get(0).get(1));
    assertEquals(image.size(), 4);
    assertEquals(image.get(0).size(), 4);
    image = ImageUtil.readFile("images/threeByTwo.ppm");
    Assert.assertEquals(new RGBPixel(59, 104, 35),
            image.get(1).get(1));
    assertEquals(new RGBPixel(46, 0, 185),
            image.get(1).get(2));
    assertEquals(image.size(), 2);
    assertEquals(image.get(0).size(), 3);
    image = ImageUtil.readFile("res/sailboat.png");
    Assert.assertEquals(new RGBPixel(18, 143, 196),
            image.get(1).get(1));
    assertEquals(new RGBPixel(49, 74, 90),
            image.get(130).get(7));
    assertEquals(image.size(), 139);
    assertEquals(image.get(0).size(), 222);
    image = ImageUtil.readFile("images/sailboat.bmp");
    Assert.assertEquals(new RGBPixel(18, 143, 196),
            image.get(1).get(1));
    assertEquals(new RGBPixel(49, 74, 90),
            image.get(130).get(7));
    assertEquals(image.size(), 139);
    assertEquals(image.get(0).size(), 222);
    image = ImageUtil.readFile("images/sailboat.jpg");
    Assert.assertEquals(new RGBPixel(18, 143, 197),
            image.get(1).get(1));
    assertEquals(new RGBPixel(54, 72, 92),
            image.get(130).get(7));
    assertEquals(image.size(), 139);
    assertEquals(image.get(0).size(), 222);
  }

  @Test
  public void testLoadImage2() {
    List<List<Pixel>> imageBMP = ImageUtil.readFile("images/sailboat.bmp");
    assertEquals(new RGBPixel(18, 143, 197),
            imageBMP.get(0).get(0));
    List<List<Pixel>> imagePNG = ImageUtil.readFile("res/sailboat.png");
    assertEquals(new RGBPixel(18, 143, 197),
            imagePNG.get(0).get(0));
    List<List<Pixel>> imageJPG = ImageUtil.readFile("images/sailboat.JPG");
    assertEquals(new RGBPixel(18, 143, 197),
            imageJPG.get(0).get(0));
  }
}
