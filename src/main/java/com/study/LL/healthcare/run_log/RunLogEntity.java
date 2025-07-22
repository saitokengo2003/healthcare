package com.study.LL.healthcare.run_log;

import java.util.ArrayList;
import java.util.List;


public class RunLogEntity {

  /** タスク情報のリスト */
  private List<RunLogData> runLogList = new ArrayList<RunLogData>();

  /** エラーメッセージ(表示用) */
  private String errorMessage;

  public List<RunLogData> getRunLogList() {
    return runLogList;
  }

  public void setrunLogList(List<RunLogData> runLogList) {
    this.runLogList = runLogList;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
