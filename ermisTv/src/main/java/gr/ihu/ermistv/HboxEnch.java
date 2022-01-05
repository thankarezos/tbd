/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.ihu.ermistv;

import javafx.scene.layout.HBox;

/**
 * @author User
 */
public class HboxEnch extends HBox {

    private boolean state = false;
    private int id;

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return state;
    }
    
    public void setValueID(int id) {
        this.id = id;
    }

    public int getValueID() {
        return id;
    }


}
