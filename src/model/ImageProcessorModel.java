package model;

import java.util.List;

/**
 * This interface represents the operations offered by the image processor model. One object of the
 * model represents a map of images with their given name to process.
 */
public interface ImageProcessorModel extends ImageProcessorModelState {

  /**
   * Load a given PPM image to the given destination.
   *
   * @param imageName the destination of the file to be read
   * @param ppmList   the 2d array of pixels representing the image
   */
  void loadImage(String imageName, List<List<Pixel>> ppmList);

  /**
   * Brightens the given image by the given increment and stores the result in the given
   * destination.
   *
   * @param increment    The amount to increase the brightness of each component by
   * @param imageName    the destination of the file to be read
   * @param newImageName the new destination of the file to save the brightened image to
   * @throws IllegalArgumentException if the given image cannot be found
   */
  void brighten(int increment, String imageName, String newImageName)
      throws IllegalArgumentException;

  /**
   * Makes the given image grayscale using the given component and stores the result in the given
   * destination.
   *
   * @param component    An Enum representing which component of the image the grayscale should be
   *                     based on
   * @param imageName    the destination of the file to be read
   * @param newImageName the new destination of the file to save the brightened image to
   * @throws IllegalArgumentException if the given component is null or the given image cannot be
   *                                  found
   */
  void greyscaleComponent(Light component, String imageName, String newImageName)
      throws IllegalArgumentException;

  /**
   * Flips the given image either vertically or horizontally and stores the result in the given
   * destination.
   *
   * @param isVerticalFlip a boolean representing whether the flip is vertical or horizontal
   * @param imageName      the destination of the file to be read
   * @param newImageName   the new destination of the file to save the brightened image to
   * @throws IllegalArgumentException if the given image cannot be found
   */
  void flipImage(boolean isVerticalFlip, String imageName, String newImageName);

}
