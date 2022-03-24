package com.example.stubedatabase;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class STubeDataBaseAPIRestController {

    private STubeDataBaseService sTubeDataBaseService;

    public STubeDataBaseAPIRestController(STubeDataBaseService sTubeDataBaseService) {
        this.sTubeDataBaseService = sTubeDataBaseService;
    }
    @PostMapping("/users")
    public User createUser(@RequestBody User user) { return sTubeDataBaseService.createUser(user);}

    @PutMapping("/users/{userId}")
    public User updateUser(@PathVariable String userId, @RequestBody User userToUpdate) throws Exception {
        return sTubeDataBaseService.updateUser(userId, userToUpdate);
    }

    @PostMapping("/users/{userId}/videos")
    public Video addVideo(@PathVariable String userId, @RequestBody Video video) {
        return sTubeDataBaseService.addVideo(userId,video);
    }

    @GetMapping("/users/{userId}/videos")
    public List<Video> getUserVideo(@PathVariable String userId){
        return sTubeDataBaseService.getUserVideos(userId);
    }

    @DeleteMapping("/users/{userId}/videos")
    public void deleteUserVideos(@PathVariable String userId){
        sTubeDataBaseService.deleteUserVideos(userId);
    }

    @GetMapping("/users/{userId}/videos/{videoId}")
    public Video getVideo (@PathVariable String userId, @PathVariable String videoId){
        return sTubeDataBaseService.getVideo(userId, videoId);
    }

    @PostMapping("/users/{userId}/videos/{videoId}")
    public void rateVideo(@PathVariable String userId, @PathVariable String videoId,
                          @RequestBody Rating rating){
        sTubeDataBaseService.rateVideo(userId, videoId, rating);
    }


}
