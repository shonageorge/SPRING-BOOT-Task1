package com.stackroute.muzix.controller;


import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.stackroute.muzix.Exception.AlreadyExistException;
import com.stackroute.muzix.Exception.TrackNotFound;
import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class MusicController {
    MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @PostMapping("/track")
    public ResponseEntity<Track> saveTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try {
            musicService.saveTrack(track);
            responseEntity = new ResponseEntity("Successfully Created", HttpStatus.CREATED);
        }catch (AlreadyExistException e){
            responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }catch (Exception e){
            responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }

        return responseEntity;
    }

    @GetMapping("track")
    public  ResponseEntity<?> getAllTrack(){
        ResponseEntity responseEntity;
        try {
            musicService.getAllTrack();
            responseEntity = new ResponseEntity<List<Track>>(musicService.getAllTrack(),HttpStatus.OK);
        }catch (NullPointerException ne){
            responseEntity=new ResponseEntity(ne.getMessage(),HttpStatus.CONFLICT);
        }catch (Exception e){
            responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
        return  responseEntity;
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable int id){
        musicService.deleteTrack(id);
        return new ResponseEntity<String>("deleted sucessfyully",HttpStatus.GONE);
    }

    @GetMapping("track/{trackName}")
    public  ResponseEntity<?> findByName(@RequestBody Track track,@PathVariable String trackName){
        return new ResponseEntity<List<Track>>(musicService.findByName(trackName),HttpStatus.OK);
    }

    @PutMapping("track")
    public ResponseEntity<?> updateComment(@RequestBody Track track) {

        ResponseEntity responseEntity;
        try {
            musicService.updateComment(track);
            responseEntity=new ResponseEntity("Updated",HttpStatus.OK);
        }catch (TrackNotFound te){
            responseEntity=new ResponseEntity(te.getMessage(),HttpStatus.CONFLICT);
        }
//       updateTrack.setTrackId(id);
 //       updateTrack.setTrackComments(trackComments);
//        try {
//            musicService.saveTrack(updateTrack);
//        } catch (AlreadyExistException e) {
//            e.printStackTrace();
//        }

//       track.setTrackId(2);
//       track.setTrackComments("Latest");
        return responseEntity;
    }
}
