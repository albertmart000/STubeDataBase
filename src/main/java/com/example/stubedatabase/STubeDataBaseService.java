package com.example.stubedatabase;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class STubeDataBaseService {

    private UserRepository userRepository;
    private VideoRepository videoRepository;
    private RatingRepository ratingRepository;

    public STubeDataBaseService(UserRepository userRepository, VideoRepository videoRepository,
                                RatingRepository ratingRepository) {
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
        this.ratingRepository = ratingRepository;
    }

    public User createUser(User user) {
        this.userRepository.save(user);
        return user;
    }


    public User updateUser (String userId, User data) throws Exception {
        User user = searchUser (userId);
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());
        userRepository.save(user);
        return user;
    }

    public Video addVideo (String userId, Video video) {
        User user = searchUser(userId);
        video = user.addVideo(video);
        videoRepository.save(video);
        return video;
    }

    public List<Video> getUserVideos(String userId) {
        User user = searchUser(userId);
        return user.getVideoList();
    }

    public void deleteUserVideos(String userId){
        User user = searchUser(userId);
        videoRepository.deleteAllByUser(user);
    }

    public Video getVideo(String userId, String videoId){
        return videoRepository.findById(videoId).get();
    }

    public void rateVideo(String userId, String videoId, Rating rating){
        Video video = getVideo(userId, videoId);
        video.addRating(rating);
        ratingRepository.save(rating);

    }

    private User searchUser(String userId) {
        return userRepository.findById(userId).get();
    }


}
