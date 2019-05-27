package com.stackroute.muzix.service;

import com.stackroute.muzix.Exception.AlreadyExistException;
import com.stackroute.muzix.Exception.TrackNotFound;
import com.stackroute.muzix.domain.Track;

import java.util.List;


public interface MusicService  {
    public Track saveTrack(Track track) throws AlreadyExistException,NullPointerException;
    public List<Track> getAllTrack();
    public Track updateComment(Track track) throws TrackNotFound;
   public void deleteTrack(int id);
    public List<Track> findByName(String trackName);
}
