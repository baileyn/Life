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

import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import life.util.ListUtils;

/**
 *
 * @author Nicholas Bailey
 */
public class LifeBoard extends Pane {
    private static final int DEFAULT_ANIMATION_DELAY = 200;
    
    private final int width;
    private final int height;
        
    private final Cell[][] cells;
    private final Timeline timeline = new Timeline();

    public LifeBoard(int width, int height) {
        this.width = width;
        this.height = height;
        
        cells = new Cell[width][height];
        
        setupBoard();
        createAnimation();
    }
    
    public void play() {
        timeline.play();
    }
    
    public void stop() {
        timeline.stop();
    }
    
    public void clear() {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                cells[x][y].setFutureLivingStatus(false);
            }
        }
        
        commit();
    }
    
    private void process() {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                Cell currentCell = getCell(x, y);
                List<Cell> neighbors = getNeighbors(currentCell);
                int aliveCount = ListUtils.count(neighbors, t -> t.isAlive());
                
                if(currentCell.isAlive()) {
                    if(aliveCount <= 1) {
                        currentCell.setFutureLivingStatus(false);
                    } else if(aliveCount <= 3) {
                        currentCell.setFutureLivingStatus(true);
                    } else {
                        currentCell.setFutureLivingStatus(false);
                    }
                } else {
                    if(aliveCount == 3) {
                        currentCell.setFutureLivingStatus(true);
                    }
                }
            }
        }
        
        commit();
    }
    
    private void commit() {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                cells[x][y].commit();
            }
        }
    }
    
    private Cell getCell(int x, int y) {
        return cells[x][y];
    }
    
    private List<Cell> getNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        
        int x = (int) cell.x();
        int y = (int) cell.y();
        
        // Left cells
        if(x > 0) {
            neighbors.add(getCell(x - 1, y));
            
            if(y > 0) {
                neighbors.add(getCell(x - 1, y - 1));
            }
            
            if(y < height - 1) {
                neighbors.add(getCell(x - 1, y + 1));
            }
        }
        
        // Right cells
        if(x < width - 1) {
            neighbors.add(getCell(x + 1, y));
            
            if(y > 0) {
                neighbors.add(getCell(x + 1, y - 1));
            }
            
            if(y < height - 1) {
                neighbors.add(getCell(x + 1, y + 1));
            }
        }
        
        // Top cell
        if(y > 0) {
            neighbors.add(getCell(x, y - 1));
        }
        
        // Bottom cell
        if(y < height - 1) {
            neighbors.add(getCell(x, y + 1));
        }
        
        return neighbors;
    }
    
    private void setupBoard() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell();
                cells[x][y].setPosition(x, y);

                cells[x][y].setOnMouseClicked(t -> {
                    Cell clickedCell = (Cell) t.getTarget();

                    clickedCell.toggleLivingStatus();
                });

                getChildren().add(cells[x][y]);
            }
        }
    }
    
    private void createAnimation() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        
        KeyFrame keyFrame = new KeyFrame(Duration.millis(DEFAULT_ANIMATION_DELAY), t -> {
            process();
        });
        
        timeline.getKeyFrames().add(keyFrame);
    }
}
