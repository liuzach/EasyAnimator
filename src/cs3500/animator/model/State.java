package animator.model;

import java.awt.Color;

/**
 * Represents one keyframe, or one half of an IMotion and offers functionality for building
 * a State to be used in an
 * IMotion.
 */
public class State implements IState{
  private int tick;
  private int x;
  private int y;
  private int width;
  private int height;
  private Color color;

  /**
   * Constructs a {@code Motion} object with the time tick and given Shape properties.
   *
   * @param tick   the time that this model.motion is shown on the screen
   * @param x      the x position of the model.shape of this model.motion
   * @param y      the y position of the model.shape of this model.motion
   * @param width  the width of the model.shape of this model.motion
   * @param height the height of the model.shape of this model.motion
   * @param color  the color of the model.shape of this model.motion
   * @throws IllegalArgumentException if any argument except color is negative, or if the Color is
   *                                  invalid
   */
  public State(int tick, int x, int y, int width, int height, Color color) {
    if (tick < 0 || width < 0 || height < 0) {
      throw new IllegalArgumentException("should have no negative arguments!");
    }
    this.tick = tick;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  @Override
  public int getTick() {
    return this.tick;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public void setX(int val) {
    this.x = val;
  }

  @Override
  public void setY(int val) {
    this.y = val;
  }

  @Override
  public void setWidth(int val) {
    this.width = val;
  }

  @Override
  public void setHeight(int val) {
    this.height = val;
  }

  @Override
  public void setR(int val) {
    this.color = new Color(val, this.getColor().getGreen(), this.getColor().getBlue());
  }

  @Override
  public void setG(int val) {
    this.color = new Color(this.getColor().getRed(), val, this.getColor().getBlue());
  }

  @Override
  public void setB(int val) {
    this.color = new Color(this.getColor().getRed(), this.getColor().getGreen(), val);
  }

  @Override
  public IState clone() {
    return new State(this.tick, this.x, this.y, this.width, this.height, this.color);
  }
}
