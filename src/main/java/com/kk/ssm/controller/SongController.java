package com.kk.ssm.controller;

import com.kk.ssm.entity.Song;
import com.kk.ssm.service.Impl.CollectSongsServiceImpl;
import com.kk.ssm.service.Impl.SongServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/getsongs")
public class SongController {

    private Map<Object, Object> resultMap = new HashMap<Object, Object>();

    @Resource
    private SongServiceImpl songService;

    @Resource
    private CollectSongsServiceImpl collectSongsService;

    @RequestMapping(value = "/getall")
    public @ResponseBody Map<Object,Object> getAll(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("usernumber");
        List<Object> songs=new ArrayList<Object>();
        List<Song> songList=songService.getAllSong();
        for(int i=0;i<songList.size();i++){
            Song _song=songList.get(i);
            Map<Object,Object> song=new HashMap<Object,Object>();
            song.put("songId", _song.getSongId());
            song.put("songName", _song.getSongName());
            song.put("singer", _song.getSinger());
            song.put("album", _song.getAlbum());
            song.put("popular", _song.getPopular());
            song.put("songUrl", _song.getSongUrl());
            if(obj==null){
                song.put("collected", "no");
            }else{
                int usernumber = (int)obj;
                if(collectSongsService.findCollectSong(_song.getSongId(),usernumber)!=null){
                    song.put("collected", "yes");
                }else{
                    song.put("collected", "no");
                    }
                }
                songs.add(song);
            }
            resultMap.put("songs", songs);
            return resultMap;
        }

   public Map<Object, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<Object, Object> resultMap) {
        this.resultMap = resultMap;
    }

    /*@RequestMapping(value = "/getall")
    public @ResponseBody Map<Object, Object> getAllSongs() throws Exception {
        List<Object> songs = new ArrayList<Object>();
        List<Song> songList = songService.getAllSong();
        for (int i = 0; i < songList.size(); i++) {
            Song _song = songList.get(i);
            Map<Object, Object> song = new HashMap<Object, Object>();
            song.put("songid", _song.getSongId());
            song.put("songname", _song.getSongName());
            song.put("singer", _song.getSinger());
            song.put("album", _song.getAlbum());
            song.put("popular", _song.getPopular());
            song.put("songurl", _song.getSongUrl());
            *//*if(username==null){
                song.put("collected", "no");
            }else{
                if(songCollectionDao.findCollected(_song.getSongId(), username)!=null){
                    song.put("collected", "yes");
                }else{
                    song.put("collected", "no");
                }
            }*//*
            songs.add(song);
        }
        resultMap.put("songs", songs);
        return resultMap;
    }*/

    /*@RequestMapping(value = "/getall")
    private String songlist(Model model){
        List<Song> songList = songService.getAllSong();
        model.addAttribute("songList", songList);
        return "/music-page.html";
    }*/

}
