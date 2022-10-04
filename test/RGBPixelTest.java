import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.Light;
import model.Pixel;
import model.RGBPixel;
import org.junit.Before;
import org.junit.Test;

/**
 * To test the methods in the RGBPixel class.
 */
public class RGBPixelTest {

  Pixel highRedLowBlue;
  Pixel highBlueLowGreen;
  Pixel highGreenLowRed;
  Pixel white;
  Pixel black;
  Pixel lightGrey;
  Pixel darkGrey;
  Pixel randomColor;


  @Before
  public void initData() {
    highRedLowBlue = new RGBPixel(250, 120, 5);
    highBlueLowGreen = new RGBPixel(120, 5, 250);
    highGreenLowRed = new RGBPixel(5, 250, 120);
    white = new RGBPixel(255, 255, 255);
    black = new RGBPixel(0, 0, 0);
    lightGrey = new RGBPixel(200, 200, 200);
    darkGrey = new RGBPixel(50, 50, 50);
    randomColor = new RGBPixel(229, 3, 248);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRedValueTooLarge() {
    Pixel invalid = new RGBPixel(256, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreenValueTooLarge() {
    Pixel invalid = new RGBPixel(5, 260, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBlueValueTooLarge() {
    Pixel invalid = new RGBPixel(5, 5, 256);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeRedValue() {
    Pixel invalid = new RGBPixel(-1, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeGreenValue() {
    Pixel invalid = new RGBPixel(5, -10, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeBlueValue() {
    Pixel invalid = new RGBPixel(0, 255, -1);
  }

  @Test
  public void testChangeAllBy() {
    assertEquals(highRedLowBlue.toString(), "250 120 5");
    highRedLowBlue.changeAllBy(10);
    assertEquals(highRedLowBlue.toString(), "255 130 15");
    highRedLowBlue.changeAllBy(-20);
    assertEquals(highRedLowBlue.toString(), "235 110 0");
    assertEquals(highGreenLowRed.toString(), "5 250 120");
    highGreenLowRed.changeAllBy(10);
    assertEquals(highGreenLowRed.toString(), "15 255 130");
    highGreenLowRed.changeAllBy(0);
    assertEquals(highGreenLowRed.toString(), "15 255 130");
    assertEquals(highBlueLowGreen.toString(), "120 5 250");
    highBlueLowGreen.changeAllBy(-10);
    assertEquals(highBlueLowGreen.toString(), "110 0 240");
    highBlueLowGreen.changeAllBy(20);
    assertEquals(highBlueLowGreen.toString(), "130 20 255");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullLightComponent() {
    highBlueLowGreen.setLightComponent(null);
  }

  @Test
  public void testSetLightComponent() {
    assertEquals(highRedLowBlue.toString(), "250 120 5");
    highRedLowBlue.setLightComponent(Light.RED);
    assertEquals(highRedLowBlue.toString(), "250 250 250");
    highRedLowBlue.setLightComponent(Light.INTENSITY);
    assertEquals(highRedLowBlue.toString(), "250 250 250");
    assertEquals(highGreenLowRed.toString(), "5 250 120");
    highGreenLowRed.setLightComponent(Light.BLUE);
    assertEquals(highGreenLowRed.toString(), "120 120 120");
    highGreenLowRed.setLightComponent(Light.LUMA);
    assertEquals(highGreenLowRed.toString(), "120 120 120");
    assertEquals(highBlueLowGreen.toString(), "120 5 250");
    highBlueLowGreen.setLightComponent(Light.GREEN);
    assertEquals(highBlueLowGreen.toString(), "5 5 5");
    highBlueLowGreen.setLightComponent(Light.VALUE);
    assertEquals(highBlueLowGreen.toString(), "5 5 5");
    assertEquals(white.toString(), "255 255 255");
    white.setLightComponent(Light.LUMA);
    assertEquals(white.toString(), "255 255 255");
    assertEquals(black.toString(), "0 0 0");
    black.setLightComponent(Light.INTENSITY);
    assertEquals(black.toString(), "0 0 0");
    assertEquals(darkGrey.toString(), "50 50 50");
    darkGrey.setLightComponent(Light.VALUE);
    assertEquals(darkGrey.toString(), "50 50 50");
    assertEquals(randomColor.toString(), "229 3 248");
    randomColor.setLightComponent(Light.LUMA);
    assertEquals(randomColor.toString(), "69 69 69");
    randomColor.setLightComponent(Light.INTENSITY);
    assertEquals(randomColor.toString(), "69 69 69");
    randomColor.setLightComponent(Light.VALUE);
    assertEquals(randomColor.toString(), "69 69 69");
  }

  @Test
  public void testPerformMatMul() {
    assertEquals(highRedLowBlue.toString(), "250 120 5");
    highRedLowBlue.performMatMult(Light.RED);
    assertEquals(highRedLowBlue.toString(), "250 250 250");
    highRedLowBlue.performMatMult(Light.INTENSITY);
    assertEquals(highRedLowBlue.toString(), "250 250 250");
    assertEquals(highGreenLowRed.toString(), "5 250 120");
    highGreenLowRed.performMatMult(Light.BLUE);
    assertEquals(highGreenLowRed.toString(), "120 120 120");
    highGreenLowRed.performMatMult(Light.LUMA);
    assertEquals(highGreenLowRed.toString(), "120 120 120");
    assertEquals(highBlueLowGreen.toString(), "120 5 250");
    highBlueLowGreen.performMatMult(Light.GREEN);
    assertEquals(highBlueLowGreen.toString(), "5 5 5");
    highBlueLowGreen.performMatMult(Light.VALUE);
    assertEquals(highBlueLowGreen.toString(), "5 5 5");
    assertEquals(white.toString(), "255 255 255");
    white.performMatMult(Light.LUMA);
    assertEquals(white.toString(), "255 255 255");
    assertEquals(black.toString(), "0 0 0");
    black.performMatMult(Light.INTENSITY);
    assertEquals(black.toString(), "0 0 0");
    assertEquals(darkGrey.toString(), "50 50 50");
    darkGrey.performMatMult(Light.VALUE);
    assertEquals(darkGrey.toString(), "50 50 50");
    assertEquals(randomColor.toString(), "229 3 248");
    randomColor.performMatMult(Light.LUMA);
    assertEquals(randomColor.toString(), "69 69 69");
    randomColor.performMatMult(Light.INTENSITY);
    assertEquals(randomColor.toString(), "69 69 69");
    randomColor.performMatMult(Light.VALUE);
    assertEquals(randomColor.toString(), "69 69 69");
  }

  @Test
  public void testSepia() {
    assertEquals(highRedLowBlue.toString(), "250 120 5");
    highRedLowBlue.sepia();
    assertEquals(highRedLowBlue.toString(), "191 170 133");
    highRedLowBlue.sepia();
    assertEquals(highRedLowBlue.toString(), "231 206 160");
    assertEquals(highGreenLowRed.toString(), "5 250 120");
    highGreenLowRed.sepia();
    assertEquals(highGreenLowRed.toString(), "217 193 151");
    highGreenLowRed.sepia();
    assertEquals(highGreenLowRed.toString(), "255 233 182");
    assertEquals(highBlueLowGreen.toString(), "120 5 250");
    highBlueLowGreen.sepia();
    assertEquals(highBlueLowGreen.toString(), "98 87 68");
    highBlueLowGreen.sepia();
    assertEquals(highBlueLowGreen.toString(), "118 105 82");
  }

  @Test
  public void testGetMaxComponentValue() {
    assertEquals(highRedLowBlue.getMaxComponentValue(), 250);
    assertEquals(highGreenLowRed.getMaxComponentValue(), 250);
    assertEquals(white.getMaxComponentValue(), 255);
  }

  @Test
  public void testEquals() {
    assertFalse(highRedLowBlue.equals(highBlueLowGreen));
    assertFalse(highBlueLowGreen.equals(highGreenLowRed));
    assertFalse(highGreenLowRed.equals(highRedLowBlue));
    Pixel newWhite = new RGBPixel(255, 255, 255);
    assertTrue(white.equals(newWhite));
    assertFalse(black.equals(newWhite));
    black.changeAllBy(255);
    assertTrue(black.equals(white));
  }

  @Test
  public void testHashCode() {
    assertEquals(highRedLowBlue.hashCode(), 270316);
    assertEquals(highGreenLowRed.hashCode(), 38566);
    assertEquals(highBlueLowGreen.hashCode(), 152866);
    assertEquals(white.hashCode(), 283006);
    highBlueLowGreen.changeAllBy(250);
    assertEquals(highBlueLowGreen.hashCode(), white.hashCode());
  }
}
