package com.kk.ssm.controller;

import com.kk.ssm.entity.UserComment;
import com.kk.ssm.service.Impl.SongServiceImpl;
import com.kk.ssm.service.Impl.UserCommentServiceImpl;
import com.kk.ssm.service.Impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/comment")
public class UserCommentController {

    private Map<Object, Object> resultMap = new HashMap<Object, Object>();

    @Resource
    private UserCommentServiceImpl userCommentService;

    @Resource
    private SongServiceImpl songService;

    @Resource
    private UserServiceImpl userService;

    @RequestMapping(value = "/add")
    public @ResponseBody Map<Object, Object> addComment(@RequestParam("songId") int songId, @RequestParam("commentTime") String commentTime,
                                                        @RequestParam("commentInf") String commentInf, HttpServletRequest request){
        HttpSession session = request.getSession();
        int userNumber = (int) session.getAttribute("usernumber");
        String str = userCommentService.addComment(songId, commentTime, commentInf, userNumber);
        resultMap.put("songId",songId);
        resultMap.put("commentTime", commentTime);
        resultMap.put("commentInf", commentInf);
        resultMap.put("userNumber", userNumber);
        resultMap.put("result",str);
        return resultMap;
    }

    @RequestMapping(value = "/user")
    public @ResponseBody Map<Object, Object> findByUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        int userNumber = (int) session.getAttribute("usernumber");
        List<Object> userComments = new ArrayList<Object>();
        List<UserComment> list = userCommentService.findByUser(userNumber);
        for(int i = 0; i < list.size(); i++)
        {
            UserComment userComment = list.get(i);
            Map<Object, Object> comment = new HashMap<Object, Object>();
            comment.put("commentId", userComment.getCommentId());
            comment.put("songId", userComment.getSongId());
            comment.put("songName", songService.findSongBySongId(userComment.getSongId()).getSongName());
            comment.put("commentTime", userComment.getCommentTime());
            comment.put("commentInf", userComment.getCommentInf());
            comment.put("userNumber", userComment.getUserNumber());
            userComments.add(comment);
        }
        resultMap.put("usercomment", userComments);
        return  resultMap;
    }

    @RequestMapping(value = "/song")
    public @ResponseBody Map<Object, Object> findBySong(@RequestParam("songId") int songId) {
        System.out.println("songId:"+songId);
        List<Object> songComments = new ArrayList<Object>();
        List<UserComment> lists = userCommentService.findBySong(songId);
        for(int i = 0; i < lists.size(); i++)
        {
            UserComment songComment = lists.get(i);
            Map<Object, Object> comment1 = new HashMap<Object, Object>();
            comment1.put("commentId", songComment.getCommentId());
            comment1.put("songId", songComment.getSongId());
            comment1.put("commentTime", songComment.getCommentTime());
            comment1.put("commentInf", songComment.getCommentInf());
            comment1.put("userNumber", songComment.getUserNumber());
            comment1.put("userName", userService.findByUserNumber(songComment.getUserNumber()).getUserName());
            songComments.add(comment1);
        }
        resultMap.put("usercomment", songComments);
        return  resultMap;
    }

    @RequestMapping(value = "/delete")
    public @ResponseBody Map<Object, Object> delete(@RequestParam("commentId") int commentId){
        String str = userCommentService.deleteComment(commentId);
        resultMap.put("result", str);
        return  resultMap;
    }

}
