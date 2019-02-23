package cs3500.animator.controller;

/**
 * This interface represents a generic controller for any IView, offering functionality for
 * running the view.
 */
public interface IController {
  /**
   * Runs the animation of the model using the view.
   */
  void run();
}
