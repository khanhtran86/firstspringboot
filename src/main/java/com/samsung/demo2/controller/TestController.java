package com.samsung.demo2.controller;

import com.samsung.demo2.common.OK;
import com.samsung.demo2.common.Response;
import com.samsung.demo2.repository.models.AppInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @PostMapping("/getAppInfo")
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
}
