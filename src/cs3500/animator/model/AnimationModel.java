package cs3500.animator.model;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This interface represents the operations offered by the AnimationModel, including adding
 * shapes/motions, removing shapes/motions, getting various data, and producing a text description
 * of the animation. One object of the model represents one animation.
 */
public interface AnimationModel extends ImmAnimationModel {


  int getX();

  int getY();

  int getWidth();

  int getHeight();

  /**
   * Adds the given IShape to this animation.
   *
   * @param s the IShape to be added.
   * @throws IllegalArgumentException if there is already an IShape with the same name
   */
  void addShape(IShape s);

  /**
   * Adds an IShape with given properties to this animation.
   *
   * @param name the name of the IShape to be added
   * @param type the ShapeType of the IShape to be added
   * @throws IllegalArgumentException if there is already an IShape with the same name
   */
  void addShape(String name, ShapeType type);

  /**
   * Removes the IShape with given name from this animation.
   *
   * @param name the name of the IShape to be removed
   * @throws NoSuchElementException if no IShape exists with given name
   */
  IShape removeShape(String name);

  /**
   * Removes and returns the first motion from IShape with given name.
   *
   * @param name the name of the IShape whose first motion will be removed
   * @throws NoSuchElementException if no IShape exists with given name
   */
  IMotion removeFirstMotion(String name);

  /**
   * Removes and returns the last motion from IShape with given name.
   *
   * @param name the name of the IShape whose last motion will be removed
   * @throws NoSuchElementException if no IShape exists with given name
   */
  IMotion removeLastMotion(String name);

  /**
   * Add given Motion m to the IShape with given name.
   *
   * @param name        the name of the IShape to be added to
   * @param startTick   the starting tick of the Motion
   * @param startX      the starting x of the Motion
   * @param startY      the starting y of the Motion
   * @param startWidth  the starting width of the Motion
   * @param startHeight the starting height of the Motion
   * @param startR      the starting red value of the Motion
   * @param startG      the starting green value of the Motion
   * @param startB      the starting blue value of the Motion
   * @param endTick     the ending tick of the Motion
   * @param endX        the ending x of the Motion
   * @param endY        the ending y of the Motion
   * @param endWidth    the ending width of the Motion
   * @param endHeight   the ending height of the Motion
   * @param endR        the ending red value of the Motion
   * @param endG        the ending green value of the Motion
   * @param endB        the ending blue value of the Motion
   * @throws IllegalArgumentException if there is no shape with the given name
   */
  void addMotion(String name, int startTick, int startX, int startY, int startWidth,
                 int startHeight, int startR, int startG, int startB, int endTick, int endX,
                 int endY, int endWidth, int endHeight, int endR, int endG, int endB);


  ArrayList<IShape> getShapes();

  ArrayList<IMotion> getMotionsForShape(String name);

  int getMaxTick();

  IState[] getStateAtTick(String name, int tick);

  /**
   * Sets the x value of the State at the given tick in the IShape with the given name.
   * @param name the name of the IShape whose State will be mutated
   * @param tick the tick of the State whose x value will be mutated
   * @param val  the new x value
   */
  void setStateX(String name, int tick, int val);

  /**
   * Sets the y value of the State at the given tick in the IShape with the given name.
   * @param name the name of the IShape whose State will be mutated
   * @param tick the tick of the State whose y value will be mutated
   * @param val  the new y value
   */
  void setStateY(String name, int tick, int val);


  /**
   * Sets the width value of the State at the given tick in the IShape with the given name.
   * @param name the name of the IShape whose State will be mutated
   * @param tick the tick of the State whose width value will be mutated
   * @param val  the new width value
   */
  void setStateW(String name, int tick, int val);

  /**
   * Sets the height value of the State at the given tick in the IShape with the given name.
   * @param name the name of the IShape whose State will be mutated
   * @param tick the tick of the State whose height value will be mutated
   * @param val  the new height value
   */
  void setStateH(String name, int tick, int val);

  /**
   * Sets the red value of the State at the given tick in the IShape with the given name.
   * @param name the name of the IShape whose State will be mutated
   * @param tick the tick of the State whose red value will be mutated
   * @param val  the new red value
   */
  void setStateR(String name, int tick, int val);

  /**
   * Sets the green value of the State at the given tick in the IShape with the given name.
   * @param name the name of the IShape whose State will be mutated
   * @param tick the tick of the State whose green value will be mutated
   * @param val  the new green value
   */
  void setStateG(String name, int tick, int val);

  /**
   * Sets the blue value of the State at the given tick in the IShape with the given name.
   * @param name the name of the IShape whose State will be mutated
   * @param tick the tick of the State whose blue value will be mutated
   * @param val  the new blue value
   */
  void setStateB(String name, int tick, int val);

  /**
   * Deletes the State with the given tick in the IShape with the given name.
   * @param name the name of the IShape whose State will be deleted
   * @param tick the tick of the State to be deleted
   */
  void deleteKeyframe(String name, int tick);

  int inMotion(ArrayList<IMotion> lom, int tick);

  int tween(int ta, int tb, int a, int b, int tick);

  /**
   * Inserts a keyframe in the IShape with the given name at the given tick. If the keyframe is the
   * first or last keyframe in the IShape's motions, it is initialized with the same properties as
   * its adjacent IMotion. If it is in between, it is initialized with the appropriately tweened
   * values. If it is the first keyframe, a blank keyframe is inserted.
   * @param name the name of the IShape to insert a keyframe in
   * @param tick the tick of the keyframe being inserted
   */
  void insertKeyframe(String name, int tick);

  Boolean existingKeyframe(String name, int tick);
}
