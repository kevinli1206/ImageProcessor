import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.IHistogram;
import model.ImageHistogram;
import view.ImageProcessorGUI;

import static org.junit.Assert.*;

/**
 * A class to test the ImageProcessorGUI, the GUI is inherently complex and difficult to test,
 * in particular it is hard to test methods that are either void and merely change the display for
 * the user, or are user input dependant. In order to try and test these methods as best we can,
 * we have created a helper class which can help expose some internal information of the
 * GUI and allow us to simulate some user input.
 */
public class ImageProcessorGUITest {
  /**
   * An internal helper class of the GUI which provides no function other than helping us test the
   * complexities of the GUI, it takes in an Appendable object which it can write information to
   * and can access protected fields of the GUI allowing it to expose those to the tests without
   * having to create any public methods in the actual GUI.
   */
  public static class TestableIPGUI extends ImageProcessorGUI {
    private final Appendable reader;

    /**
     * Constructor to assign the Appendable variable to be used for information storage.
     * @param reader The appendable which can be passed in by the test and used to track information
     */
    public TestableIPGUI(Appendable reader) {
      super();
      this.reader = reader;
    }

    @Override
    public void renderMessage(String message) throws IOException {
      super.renderMessage(message);
      this.reader.append(this.userFeedback.getText());
    }

    @Override
    public String getBrightenValue() {
      try {
        this.reader.append(super.getBrightenValue());
      } catch (IOException e) {
        throw new IllegalStateException(e.getMessage());
      }
      return super.getBrightenValue();
    }

    public void simUserEnterBrighten(String value) {
      this.brightenBy.setText(value);
    }

    public String getHistograms() {
      return this.histogramGraph1.toString() + this.histogramGraph2.toString() +
              this.histogramGraph3.toString() + this.histogramGraph4.toString();
    }

    @Override
    public Component getOpenButton() {
      try {
        this.reader.append(super.getOpenButton().toString());
      } catch (IOException e) {
        throw new IllegalStateException(e.getMessage());
      }
      return super.getOpenButton();
    }

    @Override
    public Component getSaveButton() {
      try {
        this.reader.append(super.getSaveButton().toString());
      } catch (IOException e) {
        throw new IllegalStateException(e.getMessage());
      }
      return super.getSaveButton();
    }
  }

  private TestableIPGUI gui;
  private List<List<Integer>> sampleHistVals1;
  private List<List<Integer>> sampleHistVals2;
  private List<List<Integer>> sampleHistVals3;
  private List<List<Integer>> sampleHistVals4;

  @Before
  public void setup() {
    this.sampleHistVals1 = new ArrayList<>();
    this.sampleHistVals2 = new ArrayList<>();
    this.sampleHistVals3 = new ArrayList<>();
    this.sampleHistVals4 = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      this.sampleHistVals1.add(new ArrayList<>());
      this.sampleHistVals2.add(new ArrayList<>());
      this.sampleHistVals3.add(new ArrayList<>());
      this.sampleHistVals4.add(new ArrayList<>());
      for (int j = 2; j < 7; j++) {
        this.sampleHistVals1.get(i).add(i + j);
        this.sampleHistVals2.get(i).add((i + j + 1));
        this.sampleHistVals2.get(i).add((i + j - 1));
        this.sampleHistVals2.get(i).add((i + j + j));
      }
    }
  }

  @Test
  public void renderMessage() throws IOException {
    StringBuilder reader = new StringBuilder();
    this.gui = new TestableIPGUI(reader);
    this.gui.renderMessage("this is a message");
    assertEquals("this is a message", reader.toString());
    this.gui.renderMessage(" this is another message");
    assertEquals("this is a message this is another message", reader.toString());
  }

  @Test
  public void getBrightenValue() {
    StringBuilder reader = new StringBuilder();
    this.gui = new TestableIPGUI(reader);
    this.gui.getBrightenValue();
    assertEquals("0", reader.toString());
    this.gui.simUserEnterBrighten("50");
    this.gui.getBrightenValue();
    assertEquals("050", reader.toString());
    this.gui.simUserEnterBrighten("25");
    this.gui.getBrightenValue();
    assertEquals("05025", reader.toString());
  }

  @Test
  public void setHistograms() {
    StringBuilder reader = new StringBuilder();
    this.gui = new TestableIPGUI(reader);
    IHistogram hist1 = new ImageHistogram(this.sampleHistVals1);
    List<IHistogram> hists = new ArrayList<>();
    hists.add(hist1);
    this.gui.setHistograms(hists);
    assertEquals("view.HistogramGraph[,0,0,0x0,invalid,layout=java.awt.FlowLayout," +
            "alignmentX=0.0,alignmentY=0.0,border=,flags=9,maximumSize=," +
            "minimumSize=,preferredSize=]view.HistogramGraph[,0,0,0x0,invalid,layout=" +
            "java.awt.FlowLayout,alignmentX=0.0,alignmentY=0.0,border=,flags=9,maximumSize=," +
            "minimumSize=,preferredSize=]view.HistogramGraph[,0,0,0x0,invalid," +
            "layout=java.awt.FlowLayout,alignmentX=0.0,alignmentY=0.0,border=," +
            "flags=9,maximumSize=,minimumSize=,preferredSize=]view.HistogramGraph[,0,0,0x0," +
            "invalid,layout=java.awt.FlowLayout,alignmentX=0.0,alignmentY=0.0,border=,flags=9," +
            "maximumSize=,minimumSize=,preferredSize=]", this.gui.getHistograms());
    IHistogram hist2 = new ImageHistogram(this.sampleHistVals2);
    IHistogram hist3 = new ImageHistogram(this.sampleHistVals3);
    IHistogram hist4 = new ImageHistogram(this.sampleHistVals4);
    hists.add(hist2);
    hists.add(hist3);
    hists.add(hist4);
    this.gui.setHistograms(hists);
    assertEquals("view.HistogramGraph[,0,0,0x0,invalid,layout=java.awt.FlowLayout," +
            "alignmentX=0.0,alignmentY=0.0,border=,flags=9,maximumSize=," +
            "minimumSize=,preferredSize=]view.HistogramGraph[,0,0,0x0,invalid,layout=" +
            "java.awt.FlowLayout,alignmentX=0.0,alignmentY=0.0,border=,flags=9,maximumSize=," +
            "minimumSize=,preferredSize=]view.HistogramGraph[,0,0,0x0,invalid," +
            "layout=java.awt.FlowLayout,alignmentX=0.0,alignmentY=0.0,border=," +
            "flags=9,maximumSize=,minimumSize=,preferredSize=]view.HistogramGraph[,0,0,0x0," +
            "invalid,layout=java.awt.FlowLayout,alignmentX=0.0,alignmentY=0.0,border=,flags=9," +
            "maximumSize=,minimumSize=,preferredSize=]", this.gui.getHistograms());
  }

  @Test
  public void setListener() {
  }

  @Test
  public void display() {
  }

  @Test
  public void displayImage() {
  }

  @Test
  public void getOpenButton() {
    StringBuilder reader = new StringBuilder();
    this.gui = new TestableIPGUI(reader);
    this.gui.getOpenButton();
    String[] allReaders = reader.toString().split(",");
    assertEquals("text=Open a file", allReaders[allReaders.length - 2]);
  }

  @Test
  public void getSaveButton() {
    StringBuilder reader = new StringBuilder();
    this.gui = new TestableIPGUI(reader);
    this.gui.getSaveButton();
    String[] allReaders = reader.toString().split(",");
    assertEquals("text=Save a file", allReaders[allReaders.length - 2]);
  }
}