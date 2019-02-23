package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.NumberFormatter;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IShape;
import cs3500.animator.model.IState;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.State;

/**
 * Represents the view for visually animating editing the given model. Offers functionality for
 * playback controls as well as inserting/deleting shapes and keyframes, and modifying existing
 * keyframes.
 */
public class EditView extends JFrame implements IVisualView, IListenerView {
  private JButton startButton;
  private JButton pauseButton;
  private JButton restartButton;
  private JButton speedUp;
  private JButton slowDown;
  private JLabel speedLabel;
  private JCheckBox loopBox;
  private JLabel xLabel;
  private JButton setX;
  private JFormattedTextField xField;
  private JLabel yLabel;
  private JButton setY;
  private JFormattedTextField yField;
  private JLabel wLabel;
  private JButton setW;
  private JFormattedTextField wField;
  private JLabel hLabel;
  private JButton setH;
  private JFormattedTextField hField;
  private JLabel rLabel;
  private JButton setR;
  private JFormattedTextField rField;
  private JLabel gLabel;
  private JButton setG;
  private JFormattedTextField gField;
  private JLabel bLabel;
  private JButton setB;
  private JFormattedTextField bField;
  private JButton deleteKeyframe;
  private JButton insertKeyframe;
  private JButton insertShape;
  private JTextField insertShapeField;
  private JComboBox insertShapeBox;
  private JButton deleteShape;
  private JFormattedTextField insertKeyframeField;
  private VisualPanel visualPanel;
  private JList<String> listOfShapeNames;
  private JList<Integer> listOfKeyframes;
  private DefaultListModel<String> shapeNames;
  private DefaultListModel<Integer> keyframes;
  private AnimationModel model;

  /**
   * Constructs an EditView with the given model, animating its shapes and motions and adding
   * buttons, fields, and lists which allow user interactivity for playback and editing.
   *
   * @param model the model to be animated/edited
   */
  public EditView(AnimationModel model) {
    super("Animation");
    this.model = model;
    String[] shapeTypes;
    JPanel playbackPanel;
    JPanel listPanel;
    JPanel keyframePanel;
    JPanel bottomPanel;
    JScrollPane shapeScroller;
    JScrollPane keyframeScroller;
    this.setSize(Math.max(model.getWidth(), 1600), Math.max(model.getHeight(), 900));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    visualPanel = new VisualPanel(model);
    this.add(visualPanel, BorderLayout.CENTER);

    startButton = new JButton("Start");
    startButton.setActionCommand("Start button");
    pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("Pause button");
    restartButton = new JButton("Restart");
    restartButton.setActionCommand("Restart button");
    speedUp = new JButton("Speed up");
    speedUp.setActionCommand("Speed up");
    slowDown = new JButton("Slow down");
    slowDown.setActionCommand("Slow down");
    loopBox = new JCheckBox("Loop");
    loopBox.setSelected(false);
    loopBox.setActionCommand("Loop box");

    speedLabel = new JLabel("Speed: 1.0");
    speedLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    bottomPanel = new JPanel(new BorderLayout());

    playbackPanel = new JPanel();
    playbackPanel.add(startButton);
    playbackPanel.add(pauseButton);
    playbackPanel.add(restartButton);
    playbackPanel.add(speedUp);
    playbackPanel.add(slowDown);
    playbackPanel.add(loopBox);
    playbackPanel.add(speedLabel);
    bottomPanel.add(playbackPanel, BorderLayout.PAGE_START);

    listPanel = new JPanel();
    shapeNames = new DefaultListModel<>();
    for (IShape s : model.getShapes()) {
      shapeNames.addElement(s.getName());
    }
    listOfShapeNames = new JList<>(shapeNames);
    listOfShapeNames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    shapeScroller = new JScrollPane(listOfShapeNames);
    shapeScroller.setPreferredSize(new Dimension(50, 144));
    listPanel.add(shapeScroller);

    keyframes = new DefaultListModel<>();
    listOfKeyframes = new JList<>(keyframes);
    listOfKeyframes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    keyframeScroller = new JScrollPane(listOfKeyframes);
    keyframeScroller.setPreferredSize(new Dimension(50, 144));
    listPanel.add(keyframeScroller);
    bottomPanel.add(listPanel, BorderLayout.LINE_START);

    NumberFormat format = NumberFormat.getInstance();
    format.setMinimumIntegerDigits(0);
    format.setGroupingUsed(false);
    NumberFormatter formatter = new NumberFormatter(format);
    formatter.setValueClass(Integer.class);
    formatter.setMinimum(0);
    formatter.setMaximum(Integer.MAX_VALUE);
    formatter.setAllowsInvalid(false);
    formatter.setCommitsOnValidEdit(true);

    NumberFormat colorFormat = NumberFormat.getInstance();
    colorFormat.setGroupingUsed(false);
    NumberFormatter colorFormatter = new NumberFormatter(colorFormat);
    colorFormatter.setValueClass(Integer.class);
    colorFormatter.setMinimum(0);
    colorFormatter.setMaximum(255);
    colorFormatter.setAllowsInvalid(false);
    colorFormatter.setCommitsOnValidEdit(true);


    xLabel = new JLabel();
    setX = new JButton("Set X");
    setX.setActionCommand("Set X");
    xField = new JFormattedTextField(formatter);
    xField.setColumns(5);


    yLabel = new JLabel();
    setY = new JButton("Set Y");
    setY.setActionCommand("Set Y");
    yField = new JFormattedTextField(formatter);
    yField.setColumns(5);

    wLabel = new JLabel();
    setW = new JButton("Set Width");
    setW.setActionCommand("Set W");
    wField = new JFormattedTextField(formatter);
    wField.setColumns(5);

    hLabel = new JLabel();
    setH = new JButton("Set Height");
    setH.setActionCommand("Set H");
    hField = new JFormattedTextField(formatter);
    hField.setColumns(5);

    rLabel = new JLabel();
    setR = new JButton("Set Red");
    setR.setActionCommand("Set R");
    rField = new JFormattedTextField(colorFormatter);
    rField.setColumns(5);

    bLabel = new JLabel();
    setB = new JButton("Set Blue");
    setB.setActionCommand("Set B");
    bField = new JFormattedTextField(colorFormatter);
    bField.setColumns(5);

    gLabel = new JLabel();
    setG = new JButton("Set Green");
    setG.setActionCommand("Set G");
    gField = new JFormattedTextField(colorFormatter);
    gField.setColumns(5);

    keyframePanel = new JPanel(new GridLayout(0, 9));

    keyframePanel.add(xLabel);
    keyframePanel.add(xField);
    keyframePanel.add(setX);
    keyframePanel.add(yLabel);
    keyframePanel.add(yField);
    keyframePanel.add(setY);

    keyframePanel.add(wLabel);
    keyframePanel.add(wField);
    keyframePanel.add(setW);

    keyframePanel.add(hLabel);
    keyframePanel.add(hField);
    keyframePanel.add(setH);

    keyframePanel.add(rLabel);
    keyframePanel.add(rField);
    keyframePanel.add(setR);

    keyframePanel.add(gLabel);
    keyframePanel.add(gField);
    keyframePanel.add(setG);

    keyframePanel.add(bLabel);
    keyframePanel.add(bField);
    keyframePanel.add(setB);

    keyframePanel.add(new JLabel());

    deleteKeyframe = new JButton("Delete Keyframe");
    deleteKeyframe.setActionCommand("Delete Keyframe");
    keyframePanel.add(deleteKeyframe);

    deleteShape = new JButton("Delete Shape");
    deleteShape.setActionCommand("Delete Shape");
    keyframePanel.add(deleteShape);

    keyframePanel.add(new JLabel());

    insertKeyframe = new JButton("Insert Keyframe");
    insertKeyframe.setActionCommand("Insert Keyframe");
    insertKeyframeField = new JFormattedTextField(formatter);
    keyframePanel.add(insertKeyframeField);
    keyframePanel.add(insertKeyframe);
    int enumSize = 0;
    for (ShapeType type : ShapeType.values()) {
      enumSize++;
    }
    shapeTypes = new String[enumSize];
    int count = 0;
    for (ShapeType type : ShapeType.values()) {
      shapeTypes[count] = type.getType();
      count++;
    }
    insertShapeBox = new JComboBox(shapeTypes);
    insertShape = new JButton("Insert Shape");
    insertShape.setActionCommand("Insert Shape");
    insertShapeField = new JTextField();
    keyframePanel.add(insertShapeBox);
    keyframePanel.add(insertShapeField);
    keyframePanel.add(insertShape);

    bottomPanel.add(keyframePanel, BorderLayout.CENTER);

    this.add(bottomPanel, BorderLayout.PAGE_END);
  }

  @Override
  public void deleteKeyframe() {
    if (checkSelected()) {
      String shapeName = listOfShapeNames.getSelectedValue();
      int tick = listOfKeyframes.getSelectedValue();
      model.deleteKeyframe(shapeName, tick);
      updateKeyframes(listOfShapeNames.getSelectedIndex());
    }
  }

  @Override
  public void insertKeyframe() {
    if (listOfShapeNames.getSelectedIndex() == -1) {
      JOptionPane.showMessageDialog(this, "No shape selected", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
      String shapeName = listOfShapeNames.getSelectedValue();
      int tick = Integer.parseInt(insertKeyframeField.getText());
      if (model.existingKeyframe(shapeName, tick)) {
        JOptionPane.showMessageDialog(this, "Already a keyframe at given tick",
                "Error", JOptionPane.ERROR_MESSAGE);
      } else {
        model.insertKeyframe(shapeName, tick);
        updateKeyframes(listOfShapeNames.getSelectedIndex());
      }
    }
  }

  @Override
  public void animate(int tick) {
    this.visualPanel.animate(tick);
  }

  @Override
  public void run() {
    setVisible(true);
  }

  @Override
  public void setActionListener(ActionListener listener) {
    startButton.addActionListener(listener);
    pauseButton.addActionListener(listener);
    restartButton.addActionListener(listener);
    speedUp.addActionListener(listener);
    slowDown.addActionListener(listener);
    setX.addActionListener(listener);
    setY.addActionListener(listener);
    setW.addActionListener(listener);
    setH.addActionListener(listener);
    setR.addActionListener(listener);
    setG.addActionListener(listener);
    setB.addActionListener(listener);
    deleteKeyframe.addActionListener(listener);
    insertKeyframe.addActionListener(listener);
    deleteShape.addActionListener(listener);
    insertShape.addActionListener(listener);
  }

  @Override
  public void setItemListener(ItemListener listener) {
    loopBox.addItemListener(listener);
  }

  @Override
  public void setListSelectionListener(ListSelectionListener listener) {
    listOfShapeNames.addListSelectionListener(listener);

  }

  @Override
  public void setKeyframeListener(ListSelectionListener listener) {
    listOfKeyframes.addListSelectionListener(listener);
  }

  @Override
  public void updateKeyframes(int i) {
    keyframes.clear();
    if (i != -1) {
      ArrayList<IMotion> lom = model.getShapes().get(i).getMotions();
      for (int j = 0; j < lom.size(); j++) {
        int startTick = lom.get(j).getStart().getTick();
        int endTick = lom.get(j).getEnd().getTick();
        addNonDuplicate(keyframes, startTick);
        addNonDuplicate(keyframes, endTick);
      }
    }
  }

  @Override
  public void updateState() {
    if (listOfKeyframes.getSelectedIndex() == -1) {
      xLabel.setText("");
      yLabel.setText("");
      wLabel.setText("");
      hLabel.setText("");
      rLabel.setText("");
      gLabel.setText("");
      bLabel.setText("");
    } else {
      String shapeName = listOfShapeNames.getSelectedValue();
      IState s = model.getStateAtTick(shapeName, listOfKeyframes.getSelectedValue())[0];
      // last keyframe
      if (s == null) {
        s = model.getStateAtTick(shapeName, listOfKeyframes.getSelectedValue())[1];
      }
      xLabel.setText("X: " + Integer.toString(s.getX()));
      yLabel.setText("Y: " + Integer.toString(s.getY()));
      wLabel.setText("Width: " + Integer.toString(s.getWidth()));
      hLabel.setText("Height: " + Integer.toString(s.getHeight()));
      rLabel.setText("Red: " + Integer.toString(s.getColor().getRed()));
      gLabel.setText("Green: " + Integer.toString(s.getColor().getGreen()));
      bLabel.setText("Blue: " + Integer.toString(s.getColor().getBlue()));
    }
  }

  /**
   * Add given element to given list if there is not already a duplicate element.
   * @param l  the list to add to
   * @param e  the element to add
   */
  private <T> void addNonDuplicate(DefaultListModel<T> l, T e) {
    if (!(l.contains(e))) {
      l.addElement(e);
    }
  }

  @Override
  public void setSpeed(double speed) {
    speedLabel.setText("Speed: " + Double.toString(speed));
  }


  @Override
  public void setX() {
    if (checkSelected()) {
      String shapeName = listOfShapeNames.getSelectedValue();
      int tick = listOfKeyframes.getSelectedValue();
      model.setStateX(shapeName, tick, Integer.parseInt(xField.getText()));
      updateState();
    }
  }

  @Override
  public void setY() {
    if (checkSelected()) {
      String shapeName = listOfShapeNames.getSelectedValue();
      int tick = listOfKeyframes.getSelectedValue();
      model.setStateY(shapeName, tick, Integer.parseInt(yField.getText()));
      updateState();
    }
  }

  @Override
  public void setW() {
    if (checkSelected()) {
      String shapeName = listOfShapeNames.getSelectedValue();
      int tick = listOfKeyframes.getSelectedValue();
      model.setStateW(shapeName, tick, Integer.parseInt(wField.getText()));
      updateState();
    }
  }

  @Override
  public void setH() {
    if (checkSelected()) {
      String shapeName = listOfShapeNames.getSelectedValue();
      int tick = listOfKeyframes.getSelectedValue();
      model.setStateH(shapeName, tick, Integer.parseInt(hField.getText()));
      updateState();
    }
  }

  @Override
  public void setR() {
    if (checkSelected()) {
      String shapeName = listOfShapeNames.getSelectedValue();
      int tick = listOfKeyframes.getSelectedValue();
      model.setStateR(shapeName, tick, Integer.parseInt(rField.getText()));
      updateState();
    }
  }

  @Override
  public void setG() {
    if (checkSelected()) {
      String shapeName = listOfShapeNames.getSelectedValue();
      int tick = listOfKeyframes.getSelectedValue();
      model.setStateG(shapeName, tick, Integer.parseInt(gField.getText()));
      updateState();
    }
  }

  @Override
  public void setB() {
    if (checkSelected()) {
      String shapeName = listOfShapeNames.getSelectedValue();
      int tick = listOfKeyframes.getSelectedValue();
      model.setStateB(shapeName, tick, Integer.parseInt(bField.getText()));
      updateState();
    }
  }

  /**
   * Returns whether both a IShape and keyframe tick are selected, showing an error message if
   * either is not selected.
   */
  private Boolean checkSelected() {
    if (listOfShapeNames.getSelectedIndex() == -1) {
      JOptionPane.showMessageDialog(this, "No shape selected", "Error", JOptionPane.ERROR_MESSAGE);
      return false;
    } else if (listOfKeyframes.getSelectedIndex() == -1) {
      JOptionPane.showMessageDialog(this, "No keyframe selected",
              "Error", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    return true;
  }

  @Override
  public void deleteShape() {
    if (listOfShapeNames.getSelectedIndex() == -1) {
      JOptionPane.showMessageDialog(this, "No shape selected", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
      String shapeName = listOfShapeNames.getSelectedValue();
      model.removeShape(shapeName);
      shapeNames.remove(listOfShapeNames.getSelectedIndex());
    }
  }

  @Override
  public void insertShape() {
    if (insertShapeField.getText().equals("")) {
      JOptionPane.showMessageDialog(this, "Cannot have blank shape name", "Error",
              JOptionPane.ERROR_MESSAGE);
    } else {
      try {
        model.addShape(insertShapeField.getText(),
                ShapeType.getShapeType(insertShapeBox.getSelectedItem().toString()));
        shapeNames.addElement(insertShapeField.getText());
      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(this, "Already shape with given name", "Error",
                JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
