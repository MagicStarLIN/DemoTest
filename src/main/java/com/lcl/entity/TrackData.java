package com.lcl.entity;


import java.io.Serializable;
import java.util.Date;

public class TrackData implements Serializable {

    private static final long serialVersionUID = 3468215512309613713L;

    private long userId;

    private int identity;

    private String targetId;

    private int behaviorType;

    private int date8;

    private Date triggerTime;

    private String behaviorData;

    public TrackData() {
    }

    public TrackData(long userId, int identity, int behaviorType, int date8, Date triggerTime, String behaviorData) {
        this.userId = userId;
        this.identity = identity;
        this.behaviorType = behaviorType;
        this.date8 = date8;
        this.triggerTime = triggerTime;
        this.behaviorData = behaviorData;
    }

    public TrackData(long userId, int identity, String targetId, int behaviorType, int date8, Date triggerTime, String behaviorData) {
        this.userId = userId;
        this.identity = identity;
        this.targetId = targetId;
        this.behaviorType = behaviorType;
        this.date8 = date8;
        this.triggerTime = triggerTime;
        this.behaviorData = behaviorData;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public int getBehaviorType() {
        return behaviorType;
    }

    public void setBehaviorType(int behaviorType) {
        this.behaviorType = behaviorType;
    }

    public int getDate8() {
        return date8;
    }

    public void setDate8(int date8) {
        this.date8 = date8;
    }

    public Date getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getBehaviorData() {
        return behaviorData;
    }

    public void setBehaviorData(String behaviorData) {
        this.behaviorData = behaviorData;
    }

//    @Override
//    public String toString() {
//        return "TrackData{" +
//                "userId=" + userId +
//                ", identity=" + identity +
//                ", targetId='" + targetId + '\'' +
//                ", behaviorType=" + behaviorType +
//                ", behaviorDes=" + BehaviorTrackEnum.getByType(behaviorType).getDescription() +
//                ", triggerTime=" + triggerTime +
//                ", behaviorData='" + behaviorData + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return "TrackData{" +
                "userId=" + userId +
                ", identity=" + identity +
                ", targetId='" + targetId + '\'' +
                ", behaviorType=" + behaviorType +
                ", date8=" + date8 +
                ", triggerTime=" + triggerTime +
                ", behaviorData='" + behaviorData + '\'' +
                '}';
    }

}
