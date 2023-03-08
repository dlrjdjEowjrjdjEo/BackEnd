package com.medic.userservice.controller;

import com.medic.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/check")
    public String check() {
        return "Hello User Controller";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody String code) throws Exception {
        code = code.substring(16);
        code = code.replace("\"", "").replace("}", "");
        HashMap<String, Object> userInfo = new HashMap<String, Object>();

        String reqUrl = "https://kapi.kakao.com/v2/user/me";
        URL url = new URL(reqUrl);

        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + code);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        JSONParser parser = new JSONParser();
        JSONObject jsonOb = (JSONObject) parser.parse(sb.toString());
        JSONObject kakaoAccount = (JSONObject) jsonOb.get("kakao_account");
        JSONObject getNickname = (JSONObject) kakaoAccount.get("profile");

        String name = (String) getNickname.get("nickname");
        String age = (String) kakaoAccount.get("age_range");
        age = age.substring(0, 2);
        int realAge = Integer.parseInt(age);
        String email = (String) kakaoAccount.get("email");
        String gender = (String) kakaoAccount.get("gender");
        gender = "미정";

        boolean type = userService.signup(email, name, realAge, gender);

        HashMap<String, Object> map = new HashMap();
        map.put("signup", type);
        map.put("name", name);
        map.put("age", realAge);
        map.put("email", email);
        map.put("gender", gender);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
