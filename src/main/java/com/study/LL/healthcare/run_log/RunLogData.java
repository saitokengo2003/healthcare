package com.study.LL.healthcare.run_log;

import java.util.Date;


public class RunLogData {


  private int id;


  private String userId;


  private String title;


  private Date limitday;


  private boolean complete;

  private float distance;

  private float timelog;

  public float getDistance() {
    return distance;
  }

  public void setDistance(float distance) {
    this.distance = distance;
  }


  public float getTimelog() {
    return timelog;
  }

  public void setTimelog(float timelog) {
    this.timelog = timelog;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getLimitday() {
    return limitday;
  }

  public void setLimitday(Date limitday) {
    this.limitday = limitday;
  }

  public boolean isComplete() {
    return complete;
  }

  public void setComplete(boolean complete) {
    this.complete = complete;
  }
}
