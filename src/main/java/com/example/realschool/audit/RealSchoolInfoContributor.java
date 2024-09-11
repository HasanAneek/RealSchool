package com.example.realschool.audit;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RealSchoolInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> realMap = new HashMap<String, String>();
        realMap.put("App Name", "RealSchool");
        realMap.put("App Description", "Real School Web Application for Students and Admin");
        realMap.put("App Version", "1.0.0");
        realMap.put("Contact Email", "info@realschool.com");
        realMap.put("Contact Mobile", "+88010000000");
        builder.withDetail("realschool-info", realMap);
    }
}
