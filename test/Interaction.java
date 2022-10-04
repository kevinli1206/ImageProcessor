/**
 * An interaction with the user consists of some input to send the program and some output to
 * expect.
 */
interface Interaction {

  /**
   * Take two StringBuilders and produces the intended effects on them.
   *
   * @param in  the string builder for inputs
   * @param out the string builder for outputs
   */
  void apply(StringBuilder in, StringBuilder out);

  /**
   * Appends the given strings to the output.
   * @param lines the strings to append.
   */
  static Interaction prints(String... lines) {
    return (input, output) -> {
      for (String line : lines) {
        output.append(line).append('\n');
      }
    };
  }

  /**
   * Appends the given strings to the input.
   * @param in the string to append.
   */
  static Interaction inputs(String in) {
    return (input, output) -> {
      input.append(in);
    };
  }

}