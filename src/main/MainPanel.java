package main;

import javax.swing.*;
import javax.swing.Icon;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainPanel extends JPanel {
    public Map<Vector2D, JLabel> labels = new HashMap<>();
    public int gap,frameWidth,frameHeight,width,height;

    public MainPanel(int width, int height,int gap,int frameWidth,int frameHeight) {
        this.gap = gap;
        this.frameHeight=frameHeight;
        this.frameWidth=frameWidth;
        this.width=width;
        this.height=height;
        this.setLayout(new GridLayout(height,width, gap, gap));

        for (int y = this.height-1; y >= 0; y--) {
            for (int x = 0; x <= this.width-1; x++) {
                JLabel newLabel = new JLabel("", JLabel.CENTER);
                newLabel.setOpaque(true);
                newLabel.setForeground(Color.WHITE);
                newLabel.setBackground(Color.DARK_GRAY);

                Vector2D position = new Vector2D(x,y);
                this.add(newLabel);
                labels.put(position, newLabel);
            }
        }
    }
    public void changedPosition(Vector2D oldPosition, Vector2D newPosition, int animalsOnOld, int animalsOnNew,
                                boolean isOldJungle, boolean isNewJungle){
        JLabel oldLabel=this.labels.get(oldPosition);
        JLabel newLabel=this.labels.get(newPosition);
        ImageIcon oldBackground = (isOldJungle ? MapElementsRepresentation.jungleRepresentation :
                MapElementsRepresentation.savannahRepresentation);
        oldBackground=resizeBigIcon(oldBackground);
        ImageIcon newBackground = (isNewJungle ? MapElementsRepresentation.jungleRepresentation :
                MapElementsRepresentation.savannahRepresentation);
        newBackground=resizeBigIcon(newBackground);
        oldLabel.setIcon(makeCompoundIcon(oldBackground,animalsOnOld));
        newLabel.setIcon(makeCompoundIcon(newBackground,animalsOnNew));
    }

    private Icon makeCompoundIcon(ImageIcon background, int numberOfAnimals){
        Icon icon;
        if(numberOfAnimals==0) icon=background;
        else if(numberOfAnimals==1) icon=new CompoundIcon(CompoundIcon.Axis.Z_AXIS, 0, CompoundIcon.CENTER,
                CompoundIcon.CENTER, background, resizeSmallIcon(MapElementsRepresentation.animalRepresentation));
        else if(numberOfAnimals==2) icon=new CompoundIcon(CompoundIcon.Axis.Z_AXIS, 0, CompoundIcon.CENTER,
                CompoundIcon.CENTER, background, resizeBigIcon(MapElementsRepresentation.twoAnimalsRepresentation));
        else if(numberOfAnimals==3) icon=new CompoundIcon(CompoundIcon.Axis.Z_AXIS, 0, CompoundIcon.CENTER,
                CompoundIcon.CENTER, background, resizeBigIcon(MapElementsRepresentation.threeAnimalsRepresentation));
        else icon=new CompoundIcon(CompoundIcon.Axis.Z_AXIS, 0, CompoundIcon.CENTER,
                    CompoundIcon.CENTER, background, resizeBigIcon(MapElementsRepresentation.manyAnimalsRepresentation));
        return icon;
    }

    void mapEvent(Vector2D position,boolean isItJungle,MapEvents type){
        JLabel label=this.labels.get(position);
        ImageIcon background = (isItJungle ? MapElementsRepresentation.jungleRepresentation :
                MapElementsRepresentation.savannahRepresentation);
        background=resizeBigIcon(background);
        Icon icon;
        if(type==MapEvents.DEATH){
        icon=new CompoundIcon(CompoundIcon.Axis.Z_AXIS, 0, CompoundIcon.CENTER,
                CompoundIcon.CENTER, background, resizeSmallIcon(MapElementsRepresentation.deathRepresentation));}
        else if(type==MapEvents.BREED){
        icon=new CompoundIcon(CompoundIcon.Axis.Z_AXIS, 0, CompoundIcon.CENTER,
                    CompoundIcon.CENTER, background, resizeSmallIcon(MapElementsRepresentation.breedRepresentation));}
        else{
        icon=new CompoundIcon(CompoundIcon.Axis.Z_AXIS, 0, CompoundIcon.CENTER,
                CompoundIcon.CENTER, background, resizeSmallIcon(MapElementsRepresentation.grassRepresentation));}
        label.setIcon(icon);
    }

    ImageIcon resizeBigIcon(ImageIcon icon){
            int iconWidth = (frameWidth-(width-1)*gap)/width;
            int iconHeight = (frameHeight-(height-1)*gap)/height;
            Image scaleImage=icon.getImage().getScaledInstance(iconWidth, iconHeight, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(scaleImage);
    }

    ImageIcon resizeSmallIcon(ImageIcon icon){
            int iconWidth = (frameWidth-(width-1)*gap)/(2*width);
            int iconHeight = (frameHeight-(height-1)*gap)/(2*height);
            Image scaleImage=icon.getImage().getScaledInstance(iconWidth, iconHeight, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(scaleImage);
        }
    }
