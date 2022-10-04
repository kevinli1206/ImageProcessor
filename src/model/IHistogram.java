package model;

/**
 * To represent a table of frequencies of occurrences of a given type.
 */
public interface IHistogram {

  /**
   * Get the frequency of each occurrence of a given type in the histogram.
   * @return the histogram's array of frequencies.
   */
  int[] getFrequency();
}
