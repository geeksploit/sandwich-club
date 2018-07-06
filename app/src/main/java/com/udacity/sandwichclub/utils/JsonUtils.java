package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.udacity.sandwichclub.utils.JsonContractSandwich.*;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject j = new JSONObject(json);
            return new Sandwich(
                    j.getJSONObject(NAME).getString(MAIN_NAME),
                    jsonArrayToList(j.getJSONObject(NAME).getJSONArray(ALSO_KNOWN_AS)),
                    j.getString(PLACE_OF_ORIGIN),
                    j.getString(DESCRIPTION),
                    j.getString(IMAGE),
                    jsonArrayToList(j.getJSONArray(INGREDIENTS))
            );
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<String> jsonArrayToList(JSONArray jsonArray) throws JSONException {
        // TODO: is there a better way to split the array?
        List<String> alsoKnownAs = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            alsoKnownAs.add(jsonArray.getString(i));
        }
        return alsoKnownAs;
    }
}

class JsonContractSandwich {
    final static String NAME = "name";
    final static String MAIN_NAME = "mainName";
    final static String ALSO_KNOWN_AS = "alsoKnownAs";
    final static String PLACE_OF_ORIGIN = "placeOfOrigin";
    final static String DESCRIPTION = "description";
    final static String IMAGE = "image";
    final static String INGREDIENTS = "ingredients";
}
