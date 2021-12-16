package com.example.pathvariabledemo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class IndexController {
    //curl localhost:8080/actionA/cities/Yangon
    @GetMapping("/actionA/cities/{city}")
    public String actionA(@PathVariable("city") String city){
        return String.format("Retrieved name = [%s]",city);

    }

    //curl localhost:8080/actionB/countries/Myanmar
    @GetMapping("/actionB/countries/{country}")
    public String actionB(@PathVariable("country") String country,@PathVariable(value = "city",required = false)String city){
        return String.format("Retrieved country = [%s] and city = [%s]",country,city);

    }

    //curl localhost:8080/actionE/countries/Myanmar
    @GetMapping("/actionE/countries/{country}")
    public String actionE(@PathVariable("country") String country, @PathVariable Optional<String> city){
        return String.format("Retrieved name = [%s] and city = [%s]",country,city.orElse("Mandalay"));

    }


    //curl localhost:8080/actionC/countries/Myanmar/Yangon
    @GetMapping("/actionC/countries/{country}/{name}")
    public String actionC(@PathVariable Map<String,String> parameters){
        String parameterString = parameters.entrySet().stream()
                .map(entry->String.format("[%s] -> [%s]",entry.getKey(),entry.getValue()))
                .collect(Collectors.joining(","));
        return String.format("Retrieved parameters map = [%s]",parameterString);

    }

    //curl localhost:8080/actionD/cities/Yangon,Mandalay,Naypyitaw
    @GetMapping("/actionD/cities/{cities}")
    public String actionD(@PathVariable("cities") List<String> parameters){
        return String.format("Retrieved cities identifiers [%s]",String.join(",",parameters));

    }
}
