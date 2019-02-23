package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.EditView;

/**
 * Controller for the EditView. Handles button presses, list selections, and checkboxes for the
 * view. The controller also keeps track of time through a Timer object.
 */
public class EditController implements ActionListener, ItemListener, ListSelectionListener,
        IController {
  boolean loop;
  private AnimationModel model;
  private EditView view;
  int tickRate;
  int tick = 0;
  Timer timer;
  double speed = 1;

  /**
   * Creates an EditController object with the given model, view, and tickRate in ticks per second,
   * initializing the timer according to the speed.
   *
   * @param model    The model of the animation to be edited
   * @param view     The view which will display the animation and editing controls
   * @param tickRate The speed of the animation in ticks per second
   */
  public EditController(AnimationModel model, EditView view, int tickRate) {
    this.model = model;
    this.view = view;
    this.tickRate = tickRate;
    this.timer = new Timer(1000 / tickRate, this);
  }

  @Override
  public void run() {
    this.view.setActionListener(this);
    this.view.setItemListener(this);
    this.view.setListSelectionListener(this);
    this.view.setKeyframeListener(new KeyFrameListener());
    this.view.run();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == timer) {
      if (loop && tick > model.getMaxTick()) {
        tick = -1;
      }
      view.animate(tick++);
    } else {
      switch (e.getActionCommand()) {

        case "Start button":
          timer.start();
          break;
        case "Pause button":
          timer.stop();
          break;
        case "Restart button":
          this.tick = 0;
          timer.start();
          break;
        case "Speed up":
          if (speed < 2.0) {
            speed += 0.25;
            view.setSpeed(speed);
            timer.setDelay((int) Math.round(1000 / tickRate / speed));
          }
          break;
        case "Slow down":
          if (speed > 0.25) {
            speed -= 0.25;
            view.setSpeed(speed);
            timer.setDelay((int) Math.round(1000 / tickRate / speed));
          }
          break;
        case "Set X":
          view.setX();
          break;
        case "Set Y":
          view.setY();
          break;
        case "Set W":
          view.setW();
          break;
        case "Set H":
          view.setH();
          break;
        case "Set R":
          view.setR();
          break;
        case "Set G":
          view.setG();
          break;
        case "Set B":
          view.setB();
          break;
        case "Delete Keyframe":
          view.deleteKeyframe();
          break;
        case "Insert Keyframe":
          view.insertKeyframe();
          break;
        case "Delete Shape":
          view.deleteShape();
          break;
        case "Insert Shape":
          view.insertShape();
          break;
        default:
          throw new UnsupportedOperationException("Unhandled Operation");
      }
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    String who = ((JCheckBox) e.getItemSelectable()).getActionCommand();
    if (who.equals("Loop box")) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        this.loop = true;
      } else {
        this.loop = false;
      }

    }
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    if (!(e.getValueIsAdjusting())) {
      view.updateKeyframes(((JList<String>) e.getSource()).getSelectedIndex());
    }
  }

  /**
   * This class handles listening for the second list in the EditView which keeps track of the
   * keyframes for the selected IShape.
   */
  class KeyFrameListener implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
      if (!(e.getValueIsAdjusting())) {
        view.updateState();
      }
    }
  }
}
