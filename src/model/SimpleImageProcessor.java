package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * To represent a simple image processor that represents each image as its individual pixels.
 * This image processor supports the operations of load, save, brighten, grey-scale, and flip.
 */
public class SimpleImageProcessor implements ImageProcessorModel {

  protected final Map<String, List<List<Pixel>>> rgbValues;

  /**
   * SimpleImageProcessor default constructor that initializes the map of images.
   */
  public SimpleImageProcessor() {
    this.rgbValues = new HashMap<>();
  }

  @Override
  public void loadImage(String imageName, List<List<Pixel>> ppmList)
      throws IllegalArgumentException {
    this.rgbValues.put(imageName, ppmList);
  }

  /**
   * Get a deep copy of the give image to avoid mutating images in the map when not intended.
   *
   * @param imageName the image to be copied
   * @return a new copied 2d array of pixels representing the given image.
   * @throws IllegalArgumentException if the given image cannot be found
   */
  protected List<List<Pixel>> getImageCopyByName(String imageName) throws IllegalArgumentException {
    List<List<Pixel>> copyImage = new ArrayList<>();
    if (!this.rgbValues.containsKey(imageName)) {
      throw new IllegalArgumentException("Image cannot be found");
    }
    List<List<Pixel>> originalImage = this.rgbValues.get(imageName);
    for (int i = 0; i < originalImage.size(); i++) {
      copyImage.add(new ArrayList<Pixel>());
      for (int j = 0; j < originalImage.get(i).size(); j++) {
        String pixelVals = this.rgbValues.get(imageName).get(i).get(j).toString();
        int red = Integer.parseInt(pixelVals.split(" ")[0]);
        int green = Integer.parseInt(pixelVals.split(" ")[1]);
        int blue = Integer.parseInt(pixelVals.split(" ")[2]);
        copyImage.get(i).add(new RGBPixel(red, green, blue));
      }
    }
    return copyImage;
  }

  @Override
  public void brighten(int increment, String imageName, String newImageName)
      throws IllegalArgumentException {
    List<List<Pixel>> image = this.getImageCopyByName(imageName);
    for (List<Pixel> row : image) {
      for (Pixel px : row) {
        px.changeAllBy(increment);
      }
    }
    this.rgbValues.put(newImageName, image);
  }

  @Override
  public void greyscaleComponent(Light component, String imageName, String newImageName)
      throws IllegalArgumentException {
    List<List<Pixel>> image = this.getImageCopyByName(imageName);
    for (List<Pixel> row : image) {
      for (Pixel px : row) {
        px.setLightComponent(component);
      }
    }
    this.rgbValues.put(newImageName, image);
  }

  @Override
  public void flipImage(boolean isVerticalFlip, String imageName, String newImageName)
      throws IllegalArgumentException {
    List<List<Pixel>> image = this.getImageCopyByName(imageName);
    List<List<Pixel>> newList = new ArrayList<>();
    if (isVerticalFlip) {
      for (int i = image.size() - 1; i >= 0; i--) {
        newList.add(image.get(i));
      }
    } else {
      for (int i = 0; i < image.size(); i++) {
        newList.add(new ArrayList<Pixel>());
        for (int px = image.get(i).size() - 1; px >= 0; px--) {
          newList.get(i).add(image.get(i).get(px));
        }
      }
    }
    this.rgbValues.put(newImageName, newList);
  }

  @Override
  public Pixel getPixelAt(int row, int col, String imageName) throws IllegalArgumentException {
    if (col >= this.getWidth(imageName) || row >= this.getHeight(imageName) || row < 0 || col < 0) {
      throw new IllegalArgumentException("Position not in the image");
    }
    return this.rgbValues.get(imageName).get(row).get(col);
  }

  @Override
  public int getWidth(String imageName) throws IllegalArgumentException {
    if (this.getHeight(imageName) == 0) {
      return 0;
    }
    return this.rgbValues.get(imageName).get(0).size();
  }

  @Override
  public int getHeight(String imageName) throws IllegalArgumentException {
    return this.rgbValues.get(imageName).size();
  }

  @Override
  public String convertToPPM(String imageName) throws IllegalArgumentException {
    StringBuilder file = new StringBuilder();
    file.append("P3\n");
    file.append(this.getWidth(imageName)).append(" ").
        append(this.getHeight(imageName)).append("\n");
    file.append(this.getMaximumValue(imageName)).append("\n");
    for (List<Pixel> lp : this.getImageCopyByName(imageName)) {
      for (Pixel p : lp) {
        file.append(p.toString()).append("\n");
      }
    }
    return file.toString();
  }

  /**
   * Get the value of the pixel with the highest component value.
   *
   * @return an integer representing the highest component value of any pixel in the image.
   * @throws IllegalArgumentException if the given image cannot be found
   */
  private int getMaximumValue(String imageName) throws IllegalArgumentException {
    int maxValue = 0;
    for (List<Pixel> lp : this.getImageCopyByName(imageName)) {
      for (Pixel p : lp) {
        if (p.getMaxComponentValue() > maxValue) {
          maxValue = p.getMaxComponentValue();
        }
      }
    }
    return maxValue;
  }
}
