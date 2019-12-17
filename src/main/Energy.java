package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Energy {
   private Integer maxEnergy;
   private Integer currentEnergy;
   private Integer moveEnergy;

    public Integer getCurrentEnergy() {
        return currentEnergy;
    }

    public Integer getMaxEnergy() {
        return maxEnergy;
    }

    public Integer getMoveEnergy() {
        return moveEnergy;
    }

    public Energy(Integer maxEnergy, Integer moveEnergy){
		this.maxEnergy=maxEnergy;
		this.currentEnergy=maxEnergy;
		this.moveEnergy=moveEnergy;
	}

	public Energy(Integer maxEnergy, Integer currentEnergy, Integer moveEnergy){
		this.maxEnergy=maxEnergy;
		this.currentEnergy=currentEnergy;
		this.moveEnergy=moveEnergy;
	}

	public void addEnergy(Integer energy){
   		this.currentEnergy+= energy;
   		if(this.currentEnergy>this.maxEnergy) this.currentEnergy=this.maxEnergy;
	}

	public void substractEnergy(Integer substractedEnergy){
        this.currentEnergy-=substractedEnergy;
        if(this.currentEnergy<0) this.currentEnergy=0;
    }
}