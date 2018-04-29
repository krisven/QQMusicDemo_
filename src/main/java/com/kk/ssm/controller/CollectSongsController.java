package com.kk.ssm.controller;

import com.kk.ssm.entity.CollectSongs;
import com.kk.ssm.entity.Song;
import com.kk.ssm.service.ICollectSongsService;
import com.kk.ssm.service.Impl.CollectSongsServiceImpl;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/collect")
public class CollectSongsController {

    private List<CollectSongs> collectSongs;
    private Map<Object, Object> resultMap = new HashMap<Object, Object>();

    @Resource
    private CollectSongsServiceImpl collectSongsService;

    @RequestMapping(value = "/songs")
    public @ResponseBody List<CollectSongs> getCollectSongs(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("usernumber");
        //System.out.println(obj);
        if(obj == null)
            return null;
        else
        {
            int usernumber = (int)obj;
            collectSongs = collectSongsService.getCollectSongs(usernumber);
            /*for(CollectSongs list : collectSongs){
                System.out.println(list.getSongName());
            }*/
            return collectSongs;
        }

    }

    @RequestMapping(value = "/collection")
    public @ResponseBody Map<Object, Object> collectionSong(@RequestParam("songId")int songId, HttpServletRequest request){
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("usernumber");
        if(obj == null)
        {
            resultMap.put("result","unlogin");
        }
        else {
            int usernumber = (int) obj;
            CollectSongs collectSongs = collectSongsService.findCollectSong(songId,usernumber);
            if(collectSongs == null)
            {
                String result = collectSongsService.collectionSong(songId, usernumber);
                System.out.println("result:" + result);
                if(result.equals("success")){
                    resultMap.put("result","已收藏");
                }
            }
            else{
                System.out.println("songname:"+collectSongs.getSongName());
                String result1 = collectSongsService.deleteCollectSong(songId,usernumber);
                System.out.println("result1: " + result1);
                if(result1.equals("success")){
                    resultMap.put("result","取消收藏");
                }
            }
        }
        return resultMap;
    }

}
