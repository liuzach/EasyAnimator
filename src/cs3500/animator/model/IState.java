package animator.model;

import java.awt.*;

public interface IState {

  /**
   * Returns the tick of this State.
   */
  int getTick();

  /**
   * Returns the x value of the IShape containing this State.
   */
  int getX();

  /**
   * Returns the y value of the IShape containing this State.
   */
  int getY();

  /**
   * Returns the width of the IShape containing this State.
   */
  int getWidth();

  /**
   * Returns the height of the IShape containing this State.
   */
  int getHeight();

  /**
   * Returns the color of the IShape containing this State.
   */
  Color getColor();

  /**
   * Sets the X value for this State.
   * @param val the new x value
   */
  void setX(int val);

  /**
   * Sets the Y value for this State.
   * @param val the new y value
   */
  void setY(int val);

  /**
   * Sets the width value for this State.
   * @param val the new width value
   */
  void setWidth(int val);

  /**
   * Sets the height value for this State.
   * @param val the new height value
   */
  void setHeight(int val);

  /**
   * Sets the red value for this State.
   * @param val the new red value
   */
  void setR(int val);

  /**
   * Sets the green value for this State.
   * @param val the new green value
   */
  void setG(int val);

  /**
   * Sets the blue value for this State.
   * @param val the new blue value
   */
  void setB(int val);

  /**
   * Returns a clone of this State.
   */
  IState clone();
}
