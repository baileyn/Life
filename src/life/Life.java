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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Nicholas Bailey
 */
public class Life extends Application {

    public static final int WIDTH = 25;
    public static final int HEIGHT = 25;

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        
        LifeBoard lifeBoard = new LifeBoard(WIDTH, HEIGHT);
        
        HBox controls = new HBox();
        controls.setAlignment(Pos.CENTER);
        
        Button run = new Button("Run");
        run.setOnAction(t -> lifeBoard.run());
        
        Button pause = new Button("Pause");
        pause.setOnAction(t -> lifeBoard.pause());
        
        controls.getChildren().addAll(run, pause);
        root.getChildren().addAll(controls, lifeBoard);
        
        
        Scene scene = new Scene(root, WIDTH * Cell.SIZE, HEIGHT * Cell.SIZE);

        primaryStage.setTitle("Hello World!");
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
