package com.study.LL.healthcare.run_log;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RunLogService {

  @Autowired
  private RunLogRepository runLogRepository;


  public RunLogEntity selectAll(String userId) {
    List<Map<String, Object>> resultSet;
    System.out.println("[selectAll1]:"+ userId);
    resultSet = runLogRepository.findAll(userId);
    System.out.println("[selectAll2]:"+resultSet);
    RunLogEntity runLogEntity = mappingSelectResult(resultSet);
    System.out.println("[selectAll3]:" + runLogEntity);
    return runLogEntity;
  }


  public boolean insert(String userId, String title, String limitday, float distance, float timelog) {
    RunLogData runLogData = refillToData(userId, title, limitday, distance, timelog);

    try {
      runLogRepository.save(runLogData);
    } catch (SQLException e) {
      return false;
    }
    return true;
  }

  public boolean delete(String id) {
    int i = Integer.parseInt(id);
    try {
      runLogRepository.delete(i);
    } catch (SQLException e) {
      return false;
    }
    return true;
  }


  public boolean complete(String id) {
    int i = Integer.parseInt(id);
    try {
      runLogRepository.update(i);
    } catch (SQLException e) {
      return false;
    }
    return true;
  }
  boolean validate(String comment, String limitday) {
    // nullチェック、必須チェック、50文字超過チェック
    if (comment == null || comment.isBlank() || comment.length() > 50) {
      return false;
    }

    // nullチェック、必須チェック
    if (limitday == null || limitday.isBlank()) {
      return false;
    }

    // 日付形式チェック(SimpleDateFomatの変換可否を利用)
    try {
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      format.parse(limitday);
    } catch (ParseException e) {
      return false;
    }

    return true;
  }

  boolean validate(String id) {
    // 数値チェック(1桁～Intの最大値)
    Pattern p = Pattern.compile("^\\d{1,9}$");
    if (id.isBlank()) {
      return false;
    } else if (!p.matcher(id).find()) {
      return false;
    }
    return true;
  }


  private RunLogData refillToData(String userId, String title, String limitDay, float distance, float timelog) {
    RunLogData runLogData = new RunLogData();
    runLogData.setUserId(userId);
    runLogData.setTitle(title);
    runLogData.setComplete(false);
    runLogData.setDistance(distance);
    runLogData.setTimelog(timelog);

    // String型からDate型へ変換する
    try {
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      runLogData.setLimitday(format.parse(limitDay));
    } catch (ParseException e) {
      // 何もしない（入力チェック済みのため、変換エラーは起こり得ない）
    }

    return runLogData;
  }

  private RunLogEntity mappingSelectResult(List<Map<String, Object>> resultList) {
    RunLogEntity entity = new RunLogEntity();

    System.out.println("[mappingSelectResult]:"+ resultList);

    for (Map<String, Object> map : resultList) {
      RunLogData data = new RunLogData();
      data.setId((Integer) map.get("id"));
      data.setUserId((String) map.get("user_id"));
      data.setTitle((String) map.get("title"));
      data.setLimitday((Date) map.get("limitday"));
      data.setComplete((boolean) map.get("complete"));
      data.setDistance(((Float) map.get("distance")));
      data.setTimelog(((Float) map.get("timelog")));

      entity.getRunLogList().add(data);
      System.out.println("[mappingSelectResult2]:"+ map.get("user_id"));
      System.out.println("[mappingSelectResult2]:"+ map.get("title"));
      System.out.println("[mappingSelectResult2]:"+ map.get("limitday"));
      System.out.println("[mappingSelectResult2]:"+ map.get("complete"));
      System.out.println("[mappingSelectResult2]:"+ map.get("distance"));
      System.out.println("[mappingSelectResult2]:"+ map.get("timelog"));
      
      System.out.println("[mappingSelectResult3]:"+ data);
    }
    return entity;
  }
}

