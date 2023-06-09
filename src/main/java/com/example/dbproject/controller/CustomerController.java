package com.example.dbproject.controller;

import com.example.dbproject.entity.Customer;
import com.example.dbproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/login_session")
    @ResponseBody
    public Map<String, Object> getSession(HttpSession session){
        Customer customer = (Customer)session.getAttribute("loginUser");
        Map<String, Object> map = new HashMap<>();
        map.put("session", customer);
        return map;
    }

    @RequestMapping("/loginCustomer")
    public String login(@RequestParam("username")String username, @RequestParam("password") String password,
                        HttpSession session, Model model){
//        String username = customer.getUsername();
//        String password = customer.getPassword();
        String msg = "success";
        Customer customer = null;
        try{
            customer = customerService.login(username, password);
        }catch (Exception e){
            msg = e.getMessage();
            System.out.println(msg);
        }

        if(msg.equals("success")){
            customer.setPassword(null);
            customer.setSalt(null);
            customer.setAccount(null);
            session.setAttribute("loginUser", customer);
            return "redirect:/index.html";
        }else {
            model.addAttribute("msg","用户名或密码错误!");
            return "login";
        }
    }


    @RequestMapping("/register")
    public String register(@RequestParam("username")String username, @RequestParam("password") String password,
                         @RequestParam("account") Integer account, Model model){
        String msg = "success";
        try{
            customerService.register(username, password, account);
        }catch (Exception e){
            msg = e.getMessage();
            model.addAttribute("registerFailure", msg);
            System.out.println(msg);
        }

        if(msg.equals("success")){
            return "redirect:/login.html";
        }else {
            return "register";
        }
    }

    @RequestMapping("/user/updatePwd")
    @ResponseBody
    public void updatePassword(@RequestParam("id") Integer id, @RequestParam("password")String password,
                                 Model model){
        String msg = "success";
        try {
            customerService.updatePassword(id, password);
        }catch (Exception e){
            msg = e.getMessage();
            model.addAttribute("updatePwdFailure", "密码修改失败");
        }

        System.out.println(msg);
        //增加个人信息页后，改跳转的url
//        return "index";
    }

    @RequestMapping("/user/info")
    @ResponseBody
    public void showPersonalInfo(@RequestParam("id") Integer id, Model model){
        String msg = "success";
        Customer customer = null;
        try {
            customer = customerService.showPersonalInfo(id);
            customer.setPassword(null);
            customer.setSalt(null);
            model.addAttribute("personalInfo", customer);
        }catch (Exception e){
            msg = e.getMessage();
        }

        System.out.println(msg);
//        System.out.println(model.getAttribute("personalInfo"));
        //增加个人信息页后，改跳转的url
//        return "index";
    }

    @RequestMapping("/user/updateAcc")
    @ResponseBody
    public Map<String, Object> updateAccount(@RequestParam("id") Integer id, @RequestParam("delt") Integer delt){
        String msg = "success";
        try{
            customerService.updateAccount(id, delt);
        }catch(Exception e){
            msg = e.getMessage();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        System.out.println(msg);
        return map;
    }

    @RequestMapping ("/user/{username}")
   // @ResponseBody
    public String redirectToUserPage(@PathVariable String username) {
        return "redirect:/user.html";
    }
    @RequestMapping ("/order/{username}")
    // @ResponseBody
    public String redirectToOrderePage(@PathVariable String username) {
        return "redirect:/order.html";
    }
    @RequestMapping("/user/getAccount")
    @ResponseBody
    public Map<String, Object> getAccountById(@RequestParam("id") Integer id){
        Customer data=null;
        Integer total=null;
        try{
            data = customerService.showPersonalInfo(id);
            total = customerService.showAccount(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("list", data);
        //System.out.println("cjzsb"+map);
        return map;
    }



}
