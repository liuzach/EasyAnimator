package cs3500.animator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.animator.controller.ControllerFactory;
import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;

/**
 * This program offers the functionality to create three types of views of an animation inputted
 * through a text file, as well as writing to a given filepath and giving pop-up error messages.
 */
public class Excellence {

  /**
   * Takes user input, creates a builder and parses the inputted file, creating a view with the
   * model and other arguments. Valid input requires pairs of arguments with the first item of the
   * pair being -in, -view, -out, or -speed. It also requires that each of these arguments appear
   * one time at most, and that -in and -view must appear.
   *
   * @param args user input
   */
  public static void main(String[] args) {
    Boolean containsIn = false;
    Boolean containsView = false;
    Boolean containsOut = false;
    Boolean containsSpeed = false;

    Readable in = null;
    String viewType = null;
    String out = "";
    int speed = 1;

    if (args.length % 2 == 1) {
      popUpError("Invalid number of arguments.");
    }
    for (int i = 0; i < args.length; i = i + 2) {
      switch (args[i]) {
        case "-in":
          if (containsIn) {
            popUpError("Already given -in argument.");
          } else {
            try {
              in = new BufferedReader(new FileReader(args[i + 1]));
              containsIn = true;
            } catch (FileNotFoundException e) {
              popUpError("File not found.");
            }
          }
          break;
        case "-view":
          if (containsView) {
            popUpError("Already given -view argument.");
          } else {
            viewType = args[i + 1];
            containsView = true;
          }
          break;
        case "-out":
          if (containsOut) {
            popUpError("Already given -out argument.");
          } else {
            out = args[i + 1];
            containsOut = true;
          }

          break;
        case "-speed":
          if (containsSpeed) {
            popUpError("Already given -speed argument.");
          } else {
            try {
              speed = Integer.parseInt(args[i + 1]);
              containsSpeed = true;
            } catch (NumberFormatException e) {
              popUpError(args[i] + " Argument " + args[i + 1] + " must be an integer.");
            }
          }
          break;
        default:
          throw new NoSuchElementException("unknown argument!");
      }
    }
    if (!(containsIn && containsView)) {
      popUpError("-in or -view not included in given arguments.");
    } else {
      AnimationModel model = new AnimationReader().parseFile(in, new AnimationModelImpl.Builder());
      IView view = new ViewFactory().makeView(model, viewType, out, speed);
      IController controller = new ControllerFactory().makeController(model, viewType, out, speed,
              view);
      controller.run();
    }
  }

  /**
   * Display a pop-up error with the given String as message, then exits the program.
   *
   * @param s the message to display
   */
  public static void popUpError(String s) {
    JOptionPane.showMessageDialog(new JFrame(),
            s,
            "Error",
            JOptionPane.ERROR_MESSAGE);
    System.exit(1);
  }

}
