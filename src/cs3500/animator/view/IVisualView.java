package cs3500.animator.view;

/**
 * Represents views which animate the model visually.
 */
public interface IVisualView extends IView {

  /**
   * Animates the model at the given tick.
   *
   * @param tick the current tick
   */
  void animate(int tick);
}
