package com.example.realschool.controller;

import com.example.realschool.model.Holiday;
import com.example.realschool.repository.HolidaysRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
public class HolidaysController {

    @Autowired
    private HolidaysRepository holidayRepository;

    //- Query Param
//    @GetMapping("/holidays")
//    public String displayHolidays(@RequestParam(required = false) boolean festival,
//                                  @RequestParam(required = false) boolean federal,
//                                  Model model) {
//        model.addAttribute("festival", festival);
//        model.addAttribute("federal", federal);
//
//        List<Holidays> holidays = HolidaysRepository.

    //Path Variable
    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable String display, Model model) {

        if (display.equals("all")) {
            model.addAttribute("festival", true);
            model.addAttribute("federal", true);
        } else if (display.equals("federal")) {
            model.addAttribute("federal", true);
        } else if (display.equals("festival")) {
            model.addAttribute("festival", true);
        }

        Iterable<Holiday> holidays = holidayRepository.findAll();
        List<Holiday> holidayList = StreamSupport.stream(holidays.spliterator(), false)
                .toList();

        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidayList.stream().filter(holiday -> holiday.getType().equals(type)).
                            collect(Collectors.toList())));
        }
        return "holidays";
    }
}
