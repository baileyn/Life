/*
 * Copyright (C) 2015 Nicholas Bailey
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package life;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Nicholas Bailey
 */
public class Life extends Application {
    /**
     * The width of the stage in cells.
     */
    public static final int WIDTH = 50;
    
    /**
     * The height of the stage in cells.
     */
    public static final int HEIGHT = 50;

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        
        LifeBoard lifeBoard = new LifeBoard(WIDTH, HEIGHT);
        lifeBoard.setPrefSize(WIDTH * Cell.SIZE, HEIGHT * Cell.SIZE);
        
        HBox controls = new HBox();
        controls.setAlignment(Pos.CENTER);
        
        Button run = new Button("Run");
        run.setOnAction(t -> lifeBoard.play());
        
        Button pause = new Button("Pause");
        pause.setOnAction(t -> lifeBoard.stop());
        
        Button clear = new Button("Clear");
        clear.setOnAction(t -> lifeBoard.clear());
        
        controls.getChildren().addAll(run, pause, clear);
        root.getChildren().addAll(controls, lifeBoard);
        
        
        Scene scene = new Scene(root, WIDTH * Cell.SIZE, HEIGHT * Cell.SIZE);

        primaryStage.setTitle("Conway's Game of Life!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
