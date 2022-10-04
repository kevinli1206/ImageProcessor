package model;

/**
 * This interface represents operations that can be used to monitor the state of an image processor
 * model, without changing it.
 */
public interface ImageProcessorModelState {

  /**
   * Get the pixel in the given image at the given position.
   *
   * @param row       the row of the position sought starting at 0
   * @param col       the col of the position sought starting at 0.
   * @param imageName the name of the image sought.
   * @return the pixel at the given position.
   * @throws IllegalArgumentException if the row or column is outside the image.
   */
  Pixel getPixelAt(int row, int col, String imageName) throws IllegalArgumentException;

  /**
   * Get the width of the given image represented by the number of pixels in a row.
   *
   * @param imageName the name of the image to get the width of
   * @return the width of the image as an integer.
   * @throws IllegalArgumentException if the given image cannot be found
   */
  int getWidth(String imageName) throws IllegalArgumentException;

  /**
   * Get the height of the given image represented by the number of pixels in a row.
   *
   * @param imageName the name of the image to get the height of
   * @return the height of the image as an integer.
   * @throws IllegalArgumentException if the given image cannot be found
   */
  int getHeight(String imageName) throws IllegalArgumentException;

  /**
   * Convert the given image to its ppm format.
   *
   * @param imageName the image to be converted.
   * @return the string representing the image in ppm format.
   * @throws IllegalArgumentException if the given image cannot be found
   */
  String convertToPPM(String imageName);
}
