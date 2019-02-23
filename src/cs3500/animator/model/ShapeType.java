package animator.model;

/**
 * Different types of supported Shapes.
 */
public enum ShapeType {
  RECTANGLE("rectangle"), ELLIPSE("ellipse");

  private final String type;

  /**
   * Constructs a ShapeType with given type in String form.
   *
   * @param type the type as a String
   */
  ShapeType(String type) {
    this.type = type;
  }

  /**
   * Return the String form of this ShapeType.
   */
  public String getType() {
    return this.type;
  }

  /**
   * Return the ShapeType with the value of the given String.
   * @param val the value of the ShapeType
   */
  public static ShapeType getShapeType(String val) {
    for (ShapeType type : ShapeType.values()) {
      if (type.getType().equals(val)) {
        return type;
      }
    }
    return null;
  }
}
