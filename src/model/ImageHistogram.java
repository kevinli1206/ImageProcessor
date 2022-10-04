package model;

import java.util.List;

/**
 * To represent a histogram of color values for a given image.
 */
public class ImageHistogram implements IHistogram {

  private final int[] frequency;

  /**
   * Construct the histogram given the 2d list of values.
   * @param values the values to construct the histogram from.
   */
  public ImageHistogram(List<List<Integer>> values) {
    this.frequency = new int[256];
    for (List<Integer> row : values) {
      for (Integer value : row) {
        this.frequency[value]++;
      }
    }
  }

  @Override
  public int[] getFrequency() {
    int[] toBeReturned = new int[256];
    System.arraycopy(this.frequency, 0, toBeReturned, 0, frequency.length);
    return toBeReturned;
  }
}
