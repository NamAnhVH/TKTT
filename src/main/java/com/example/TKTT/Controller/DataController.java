package com.example.TKTT.Controller;

import com.example.TKTT.Service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping
    public String mainPage(){
        return "mainPage";
    }

    @GetMapping("/searchData")
    public String searchData(Model model, @RequestParam("query") String query){
        model.addAttribute("datas", dataService.searchData(query));
        return "mainPage";
    }

}
