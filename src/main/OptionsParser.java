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
    Integer startEnergy;
    Integer moveEnergy;
    Integer plantEnergy;
    Double jungleRatio;
    Integer startAnimalNumber;

    public OptionsParser() {
        JSONParser initialArguments = new JSONParser();
        try (FileReader reader = new FileReader("initialArguments.json")) {
            Object obj = initialArguments.parse(reader);
            JSONObject data = (JSONObject) obj;
            Long longWidth = (Long) data.get("width");
            this.width =longWidth.intValue();
            Long longHeight = (Long) data.get("height");
            this.height=longHeight.intValue();
            Long longStartEnergy = (Long) data.get("startEnergy");
            this.startEnergy = longStartEnergy.intValue();
            Long longPlantEnergy = (Long) data.get("plantEnergy");
            this.plantEnergy = longPlantEnergy.intValue();
            Long longMoveEnergy = (Long) data.get("moveEnergy");
            this.moveEnergy = longMoveEnergy.intValue();
            this.jungleRatio = (Double) data.get("jungleRatio");
            Long longStartAnimalNumber = (Long) data.get("startAnimalNumber");
            this.startAnimalNumber = longStartAnimalNumber.intValue();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}
