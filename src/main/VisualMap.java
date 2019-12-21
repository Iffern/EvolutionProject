package main;


import javax.swing.*;
import java.awt.*;

public class VisualMap extends JFrame {
        public int gap=0;
        public MainPanel mapPanel;
        public int frameWidth ,frameHeight;

        public VisualMap(int width, int height) {
            JFrame frame=new JFrame();
            frame.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0 ));

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth=(int) Math.floor(screenSize.getWidth());
            int screenHeight=(int) Math.floor(screenSize.getHeight());
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
                if(frameWidth>screenWidth){
                    frameWidth=screenWidth;
                    frameHeight=height*(frameWidth/width);
                }
            }
            System.out.println(frameHeight);
            System.out.println(screenHeight);
            System.out.println(frameWidth);
            System.out.println(screenWidth);
            mapPanel=new MainPanel(width,height,gap,frameWidth,frameHeight);

            frame.add(mapPanel);

            frame.setSize(frameWidth,frameHeight);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }
