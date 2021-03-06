package com.example.resslen.projektbdio;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Resslen on 02.06.2017.
 */

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://bdiol4.herokuapp.com/rejestracja";
    private Map<String, String> params;

    public RegisterRequest(String surname, String name, String password, String email, String state, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("nazwisko", surname);
        params.put("imie", name);
        params.put("haslo", password);
        params.put("email", email);
        params.put("status", state);
    }
    // 0 egzaminator
    // 1 student

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
