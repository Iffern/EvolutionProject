package main;

public class Main {
    public static void main(String[] args){
        try {
            OptionsParser parsedArguments = new OptionsParser();
            Evolution evolution=new Evolution(parsedArguments.width,parsedArguments.height,parsedArguments.jungleRatio,parsedArguments.startEnergy,
                    parsedArguments.moveEnergy,parsedArguments.plantEnergy,parsedArguments.startAnimalNumber);
            evolution.prepareAnimals();
            evolution.world.renderMainPanel();
            evolution.run();
        }
        catch (IllegalArgumentException e){
            System.out.println("Arguments you have inserted were not suitable considered assumptions of the Evolution Project. PLease make " +
                    "necessary corrections in your .json file.");
            e.printStackTrace();
        }
        }
    }
