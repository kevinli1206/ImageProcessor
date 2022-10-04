package model;

/**
 * An enumerated type to represent a light component of an RGB pixel.
 */
public enum Light {
  RED("red"),
  GREEN("green"),
  BLUE("blue"),
  VALUE("value"),
  LUMA("luma"),
  INTENSITY("intensity");

  private final String text;

  /**
   * Light constructor.
   *
   * @param text the string representation of the light component
   */
  Light(String text) {
    this.text = text;
  }

  /**
   * Return the light component given its string representation.
   *
   * @param text the given string to convert to light component.
   * @return the light component of the given string representation.
   * @throws IllegalArgumentException if the given string cannot be converted to a component.
   */
  public static Light fromString(String text) {
    for (Light l : Light.values()) {
      if (l.text.equalsIgnoreCase(text)) {
        return l;
      }
    }
    throw new IllegalArgumentException("Component does not exist");
  }
}
