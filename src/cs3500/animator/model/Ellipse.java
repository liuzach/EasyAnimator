package cs3500.animator.model;

import java.util.ArrayList;

/**
 * Represents an Ellipse object that contains a list of Motions. Offers functionality to produce an
 * SVG format description of this shape and its motions.
 */
public class Ellipse extends AShape {
  /**
   * Constructs an Ellipse object with given motions, a name to identify it, and the type of shape.
   *
   * @param name    an identifying String for this object
   * @param motions the list of motions for this Shape to follow
   */
  public Ellipse(ArrayList<IMotion> motions, String name) {
    super(motions, name, ShapeType.ELLIPSE);
  }

  @Override
  public String getSVG(int speed) {
    String ans = "";
    IMotion m1 = this.motions.get(0);
    IState s1 = m1.getStart();
    ans += "<ellipse id=\"" + name + "\" " + "cx=\"" + s1.getX() + "\" cy=\"" + s1.getY() +
            "\" rx=\"" + s1.getWidth()
            + "\" ry=\"" + s1.getHeight() + "\" fill=\"rgb(" + s1.getColor().getRed() + ","
            + s1.getColor().getGreen() + "," +
            s1.getColor().getBlue() + ")\" visibility=\"visible\" >\n";
    for (IMotion m : motions) {
      ans += m.getSVG(speed, "\"cx\"", "\"cy\"", "\"rx\"", "\"ry\"");
    }
    ans += "\n</ellipse>\n";
    return ans;
  }
}