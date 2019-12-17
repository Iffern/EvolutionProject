package main;

import java.util.ArrayList;
import java.util.List;

public class Jungle
{   Vector2D upperRight;
    Vector2D lowerLeft;
    List<Vector2D> emptyJungleFields=new ArrayList<>();
    int plantEnergy;

    public Jungle(int mapWidth, int mapHeight,double jungleRatio,int energy){
        int mapSurface=mapWidth*mapHeight;
        double junglePercent=jungleRatio*100;
        Double jungleSurface=(mapSurface*junglePercent)/(100+junglePercent);
        Integer jungleWidth=(int) Math.sqrt(jungleSurface);
        if(jungleWidth>(mapWidth-2)) jungleWidth=mapWidth-2;
        Integer jungleHeight=(int) Math.floor(jungleSurface/jungleWidth);
        if(jungleHeight>(mapHeight-2)){
            jungleHeight=mapHeight-2;
            jungleWidth=(int)Math.floor(jungleSurface/jungleHeight);
        }
        System.out.println("Jungle width".concat(jungleWidth.toString()));
        System.out.println("Jungle height".concat(jungleHeight.toString()));
        int lowerX=(int)Math.floor((mapWidth-jungleWidth)/2);
        int lowerY=(int)Math.floor((mapHeight-jungleHeight)/2);
        int upperX=(int)Math.floor((mapWidth-jungleWidth)/2)+jungleWidth-1;
        int upperY=(int)Math.floor((mapHeight-jungleHeight)/2)+jungleHeight-1;
        this.upperRight=new Vector2D(upperX,upperY);
        this.lowerLeft= new Vector2D(lowerX,lowerY);
        for(int i=lowerX;i<=upperX;i++){
            for(int j=lowerY;j<=upperY;j++) this.emptyJungleFields.add(new Vector2D(i,j));
        }
        this.plantEnergy=energy;
    }

}
