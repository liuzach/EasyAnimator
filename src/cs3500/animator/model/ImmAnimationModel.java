package cs3500.animator.model;

import java.util.ArrayList;

/**
 * Represents an immutable version of the AnimationModel which allows all the same functionality
 * except editing the animation.
 */
public interface ImmAnimationModel {
  /**
   * Returns the x value of the canvas of this animation.
   */
  int getX();

  /**
   * Returns the y value of the canvas of this animation.
   */
  int getY();

  /**
   * Returns the width of the canvas of this animation.
   */
  int getWidth();

  /**
   * Returns the height of the canvas of this animation.
   */
  int getHeight();


  /**
   * Returns the ArrayList of IShapes contained in this animation.
   */
  ArrayList<IShape> getShapes();

  /**
   * Returns the ArrayList of IMotions contained in the IShape with given name.
   *
   * @param name the name of the shape whose motions are returned
   */
  ArrayList<IMotion> getMotionsForShape(String name);

  /**
   * Returns the maximum tick for this animation (when the animation is over).
   */
  int getMaxTick();

  /**
   * Returns up to two States of the IShape with the given name at the given tick. Returns null if
   * it does not exist. The first item of the array is the State whose start tick is the given tick,
   * and the second item is the State whose end tick is the given tick.
   * @param name the name of the shape to be searched
   * @param tick the tick of the States to be returned
   */
  IState[] getStateAtTick(String name, int tick);

  /**
   * Returns the index of the motion which contains the given tick.
   *
   * @param lom  List of motions to check
   * @param tick The tick to check
   */
  int inMotion(ArrayList<IMotion> lom, int tick);

  /**
   * Returns the intermediate state of a and b given the current tick, tick of a, and tick of b.
   *
   * @param ta   the tick at which f(t) = a
   * @param tb   the tick at which f(t) = b
   * @param a    the value when t = a
   * @param b    the value when t = b
   * @param tick the current tick
   */
  int tween(int ta, int tb, int a, int b, int tick);
}
