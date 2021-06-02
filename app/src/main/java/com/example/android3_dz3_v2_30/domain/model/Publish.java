package com.example.android3_dz3_v2_30.domain.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressLint("ParcelCreator")
public class Publish implements Serializable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("user")
    private Integer userID;

    @SerializedName("group")
    private Integer groupID;

    public Publish(String title, String content, Integer userID, Integer groupID) {
        this.title = title;
        this.content = content;
        this.userID = userID;
        this.groupID = groupID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    @Override
    public String toString() {
        return "Publish{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
