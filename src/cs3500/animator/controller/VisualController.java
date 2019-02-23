package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import cs3500.animator.view.IVisualView;

/**
 * Controller for IVisualViews. This controller keeps track of the tick through a timer and passes
 * it into the view so that the animation can be updated.
 */
public class VisualController implements ActionListener, IController {
  private IVisualView view;
  int tickRate;
  int tick = 0;
  Timer timer;

  /**
   * Creates a VisualController with the given view and tickRate in ticks per second, creating a
   * Timer according to the given speed.
   * @param view     the IVisualView to be controlled
   * @param tickRate the tickRate of the animation in ticks per second
   */
  VisualController(IVisualView view, int tickRate) {
    this.view = view;
    this.tickRate = tickRate;
    this.timer = new Timer(1000 / tickRate, this);
  }

  @Override
  public void run() {
    timer.start();
    this.view.run();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == timer) {
      view.animate(tick++);
    }
  }
}
