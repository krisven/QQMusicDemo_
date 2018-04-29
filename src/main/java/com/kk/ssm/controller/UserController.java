package com.kk.ssm.controller;

import com.kk.ssm.entity.User;
import com.kk.ssm.service.Impl.UserServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/user")
public class UserController {

    @Resource
    private UserServiceImpl userService;

    private Map<Object,Object> resultMap = new HashMap<Object, Object>();
    private Map<Integer, String> userSessionMap = null;

    /*@RequestMapping(value="/test")
    public ModelAndView testCon(HttpServletRequest request, Model model){
        System.out.println("hello");
        System.out.println(request.getParameter("userNumber"));
        User u = userService.getUserByNumber(147258001);
        System.out.println(u.getUserName());
        ModelAndView mod = new ModelAndView();
        mod.addObject("user",u);
        mod.setViewName("/WEB-INF/jsp/show.jsp");
        return mod;
    }

    @RequestMapping("submit.do")
    public String testBean(User user, HttpServletRequest request){
        System.out.println("========+" + user.getUserName() + "..." + user.getPassword());
        return "success";
    }*/

    @RequestMapping(value="/login")
    public @ResponseBody Map<Object, Object> login(@RequestParam("usernumber") int usernumber, @RequestParam("password") String password, HttpServletRequest request){
        User user1 = userService.login(usernumber, password);
        if(user1 == null)
        {
            resultMap.put("login","用户名或密码错误");
            return resultMap;
        }
        else
        {
            if(user1.getPassword().equals(password))
            {
                /*HttpSession session = request.getSession();
                session.setAttribute("usernumber", user1.getUserNumber());
                session.setAttribute("username", user1.getUserName());
                session.setAttribute("password", user1.getPassword());
                resultMap.put("login", "success");
                return resultMap;*/
                HttpSession session = request.getSession();
                session.setAttribute("usernumber",usernumber);
                ServletContext application = session.getServletContext();
                userSessionMap = (Map)application.getAttribute("userSessionMap");
                if(null == userSessionMap){
                    userSessionMap = new HashMap<Integer, String>();
                    userSessionMap.put(user1.getUserNumber(), session.getId());
                    application.setAttribute("userSessionMap", userSessionMap);
                    System.out.println("添加了Map");
                    resultMap.put("login", "success");
                    return resultMap;
                }
                String sessionId = userSessionMap.get(usernumber);
                if(null == sessionId){
                    userSessionMap.put(usernumber, session.getId());
                    resultMap.put("login", "success");
                    return resultMap;
                }
                else {
                    resultMap.put("login","用户名或密码错误");
                    return resultMap;
                }
            }
            else
            {
                resultMap.put("login","用户名或密码错误");
                return resultMap;
            }
        }
    }

    @RequestMapping(value = "/loginState")
    public @ResponseBody Map<Object, Object> loginState(@RequestParam("usernumber") int usernumber, HttpServletRequest request){
        HttpSession session = request.getSession();
        ServletContext application = session.getServletContext();
        userSessionMap = (Map)application.getAttribute("userSessionMap");
        if(userSessionMap == null){
            resultMap.put("login", "false");
        }
        else{
            if(userSessionMap.get(usernumber)==null)
                resultMap.put("login", "false");
            else {
                resultMap.put("login", "true");
                String username = userService.getUserDao().selectByUserNumber(usernumber).getUserName();
                resultMap.put("username", username);
                resultMap.put("usernumber",usernumber);
            }
        }
        return resultMap;
    }

    @RequestMapping(value = "/register")
    public @ResponseBody Map<Object, Object> register(@RequestParam("usernumber") int userNumber, @RequestParam("username") String userName, @RequestParam("password") String password){
        String str = null;
        try {
            str = userService.insert(userNumber, userName, password);
        }catch (Exception e){
            resultMap.put("result","该账号已存在");
            return resultMap;
        }
        resultMap.put("result",str);
        return resultMap;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public Map<Object, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<Object, Object> resultMap) {
        this.resultMap = resultMap;
    }
}

