package com.example.stubedatabase;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "users")
public class User {

    @Id
    private String id = UUID.randomUUID().toString();

    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Video> videoList = new ArrayList<>();

    public User() {
    }

    public User(String name, String email, String password) throws Exception {
        checkName(name);
        checkEmail(email);
        checkPassword(password);

        this.name = name;
        this.email = email;
        this.password = password;
    }

    private void checkPassword(String password) throws Exception {
        if (!password.trim().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,}$"))
            throw new Exception("La contrassenya ha de tenir mínim 7 caràcters, mínim un número i mínim una lletra");
    }

    private void checkEmail(String email) throws Exception {
        if (!email.trim().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"))
            throw new Exception("El correu electrònic té caràcters no acceptats");
    }

    private void checkName(String name) throws Exception {
        if (!name.trim().matches("^[A-Za-z0-9]{5,20}$"))
            throw new Exception("El nom d'usuari ha de tenir entre 5 i 20 caràcters i ha d'estar format per caràcters alfanumèrics");
    }

    public String getId() { return id; }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Video> getVideoList() { return videoList; }

    public void setName(String name) throws Exception {
        checkName(name);
        this.name = name;
    }

    public void setEmail(String email) throws Exception {
        checkEmail(email);
        this.email = email;
    }

    public void setPassword(String password) throws Exception {
        checkPassword(password);
        this.password = password;
    }

    public Video addVideo(Video video) {
        video.setUser(this);
        this.videoList.add(video);
        return video;
    }

    public void removeAllVideos() {
        videoList = new ArrayList<>();
    }

    public void removeVideo(String videoId) {
        for (int i = 0; i < videoList.size(); i++) {
            if (videoList.get(i).getId().equals(videoId)) {
                videoList.remove(i);
                break;
            }
        }
    }

    public Video getVideo(String videoId) throws Exception {

        for (Video video : videoList) {
            if (video.getId().equals(videoId)) {
                return video;
            }
        }
        throw new Exception("No s'ha trobat");
    }

    public Video findVideo(String videoId) throws Exception {
        for (Video video: new ArrayList<>(videoList)) {
            if (video.getId().equals(videoId)) {
                return video;
            }
        }
        throw new Exception("No s'ha trobat");

    }

    public void updateVideo(Video videoToUpdate, String videoId) throws Exception {
        for (Video video : new ArrayList<>(videoList)) {
            if (video.getId().equals(videoId)) {
                video.setTitle(videoToUpdate.getTitle());
                video.setUrl(videoToUpdate.getUrl());
                video.setDescription(videoToUpdate.getDescription());
                return;
            }
        }
        throw new Exception("No s'ha trobat");
    }

}
