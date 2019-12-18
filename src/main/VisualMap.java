package main;


import javax.swing.*;
import java.awt.*;

public class VisualMap extends JFrame {
        public int gap=0;
        public MainPanel mapPanel;
        public Evolution evolution;
        public int frameWidth ,frameHeight;

        public VisualMap(){
            OptionsParser parsedArguments=new OptionsParser();

            JFrame frame=new JFrame();
            frame.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0 ));

            this.evolution=new Evolution(parsedArguments.width,parsedArguments.height,parsedArguments.jungleRatio,parsedArguments.startEnergy,
            parsedArguments.moveEnergy,parsedArguments.plantEnergy,parsedArguments.startAnimalNumber);

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth=(int) Math.floor(screenSize.getHeight());
            int screenHeight=(int) Math.floor(screenSize.getWidth());
            frameHeight=(evolution.world.upperRight.y+1)*30;
            frameWidth=(evolution.world.upperRight.x+1)*30;
            mapPanel=new MainPanel(evolution.world.upperRight.y+1,evolution.world.upperRight.x+1,gap,evolution,frameWidth,frameHeight);

            frame.add(mapPanel);

            frame.setSize(frameWidth,frameHeight);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }

        public void run(){
            while(!evolution.world.animalList.isEmpty()){
            mapPanel.renderMainPanel();
            evolution.nextDay();}
        }
    }
