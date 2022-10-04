Class and Interface Overview

src/controller/ImageProcessorController

interface ImageProcessorController

The purpose of this interface is to represent all operations that our controller can perform for an Image Processor.

src/controller/ImageProcessorControllerImpl

class ImageProcessorControllerImpl

This class represents a controller that takes user input using a scanner 
and calls the appropiate operations on the given Image Processor model and view.

src/model/ImageProcessorModel

interface ImageProcessorModel

This interface has methods that modify(void) the Image Processor. 

src/model/ImageProcessorModelState

interface ImageProcessorModelState

This interface has methods that can monitor the state of the ImageProcessor without changing anything in it.

src/model/ImageUtil

class ImageUtil

This is the provided class that we modified to read a PPM file and turn that into a 2d array of pixels.

src/controller/ImageUtil

enum Light

This enumeration represents all the possible grey-scale operations on a pixel.

src/model/Pixel

interface Pixel

This interface is used to represent a single component of an image as the color of that component.

src/model/RGBPixel

class RGBPixel

This class represents a Pixel as its RGB values.

src/model/SimpleImageProcessor

class SimpleImageProcessor

This class is a simple image processor that represents each image it has as their 2d array of pixels.

src/view/ImageProcessorTextView

class ImageProcessorTextView

This class displays the command line instructions as text to the user.

src/view/ImageProcessorView

interface ImageProcessorView

This interface represents operations that are offered by a view for an Image Processor.

src/ImageProcessor

class ImageProcessor

This class represents the main method for the ImageProcessor that runs the Image Processor through the console.

test/ImageProcessorControllerImplTest

class ImageProcessorControllerImplTest

This class tests the ImageProcessorControllerImpl class.

test/ImageProcessorTextViewTest

class ImageProcessorTextViewTest

This class tests the ImageProcessorTextView class.

test/ImageUtilTest

class ImageUtilTest

This class tests the ImageUtil class.

test/Interaction

interface Interaction

This interface represents a user sending input and the output expected from it.

test/LightTest

class LightTest

This class tests the Light enumeration.

test/RGBPixelTest

class RGBPixelTest

This class tests the RGBPixel class.

test/SimpleImageProcessorTest

class SimpleImageProcessorTest

This class tests the SimpleImageProcessor class.

SCRIPT TO RUN

Navigate to the ImageProcessor class that contains the main method.

Run the main method to initialize the Image Processor. You should see the view transmit the messages in the console.

In the console, run the command, "load images/koala.ppm koala" to load the koala.ppm file into the image processor with the name koala.

In the console, run the command, "horizontal-flip koala koala-horizontal" to horizontally flip the image and give it the name koala-horizontal in the image processor.

In the console, run the command, "vertical-flip koala-horizontal koala-horizontal-vertical" to vertically flip the previously saved image and give it the given name in the image processor.

In the console, run the command, "brighten -10 koala-horizontal-vertical koala-darkened-horizontal-vertical" to darken the given image by 10(brighten by -10) and give it the given name in the image processor.

In the console, run the command, "save images/koala-darkenend-horizontal-vertical.ppm koala-darkened-horizontal-vertical" to save the image with the given name in the image processor to images/koala-darkenend-horizontal-vertical.ppm. If this file has not been created yet, then this script will create it for you.

In the console, enter "q" or "quit" to exit the main method.

CITATIONS:

Image from : https://www.boatsetter.com/boating-resources/rent-sailboat-live-aboard

Converted into PPM (P6 type) using : https://onlineconvertfree.com/convert-format/png-to-ppm/

Converted from P6 type PPM to P3 type PPM using : https://github.com/zowiehi/ppm-converter

ASSIGNMENT 5 :

CHANGES :
Inside our ImageProcessorController class, we made a new private helper called processCommand that we call in our runImageProcessor method to process what command the user gave us. This command also calls the new method ImageUtil.readFile(filepath) to read the type of file we pass it.

Justification : 

We made this change so that we can use readFile on different filepaths instead of just on ppm files.

Inside our ImageUtil class, we also added a new method called readFile that returned a 2d array of pixels given the file. This method added implementation for jpgs, pngs, and bmps. 

Justification :

To support additional functionality of loading jpgs, pngs, and bmps.

Inside our Pixel interface, we introduced two new void methods, performMaxMult(Light component) which does matrix multiplication on a grey-scale component and sepia which does matrix multiplication to sepia an image.

Justification :

In order to do matrix operations, we delegated some of the work to the Pixel interface in the form of these methods.

Inside our RGBPixel class, we implemented the new void methods above and added helpers to do matrix multiplaction.

Justification :

In order to implement the new methods in the Pixel interface.

Our ImageProcessorControllerImplTest was abstracted to extend the new AbstractImageProcessorControllerTest and some of its tests were abstracted to the new test class.

Justification :

To abstract code away for testing.

Similarly, our SimpleImageProcessorTest was abstracted to extend the new AbstractImageProcessorModelTest and some of its tests were abstracted to the new test class.

Justification :

To abstract code away for testing.

New tests were added in ImageUtilTest to test the new readFile method.

Justification :

To test the additional functionality added.

The main method was changed to use a BetterImageProcessor and an ExtendedImageProcesoorController to test the new methods we added in Assignment 5.

Justification :

In order to test the new model and controller we implemented.

NEW CLASSES AND INTERFACES :

We created a new ExtendedImageProcessorController class that extends ImageProcessorControllerImpl and supports the new methods of blur, sharpen, and sepia commands. This class also allows us to save png, jpg, and bmp images.

interface BetterImageProcessorModel extends ImageProcessorModel

This interace gives us the additional supported operations of sepia, blur, sharpen, and get the buffered image.

class BetterImageProcessor extends SimpleImageProcessor implements BetterImageProcessorModel

This class represents an image processor and supports all the previous operations on images in a SimpleImageProcessor but has additional operations that include sepia, blur, and sharpen.

class AbstractImageProcessorControllerTest

This class tests operations on all ImageProcessorControllers.

class AbstractImageProcessorModelTest

This class tests operations on all ImageProcessorModels.

class BetterImageProcessorTest 

This class tests the BetterImageProcessor class.

class ExtendedImageProcessorControllerTest

This class tests the ExtendedImageProcessorController class.

ASSIGNMENT 6 :

CHANGES : 

Added additional testing in ExtendedImageProcessorControllerTest to test saving images with different file formats.

Justification :

We were missing tests before so we added them to our test class.

NEW CLASSES AND INTERFACES :

interface IHistogram

This interface represents operations on a histogram that has values and frequency of those values.

interface HistogramImageProcesserModel extends BetterImageProcessorModel

This interface represents operations on images that includes creating histograms from images and supports previous operations.

class ImageHistogram implements IHistogram

This class represents a histogram on images that makes a histogram out of the pixels in the image.

class HistogramImageProcessor extends BetterImageProcessor implements HistogramImageProcessorModel

This class represents an image processor that can create histograms from its images while still retaining previous functionality.

class HistogramImageProcessorTest 

This class tests the HistogramImageProcessor class.

class ImageHistogramTest

This class tests the ImageHistogram class.

interface ImageProcessorGUIView extends ImageProcessorView

A new interface which extends the role of the view to include the use of a GUI

class ImageProcessorGUI extends JFrame implements ImageProcessorGUIView

A new class which creates a Swing GUI to use as a view

class HistogramGraph

A new class which acts as a custom JPanel to create a line graph of a histogram





