package model;

import java.awt.image.BufferedImage;

/**
 * To represent an ImageProcessorModel with the additional operations on the given image of sepia,
 * blur,sharpen, and get the buffered image.
 */
public interface BetterImageProcessorModel extends ImageProcessorModel {

  /**
   * Process the given 2d array of pixels and return it as an image.
   *
   * @param imageName the name of the 2d array of pixels.
   * @return the 2d array of pixels as an image.
   * @throws IllegalArgumentException if the given image cannot be found
   */
  BufferedImage getBufferedImage(String imageName) throws IllegalArgumentException;

  /**
   * Turn the given image into a sepia tone and save it as the given new image name.
   *
   * @param imageName    the image to turn.
   * @param newImageName the name of the new image to save to.
   * @throws IllegalArgumentException if the given image cannot be found
   */
  void sepiaImage(String imageName, String newImageName) throws IllegalArgumentException;

  /**
   * Blur the given image and save it as the given new image name.
   *
   * @param imageName    the image to turn.
   * @param newImageName the name of the new image to save to.
   * @throws IllegalArgumentException if the given image cannot be found
   */
  void blurImage(String imageName, String newImageName) throws IllegalArgumentException;

  /**
   * Sharpen the given image and save it as the given new image name.
   *
   * @param imageName    the image to turn.
   * @param newImageName the name of the new image to save to.
   * @throws IllegalArgumentException if the given image cannot be found
   */
  void sharpenImage(String imageName, String newImageName) throws IllegalArgumentException;
}
