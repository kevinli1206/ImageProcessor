package view;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import model.IHistogram;

/**
 * This class represents an implementation of the GUI view.
 * Instead of displaying messages to our user through a log or command line,
 * the view creates a GUI for the user to interact with.
 */
public class ImageProcessorGUI extends JFrame implements ImageProcessorGUIView {
  private final JPanel histogramPanel;
  protected final HistogramGraph histogramGraph1 = new HistogramGraph(Color.RED);
  protected final HistogramGraph histogramGraph2 = new HistogramGraph(Color.GREEN);
  protected final HistogramGraph histogramGraph3 = new HistogramGraph(Color.BLUE);
  protected final HistogramGraph histogramGraph4 = new HistogramGraph(Color.black);

  private final JButton fileOpenButton;
  private final JButton fileSaveButton;
  private final JButton sharpenButton;
  private final JButton blurButton;
  private final JButton sepiaButton;
  private final JButton flipVertButton;
  private final JButton flipHorButton;
  private final JButton grayscaleButton;
  private final JButton brightenButton;
  protected final JTextField brightenBy;

  protected final JLabel userFeedback;
  private final JLabel imageLabel = new JLabel();

  /**
   * Constructor to build the swing elements of the GUI, explained in more detail within the
   * implementation comments, but in summary, creates a large layout with 3 main sections: a section
   * for the image and histogram(s), a section for the image modification buttons, and a section for
   * the file IO buttons.
   */
  public ImageProcessorGUI() {
    super();
    setTitle("Swing features");
    setSize(400, 400);


    JPanel mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    // the panel with the image and the histogram(s)
    JPanel imagePanel = new JPanel();
    // a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    mainPanel.add(imagePanel);

    JScrollPane imageScrollPane = new JScrollPane(this.imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(100, 600));
    imagePanel.add(imageScrollPane);
    this.histogramPanel = new JPanel();
    this.histogramPanel.setLayout(new GridLayout(2, 2, 10, 10));
    this.histogramPanel.add(this.histogramGraph1, 0);
    this.histogramPanel.add(this.histogramGraph2, 1);
    this.histogramPanel.add(this.histogramGraph3, 2);
    this.histogramPanel.add(this.histogramGraph4, 3);
    imagePanel.add(this.histogramPanel);

    // A panel to hold the buttons
    JPanel commandButtons = new JPanel();
    commandButtons.setLayout(new FlowLayout());
    mainPanel.add(commandButtons);
    this.blurButton = new JButton("Blur Image");
    this.blurButton.setActionCommand("Blur");
    commandButtons.add(this.blurButton);
    this.sharpenButton = new JButton("Sharpen Image");
    this.sharpenButton.setActionCommand("Sharpen");
    commandButtons.add(this.sharpenButton);
    this.sepiaButton = new JButton("Sepia Image");
    this.sepiaButton.setActionCommand("Sepia");
    commandButtons.add(this.sepiaButton);
    this.flipHorButton = new JButton("Flip Image Horizontally");
    this.flipHorButton.setActionCommand("Flip Horizontal");
    commandButtons.add(this.flipHorButton);
    this.flipVertButton = new JButton("Flip Image Vertically");
    this.flipVertButton.setActionCommand("Flip Vertical");
    commandButtons.add(this.flipVertButton);
    this.grayscaleButton = new JButton("Grayscale Image By:");
    commandButtons.add(this.grayscaleButton);
    JComboBox<String> grayscaleBox = new JComboBox<String>();
    String[] options = {"Red", "Green", "Blue", "Intensity", "Luma", "Value"};
    for (String option : options) {
      grayscaleBox.addItem(option);
    }
    commandButtons.add(grayscaleBox);
    this.grayscaleButton.setActionCommand("Grayscale " + grayscaleBox.getSelectedItem());
    this.brightenButton = new JButton("Brighten Image By: ");
    this.brightenButton.setActionCommand("Brighten");
    commandButtons.add(this.brightenButton);
    this.brightenBy = new JTextField("0");
    this.brightenBy.setPreferredSize(new Dimension(200, 25));
    commandButtons.add(this.brightenBy);

    // A panel for fileIO buttons
    JPanel fileIOPanel = new JPanel();
    fileIOPanel.setLayout(new FlowLayout());
    mainPanel.add(fileIOPanel);
    this.fileOpenButton = new JButton("Open a file");
    this.fileOpenButton.setActionCommand("Open file");
    fileIOPanel.add(this.fileOpenButton);
    this.fileSaveButton = new JButton("Save a file");
    this.fileSaveButton.setActionCommand("Save file");
    fileIOPanel.add(this.fileSaveButton);

    //user feedback
    this.userFeedback = new JLabel("");
    mainPanel.add(this.userFeedback);
  }

  // Convert an int array to an Integer ArrayList
  private List<Integer> convertArr(int[] arr) {
    List<Integer> out = new ArrayList<>();
    for (int i : arr) {
      out.add(i);
    }
    return out;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.userFeedback.setText(message);
  }

  @Override
  public void renderMenu() throws IOException {
    // not needed for this implementation but useful to have,
    // so it can be used in place of another controller
  }

  @Override
  public String getBrightenValue() {
    return this.brightenBy.getText();
  }

  @Override
  public void setHistograms(List<IHistogram> histograms) {
    this.histogramGraph1.setFrequencies(this.convertArr(histograms.get(0).getFrequency()));
    if (histograms.size() == 1) {
      this.histogramGraph2.setFrequencies(new ArrayList<>());
      this.histogramGraph3.setFrequencies(new ArrayList<>());
      this.histogramGraph4.setFrequencies(new ArrayList<>());
    } else {
      this.histogramGraph2.setFrequencies(this.convertArr(histograms.get(1).getFrequency()));
      this.histogramGraph3.setFrequencies(this.convertArr(histograms.get(2).getFrequency()));
      this.histogramGraph4.setFrequencies(this.convertArr(histograms.get(3).getFrequency()));
    }
    this.histogramPanel.repaint();
  }

  @Override
  public void setListener(ActionListener listener) {
    this.fileOpenButton.addActionListener(listener);
    this.fileSaveButton.addActionListener(listener);
    this.sepiaButton.addActionListener(listener);
    this.sharpenButton.addActionListener(listener);
    this.blurButton.addActionListener(listener);
    this.flipHorButton.addActionListener(listener);
    this.flipVertButton.addActionListener(listener);
    this.grayscaleButton.addActionListener(listener);
    this.brightenButton.addActionListener(listener);
  }

  @Override
  public void display() {
    setVisible(true);
  }

  @Override
  public void displayImage(BufferedImage image) {
    this.imageLabel.setIcon(new ImageIcon(image));
  }

  @Override
  public Component getOpenButton() {
    return this.fileOpenButton;
  }

  @Override
  public Component getSaveButton() {
    return this.fileSaveButton;
  }

}
