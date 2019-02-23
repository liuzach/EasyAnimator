package cs3500.animator.view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import cs3500.animator.model.IShape;
import cs3500.animator.model.ImmAnimationModel;

/**
 * Represents the view for an Svg animation. Offers functionality to convert given model's animation
 * to Svg format and write to a given file.
 */
public class SvgView implements ITextView {
  ImmAnimationModel model;
  String out;
  int speed;

  /**
   * Constructs a SvgView which creates an Svg format animation of the given model at the given
   * speed in the given file.
   *
   * @param model The model of the animation
   * @param out   The file which will be written to/created
   * @param speed the ticks per second of the animation
   */
  public SvgView(ImmAnimationModel model, String out, int speed) {
    this.model = model;
    this.out = out;
    this.speed = speed;
  }

  /**
   * Returns the Svg format of the given model's animation as a String.
   */
  public String getString() {
    String ans = "<svg width=\"" + model.getWidth() + "\" height=\"" + model.getHeight() +
            "\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n";
    for (IShape s : model.getShapes()) {
      ans += s.getSVG(speed) + "\n";
    }
    ans += "</svg>";
    return ans;
  }

  @Override
  public void tryAppend(String s, String out) {
    if (out.isEmpty()) {
      System.out.println(s);
    } else {
      try {
        BufferedWriter writer = new BufferedWriter(new FileWriter(out));
        writer.write(s);
        writer.flush();
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }


  @Override
  public void run() {
    tryAppend(getString(), out);
  }
}


