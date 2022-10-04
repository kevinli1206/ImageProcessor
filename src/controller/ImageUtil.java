package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import model.Pixel;
import model.RGBPixel;

/**
 * This class contains utility methods to read a PPM image from file and turn it into a 2d array of
 * pixels. Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read the given image and return it as a 2d array of pixels.
   * @param filename the image to read from.
   * @return a 2d array of pixels representing the image.
   * @throws IllegalArgumentException if reading from the file fails.
   */
  public static List<List<Pixel>> readFile(String filename) throws IllegalArgumentException {
    if (filename.endsWith(".ppm")) {
      return ImageUtil.readPPM(filename);
    }
    File file = new File(filename);
    List<List<Pixel>> listOfPixel = new ArrayList<>();
    try {
      BufferedImage image = ImageIO.read(file);
      int height = image.getHeight();
      int width = image.getWidth();
      for (int i = 0; i < height; i++) {
        listOfPixel.add(new ArrayList<Pixel>());
        for (int j = 0; j < width; j++) {
          int p = image.getRGB(j, i);
          int r = (p >> 16) & 0xff;
          int g = (p >> 8) & 0xff;
          int b = p & 0xff;
          Pixel px = new RGBPixel(r, g, b);
          listOfPixel.get(i).add(px);
        }
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("IOException: " + e.getMessage());
    }

    return listOfPixel;
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   * @throws IllegalArgumentException if the filename cannot be found
   */
  private static List<List<Pixel>> readPPM(String filename) throws IllegalArgumentException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException(filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    // read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      try {
        if (s.charAt(0) != '#') {
          builder.append(s).append(System.lineSeparator());
        }
      } catch (StringIndexOutOfBoundsException e) {
        throw new IllegalArgumentException("Invalid PPM file: plain RAW file" +
            " should not have blank lines");
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    List<List<Pixel>> listOfPixel = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      listOfPixel.add(new ArrayList<Pixel>());
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        RGBPixel p1 = new RGBPixel(r, g, b);
        listOfPixel.get(i).add(p1);
      }
    }
    return listOfPixel;
  }

}

