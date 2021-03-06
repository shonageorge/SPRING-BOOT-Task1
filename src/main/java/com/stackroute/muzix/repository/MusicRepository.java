package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Track,Integer> {
//    @Query("update track set trackName='Hello' Where id=1 ")
//    public Track update();
    @Query(value = "select * from track where track_Name=?",nativeQuery = true)
    public List<Track> findByName(String trackName);

}
