package com.example.heroku.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("")
public class WelcomeApi
{
    @GetMapping("/")
    public Map<String, Object> welcome()
    {
        Map<String, Object> ret = new HashMap<>();
        ret.put("message", "Hello World");
        return ret;
    }

}
