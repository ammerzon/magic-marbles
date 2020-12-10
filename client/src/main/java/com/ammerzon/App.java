package com.ammerzon;

import com.airhacks.afterburner.injection.Injector;
import com.ammerzon.presentation.board.BoardView;
import com.ammerzon.presentation.helper.BasePresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setMaxWidth(1200);
        stage.setMaxHeight(1000);
        BoardView boardView = new BoardView();
        BasePresenter presenter = (BasePresenter) boardView.getPresenter();
        presenter.setStage(stage);
        Scene scene = new Scene(boardView.getView());
        stage.setTitle("Magic Marbles");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        Injector.forgetAll();
    }
}
