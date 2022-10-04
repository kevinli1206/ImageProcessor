package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import javax.swing.*;

import model.HistogramImageProcessorModel;
import view.ImageProcessorGUIView;

/**
 * Represents a controller for the GUI view of an image processor. This controller is able to use a
 * GUI for getting user inputs.
 */
public class GUIImageProcessorController extends ExtendedImageProcessorController
    implements ImageProcessorController, ActionListener {

  private final HistogramImageProcessorModel model;
  private final ImageProcessorGUIView view;

  /**
   * ImageProcessorControllerImpl constructor that takes a readable to pass to the scanner.
   *
   * @param model the model to be used.
   * @param view  the view for the image processor.
   */
  public GUIImageProcessorController(HistogramImageProcessorModel model,
      ImageProcessorGUIView view) {
    super(model, view, new StringReader(""));
    this.model = model;
    this.view = view;
  }

  @Override
  public void runImageProcessor() throws IllegalStateException {
    this.view.setListener(this);
    this.view.display();
  }

  // run an action that doesn't require any additional parameters.
  private void runNoParamAction(String action) {
    try {
      try {
        this.scan = new Scanner("currentImage currentImage");
        this.processCommand(action);
        this.view.displayImage(this.model.getBufferedImage("currentImage"));
      } catch (IllegalArgumentException e) {
        this.view.renderMessage("Load an Image First!");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // run an action that takes a parameter to help run it.
  private void runOneParamAction(String action, String param) {
    try {
      try {
        if (action.equals("brighten")) {
          int brightenBy;
          try {
            brightenBy = Integer.parseInt(param);
          } catch (IllegalArgumentException e) {
            this.view.renderMessage("Do not include letters in the Brighten By box");
            return;
          }
          this.scan = new Scanner(brightenBy + " currentImage currentImage");
        } else {
          this.scan = new Scanner(param + " currentImage currentImage");
        }
        this.processCommand(action);
        this.view.displayImage(this.model.getBufferedImage("currentImage"));
      } catch (IllegalArgumentException e) {
        this.view.renderMessage("Load an Image First!");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    try {
      switch (arg0.getActionCommand()) {
        case "Open file": {
          final JFileChooser fchooser = new JFileChooser(".");
          int retvalue = fchooser.showOpenDialog(this.view.getOpenButton());
          if (retvalue == JFileChooser.APPROVE_OPTION) {
            File f = fchooser.getSelectedFile();
            this.scan = new Scanner(f.getAbsolutePath() + " currentImage");
            this.processCommand("load");
            this.view.displayImage(this.model.getBufferedImage("currentImage"));
          }
          break;
        }
        case "Save file": {
          final JFileChooser fchooser = new JFileChooser(".");
          int retvalue = fchooser.showSaveDialog(this.view.getSaveButton());
          if (retvalue == JFileChooser.APPROVE_OPTION) {
            File f = fchooser.getSelectedFile();
            this.scan = new Scanner(f.getAbsolutePath() + " currentImage");
            this.processCommand("save");
          }
          break;
        }
        case "Blur": {
          this.runNoParamAction("blur");
          break;
        }
        case "Sharpen": {
          this.runNoParamAction("sharpen");
          break;
        }
        case "Sepia": {
          this.runNoParamAction("sepia");
          break;
        }
        case "Flip Horizontal": {
          this.runNoParamAction("horizontal-flip");
          break;
        }
        case "Flip Vertical": {
          this.runNoParamAction("vertical-flip");
          break;
        }
        case "Grayscale Red": {
          this.runOneParamAction("grey-scale", "red");
          break;
        }
        case "Grayscale Blue": {
          this.runOneParamAction("grey-scale", "blue");
          break;
        }
        case "Grayscale Green": {
          this.runOneParamAction("grey-scale", "green");
          break;
        }
        case "Grayscale Luma": {
          this.runOneParamAction("grey-scale", "luma");
          break;
        }
        case "Grayscale Intensity": {
          this.runOneParamAction("grey-scale", "intensity");
          break;
        }
        case "Grayscale Value": {
          this.runOneParamAction("grey-scale", "value");
          break;
        }
        case "Brighten": {
          this.runOneParamAction("brighten", this.view.getBrightenValue());
          break;
        }
      }
      this.view.setHistograms(this.model.getHistogram("currentImage"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
