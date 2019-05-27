package com.stackroute.muzix.service;

import com.stackroute.muzix.Exception.AlreadyExistException;
import com.stackroute.muzix.Exception.TrackNotFound;
import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService{

    MusicRepository musicRepository;

    @Autowired
    public MusicServiceImpl(MusicRepository musicRepository){
        this.musicRepository=musicRepository;
    }

    @Override
    public Track saveTrack(Track track) throws AlreadyExistException,NullPointerException {
        if(musicRepository.existsById(track.getTrackId())){
            throw new AlreadyExistException("Track Already Exist");
        }
        Track saveTrack=musicRepository.save(track);
        return saveTrack;
    }


    @Override
    public List<Track> getAllTrack() {
        List<Track> list=musicRepository.findAll();
            if (list.isEmpty()) {
                throw new NullPointerException("Track is Empty");
        }
        return list;
    }

//    @Override
//    public Track updateComment(Track track) {
//        return null;
//    }
//
    @Override
    public Track updateComment(Track track) throws TrackNotFound {
//        Track updateTrack= musicRepository.findById(track.getTrackId()).get();
//        updateTrack.setTrackComments(updateTrack.getTrackComments());
       //return musicRepository.save(track);
       if(musicRepository.existsById(track.getTrackId())){
         return musicRepository.save(track);
       }
       else{
           throw new TrackNotFound("Track not Found");
       }
    }

    @Override
    public void deleteTrack(int id) {
        musicRepository.deleteById(id);
        //return null;
    }
//
    @Override
    public List<Track> findByName(String trackName) {
        return musicRepository.findByName(trackName);
    }
}
