package main;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OptionsParser {
    Integer width;
    Integer height;
    Double startEnergy;
    Double moveEnergy;
    Double plantEnergy;
    Double jungleRatio;
    Integer startAnimalNumber;

    public OptionsParser() throws IllegalArgumentException{
        JSONParser initialArguments = new JSONParser();
        try (FileReader reader = new FileReader("initialArguments.json")) {
            Object obj = initialArguments.parse(reader);
            JSONObject data = (JSONObject) obj;
            Long longWidth = (Long) data.get("width");
            this.width =longWidth.intValue();
            Long longHeight = (Long) data.get("height");
            this.height=longHeight.intValue();
            this.startEnergy = (Double) data.get("startEnergy");
            this.plantEnergy = (Double) data.get("plantEnergy");
            this.moveEnergy = (Double) data.get("moveEnergy");
            this.jungleRatio = (Double) data.get("jungleRatio");
            Long longStartAnimalNumber = (Long) data.get("startAnimalNumber");
            this.startAnimalNumber = longStartAnimalNumber.intValue();
            if(jungleRatio>1.0)  throw new IllegalArgumentException("Jungle ratio "+jungleRatio+" is too large. Please insert jungle ratio smaller " +
                    "than 1.0");
            if(moveEnergy>startEnergy) throw new IllegalArgumentException("Your animals have no chance to move. Please provide move energy smaller than " +
                    "the start energy");
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}
