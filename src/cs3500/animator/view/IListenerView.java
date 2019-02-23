package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.event.ListSelectionListener;

/**
 * Represents views which require a listener for user interactivity.
 */
public interface IListenerView extends IView {

  /**
   * Sets the given ActionListener to each button or timer in the view.
   *
   * @param listener the listener to handle actions
   */
  void setActionListener(ActionListener listener);

  /**
   * Sets the given ItemListener to each JCheckbox in the view.
   *
   * @param listener the listener to handle actions
   */
  void setItemListener(ItemListener listener);

  /**
   * Sets the given ListSelectionListener to to each JList in the view.
   *
   * @param listener the listener to handle actions
   */
  void setListSelectionListener(ListSelectionListener listener);

  /**
   * Delete the keyframe of the currently selected IShape at the currently selected tick.
   */
  void deleteKeyframe();

  /**
   * Insert a keyframe into the currently selected IShape at the user inputted tick.
   */
  void insertKeyframe();

  /**
   * Sets the listener for the list of keyframes.
   * @param listener the listener for the list of keyframes
   */
  void setKeyframeListener(ListSelectionListener listener);

  /**
   * Updates the list of keyframes for the currently selected IShape.
   * @param i the index of the currently selected IShape's name
   */
  void updateKeyframes(int i);

  /**
   * Updates the State labels for the currently selected keyframe in the currently selected Shape.
   */
  void updateState();

  /**
   * Update the speed label with the given speed.
   * @param speed the current speed
   */
  void setSpeed(double speed);

  /**
   * Sets the X value of the selected keyframe in the selected IShape, to the user inputted integer.
   */
  void setX();

  /**
   * Sets the Y value of the selected keyframe in the selected IShape, to the user inputted integer.
   */
  void setY();

  /**
   * Sets the width value of the selected keyframe in the selected IShape, to the user inputted
   * integer.
   */
  void setW();

  /**
   * Sets the height value of the selected keyframe in the selected IShape, to the user inputted
   * integer.
   */
  void setH();

  /**
   * Sets the red value of the selected keyframe in the selected IShape, to the user inputted
   * integer.
   */
  void setR();

  /**
   * Sets the green value of the selected keyframe in the selected IShape, to the user inputted
   * integer.
   */
  void setG();

  /**
   * Sets the blue value of the selected keyframe in the selected IShape, to the user inputted
   * integer.
   */
  void setB();

  /**
   * Delete the currently selected Shape.
   */
  void deleteShape();

  /**
   * Insert a Shape with non empty user inputted name if it does not already exist.
   */
  void insertShape();
}
