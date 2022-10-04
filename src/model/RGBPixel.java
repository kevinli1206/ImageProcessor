package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * To represent a pixel as its red, green, and blue values.
 */
public class RGBPixel implements Pixel {

  private int redValue;
  private int greenValue;
  private int blueValue;

  /**
   * Constructor to make a Pixel given a red, green, and blue value.
   *
   * @param redValue   the value of red in this pixel
   * @param greenValue the value of green in this pixel
   * @param blueValue  the value of blue in this pixel
   * @throws IllegalArgumentException if any provided value is not in the range [0, 255]
   */
  public RGBPixel(int redValue, int greenValue, int blueValue) throws IllegalArgumentException {
    if (redValue > 255 || greenValue > 255 || blueValue > 255
        || redValue < 0 || greenValue < 0 || blueValue < 0) {
      throw new IllegalArgumentException("Not a valid RBG Pixel");
    }
    this.redValue = redValue;
    this.greenValue = greenValue;
    this.blueValue = blueValue;
  }

  /**
   * Returns bounded addition on an RGB pixel value.
   *
   * @param increment     the amount to add to the value
   * @param startingValue the value to be added to.
   * @return the values added bounded by [0, 255]
   */
  private int incrementValueTo(int increment, int startingValue) {
    if (increment + startingValue < 0) {
      return 0;
    } else if (increment + startingValue > 255) {
      return 255;
    }
    return increment + startingValue;
  }

  @Override
  public void changeAllBy(int increment) {
    this.redValue = this.incrementValueTo(increment, this.redValue);
    this.blueValue = this.incrementValueTo(increment, this.blueValue);
    this.greenValue = this.incrementValueTo(increment, this.greenValue);
  }

  /**
   * Set all of the rgb components to the given value.
   *
   * @param value the value to be set to.
   */
  private void setAllTo(int value) {
    this.redValue = value;
    this.blueValue = value;
    this.greenValue = value;
  }

  @Override
  public void setLightComponent(Light component) {
    if (component == null) {
      throw new IllegalArgumentException("Invalid Light component");
    }
    switch (component) {
      case RED:
        this.setAllTo(this.redValue);
        break;
      case BLUE:
        this.setAllTo(this.blueValue);
        break;
      case GREEN:
        this.setAllTo(this.greenValue);
        break;
      case LUMA:
        this.setAllTo((int) Math.round(this.calculateLuma()));
        break;
      case VALUE:
        this.setAllTo(this.getMaxComponentValue());
        break;
      case INTENSITY:
        this.setAllTo((int) Math.round((this.redValue + this.blueValue + this.greenValue) / 3.0));
        break;
      default:
        throw new IllegalArgumentException("Invalid Light component");
    }
  }

  @Override
  public int getMaxComponentValue() {
    return Math.max(this.blueValue, Math.max(this.redValue, this.greenValue));
  }

  @Override
  public void performMatMult(Light component) {
    if (component == null) {
      throw new IllegalArgumentException("Invalid Light component");
    }
    List<List<Double>> matrix;
    switch (component) {
      case RED:
        matrix = this.setRedMatrix();
        break;
      case BLUE:
        matrix = this.setBlueMatrix();
        break;
      case GREEN:
        matrix = this.setGreenMatrix();
        break;
      case LUMA:
        matrix = this.setLumaMatrix();
        break;
      case VALUE:
        int toBeSet = this.getMaxComponentValue();
        if (toBeSet == this.redValue) {
          matrix = this.setRedMatrix();
        } else if (toBeSet == this.blueValue) {
          matrix = this.setBlueMatrix();
        } else {
          matrix = this.setGreenMatrix();
        }
        break;
      case INTENSITY:
        matrix = this.setIntensityMatrix();
        break;
      default:
        throw new IllegalArgumentException("Invalid Light component");
    }
    this.matrixMult(matrix);
  }

  @Override
  public void sepia() {
    List<List<Double>> matrix = new ArrayList<>();
    matrix.add(Arrays.asList(0.393, 0.769, 0.189));
    matrix.add(Arrays.asList(0.349, 0.686, 0.168));
    matrix.add(Arrays.asList(0.272, 0.534, 0.131));
    this.matrixMult(matrix);
  }

  /**
   * Get the matrix to be multiplied for a red value grey-scale.
   *
   * @return the matrix for a red value grey-scale.
   */
  private List<List<Double>> setRedMatrix() {
    List<List<Double>> matrix = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      matrix.add(new ArrayList<Double>());
      for (int j = 0; j < 3; j++) {
        if (j == 0) {
          matrix.get(i).add(1.0);
        } else {
          matrix.get(i).add(0.0);
        }
      }
    }
    return matrix;
  }

  /**
   * Get the matrix to be multiplied for a blue value grey-scale.
   *
   * @return the matrix for a blue value grey-scale.
   */
  private List<List<Double>> setBlueMatrix() {
    List<List<Double>> matrix = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      matrix.add(new ArrayList<Double>());
      for (int j = 0; j < 3; j++) {
        if (j == 2) {
          matrix.get(i).add(1.0);
        } else {
          matrix.get(i).add(0.0);
        }
      }
    }
    return matrix;
  }

  /**
   * Get the matrix to be multiplied for a green value grey-scale.
   *
   * @return the matrix for a green value grey-scale.
   */
  private List<List<Double>> setGreenMatrix() {
    List<List<Double>> matrix = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      matrix.add(new ArrayList<Double>());
      for (int j = 0; j < 3; j++) {
        if (j == 1) {
          matrix.get(i).add(1.0);
        } else {
          matrix.get(i).add(0.0);
        }
      }
    }
    return matrix;
  }

  /**
   * Get the matrix to be multiplied for a luma value grey-scale.
   *
   * @return the matrix for a luma value grey-scale.
   */
  private List<List<Double>> setLumaMatrix() {
    List<List<Double>> matrix = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      matrix.add(new ArrayList<Double>());
      for (int j = 0; j < 3; j++) {
        if (j == 0) {
          matrix.get(i).add(0.2126);
        } else if (j == 1) {
          matrix.get(i).add(0.7152);
        } else {
          matrix.get(i).add(0.0722);
        }
      }
    }
    return matrix;
  }

  /**
   * Get the matrix to be multiplied for an intensity value grey-scale.
   *
   * @return the matrix for an intensity value grey-scale.
   */
  private List<List<Double>> setIntensityMatrix() {
    List<List<Double>> matrix = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      matrix.add(new ArrayList<Double>());
      for (int j = 0; j < 3; j++) {
        matrix.get(i).add(1.0 / 3.0);
      }
    }
    return matrix;
  }

  /**
   * Multiply the given matrix with the RGB values and set the new RGB values as the product.
   *
   * @param matrix the matrix to multiply with.
   */
  private void matrixMult(List<List<Double>> matrix) {
    int oldRedValue = this.redValue;
    int oldBlueValue = this.blueValue;
    int oldGreenValue = this.greenValue;
    this.redValue = (int) Math.round(oldRedValue * matrix.get(0).get(0)
        + oldGreenValue * matrix.get(0).get(1) + oldBlueValue * matrix.get(0).get(2));
    if (this.redValue > 255) {
      this.redValue = 255;
    }
    this.greenValue = (int) Math.round(oldRedValue * matrix.get(1).get(0)
        + oldGreenValue * matrix.get(1).get(1) + oldBlueValue * matrix.get(1).get(2));
    if (this.greenValue > 255) {
      this.greenValue = 255;
    }
    this.blueValue = (int) Math.round(oldRedValue * matrix.get(2).get(0)
        + oldGreenValue * matrix.get(2).get(1) + oldBlueValue * matrix.get(2).get(2));
    if (this.blueValue > 255) {
      this.blueValue = 255;
    }
  }

  /**
   * Calculates the Luma values for this Pixel.
   *
   * @return The weighted Luma value of this pixel as a double
   */
  private double calculateLuma() {
    return (0.2126 * this.redValue) + (0.7152 * this.greenValue) + (0.0722 * this.blueValue);
  }

  @Override
  public String toString() {
    return String.format("%d %d %d", this.redValue, this.greenValue, this.blueValue);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof RGBPixel)) {
      return false;
    }
    RGBPixel that = (RGBPixel) obj;

    return this.redValue == that.redValue &&
        this.greenValue == that.greenValue &&
        this.blueValue == that.blueValue;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.redValue, this.blueValue, this.greenValue);
  }
}
