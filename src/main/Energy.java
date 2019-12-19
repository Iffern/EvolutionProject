package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Energy {
    private Integer startEnergy;
    private Integer currentEnergy;
    private Integer moveEnergy;

    public Integer getCurrentEnergy() {
        return currentEnergy;
    }

    public Integer getStartEnergy() {
        return startEnergy;
    }

    public Integer getMoveEnergy() {
        return moveEnergy;
    }


    public Energy(Integer maxEnergy, Integer moveEnergy){
        this.startEnergy=maxEnergy;
        this.currentEnergy=maxEnergy;
        this.moveEnergy=moveEnergy;
    }

    public Energy(Integer maxEnergy, Integer currentEnergy, Integer moveEnergy){
        this.startEnergy=maxEnergy;
        this.currentEnergy=currentEnergy;
        this.moveEnergy=moveEnergy;
    }

    public void addEnergy(Integer energy){
        this.currentEnergy+= energy;
    }

    public void substractEnergy(Integer substractedEnergy){
        this.currentEnergy-=substractedEnergy;
        if(this.currentEnergy<0) this.currentEnergy=0;
    }
}