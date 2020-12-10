package com.ammerzon.business;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameService {
  private IntegerProperty width = new SimpleIntegerProperty();
  private IntegerProperty height = new SimpleIntegerProperty();

  public int getWidth() {
    return width.get();
  }

  public void setWidth(int width) {
    this.width.set(width);
  }

  public IntegerProperty widthProperty() {
    return width;
  }

  public int getHeight() {
    return height.get();
  }

  public void setHeight(int height) {
    this.height.set(height);
  }

  public IntegerProperty heightProperty() {
    return height;
  }
}
