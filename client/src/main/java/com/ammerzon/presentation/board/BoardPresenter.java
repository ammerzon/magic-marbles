package com.ammerzon.presentation.board;

import com.ammerzon.api.DefaultApi;
import com.ammerzon.api.invoker.ApiClient;
import com.ammerzon.api.invoker.ApiException;
import com.ammerzon.api.model.GameSessionDto;
import com.ammerzon.api.model.GameState;
import com.ammerzon.api.model.PositionDto;
import com.ammerzon.api.model.SizeDto;
import com.ammerzon.business.GameService;
import com.ammerzon.presentation.helper.BasePresenter;
import com.ammerzon.presentation.settings.SettingsView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javax.inject.Inject;

public class BoardPresenter extends BasePresenter implements Initializable {

  private static final Logger LOGGER = Logger.getLogger(BoardPresenter.class.getName());
  private static final int PADDING = 5;
  private static final int CIRCLE_PADDING = 3;
  private static final Color RED_COLOR = Color.valueOf("#E74D5E");
  private static final Color BLUE_COLOR = Color.valueOf("#3193F8");
  private static final Color GREEN_COLOR = Color.valueOf("#009688");
  private static final Color EMPTY_COLOR = Color.valueOf("#2F2F2F");
  private final ApiClient apiClient = new ApiClient().setPort(8080);
  private final DefaultApi api = new DefaultApi(apiClient);

  @FXML
  public GridPane fieldGridPane;
  @Inject
  public GameService gameService;
  private GameSessionDto gameSession = null;

  @FXML
  public void newGameMenuItemClicked(ActionEvent actionEvent) {
    presentModally(SettingsView.class, "Settings");
  }

  @FXML
  public void exitMenuItemClicked(ActionEvent actionEvent) {
    Platform.exit();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    gameService.heightProperty().addListener((observable, oldValue, newValue) -> {
      var size = new SizeDto();
      size.setHeight(gameService.getHeight());
      size.setWidth(gameService.getWidth());
      try {
        gameSession = api.startGame(size);
        fieldGridPane.disableProperty().setValue(false);
        renderMarbles();
      } catch (ApiException e) {
        LOGGER.severe(e.getMessage());
      }
    });
    //fieldGridPane.disableProperty().setValue(true);
    presentModally(SettingsView.class, "New Game");
  }

  private double getCellSize() {
    return fieldGridPane.getWidth() / gameService.getWidth();
  }

  private void renderMarbles() {
    var width = gameSession.getGameBoard().getSize().getWidth();
    var height = gameSession.getGameBoard().getSize().getHeight();
    var gameBoard = gameSession.getGameBoard().getBoard();

    if (fieldGridPane.getColumnConstraints().size() > 0) {
      fieldGridPane.getColumnConstraints().subList(0, fieldGridPane.getColumnConstraints().size())
          .clear();
    }
    if (fieldGridPane.getRowConstraints().size() > 0) {
      fieldGridPane.getRowConstraints().subList(0, fieldGridPane.getRowConstraints().size())
          .clear();
    }

    for (int i = fieldGridPane.getColumnConstraints().size(); i < width; i++) {
      fieldGridPane.getColumnConstraints().add(new ColumnConstraints(getCellSize()));
    }

    for (int i = fieldGridPane.getRowConstraints().size(); i < height; i++) {
      fieldGridPane.getRowConstraints().add(new RowConstraints(getCellSize()));
    }

    fieldGridPane.getChildren().clear();
    renderBoardHoles();

    for (int row = 0; row < width; row++) {
      var columns = (List<String>) gameBoard.get(row);

      for (int col = 0; col < height; col++) {
        Circle circle = new Circle(getCellSize() / 2 - CIRCLE_PADDING);

        switch (columns.get(col)) {
          case "RED":
            circle.setFill(RED_COLOR);
            break;
          case "BLUE":
            circle.setFill(BLUE_COLOR);
            break;
          case "GREEN":
            circle.setFill(GREEN_COLOR);
            break;
          default:
            circle.setFill(EMPTY_COLOR);
            break;
        }
        fieldGridPane.add(circle, col, row);
      }
    }
  }

  private void renderBoardHoles() {
    for (int col = 0; col < gameSession.getGameBoard().getSize().getWidth(); col++) {
      for (int row = 0; row < gameSession.getGameBoard().getSize().getHeight(); row++) {
        Circle circle = new Circle(getCellSize() / 2 - CIRCLE_PADDING);
        circle.setFill(Color.WHITE);
        circle.setEffect(
            new DropShadow(BlurType.GAUSSIAN, Color.valueOf("#00000024"), 10.0, 0.0, -1, -2));
        int finalCol = col;
        int finalRow = row;
        circle.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
          gameAreaGridPaneClicked(finalRow, finalCol);
        });
        fieldGridPane.add(circle, col, row);
      }
    }
  }

  private void gameAreaGridPaneClicked(int row, int col) {
    try {
      var position = new PositionDto();
      position.setX(col);
      position.setY(row);
      gameSession = api.makeMove(position);

      if (gameSession.getState() == GameState.FINISHED) {
        Platform.runLater(
            () -> {
              fieldGridPane.disableProperty().setValue(true);
              showDialog("Game ended!", gameSession.getScore().getPoints().toString());
            });
      }
      renderMarbles();
    } catch (ApiException e) {
      LOGGER.severe(e.getMessage());
    }
  }
}
