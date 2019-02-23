package cs3500.animator.view;

import javax.swing.JFrame;

import java.awt.Dimension;

import cs3500.animator.model.ImmAnimationModel;

/**
 * Represents one frame of the animation given by the model. Offers functionality to animate given
 * model, return which motion is occurring at the current tick, and calculate the 'tween' value of
 * two values given their tick values.
 */
public class VisualView extends JFrame implements IVisualView {
  VisualPanel visualPanel;

  /**
   * Creates the JFrame and JPanel for the animation window. The top left corner of the window is
   * the top left of the canvas defined by the model. Implements a Timer which cycles every tick
   * according to the given tick rate in ticks per second.
   *
   * @param model the given model to animate
   */
  public VisualView(ImmAnimationModel model) {
    super("Animation");
    setSize(new Dimension(model.getWidth(), model.getHeight()));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    visualPanel = new VisualPanel(model);
    visualPanel.setLocation(model.getX(), model.getY());
    this.add(visualPanel);
  }

  @Override
  public void animate(int tick) {
    this.visualPanel.animate(tick);
  }

  @Override
  public void run() {
    this.setVisible(true);
  }
}
