package pa2;
/**
 * 
 */


import java.util.HashMap;
import java.util.Map;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class TestCases extends CyclicIterator<Map<String, Angled>> {

  Map<String, Angled> stop() {
    return this.stop;
  }

  private final Map<String, Angled> stop;

  @SuppressWarnings("unchecked")
  TestCases() {
    this.stop = new HashMap<String, Angled>();
    final Map<String, Angled> peace = new HashMap<String, Angled>();
    final Map<String, Angled> fist = new HashMap<String, Angled>();
    final Map<String, Angled> shaka = new HashMap<String, Angled>();
    final Map<String, Angled> spread = new HashMap<String, Angled>();
    final Map<String, Angled> claw = new HashMap<String, Angled>();

    super.add(stop, peace, fist, shaka, spread, claw);

    // the upper arm, forearm, and hand angles do not change through any of the
    // test cases
 
  }
}
