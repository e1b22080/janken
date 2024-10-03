package oit.is.z2636.kaizi.janken.controller;

//import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import oit.is.z2636.kaizi.janken.model.Janken;

@Controller
@RequestMapping("/janken")
public class JankenController {
  @GetMapping
  public String janken() {
    return "janken.html";
  }

  @PostMapping("name")
  public String Name(@RequestParam String name, ModelMap model) {
    model.addAttribute("name", name);
    return "janken.html";
  }

}
