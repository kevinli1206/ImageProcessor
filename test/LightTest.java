import static org.junit.Assert.assertEquals;

import model.Light;
import org.junit.Test;

/**
 * A class to test the Light enumeration.
 */
public class LightTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLightComponentFromString() {
    Light.fromString("randomString");
  }

  @Test
  public void testFromString() {
    assertEquals(Light.fromString("RED"), Light.RED);
    assertEquals(Light.fromString("red"), Light.RED);
    assertEquals(Light.fromString("ReD"), Light.RED);
    assertEquals(Light.fromString("BLUE"), Light.BLUE);
    assertEquals(Light.fromString("blue"), Light.BLUE);
    assertEquals(Light.fromString("BlUe"), Light.BLUE);
    assertEquals(Light.fromString("Green"), Light.GREEN);
    assertEquals(Light.fromString("GREEN"), Light.GREEN);
    assertEquals(Light.fromString("green"), Light.GREEN);
    assertEquals(Light.fromString("VALUE"), Light.VALUE);
    assertEquals(Light.fromString("value"), Light.VALUE);
    assertEquals(Light.fromString("vAlue"), Light.VALUE);
    assertEquals(Light.fromString("LUMA"), Light.LUMA);
    assertEquals(Light.fromString("luma"), Light.LUMA);
    assertEquals(Light.fromString("LuMa"), Light.LUMA);
    assertEquals(Light.fromString("INTENSITY"), Light.INTENSITY);
    assertEquals(Light.fromString("intensity"), Light.INTENSITY);
    assertEquals(Light.fromString("InTenSity"), Light.INTENSITY);
  }
}
