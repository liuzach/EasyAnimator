package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import cs3500.animator.util.AnimationBuilder;


/**
 * Implementation of an AnimationModel. One instance of AnimationModelImpl represents one full
 * animation.
 */
public class AnimationModelImpl implements AnimationModel, ImmAnimationModel {

  private ArrayList<IShape> shapes;
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * construct a animation model.
   *
   * @param shapes a list of shapes.
   * @param x      the x position
   * @param y      the y position
   * @param width  the width
   * @param height the height
   */
  public AnimationModelImpl(ArrayList<IShape> shapes, int x, int y, int width, int height) {
    this.shapes = shapes;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * construct a animation model with a empty list of shapes.
   *
   * @param x      the x position
   * @param y      the y position
   * @param width  the width
   * @param height the height
   */
  public AnimationModelImpl(int x, int y, int width, int height) {
    this.shapes = new ArrayList<IShape>();
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public AnimationModelImpl() {
    this.shapes = new ArrayList<IShape>();
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
  public void addShape(IShape s) {
    for (IShape existingShape : shapes) {
      if (existingShape.getName().equals(s.getName())) {
        throw new IllegalArgumentException("Already existing shape with same name.");
      }
    }
    this.shapes.add(s);
  }

  @Override
  public void addShape(String name, ShapeType type) {
    for (IShape existingShape : shapes) {
      if (existingShape.getName().equals(name)) {
        throw new IllegalArgumentException("Already existing shape with same name.");
      }
    }
    IShape i;
    if (type.getType().equals("rectangle")) {
      i = new Rectangle(new ArrayList<IMotion>(), name);
    } else if (type.getType().equals("ellipse")) {
      i = new Ellipse(new ArrayList<IMotion>(), name);
    } else {
      throw new NoSuchElementException("No such type.");
    }
    this.shapes.add(i);
  }

  @Override
  public IShape removeShape(String name) {
    int size = shapes.size();
    for (int i = 0; i < size; i++) {
      IShape s = shapes.get(i);
      if (s.getName().equals(name)) {
        return shapes.remove(i);
      }
    }
    throw new NoSuchElementException("No such shape with given name.");
  }

  @Override
  public void addMotion(String name, int startTick, int startX, int startY, int startWidth,
                        int startHeight, int startR, int startG, int startB, int endTick, int endX,
                        int endY, int endWidth, int endHeight, int endR, int endG, int endB) {
    for (IShape s : shapes) {
      if (s.getName().equals(name)) {
        s.addMotion(new Motion(new State(startTick, startX, startY, startWidth, startHeight,
                new Color(startR, startG, startB)), new State(endTick, endX, endY, endWidth,
                endHeight, new Color(endR, endG, endB))));
        return;
      }
    }
    throw new NoSuchElementException("No such shape with given name.");
  }


  @Override
  public IMotion removeFirstMotion(String name) {
    for (IShape s : shapes) {
      if (s.getName().equals(name)) {
        return s.removeFirstMotion();
      }
    }
    throw new NoSuchElementException("No such shape with given name.");
  }

  @Override
  public IMotion removeLastMotion(String name) {
    for (IShape s : shapes) {
      if (s.getName().equals(name)) {
        return s.removeLastMotion();
      }
    }
    throw new NoSuchElementException("No such shape with given name.");
  }


  @Override
  public ArrayList<IShape> getShapes() {
    return new ArrayList<IShape>(shapes);
  }

  @Override
  public ArrayList<IMotion> getMotionsForShape(String name) {
    for (IShape s : shapes) {
      if (s.getName().equals(name)) {
        return s.getMotions();
      }
    }
    throw new NoSuchElementException("No such shape with given name.");
  }

  @Override
  public int getMaxTick() {
    int max = 0;
    if (shapes.size() == 1) {
      max = shapes.get(0).getMaxTick();
    }
    for (int i = 0; i < shapes.size() - 1; i++) {
      int currMax = Math.max(shapes.get(i).getMaxTick(), shapes.get(i + 1).getMaxTick());
      if (currMax > max) {
        max = currMax;
      }
    }
    return max;
  }

  @Override
  public IState[] getStateAtTick(String name, int tick) {
    ArrayList<IMotion> lom = getMotionsForShape(name);
    IState[] ret = new State[2];
    for (IMotion m : lom) {
      if (m.getStart().getTick() == tick) {
        ret[0] = m.getStart();
      } else if (m.getEnd().getTick() == tick) {
        ret[1] = m.getEnd();
      }
    }
    return ret;
  }

  @Override
  public void setStateX(String name, int tick, int val) {
    IState s1 = getStateAtTick(name, tick)[0];
    IState s2 = getStateAtTick(name, tick)[1];
    if (s1 != null) {
      s1.setX(val);
    }
    if (s2 != null) {
      s2.setX(val);
    }
  }

  @Override
  public void setStateY(String name, int tick, int val) {
    IState s1 = getStateAtTick(name, tick)[0];
    IState s2 = getStateAtTick(name, tick)[1];
    if (s1 != null) {
      s1.setY(val);
    }
    if (s2 != null) {
      s2.setY(val);
    }
  }

  @Override
  public void setStateW(String name, int tick, int val) {
    IState s1 = getStateAtTick(name, tick)[0];
    IState s2 = getStateAtTick(name, tick)[1];
    if (s1 != null) {
      s1.setWidth(val);
    }
    if (s2 != null) {
      s2.setWidth(val);
    }
  }

  @Override
  public void setStateH(String name, int tick, int val) {
    IState s1 = getStateAtTick(name, tick)[0];
    IState s2 = getStateAtTick(name, tick)[1];
    if (s1 != null) {
      s1.setHeight(val);
    }
    if (s2 != null) {
      s2.setHeight(val);
    }
  }

  @Override
  public void setStateR(String name, int tick, int val) {
    IState s1 = getStateAtTick(name, tick)[0];
    IState s2 = getStateAtTick(name, tick)[1];
    if (s1 != null) {
      s1.setR(val);
    }
    if (s2 != null) {
      s2.setR(val);
    }
  }

  @Override
  public void setStateG(String name, int tick, int val) {
    IState s1 = getStateAtTick(name, tick)[0];
    IState s2 = getStateAtTick(name, tick)[1];
    if (s1 != null) {
      s1.setG(val);
    }
    if (s2 != null) {
      s2.setG(val);
    }
  }

  @Override
  public void setStateB(String name, int tick, int val) {
    IState s1 = getStateAtTick(name, tick)[0];
    IState s2 = getStateAtTick(name, tick)[1];
    if (s1 != null) {
      s1.setB(val);
    }
    if (s2 != null) {
      s2.setB(val);
    }
  }

  @Override
  public void deleteKeyframe(String name, int tick) {
    for (IShape s : getShapes()) {
      if (s.getName().equals(name)) {
        s.deleteKeyframe(tick);
      }
    }
  }

  @Override
  public void insertKeyframe(String name, int tick) {
    for (IShape s : getShapes()) {
      if (s.getName().equals(name)) {
        ArrayList<IMotion> motions = s.getMotions();

        if (motions.isEmpty()) {
          motions.add(new Motion(tick, 0, 0, 0, 0, 0, 0, 0, tick, 0, 0, 0, 0, 0, 0, 0));
        } else {
          IState firstMotionStart = motions.get(0).getStart();
          IState lastMotionEnd = motions.get(motions.size() - 1).getEnd();
          if (tick < firstMotionStart.getTick()) {
            motions.add(0, new Motion(new State(tick, firstMotionStart.getX(),
                    firstMotionStart.getY(), firstMotionStart.getWidth(),
                    firstMotionStart.getHeight(),
                    firstMotionStart.getColor()), firstMotionStart));
          } else if (tick > lastMotionEnd.getTick()) {
            motions.add(new Motion(lastMotionEnd, new State(tick,
                    lastMotionEnd.getX(), lastMotionEnd.getY(), lastMotionEnd.getWidth(),
                    lastMotionEnd.getHeight(), lastMotionEnd.getColor())));
          } else {
            for (int i = 0; i < motions.size(); i++) {
              IState startState = motions.get(i).getStart();
              IState endState = motions.get(i).getEnd();
              if (tick > startState.getTick() && tick < endState.getTick()) {
                int x = tween(startState.getTick(), endState.getTick(), startState.getX(),
                        endState.getX(), tick);
                int y = tween(startState.getTick(), endState.getTick(), startState.getY(),
                        endState.getY(), tick);
                int w = tween(startState.getTick(), endState.getTick(), startState.getWidth(),
                        endState.getWidth(), tick);
                int h = tween(startState.getTick(), endState.getTick(), startState.getHeight(),
                        endState.getHeight(), tick);
                int r = tween(startState.getTick(), endState.getTick(),
                        startState.getColor().getRed(), endState.getColor().getRed(), tick);
                int g = tween(startState.getTick(), endState.getTick(),
                        startState.getColor().getGreen(), endState.getColor().getGreen(), tick);
                int b = tween(startState.getTick(), endState.getTick(),
                        startState.getColor().getBlue(), endState.getColor().getBlue(), tick);
                IState tweenState = new State(tick, x, y, w, h, new Color(r, g, b));
                motions.remove(i);
                motions.add(i, new Motion(startState, tweenState));
                motions.add(i, new Motion(tweenState, endState));
              }
            }
          }
        }
      }
    }
  }


  @Override
  public Boolean existingKeyframe(String name, int tick) {
    for (IShape s : getShapes()) {
      if (s.getName().equals(name)) {
        for (IMotion m : s.getMotions()) {
          if (m.getStart().getTick() == tick || m.getEnd().getTick() == tick) {
            return true;
          }
        }
      }
    }
    return false;
  }


  @Override
  public int inMotion(ArrayList<IMotion> lom, int tick) {
    for (int i = 0; i < lom.size(); i++) {
      if ((tick >= lom.get(i).getStart().getTick()) && (tick <= lom.get(i).getEnd().getTick())) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public int tween(int ta, int tb, int a, int b, int tick) {
    return (a * (tb - tick) / (tb - ta)) + (b * (tick - ta) / (tb - ta));
  }

  /**
   * Builder class for AnimationModel. Works as an adapter for the AnimationModel interface and the
   * AnimationReader.
   */
  public static final class Builder implements AnimationBuilder<AnimationModel> {

    private ArrayList<IShape> shapes = new ArrayList<>();
    private int x;
    private int y;
    private int width;
    private int height;

    @Override
    public AnimationModel build() {
      return new AnimationModelImpl(this.shapes, this.x, this.y, this.width, this.height);
    }

    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
      for (IShape existingShape : shapes) {
        if (existingShape.getName().equals(name)) {
          throw new IllegalArgumentException("Already existing shape with same name.");
        }
      }
      IShape i;
      if (type.equals("rectangle")) {
        i = new Rectangle(new ArrayList<IMotion>(), name);
      } else if (type.equals("ellipse")) {
        i = new Ellipse(new ArrayList<IMotion>(), name);
      } else {
        throw new NoSuchElementException("No such type.");
      }
      this.shapes.add(i);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1,
                                                      int w1, int h1, int r1, int g1, int b1,
                                                      int t2, int x2, int y2, int w2, int h2,
                                                      int r2, int g2, int b2) {
      for (IShape s : shapes) {
        if (s.getName().equals(name)) {
          s.addMotion(new Motion(t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2));
        }
      }
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addKeyframe(String name, int t, int x, int y, int w,
                                                        int h, int r, int g, int b) {
      for (IShape s : shapes) {
        if (s.getName().equals(name)) {
          IState s1 = s.getMotions().get(s.getMotions().size() - 1).getEnd();
          IMotion m = new Motion(s1, new State(t, x, y, w, h, new Color(r, g, b)));
          s.addMotion(m);
          return this;
        }
      }
      throw new NoSuchElementException("No such shape with given name.");
    }
  }
}
