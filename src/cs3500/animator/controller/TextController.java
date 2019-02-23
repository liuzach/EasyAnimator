package cs3500.animator.controller;

import cs3500.animator.view.ITextView;

/**
 * Controller for any ITextView. Offers functionality to run the view's run method.
 */
public class TextController implements IController {
  ITextView view;

  /**
   * Creates the TextController with the given view so that it's process can be run.
   * @param view the view to be run with this controller
   */
  TextController(ITextView view) {
    this.view = view;
  }

  @Override
  public void run() {
    this.view.run();
  }
}
