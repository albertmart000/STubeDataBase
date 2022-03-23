package com.example.stubedatabase;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "ratings")
public class Rating {

    @Id
    private String id= UUID.randomUUID().toString();

    private int rate;
    private String comment;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="video_id")
    private Video video;

    public Rating() {

    }

    public Rating(int rate, String comment) throws Exception {
        checkRate(rate);
        checkComment(comment);
        this.rate = rate;
        this.comment = comment;
    }

    private void checkComment(String comment) throws Exception {
        if(comment.equals("")) throw new Exception();
    }

    private void checkRate(int rate) throws Exception {
        if (!((rate > 0) && (rate <= 5))) {
            throw new Exception ();
        }
    }

    public String getId() { return id; }

    public int getRate() { return rate; }

    public String getComment() { return comment; }

    public void setRate(int rate) throws Exception {
        checkRate(rate);
        this.rate = rate;
    }

    public void setComment(String comment) throws Exception {
        checkComment(comment);
        this.comment = comment;
    }

    public void setVideo(Video video) { this.video = video; }

}
