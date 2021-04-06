package cn.coding.boot.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = {"/", "/index"})
    public String index(){
        return "index";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("login")
    public String login(String username, String password, Model model){
        String info = null;
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            info = String.valueOf(subject.isAuthenticated());
            model.addAttribute("info", "登录信息是==>" + info);
            return "index";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            info = "未知用户异常";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            info = "账户或密码错误";
        } catch (Exception e) {
            e.printStackTrace();
            info = "未知异常";
        }
        model.addAttribute("info", "登录信息是==>" + info);
        logger.info("登录状态==>{}", info);
        return "/login";
    }
}
