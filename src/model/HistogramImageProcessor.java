package model;

import java.util.ArrayList;
import java.util.List;

/**
 * To represent an image processor that represents each image as its individual pixels and supports
 * the additional methods of getting the histograms of an image.
 */
public class HistogramImageProcessor extends BetterImageProcessor implements
    HistogramImageProcessorModel {

  @Override
  public List<IHistogram> getHistogram(String imageName) {
    if (!this.rgbValues.containsKey(imageName)) {
      throw new IllegalArgumentException("Image cannot be found");
    }
    List<IHistogram> histograms = new ArrayList<IHistogram>();
    if (this.isGreyScale(imageName)) {
      histograms.add(new ImageHistogram(this.getComponent(imageName, "red")));
    } else {
      histograms.add(new ImageHistogram(this.getComponent(imageName, "red")));
      histograms.add(new ImageHistogram(this.getComponent(imageName, "green")));
      histograms.add(new ImageHistogram(this.getComponent(imageName, "blue")));
      histograms.add(new ImageHistogram(this.getComponent(imageName, "intensity")));
    }
    return histograms;
  }

  /**
   * Get an image representation of the given image component.
   *
   * @param imageName the image to get the representation of.
   * @param component the component to get the image representation of.
   * @return a 2d list of the value of the given component.
   * @throws IllegalArgumentException if the given component is invalid.
   */
  private List<List<Integer>> getComponent(String imageName, String component) {
    List<List<Integer>> toBeReturned = new ArrayList<>();
    List<List<Pixel>> image = this.rgbValues.get(imageName);
    for (int i = 0; i < image.size(); i++) {
      toBeReturned.add(new ArrayList<Integer>());
      for (int j = 0; j < image.get(i).size(); j++) {
        String pixelValues = image.get(i).get(j).toString();
        int red = Integer.parseInt(pixelValues.split(" ")[0]);
        int green = Integer.parseInt(pixelValues.split(" ")[1]);
        int blue = Integer.parseInt(pixelValues.split(" ")[2]);
        switch (component.toLowerCase()) {
          case "red":
            toBeReturned.get(i).add(red);
            break;
          case "green":
            toBeReturned.get(i).add(green);
            break;
          case "blue":
            toBeReturned.get(i).add(blue);
            break;
          case "intensity":
            int average = (int) Math.round((red + green + blue) / 3.0);
            toBeReturned.get(i).add(average);
            break;
          default:
            throw new IllegalArgumentException("Invalid component");
        }
      }
    }
    return toBeReturned;
  }

  /**
   * Check to see if the given image is a grey-scale image.
   *
   * @param imageName the image to check
   * @return true if the image is grey-scale image, false otherwise.
   */
  private boolean isGreyScale(String imageName) {
    for (List<Pixel> lp : this.rgbValues.get(imageName)) {
      for (Pixel p : lp) {
        int red = Integer.parseInt(p.toString().split(" ")[0]);
        int green = Integer.parseInt(p.toString().split(" ")[1]);
        int blue = Integer.parseInt(p.toString().split(" ")[2]);
        if (!(red == green && green == blue)) {
          return false;
        }
      }
    }
    return true;
  }
}
