package main;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class World {
    public static void main(String[] args) {
        OptionsParser parseJSON=new OptionsParser();
        Evolution evolve=new Evolution(parseJSON.width,parseJSON.height,parseJSON.jungleRatio,
                parseJSON.startEnergy,parseJSON.moveEnergy,parseJSON.plantEnergy,parseJSON.startAnimalNumber);
        evolve.startEvolution();
        }
    }
