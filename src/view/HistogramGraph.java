package view;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Point;
import java.awt.Dimension;

import javax.swing.*;
// Inspiration and skeleton from :
// https://stackoverflow.com/questions/8693342/drawing-a-simple-line-graph-in-java

/**
 * A class which represents a line graph of a histogram, the class is used by calling the default
 * constructor to initialize a graph object then using the setFrequencies method to provide the
 * histogram data, to update the histogram, simply call the setFrequencies method with new data
 * and repaint the parent container.
 */
public class HistogramGraph extends JPanel {
  private static int MAX_SCORE = 1000;
  private static final int PREF_W = 200;
  private static final int PREF_H = 100;
  private static final int BORDER_GAP = 30;
  private final Color GRAPH_COLOR;
  private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
  private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
  private static final int GRAPH_POINT_WIDTH = 1;
  private static final int Y_HATCH_CNT = 20;
  private List<Integer> frequencies;

  /**
   * Default constructor which simply initializes the frequencies list to an empty arraylist to be
   * populated later.
   */
  public HistogramGraph(Color color) {
    this.GRAPH_COLOR = color;
    this.frequencies = new ArrayList<>();
  }

  /**
   * Set the frequencies list to the provided list of occurrences and increases the maximum y value
   * if it would be off the graph otherwise.
   * @param frequencies the list of frequencies of occurrences for each element
   */
  public void setFrequencies(List<Integer> frequencies) {
    this.frequencies = frequencies;
    for (Integer val : frequencies) {
      if (val > MAX_SCORE) {
        MAX_SCORE = val;
      }
    }
  }

  /**
   * Overriding the protected paintComponent method from the parent JPanel class so when an object
   * of type HistogramGraph is draw, it is draw as a line graph based on its fields.
   * @param g The graphics object used to create visuals
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (frequencies.size() - 1);
    double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

    List<Point> graphPoints = new ArrayList<Point>();
    for (int i = 0; i < frequencies.size(); i++) {
      int x1 = (int) (i * xScale + BORDER_GAP);
      int y1 = (int) ((MAX_SCORE - frequencies.get(i)) * yScale + BORDER_GAP);
      graphPoints.add(new Point(x1, y1));
    }

    // create x and y axes
    g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
    g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

    // create hatch marks for y axis.
    for (int i = 0; i < Y_HATCH_CNT; i++) {
      int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
      int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
      g2.drawLine(BORDER_GAP, y0, x1, y0);
    }

    // and for x axis
    for (int i = 0; i < frequencies.size() - 1; i++) {
      int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (frequencies.size() - 1) + BORDER_GAP;
      int y0 = getHeight() - BORDER_GAP;
      int y1 = y0 - GRAPH_POINT_WIDTH;
      g2.drawLine(x0, y0, x0, y1);
    }

    Stroke oldStroke = g2.getStroke();
    g2.setColor(GRAPH_COLOR);
    g2.setStroke(GRAPH_STROKE);
    for (int i = 0; i < graphPoints.size() - 1; i++) {
      int x1 = graphPoints.get(i).x;
      int y1 = graphPoints.get(i).y;
      int x2 = graphPoints.get(i + 1).x;
      int y2 = graphPoints.get(i + 1).y;
      g2.drawLine(x1, y1, x2, y2);
    }

    g2.setStroke(oldStroke);
    g2.setColor(GRAPH_POINT_COLOR);
    for (Point graphPoint : graphPoints) {
      int x = graphPoint.x;
      int y = graphPoint.y;
      g2.fillOval(x, y, GRAPH_POINT_WIDTH, GRAPH_POINT_WIDTH);
    }
  }

  /**
   * Get the preferred size of this object as a Dimension object based on predefined constants
   * @return A Dimension object made of the preferred width and height of the object.
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(PREF_W, PREF_H);
  }

}
