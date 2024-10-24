package oit.is.z2636.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z2636.kaizi.janken.model.Entry;
import oit.is.z2636.kaizi.janken.model.Match;
import oit.is.z2636.kaizi.janken.model.MatchMapper;
import oit.is.z2636.kaizi.janken.model.MatchInfo;
import oit.is.z2636.kaizi.janken.model.MatchInfoMapper;
import oit.is.z2636.kaizi.janken.model.User;
import oit.is.z2636.kaizi.janken.model.UserMapper;

@Controller
public class JankenController {

  @Autowired
  private Entry entry;

  @Autowired
  UserMapper UserMapper;

  @Autowired
  MatchMapper MatchMapper;

  @Autowired
  MatchInfoMapper MatchInfoMapper;

  @GetMapping("/janken")
  public String janken_nameless(Principal prin, ModelMap model) {
    String loginUser = prin.getName(); // ログインユーザ情報
    ArrayList<User> user = UserMapper.selectAllUser();
    ArrayList<Match> match = MatchMapper.selectAllResult();
    ArrayList<MatchInfo> MatchInfo = MatchInfoMapper.selectActive();
    this.entry.addUser(loginUser);
    model.addAttribute("login_user", loginUser);
    model.addAttribute("entry", this.entry);
    model.addAttribute("user", user);
    model.addAttribute("match", match);
    model.addAttribute("matchInfo", MatchInfo);
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

  @GetMapping("/match")
  public String match(@RequestParam Integer id, Principal prin, ModelMap model) {
    String loginUser = prin.getName(); // ログインユーザ情報
    String Name = UserMapper.selectById(id);
    model.addAttribute("ID", id);
    model.addAttribute("login_user", loginUser);
    model.addAttribute("name", Name);
    return "match.html";
  }

  @GetMapping("/fight")
  public String fight(@RequestParam Integer id, @RequestParam String hand, Principal prin, ModelMap model) {
    String user1Name = prin.getName(); // ログインユーザ情報
    int user1Id = UserMapper.selectByName(user1Name);
    String user2Name = UserMapper.selectById(id);
    String data = MatchInfoMapper.selectActiveMyId(id, user1Id);
    if (data == null) {
      MatchInfo ui = new MatchInfo();
      ui.setUser1(user1Id);
      ui.setUser2(id);
      ui.setUser1Hand(hand);
      try {
        MatchInfoMapper.insertmatchInfo(ui);
      } catch (RuntimeException e) {// 既に身長が登録されているユーザでさらに登録しようとすると実行時例外が発生するので，コンソールに出力してinsertをSkipする
        System.out.println("Exception:" + e.getMessage());
      }
    } else {
      Match result = new Match();
      result.setUser1(id);
      result.setUser2(user1Id);
      result.setUser1Hand(data);
      result.setUser2Hand(hand);
      try {
        MatchMapper.insertmatches(result);
      } catch (RuntimeException e) {// 既に身長が登録されているユーザでさらに登録しようとすると実行時例外が発生するので，コンソールに出力してinsertをSkipする
        System.out.println("Exception:" + e.getMessage());
      }
    }

    model.addAttribute("login_user", user1Name);
    model.addAttribute("name", user2Name);
    model.addAttribute("id", id);
    model.addAttribute("hand", hand);
    return "wait.html";
  }
}
