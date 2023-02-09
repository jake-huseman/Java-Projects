package com.project.interfaceexp;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public class userLogic implements IVolleyListener {
    JSONObject jsonObject;
    IView i;

    public void setJsonObject(JSONObject obj) {
        jsonObject = obj;
    }

    @Override
    public void success(String s) {
        i.showText(s);
    }

    @Override
    public void onError(VolleyError error) {
        i.showError(error.toString());
    }
}
