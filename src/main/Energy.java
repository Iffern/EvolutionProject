package main;

public class Energy {
    private double startEnergy;
    private double currentEnergy;
    private double moveEnergy;

    public double getCurrentEnergy() {
        return currentEnergy;
    }

    public double getStartEnergy() {
        return startEnergy;
    }

    public double getMoveEnergy() {
        return moveEnergy;
    }


    Energy(double maxEnergy, double moveEnergy){
        this.startEnergy=maxEnergy;
        this.currentEnergy=maxEnergy;
        this.moveEnergy=moveEnergy;
    }

    Energy(double maxEnergy, double currentEnergy, double moveEnergy){
        this.startEnergy=maxEnergy;
        this.currentEnergy=currentEnergy;
        this.moveEnergy=moveEnergy;
    }

    void addEnergy(double energy){
        this.currentEnergy+= energy;
    }

    void subtractEnergy(double subtractedEnergy){
        this.currentEnergy-=subtractedEnergy;
        if(this.currentEnergy<0) this.currentEnergy=0.0;
    }
}