import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import view.HistogramGraph;

import static org.junit.Assert.*;

/**
 * A simple class to test one constant public method in the HistogramGraphTest class. Almost all
 * methods in this class are inherited directly from the JPanel parent class without being
 * overridden and so do not need to be tested, the exception being the getPreferredSize method which
 * always returns the same Dimension in this implementation.
 */
public class HistogramGraphTest {
  private HistogramGraph hg1;

  @Before
  public void setup() {
    this.hg1 = new HistogramGraph(Color.black);
  }

  @Test
  public void getPreferredSize() {
    assertEquals(new Dimension(200, 100), this.hg1.getPreferredSize());
  }
}