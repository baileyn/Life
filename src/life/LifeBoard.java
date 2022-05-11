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

import javafx.scene.layout.Pane;

/**
 *
 * @author Nicholas Bailey
 */
public class LifeBoard extends Pane {
    private final int width;
    private final int height;
        
    private final Cell[][] cells;

    public LifeBoard(int width, int height) {
        this.width = width;
        this.height = height;
        
        cells = new Cell[width][height];
        
        setPrefSize(width * Cell.SIZE, height * Cell.SIZE);
        setupBoard();
    }
    
    private void setupBoard() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell();
                cells[x][y].translate(x, y);

                cells[x][y].setOnMouseClicked(t -> {
                    Cell clickedCell = (Cell) t.getTarget();

                    clickedCell.toggleAlive();
                });

                getChildren().add(cells[x][y]);
            }
        }
    }

    void run() {
    }

    void pause() {
    }
}
