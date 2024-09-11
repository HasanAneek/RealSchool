package com.example.realschool.controller;

import com.example.realschool.model.Person;
import com.example.realschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session) {
        Person person = personRepository.findByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());

        if (null != person.getRealClass() && null != person.getRealClass().getName()) {
            model.addAttribute("enrolledClass", person.getRealClass().getName());
        }

        session.setAttribute("loggedInPerson", person);
//        logMessages();
        return "dashboard";
    }

    private void logMessages() {
        log.error("Error Message from Dashboard Page");
        log.warn("Warn Message from Dashboard Page");
        log.info("Info Message from Dashboard Page");
        log.debug("Debug Message from Dashboard Page");
        log.trace("Trace Message from Dashboard Page");
    }

}
