package com.samsung.demo2.controller;

import com.samsung.demo2.common.OK;
import com.samsung.demo2.common.Response;
import com.samsung.demo2.repository.models.AppInfo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @PostMapping("/getAppInfo")
    @ResponseBody
    public ResponseEntity<Response> getAppInfo(@RequestHeader("appId") String appId)
    {
        if(appId.isEmpty())
        {
            return ResponseEntity.ok(new Response(400, "AppId is required"));
        }
        else {
            List<AppInfo> apps = new ArrayList<>();
            AppInfo app = new AppInfo();
            app.AppId = appId;
            app.CompanyName = "Samsung SDS";
            app.Version = "1.0";

            apps.add(app);

            AppInfo app2 = new AppInfo();
            app2.AppId = appId;
            app2.CompanyName = "Samsung";
            app2.Version = "2.0";

            apps.add(app2);

            return ResponseEntity.ok(new OK<List<AppInfo>>(200, "Ok", apps));
        }
    }

    @GetMapping("/redirect")
    @ResponseBody
    public ResponseEntity<Response> testRedirect(HttpServletResponse response)
    {
        try {
            response.sendRedirect("/target");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(new Response(200, "Ok"));
    }

    @GetMapping("/target")
    @ResponseBody
    public String targetRedirect()
    {
        return "This is redirect action";
    }
}
