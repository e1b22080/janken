package oit.is.z2636.kaizi.janken.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z2636.kaizi.janken.model.Entry;

@Controller
public class JankenController {

  @Autowired
  private Entry entry;

  @GetMapping("/janken")
  public String janken_nameless(Principal prin, ModelMap model) {
    String loginUser = prin.getName(); // ログインユーザ情報
    this.entry.addUser(loginUser);
    model.addAttribute("login_user", loginUser);
    model.addAttribute("entry", this.entry);
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
