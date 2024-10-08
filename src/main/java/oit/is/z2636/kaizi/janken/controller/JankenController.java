package oit.is.z2636.kaizi.janken.controller;

//import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// import oit.is.z2636.kaizi.janken.model.Janken;

@Controller
public class JankenController {

  @GetMapping("/janken")
  public String janken_nameless() {
    return "janken.html";
  }

  @PostMapping("/janken")
  public String Name(@RequestParam String name, ModelMap model) {
    model.addAttribute("name", name);
    return "janken.html";
  }

  @GetMapping("/jankengame")
  public String janken(@RequestParam String hand, ModelMap model) {
    String message = "";
    if (hand.equals("gu")) {
      message = "Draw";
    } else if (hand.equals("tyoki")) {
      message = "You lose!";
    } else if (hand.equals("pa")) {
      message = "You Win!";
    }
    model.addAttribute("hand", hand);
    model.addAttribute("result", message);
    return "janken.html";
  }
}
