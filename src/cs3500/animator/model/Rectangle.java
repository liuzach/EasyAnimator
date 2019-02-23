package animator.model;

import java.util.ArrayList;

/**
 * Represents a Rectangle with a name and a list of Motions containing its States at given times.
 */
public class Rectangle extends cs3500.animator.model.AShape {
  /**
   * Constructs a Rectangle object with given motions, a name to identify it, and the type of
   * Shape.
   *
   * @param name    an identifying String for this object
   * @param motions the list of motions for this Shape to follow
   */
  public Rectangle(ArrayList<IMotion> motions, String name) {
    super(motions, name, ShapeType.RECTANGLE);
  }

  @Override
  public String getSVG(int rate) {
    String ans = "";
    IMotion m1 = motions.get(0);
    IState s1 = m1.getStart();
    ans += "<rect id=\"" + name + "\" " + "x=\"" + s1.getX() + "\" y=\"" +
            s1.getY() + "\" width=\"" + s1.getWidth()
            + "\" height=\"" + s1.getHeight() + "\" fill=\"rgb(" + s1.getColor().getRed() + ","
            + s1.getColor().getGreen() + "," + s1.getColor().getBlue() +
            ")\" visibility=\"visible\" >\n";
    for (IMotion m : motions) {
      ans += m.getSVG(rate, "\"x\"", "\"y\"", "\"width\"", "\"height\"");
    }
    ans += "\n</rect>\n";
    return ans;
  }
}
