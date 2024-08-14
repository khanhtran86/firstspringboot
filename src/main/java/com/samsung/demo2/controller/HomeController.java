package com.samsung.demo2.controller;

import com.samsung.demo2.repository.models.Student;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class HomeController {
    @RequestMapping(value = {"/", "/hello"}, method = RequestMethod.GET)
    @ResponseBody
    public String HelloWorld(@RequestParam(value = "name") String name,
                             @RequestParam(value = "dob", required = false, defaultValue = "0") int dob)
    {
        return "HelloJava. I am "+ name +" i am was born in "+ dob;
    }

    @RequestMapping(value = {"/hello/{name}", "/hello/{name}/{dob}"}, method = RequestMethod.GET)
    @ResponseBody
    public String SayHello(@PathVariable(value = "name") String name,
                           @PathVariable(value = "dob", required = false) Integer dob,
                           @RequestHeader("user-agent") String browserName)
    {
        return "HelloJava. I am "+ name +" was born in "+ dob + "\nRequest from: "+ browserName;
    }

    @PostMapping( value = "/postdata",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseBody
    public String postData(@RequestBody MultiValueMap<String, String> student)
    {
        //return "FulName: "+ student.firstName + " " + student.lastName + "\nDate of birth: "+ student.DoB;
        return "Hello "+ student.get("firstName") + " "+ student.get("lastName");
    }

    @PostMapping("/student")
    @ResponseBody
    public String postStudent(@RequestBody Student student, @RequestHeader String clientId)
    {
        if(clientId!=null)
        {
            return "Hello "+ student.firstName + " "+ student.lastName +"\n"+ clientId;
        }
        return "Unknown clientId";
    }
}
