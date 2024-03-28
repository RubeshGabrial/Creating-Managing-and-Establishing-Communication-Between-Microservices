package com.bej.muzixservice.service;

import com.bej.muzixservice.domain.Track;
import com.bej.muzixservice.domain.User;
import com.bej.muzixservice.exception.TrackAlreadyExistsException;
import com.bej.muzixservice.exception.TrackNotFoundException;
import com.bej.muzixservice.exception.UserAlreadyExistsException;
import com.bej.muzixservice.exception.UserNotFoundException;

import com.bej.muzixservice.repository.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements ITrackService{

    // Autowire the UserTrackRepository using constructor autowiring
    private final UserTrackRepository userTrackRepository;
    @Autowired
    public TrackServiceImpl(UserTrackRepository userTrackRepository) {
        this.userTrackRepository = userTrackRepository;
    }
    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        // Register a new user
        Optional<User>optionalUser=userTrackRepository.findById(user.getUserId());
        if(optionalUser.isPresent()){
            throw new UserAlreadyExistsException("User Already Exists");
        }
        else {
            return userTrackRepository.save(user);
        }
    }

    @Override
    public User saveUserTrackToWishList(Track track, String userId) throws TrackAlreadyExistsException, UserNotFoundException {
        // Save the tracks to the play list of user.
        Optional<User>optionalUser=userTrackRepository.findById(userId);
        if (optionalUser.isPresent()){
            User fetchedUser=optionalUser.get();
            List<Track>trackList=fetchedUser.getTrackList();
            for(Track track1:trackList){
                if(track1.getTrackId().equals(track.getTrackId())){
                    throw new TrackAlreadyExistsException("Track already Exist on the ");
                }
            }
            trackList.add(track);
            fetchedUser.setTrackList(trackList);
            return userTrackRepository.save(fetchedUser);
        }
        else {
            throw new UserNotFoundException("user not found");
        }
    }

    @Override
    public List<Track> getAllUserTracksFromWishList(String userId) throws Exception {
        // Get all the tracks for a specific user
        Optional<User>optionalUser=userTrackRepository.findById(userId);
        if(optionalUser.isPresent()){
            Optional<User> user1=userTrackRepository.findById(userId);
            User user=optionalUser.get();
            return user.getTrackList();
        }else{
            throw new Exception();
        }
    }

    @Override
    public User deleteTrack(String userId, String trackId) throws TrackNotFoundException, UserNotFoundException {
        // delete the user details specified
        Optional<User> optionalUser = userTrackRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Track> trackList = user.getTrackList();
            boolean trackFound = false;
            for (Track track : trackList) {
                if (track.getTrackId().equals(trackId)) {
                    trackFound = true;
                    trackList.remove(track);
                    break;
                }
            }
            if (trackFound) {
                user.setTrackList(trackList);
                return userTrackRepository.save(user);
            } else {
                throw new TrackNotFoundException("Track not found in the user's wish list");
            }
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
    @Override
    public User updateUserTrackWishListWithGivenTrack(String userId, Track track) throws UserNotFoundException, TrackNotFoundException, TrackAlreadyExistsException {
        // Update the specific details of User
        Optional<User> optionalUser = userTrackRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Track> trackList = user.getTrackList();
            boolean trackFound = false;
            for (Track existingTrack : trackList) {
                if (existingTrack.getTrackId().equals(track.getTrackId())) {
                    trackFound = true;
                    existingTrack.setTrackName(track.getTrackName());
                    existingTrack.setTrackComments(track.getTrackComments());
                    existingTrack.setRating(track.getRating());
                    existingTrack.setArtist(track.getArtist());
                    break;
                }
            }

            if (trackFound) {
                user.setTrackList(trackList);
                return userTrackRepository.save(user);
            } else {
                throw new TrackNotFoundException("Track not found in the user's wish list");
            }
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}
