package animator.model;

/**
 * Represents any kind of Motion applied to an IShape.
 */
public interface IMotion {
  /**
   * Returns the start State of the IMotion.
   */
  IState getStart();

  /**
   * Returns the end State of the IMotion.
   */
  IState getEnd();

  /**
   * Return a String of a text description of this IMotion.
   */
  String getDescription();

  /**
   * Returns a clone of this IMotion.
   */
  IMotion clone();

  /**
   * Returns a String description of this IMotion in SVG format.
   *
   * @param speed the speed of the SVG animation in ticks per second
   * @param x     the x attributeName of the IShape containing this IMotion
   * @param y     the y attributeName of the IShape containing this IMotion
   * @param w     the width attributeName of the IShape containing this IMotion
   * @param h     the height attributeName of the IShape containing this IMotion
   */
  String getSVG(int speed, String x, String y, String w, String h);

}
