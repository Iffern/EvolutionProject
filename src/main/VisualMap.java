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
            int screenWidth=(int) Math.floor(screenSize.getWidth());
            int screenHeight=(int) Math.floor(screenSize.getHeight());
            int width=evolution.world.upperRight.x+1;
            int height=evolution.world.upperRight.y+1;
            if(height==width){
                frameWidth=screenHeight-50;
                frameHeight=screenHeight-50;
            }
            else if(height>width){
                frameHeight=screenHeight-50;
                frameWidth=width*(frameHeight/height);
            }
            else{
                frameHeight=screenHeight-50;
                frameWidth=width*(frameHeight/height);
                if(frameWidth>screenWidth) frameWidth=screenWidth;
            }
            System.out.println(frameHeight);
            System.out.println(screenHeight);
            System.out.println(frameWidth);
            System.out.println(screenWidth);
            mapPanel=new MainPanel(height,width,gap,evolution,frameWidth,frameHeight);

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
