package animator.model;

import java.awt.Color;
import java.util.Formatter;
import java.util.Locale;

/**
 * Represents a motion applied to an IShape, with the IShape's start State and end State.
 */
public class Motion implements IMotion {
  private IState start;
  private IState end;

  /**
   * Constructs a Motion with the given properties.
   *
   * @param t1 start time
   * @param x1 start x
   * @param y1 start y
   * @param w1 start width
   * @param h1 start height
   * @param r1 start red
   * @param g1 start green
   * @param b1 start blue
   * @param t2 end time
   * @param x2 end x
   * @param y2 end y
   * @param w2 end width
   * @param h2 end height
   * @param r2 end red
   * @param g2 end green
   * @param b2 end blue
   */
  public Motion(int t1, int x1, int y1, int w1, int h1, int r1, int g1,
                int b1, int t2, int x2, int y2, int w2,
                int h2, int r2, int g2, int b2) {
    if (t1 > t2) {
      throw new IllegalArgumentException("invalid motion");
    }
    this.start = new State(t1, x1, y1, w1, h1, new Color(r1, g1, b1));
    this.end = new State(t2, x2, y2, w2, h2, new Color(r2, g2, b2));
  }

  /**
   * Constructs a motion object with the given States.
   *
   * @param start start state
   * @param end   end state
   */
  public Motion(IState start, IState end) {
    if (start.getTick() > end.getTick()) {
      throw new IllegalArgumentException("invalid motion");
    }
    this.start = start;
    this.end = end;
  }

  @Override
  public IState getStart() {
    return this.start;
  }

  @Override
  public IState getEnd() {
    return this.end;
  }

  @Override
  public String getDescription() {
    StringBuilder sb1 = new StringBuilder();
    Formatter formatter1 = new Formatter(sb1, Locale.US);
    IState a = this.getStart();
    Color c1 = a.getColor();
    String str1 = formatter1.format("%1$-3s %2$-3s %3$-3s %4$-3s " +
                    "%5$-3s %6$-3s %7$-3s %8$-3s", a.getTick(),
            a.getX(), a.getY(), a.getWidth(), a.getHeight(), c1.getRed(),
            c1.getGreen(), c1.getBlue()).toString();
    StringBuilder sb2 = new StringBuilder();
    Formatter formatter2 = new Formatter(sb2, Locale.US);
    IState b = this.getEnd();
    Color c2 = b.getColor();
    String str2 = formatter2.format("%1$-3s %2$-3s %3$-3s %4$-3s %5$-3s " +
                    "%6$-3s %7$-3s %8$-3s", b.getTick(),
            b.getX(), b.getY(), b.getWidth(), b.getHeight(), c2.getRed(),
            c2.getGreen(), c2.getBlue()).toString();
    String c3 = str1 + "    " + str2 + "\n";
    return c3;
  }

  @Override
  public IMotion clone() {
    return new Motion(this.getStart(), this.getEnd());
  }

  @Override
  public String getSVG(int speed, String x, String y, String w, String h) {
    String ans = "";
    if (start.getX() != end.getX()) {
      ans += "    <animate attributeType=\"xml\" begin=\"" +
              this.start.getTick() / speed * 1000 + "ms\" " +
              "dur=\"" + (this.end.getTick() - this.start.getTick())
              * 1000 / speed + "ms\" attributeName=" + x +
              " from=\"" +
              this.start.getX() + "\" to=\"" + this.end.getX() + "\" fill=\"freeze\" />\n";
    }
    if (start.getY() != end.getY()) {
      ans += "    <animate attributeType=\"xml\" begin=\"" +
              this.start.getTick() / speed * 1000 + "ms\" " +
              "dur=\"" + (this.end.getTick() - this.start.getTick()) *
              1000 / speed + "ms\" attributeName=" + y +
              " from=\"" +
              this.start.getY() + "\" to=\"" + this.end.getY() + "\" fill=\"freeze\" />\n";
    }
    if (start.getWidth() != end.getWidth()) {
      ans += "    <animate attributeType=\"xml\" begin=\"" +
              this.start.getTick() / speed * 1000 + "ms\" " +
              "dur=\"" + (this.end.getTick() - this.start.getTick()) *
              1000 / speed + "ms\" attributeName=" + w +
              " from=\"" +
              this.start.getWidth() + "\" to=\"" + this.end.getWidth() + "\" fill=\"freeze\" />\n";
    }
    if (start.getHeight() != end.getHeight()) {
      ans += "    <animate attributeType=\"xml\" begin=\"" +
              this.start.getTick() / speed * 1000 + "ms\" " +
              "dur=\"" + (this.end.getTick() - this.start.getTick()) *
              1000 / speed + "ms\" attributeName=" + h +
              " from=\"" +
              this.start.getHeight() + "\" to=\"" + this.end.getHeight() +
              "\" fill=\"freeze\" />\n";
    }
    if (!start.getColor().equals(end.getColor())) {
      ans += "    <animate attributeType=\"xml\" begin=\"" + this.start.getTick() /
              speed * 1000 + "ms\" " +
              "dur=\"" + (this.end.getTick() - this.start.getTick()) * 1000 / speed +
              "ms\" attributeName=" +
              "\"fill\"" + " from=\"" +
              "rgb(" + start.getColor().getRed() + "," + start.getColor().getGreen() + "," +
              start.getColor().getBlue() + ")" + "\" to=\"" + "rgb(" +
              end.getColor().getRed() + "," +
              end.getColor().getGreen() + "," +
              end.getColor().getBlue() + ")" + "\" fill=\"freeze\" />\n";
    }
    return ans;
  }


}
