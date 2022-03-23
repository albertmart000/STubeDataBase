package com.example.stubedatabase;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "videos")
public class Video {

    @Id
    private String id = UUID.randomUUID().toString();

    private String title;
    private String url;
    private String description;

    @OneToMany(mappedBy = "video")
    private List<Rating> ratingList = new ArrayList<>();
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    public Video() {

    }

    public Video(User user, String title, String url, String description) throws Exception {
        checkTitle(title);
        checkUrl(url);
        checkDescription(description);
        this.title = title;
        this.url = url;
        this.description = description;
        this.user = user;
    }

    private void checkDescription(String description) throws Exception {
        if (description.equals("")) throw new Exception();
    }

    private void checkUrl(String url) throws Exception {
        if (!(url.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"))) {
            throw new Exception();
        }
    }

//        {
//         if (url.trim().equals("")) throw new Exception("La url no pot estar buida");
//         if (!url.trim().contains("www.")) throw new Exception("La url no és vàlida, ha de contenir www.");
//        }


    private void checkTitle(String title) throws Exception {
        if (!(title.matches("^.{10,50}$")))
            throw new Exception();
    }

//        {
//        if (title.trim().equals("")) throw new Exception("El títol no pot estar en blanc");
//        if (title.trim().length() < 10) throw new Exception("El títol ha de tenir mínim 10 caràcters");
//        }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setTitle(String title) throws Exception {
        checkTitle(title);
        this.title = title;
    }

    public void setUrl(String url) throws Exception {
        checkUrl(url);
        this.url = url;
    }

    public void setDescription(String description) throws Exception {
        checkDescription(description);
        this.description = description;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addRating(Rating rating) {
        rating.setVideo(this);
        this.ratingList.add(rating);
    }

    public void removeAllRatings() {
        ratingList = new ArrayList<>();
    }

    public void removeRating(String ratingId) {
        for (int i = 0; i < ratingList.size(); i++) {
            if (ratingList.get(i).getId().equals(ratingId)) {
                ratingList.remove(i);
                break;
            }
        }
    }

    public Rating findRating(String ratingId) throws Exception {
        for (Rating rating : new ArrayList<>(ratingList)) {
            if (rating.getId().equals(ratingId)) {
                return rating;
            }
        }
        throw new Exception("No s'ha trobat");

    }

    public Rating getRating(String ratingId) throws Exception {

        for (Rating rating : ratingList) {
            if (rating.getId().equals(ratingId)) {
                return rating;
            }
        }
        throw new Exception("No s'ha trobat");
    }

}

