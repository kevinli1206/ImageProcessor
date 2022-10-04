package model;

/**
 * To represent one component of an image as its color.
 */
public interface Pixel {

  /**
   * Changes all values by the given amount.
   *
   * @param increment the amount to increment or decrement the brightness by
   */
  void changeAllBy(int increment);

  /**
   * Sets all values to a given components value.
   *
   * @param component an Enum representing the component to base the grayscale off of.
   * @throws IllegalArgumentException if the given component is null.
   */
  void setLightComponent(Light component) throws IllegalArgumentException;

  /**
   * Get the maximum value of the components of the pixel.
   *
   * @return an integer representing the maximum components value of the pixel.
   */
  int getMaxComponentValue();

  /**
   * Performs grey-scaling via a matrix color transformation.
   *
   * @param component an Enum representing the component to base the grayscale off of.
   */
  void performMatMult(Light component);

  /**
   * Performs sepia via a matrix color transformation.
   */
  void sepia();
}
