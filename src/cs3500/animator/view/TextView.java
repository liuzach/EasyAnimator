package cs3500.animator.view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import cs3500.animator.model.ImmAnimationModel;

/**
 * Represents the process of appending the information of a given model to a given filepath,
 * defaulting to an empty String and writing to System.out.
 */
public class TextView implements ITextView {
  String out;
  ImmAnimationModel model;

  /**
   * Constructs a TextView which appends the given model's animation description to the given
   * filepath.
   *
   * @param model The model whose description will be printed
   * @param out   The filepath to be appended to (defaults to an empty String)
   */
  public TextView(ImmAnimationModel model, String out) {
    this.model = model;
    this.out = out;
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
  public String getString() {
    String ans = "canvas " + model.getX() + " " + model.getY() + " " + model.getWidth() + " "
            + model.getHeight() + "\n";
    int size = model.getShapes().size();
    for (int i = 0; i < size; i++) {
      ans += model.getShapes().get(i).getDescription();
    }
    return ans;
  }


  @Override
  public void run() {
    tryAppend(getString(), out);
  }
}
