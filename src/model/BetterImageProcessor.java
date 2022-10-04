package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * To represent a better image processor that represents each image as its individual pixels and
 * supports additional operations on the image such as getting the buffered image,
 * sepia, blur, and sharpen.
 */
public class BetterImageProcessor extends SimpleImageProcessor
    implements BetterImageProcessorModel {

  @Override
  public BufferedImage getBufferedImage(String imageName) throws IllegalArgumentException {
    if (!this.rgbValues.containsKey(imageName)) {
      throw new IllegalArgumentException("Image cannot be found");
    }
    BufferedImage outImage = new BufferedImage(this.getWidth(imageName),
        this.getHeight(imageName),
        BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < this.getWidth(imageName); i++) {
      for (int j = 0; j < this.getHeight(imageName); j++) {
        String pixelVals = this.rgbValues.get(imageName).get(j).get(i).toString();
        int red = Integer.parseInt(pixelVals.split(" ")[0]);
        int green = Integer.parseInt(pixelVals.split(" ")[1]);
        int blue = Integer.parseInt(pixelVals.split(" ")[2]);
        Color myColor = new Color(red, green, blue);
        outImage.setRGB(i, j, myColor.getRGB());
      }
    }
    return outImage;
  }

  @Override
  public void greyscaleComponent(Light component, String imageName, String newImageName)
      throws IllegalArgumentException {
    java.util.List<java.util.List<Pixel>> image = this.getImageCopyByName(imageName);
    for (List<Pixel> row : image) {
      for (Pixel px : row) {
        px.performMatMult(component);
      }
    }
    this.rgbValues.put(newImageName, image);
  }

  @Override
  public void sepiaImage(String imageName, String newImageName) {
    java.util.List<java.util.List<Pixel>> image = this.getImageCopyByName(imageName);
    for (List<Pixel> row : image) {
      for (Pixel px : row) {
        px.sepia();
      }
    }
    this.rgbValues.put(newImageName, image);
  }

  /**
   * Get the neighbors of the given pixel and return them as a 2d array of their component values.
   *
   * @param component the component to get the value of.
   * @param height    the height of the array to be returned.
   * @param width     the width of the array to be returned.
   * @param centerRow the integer representing what the given pixel row is
   * @param centerCol the integer representing what the given pixel column is
   * @param imageName the image to get the neighbors of.
   * @return a 2d array of doubles representing the component values of the neighbors.
   */
  private List<List<Double>> getNeighbors(String component, int height, int width,
      int centerRow, int centerCol, String imageName) {
    List<List<Double>> result = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      result.add(new ArrayList<>());
      for (int j = 0; j < width; j++) {
        int newX = centerRow - (width / 2 - i);
        int newY = centerCol - (height / 2 - j);
        try {
          String[] rgbValues = this.getPixelAt(newX, newY, imageName).toString().split(" ");
          switch (component.toLowerCase()) {
            case "red":
              result.get(i).add(Double.parseDouble(rgbValues[0]));
              break;
            case "green":
              result.get(i).add(Double.parseDouble(rgbValues[1]));
              break;
            case "blue":
              result.get(i).add(Double.parseDouble(rgbValues[2]));
              break;
            default:
              throw new IllegalArgumentException("Invalid component, must be red, green, or blue");
          }
        } catch (IllegalArgumentException e) {
          result.get(i).add(0.0);
        }
      }
    }
    return result;
  }

  /**
   * Given a kernel and a matrix, sum the multiplication between each respective place in the kernel
   * and matrix.
   *
   * @param kernel the kernel to use.
   * @param values the matrix to use.
   * @return the total of the sum of multiplication between each corresponding place in the kernel
   *         and matrix.
   */
  private int kernelMath(List<List<Double>> kernel, List<List<Double>> values) {
    double total = 0.0;
    for (int i = 0; i < kernel.size(); i++) {
      for (int j = 0; j < kernel.get(i).size(); j++) {
        total += kernel.get(i).get(j) * values.get(i).get(j);
      }
    }
    if (total > 255) {
      return 255;
    }
    if (total < 0) {
      return 0;
    }
    return (int) total;
  }

  /**
   * Do the kernel operation for each pixel in the given image and save it as the new image name.
   *
   * @param imageName    the image to do the operation on
   * @param newImageName the new image to save the result to.
   * @param kernel       the kernel to do the operation on.
   */
  private void kernelImage(String imageName, String newImageName, List<List<Double>> kernel) {
    if (!this.rgbValues.containsKey(imageName)) {
      throw new IllegalArgumentException("Image cannot be found");
    }
    List<List<Pixel>> image = this.rgbValues.get(imageName);
    List<List<Pixel>> newImage = new ArrayList<>();
    for (int i = 0; i < image.size(); i++) {
      newImage.add(new ArrayList<>());
      for (int j = 0; j < image.get(i).size(); j++) {
        List<List<Double>> redNeighbors = this.getNeighbors(
            "red", kernel.size(),
            kernel.get(0).size(), i, j, imageName);
        List<List<Double>> greenNeighbors = this.getNeighbors("green", kernel.size(),
            kernel.get(0).size(), i, j, imageName);
        List<List<Double>> blueNeighbors = this.getNeighbors("blue", kernel.size(),
            kernel.get(0).size(), i, j, imageName);
        int redValue = this.kernelMath(kernel, redNeighbors);
        int greenValue = this.kernelMath(kernel, greenNeighbors);
        int blueValue = this.kernelMath(kernel, blueNeighbors);
        newImage.get(i).add(new RGBPixel(redValue, greenValue, blueValue));
      }
    }
    this.rgbValues.put(newImageName, newImage);
  }

  @Override
  public void blurImage(String imageName, String newImageName) throws IllegalArgumentException {
    List<List<Double>> blurMatrix = new ArrayList<>();
    blurMatrix.add(Arrays.asList(0.0625, 0.125, 0.0625));
    blurMatrix.add(Arrays.asList(0.125, 0.25, 0.125));
    blurMatrix.add(Arrays.asList(0.0625, 0.125, 0.0625));
    this.kernelImage(imageName, newImageName, blurMatrix);
  }

  @Override
  public void sharpenImage(String imageName, String newImageName) throws IllegalArgumentException {
    List<List<Double>> sharpenMatrix = new ArrayList<>();
    sharpenMatrix.add(Arrays.asList(-0.125, -0.125, -0.125, -0.125, -0.125));
    sharpenMatrix.add(Arrays.asList(-0.125, 0.25, 0.25, 0.25, -0.125));
    sharpenMatrix.add(Arrays.asList(-0.125, 0.25, 1.0, 0.25, -0.125));
    sharpenMatrix.add(Arrays.asList(-0.125, 0.25, 0.25, 0.25, -0.125));
    sharpenMatrix.add(Arrays.asList(-0.125, -0.125, -0.125, -0.125, -0.125));
    this.kernelImage(imageName, newImageName, sharpenMatrix);
  }
}
