package cs3500.animator.view;


import java.util.NoSuchElementException;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.ImmAnimationModel;

/**
 * A factory for creating views given its information and the type of IView as a String.
 */
public class ViewFactory {

  /**
   * Construct a View with given immutable animation model, the type of IView, the output string,
   * and the speed of the animation per second.
   *
   * @param model    the immutable model
   * @param viewType the type of IView
   * @param out      the output string
   * @param speed    the speed of the animation per second.
   * @return a View
   */
  public IView makeView(AnimationModel model, String viewType, String out, int speed) {
    IView view = null;
    switch (viewType) {
      case "text":
        view = new TextView((ImmAnimationModel) model, out);
        break;
      case "svg":
        view = new SvgView((ImmAnimationModel) model, out, speed);
        break;
      case "visual":
        view = new VisualView(model);
        break;
      case "edit":
        view = new EditView(model);
        break;
      default:
        throw new NoSuchElementException("No such type of view.");
    }
    return view;
  }

}
