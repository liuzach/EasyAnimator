package cs3500.animator.view;

import cs3500.animator.model.IMotion;
import cs3500.animator.model.IShape;
import cs3500.animator.model.ImmAnimationModel;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

/**
 * This class represents a JPanel object which animates the given model.
 */
public class VisualPanel extends JPanel {
  ImmAnimationModel model;
  int tick = 1;

  /**
   * Creates a VisualPanel with the given model.
   * @param model the model to be animated
   */
  VisualPanel(ImmAnimationModel model) {
    this.model = model;
  }

  /**
   * Updates the tick to the given current tick and repaints the panel.
   *
   * @param tick the current tick
   */
  public void animate(int tick) {
    this.tick = tick;
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int x = 0;
    int y = 0;
    int w = 0;
    int h = 0;
    Graphics2D g2d = (Graphics2D) g;
    for (IShape s : model.getShapes()) {
      int index = model.inMotion(s.getMotions(), tick);
      if (index != -1) {
        IMotion m = s.getMotions().get(index);
        if (m.getStart().getTick() == m.getEnd().getTick()) {
          x = m.getStart().getX();
          y = m.getStart().getY();
          w = m.getStart().getWidth();
          h = m.getStart().getHeight();
          g2d.setColor(m.getStart().getColor());

        } else {
          int t1 = m.getStart().getTick();
          int t2 = m.getEnd().getTick();
          int x1 = m.getStart().getX();
          int x2 = m.getEnd().getX();
          int y1 = m.getStart().getY();
          int y2 = m.getEnd().getY();
          int w1 = m.getStart().getWidth();
          int w2 = m.getEnd().getWidth();
          int h1 = m.getStart().getHeight();
          int h2 = m.getEnd().getHeight();
          Color c1 = m.getStart().getColor();
          Color c2 = m.getEnd().getColor();


          x = model.tween(t1, t2, x1, x2, tick) - model.getX();
          y = model.tween(t1, t2, y1, y2, tick) - model.getY();
          w = model.tween(t1, t2, w1, w2, tick);
          h = model.tween(t1, t2, h1, h2, tick);

          g2d.setColor(new Color(model.tween(t1, t2, c1.getRed(), c2.getRed(), tick),
                  model.tween(t1, t2, c1.getGreen(), c2.getGreen(), tick),
                  model.tween(t1, t2, c1.getBlue(), c2.getBlue(), tick)));
        }
      } else {
        x = 0;
        y = 0;
        w = 0;
        h = 0;
      }

      String type = s.getType();
      if (type.equals("rectangle")) {
        g2d.fill(new Rectangle(x, y, w, h));
      } else if (type.equals("ellipse")) {
        g2d.fill(new Ellipse2D.Double(x, y, w, h));
      }
    }
  }


}
