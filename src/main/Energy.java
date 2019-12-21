package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Energy {
    private Double startEnergy;
    private Double currentEnergy;
    private Double moveEnergy;

    public Double getCurrentEnergy() {
        return currentEnergy;
    }

    public Double getStartEnergy() {
        return startEnergy;
    }

    public Double getMoveEnergy() {
        return moveEnergy;
    }


    public Energy(Double maxEnergy, Double moveEnergy){
        this.startEnergy=maxEnergy;
        this.currentEnergy=maxEnergy;
        this.moveEnergy=moveEnergy;
    }

    public Energy(Double maxEnergy, Double currentEnergy, Double moveEnergy){
        this.startEnergy=maxEnergy;
        this.currentEnergy=currentEnergy;
        this.moveEnergy=moveEnergy;
    }

    public void addEnergy(Double energy){
        this.currentEnergy+= energy;
    }

    public void subtractEnergy(Double subtractedEnergy){
        this.currentEnergy-=subtractedEnergy;
        if(this.currentEnergy<0) this.currentEnergy=0.0;
    }
}