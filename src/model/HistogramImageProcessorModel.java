package model;

import java.util.List;

/**
 * To represent a image processor model that includes functionality for images to be represented
 * as histograms.
 */
public interface HistogramImageProcessorModel extends BetterImageProcessorModel {

  /**
   * Get the histogram(s) of the given image.
   * @param imageName the image to get the histogram(s) of.
   * @return a list of the image's histograms(s)
   * @throws IllegalArgumentException if the given image cannot be found
   */
  List<IHistogram> getHistogram(String imageName) throws IllegalArgumentException;
}
