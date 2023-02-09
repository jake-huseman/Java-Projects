package com.project.interfaceexp;

import com.android.volley.VolleyError;

public interface IVolleyListener {
    public void success(String s);
    public void onError(VolleyError error);
}

