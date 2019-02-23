package cs3500.animator.controller;


import java.util.NoSuchElementException;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.EditView;
import cs3500.animator.view.ITextView;
import cs3500.animator.view.IView;
import cs3500.animator.view.IVisualView;

/**
 * A factory for creating views given its information and the type of IView as a String.
 */
public class ControllerFactory {
  /**
   * Construct a View with given animation model, the type of IView, the output string,
   * and the speed of the animation per second.
   *
   * @param model    the immutable model
   * @param view     the view
   * @param out      the output string
   * @param speed    the speed of the animation per second.
   * @return a View
   */
  public IController makeController(AnimationModel model, String viewType, String out, int speed,
                              IView view) {
    IController controller;
    switch (viewType) {
      case "text":
        controller = new TextController((ITextView) view);
        break;
      case "svg":
        controller = new TextController((ITextView) view);
        break;
      case "visual":
        controller = new VisualController((IVisualView) view, speed);
        break;
      case "edit":
        controller = new EditController(model, (EditView) view, speed);
        break;
      default:
        throw new NoSuchElementException("No such type of controller.");
    }
    return controller;
  }

}
