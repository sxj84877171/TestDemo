package com.lenovo.quickapp.data.local.db.bean;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import java.util.Date;

/**
 * Created by elvissun on 8/19/2016.
 */
@Entity
public class Note {


    @Id
    private Long id;

    @NotNull
    private String text;
    private String comment;
    private java.util.Date date;
    public java.util.Date getDate() {
        return this.date;
    }
    public void setDate(java.util.Date date) {
        this.date = date;
    }
    public String getComment() {
        return this.comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1028896452)
    public Note(Long id, @NotNull String text, String comment, java.util.Date date) {
        this.id = id;
        this.text = text;
        this.comment = comment;
        this.date = date;
    }
    @Generated(hash = 1272611929)
    public Note() {
    }


}
