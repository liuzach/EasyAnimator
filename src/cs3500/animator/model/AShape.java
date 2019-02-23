package cs3500.animator.model;

import java.util.ArrayList;

/**
 * Represents a Shape in the animation which has a list of IMotions.
 */
public abstract class AShape implements IShape {
  protected ArrayList<IMotion> motions;
  protected String name;
  protected ShapeType type;

  /**
   * Constructs an AShape object with given motions, a name to identify it, and the type of shape.
   *
   * @param motions the list of motions for this cs3500.animator.cs3500.animator.model.Shape to
   *                follow
   * @param name    an identifying String for this object
   * @param type    the cs3500.animator.cs3500.animator.model.ShapeType of this
   *                cs3500.animator.cs3500.animator.model.Shape
   */
  AShape(ArrayList<IMotion> motions,
         String name, ShapeType type) {
    this.motions = motions;
    this.name = name;
    this.type = type;
  }


  @Override
  public void addMotion(IMotion m) {
    if (this.motions.isEmpty()) {
      this.motions.add(m);
    } else if (this.motions.get(this.motions.size() - 1).getEnd().getTick() >
            m.getStart().getTick()) {
      throw new IllegalArgumentException("Given motion overlaps with existing motion");
    } else if (this.motions.get(this.motions.size() - 1).getEnd().getTick() <
            m.getStart().getTick()) {
      throw new IllegalArgumentException("Given motion has a gap with existing motion");
    } else {
      this.motions.add(m);
    }
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getDescription() {
    String ans = "shape " + name + " " + type.getType() + "\n";
    for (int i = 0; i < motions.size(); i++) {
      ans += "motion " + name + " " + this.motions.get(i).getDescription();
    }
    return ans;
  }

  @Override
  public ArrayList<IMotion> getMotions() {
    return this.motions;
  }

  @Override
  public String getType() {
    return this.type.getType();
  }

  @Override
  public abstract String getSVG(int speed);

  @Override
  public IMotion removeLastMotion() {
    return motions.remove(motions.size() - 1);
  }

  @Override
  public IMotion removeFirstMotion() {
    return motions.remove(0);
  }

  @Override
  public void deleteKeyframe(int tick) {
    ArrayList<IMotion> motionsClone = new ArrayList<>();
    if (motions.size() == 1) {
      if (motions.get(0).getStart().getTick() == tick &&
              motions.get(0).getEnd().getTick() != tick) {
        motions.add(new Motion(motions.get(0).getEnd(), motions.get(0).getEnd()));
      } else if (motions.get(0).getStart().getTick() != tick && motions.get(0).getEnd().getTick() ==
              tick) {
        motions.add(new Motion(motions.get(0).getStart(), motions.get(0).getStart()));
      }
    }
    for (IMotion m : motions) {
      motionsClone.add(m.clone());
      if (m.getStart().getTick() == tick && m.getEnd().getTick() != tick &&
              motions.indexOf(m) == 0) {
        motions.remove(m);
        return;
      } else if (m.getEnd().getTick() == tick && motions.indexOf(m) == motions.size() - 1) {
        motions.remove(m);
        return;
      }
    }
    for (int i = 0; i < motionsClone.size() - 1; i++) {
      int startTick = motionsClone.get(i).getStart().getTick();
      int endTick = motionsClone.get(i).getEnd().getTick();
      int nextStartTick = motionsClone.get(i + 1).getStart().getTick();
      int nextEndTick = motionsClone.get(i + 1).getEnd().getTick();
      if (startTick == tick && endTick == tick) {
        motions.remove(i);
        motions.remove(i);
      } else if (startTick != tick && endTick == tick &&
              nextStartTick == tick && nextEndTick != tick) {
        IState s1 = motions.get(i).getStart();
        IState s2 = motions.get(i + 1).getEnd();
        motions.remove(i);
        motions.remove(i);
        motions.add(i, new Motion(s1, s2));
      }
    }
  }


  @Override
  public int getMaxTick() {
    int max = 0;
    for (int i = 0; i < motions.size() - 1; i++) {
      int currMax = Math.max(motions.get(i).getEnd().getTick(),
              motions.get(i + 1).getEnd().getTick());
      if (currMax > max) {
        max = currMax;
      }
    }
    return max;
  }
}
