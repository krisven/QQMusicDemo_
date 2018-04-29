package com.kk.ssm.controller;

import com.kk.ssm.entity.ListenHistory;
import com.kk.ssm.service.ICollectSongsService;
import com.kk.ssm.service.IListenHistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/listen")
public class ListenHistoryController {

    @Resource
    private IListenHistoryService iListenHistoryService;

    @RequestMapping(value = "/history")
    public @ResponseBody List<ListenHistory> getListenHistory(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("usernumber");
        if(obj == null)
            return null;
        else
        {
            int usernumber = (int) obj;
            List<ListenHistory> listenHistories = iListenHistoryService.getListenHistory(usernumber);
            return listenHistories;
        }
    }

    @RequestMapping(value = "/once")
    public @ResponseBody String listenOnce(@RequestParam("songId") int songId, HttpServletRequest request){
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("usernumber");
        if(obj == null)
            return "unlogin";
        else
        {
            int usernumber = (int) obj;
            ListenHistory listenHistory = iListenHistoryService.findLH(songId, usernumber);
            String str;
            if(listenHistory == null)
                str = iListenHistoryService.insertLH(songId, usernumber);
            else
                str = iListenHistoryService.updateLT(songId, usernumber);
            return str;
        }
    }
}
