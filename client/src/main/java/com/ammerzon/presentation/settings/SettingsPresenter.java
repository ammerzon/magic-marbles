package com.ammerzon.presentation.settings;

import com.ammerzon.business.GameService;
import com.ammerzon.presentation.helper.BasePresenter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javax.inject.Inject;

public class SettingsPresenter extends BasePresenter implements Initializable {

  @FXML
  public JFXTextField widthTextField;
  @FXML
  public JFXTextField heightTextField;
  @FXML
  public JFXButton startButton;
  @Inject
  public GameService gameService;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    widthTextField.setText("3");
    heightTextField.setText("3");

    var widthValid = Bindings.createBooleanBinding(() -> Integer.parseInt(widthTextField.getText()) >= 3, widthTextField.textProperty());
    var heightValid = Bindings.createBooleanBinding(() -> Integer.parseInt(heightTextField.getText()) >= 3, heightTextField.textProperty());
    startButton.disableProperty().bind(widthValid.not().or(heightValid.not()));
  }

  public void start(MouseEvent mouseEvent) {
    gameService.setWidth(Integer.parseInt(widthTextField.getText()));
    gameService.setHeight(Integer.parseInt(heightTextField.getText()));
    stage.close();
  }
}
