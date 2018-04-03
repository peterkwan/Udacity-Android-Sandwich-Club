package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String LOG_TAG = JsonUtils.class.getSimpleName();

    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject nameJson = sandwichJson.getJSONObject(NAME);

            return new Sandwich(
                    nameJson.getString(MAIN_NAME),
                    convertJsonArrayToStringArray(nameJson.getJSONArray(ALSO_KNOWN_AS)),
                    sandwichJson.getString(PLACE_OF_ORIGIN),
                    sandwichJson.getString(DESCRIPTION),
                    sandwichJson.getString(IMAGE),
                    convertJsonArrayToStringArray(sandwichJson.getJSONArray(INGREDIENTS)));
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Failed to parse the input string to JSON object");
            return null;
        }
    }

    private static List<String> convertJsonArrayToStringArray(JSONArray jsonArray) throws JSONException {
        List<String> strArray = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++)
            strArray.add(jsonArray.getString(i));

        return strArray;
    }
}
