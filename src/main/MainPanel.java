package main;

import javax.swing.*;
import javax.swing.Icon;
import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MainPanel extends JPanel {
    public Map<Vector2D, JLabel> labels = new HashMap<>();
    public int gap,frameWidth,frameHeight,width,height;
    public Evolution evolution;
    public Map<String, ImageIcon> sprites = new HashMap<>();

    public MainPanel(int width, int height,int gap,Evolution evolution,int frameWidth,int frameHeight) {
        this.gap = gap;
        this.frameHeight=frameHeight;
        this.frameWidth=frameWidth;
        this.width=width;
        this.height=height;
        this.evolution = evolution;
        this.setLayout(new GridLayout(width, height, gap, gap));

        for (int y = this.width-1; y >= 0; y--) {
            for (int x = 0; x <= this.height-1; x++) {
                JLabel newLabel = new JLabel("", JLabel.CENTER);
                newLabel.setOpaque(true);
                newLabel.setForeground(Color.WHITE);
                newLabel.setBackground(Color.DARK_GRAY);

                this.add(newLabel);
                labels.put(new Vector2D(x, y), newLabel);
            }
        }
    }

    public void renderMainPanel() {
        for (int y = width-1; y >= 0; y--) {
            for (int x = 0; x <= height-1; x++) {
                Vector2D position = new Vector2D(x, y);
                boolean jungleField = evolution.world.jungleField.isItJungle(position);
                Grass plantOnPosition = null;
                Animal[] animalsOnPosition = null;
                if (this.evolution.world.isOccupied(position)) {
                    Object object = this.evolution.world.objectAt(position);
                    if (object instanceof Grass) {
                        plantOnPosition = (Grass) object;
                    } else if (object instanceof Collection) {
                        animalsOnPosition = (Animal[]) ((Collection) object).toArray(new Animal[((Collection) object).size()]);
                    }
                }

                JLabel label = labels.get(position);
                label.setText("");
                ImageIcon background=(jungleField ? MapElementsRepresentation.jungleRepresentation:MapElementsRepresentation.savannahRepresentation);
                background=resizeBigIcon(background);
                Icon icon;
                if(plantOnPosition==null && animalsOnPosition==null) icon=background;
                else if(plantOnPosition!=null)  icon = new CompoundIcon(CompoundIcon.Axis.Z_AXIS, 0, CompoundIcon.CENTER,
                        CompoundIcon.CENTER, background, resizeSmallIcon(MapElementsRepresentation.grassRepresentation));
                else {
                    icon= new CompoundIcon(CompoundIcon.Axis.Z_AXIS, 0, CompoundIcon.CENTER,
                            CompoundIcon.CENTER, background, resizeSmallIcon(MapElementsRepresentation.animalRepresentation));
                }
                label.setIcon(icon);
                }
            }
        }
        public ImageIcon resizeBigIcon(ImageIcon icon){
            int iconWidth = (frameWidth-(width-1)*gap)/width;
            int iconHeight = (frameHeight-(height-1)*gap)/height;
            Image scaleImage=icon.getImage().getScaledInstance(iconWidth, iconHeight, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(scaleImage);
    }

        public ImageIcon resizeSmallIcon(ImageIcon icon){
            int iconWidth = (frameWidth-(width-1)*gap)/(2*width);
            int iconHeight = (frameHeight-(height-1)*gap)/(2*height);
            Image scaleImage=icon.getImage().getScaledInstance(iconWidth, iconHeight, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(scaleImage);
        }
    }
