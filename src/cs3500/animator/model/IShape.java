package animator.model;

import java.util.ArrayList;

/**
 * Represents a generic shape with a name, type, and motions. This interface offers functionality
 * for adding and removing from the motions of this Shape as well as calculating the max tick for
 * all IMotions in this Shape.
 */
public interface IShape {
  /**
   * Add a Motion to this shapes list of Motions.
   *
   * @param m the motion to be added.
   * @throws IllegalArgumentException if the motion overlaps with existing motions in this shape
   */
  void addMotion(IMotion m);

  /**
   * Return the name of this IShape.
   */
  String getName();

  /**
   * Return a String of a text description of this IShape.
   * The description contains the name of this
   * shape, its type, and describes its motions.
   */
  String getDescription();

  /**
   * Return the ArrayList of IMotions contained in this IShape.
   */
  ArrayList<IMotion> getMotions();

  /**
   * Return the type of this IShape.
   */
  String getType();

  /**
   * Returns a String description of this IShape in SVG format.
   *
   * @param speed the speed of the SVG animation in ticks per second
   */
  String getSVG(int speed);

  /**
   * Removes and returns the first motion from this IShape's ArrayList of IMotions.
   */
  IMotion removeFirstMotion();

  /**
   * Removes and returns the last motion from this IShape's ArrayList of IMotions.
   */
  IMotion removeLastMotion();

  /**
   * Returns the maximum tick for this Shape (when the shape's animation is over).
   */
  int getMaxTick();


  /**
   * Deletes keyframes containing the given tick.
   * @param tick the tick at which the keyframes should be deleted
   */
  void deleteKeyframe(int tick);

}
