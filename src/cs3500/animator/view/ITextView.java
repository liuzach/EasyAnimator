package cs3500.animator.view;

/**
 * Represents views which output the AnimationModel as a String.
 */
public interface ITextView extends IView {

  /**
   * Try to append the given string to the given filepath. If given filepath is empty, defaults to
   * System.out.
   *
   * @param s   the string to append
   * @param out the filepath to append to
   */
  void tryAppend(String s, String out);

  /**
   * Returns the text representation of the Animation.
   */
  String getString();
}
