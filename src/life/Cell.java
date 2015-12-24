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

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Nicholas Bailey
 */
public class Cell extends Rectangle {
    public static final int SIZE = 20;
    
    private boolean livingStatus = false;
    private boolean futureLivingStatus = false;
    
    public Cell() {
        super(SIZE, SIZE);
        
        setStroke(Color.BLACK);
        setStrokeWidth(1.0);
        
        setFill(Color.WHITE);
    }

    public void setPosition(int x, int y) {
        setTranslateX(x * SIZE);
        setTranslateY(y * SIZE);
    }
    
    public int x() {
        return (int) getTranslateX() / SIZE;
    }
    
    public int y() {
        return (int) getTranslateY() / SIZE;
    }
    
    public void toggleLivingStatus() {
        setLivingStatus(!livingStatus);
    }
    
    public void setFutureLivingStatus(boolean livingStatus) {
        this.futureLivingStatus = livingStatus;
    }
    
    public void commit() {
        if(livingStatus != futureLivingStatus) {
            setLivingStatus(futureLivingStatus);
        }
    }
    
    private void setLivingStatus(boolean alive) {
        this.livingStatus = alive;
        
        
        if(!alive) {
            setFill(Color.WHITE);
        } else {
            setFill(Color.BLACK);
        }
    }
    
    public boolean isAlive() {
        return livingStatus;
    }
}
