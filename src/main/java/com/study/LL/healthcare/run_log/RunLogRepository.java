package com.study.LL.healthcare.run_log;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class RunLogRepository {

  /** SQL 全件取得（期限日昇順） */
  private static final String SQL_SELECT_ALL =
    "SELECT * FROM run_log WHERE user_id = :userId order by limitday";

  /** SQL 1件追加 */
  private static final String SQL_INSERT_ONE =
    "INSERT INTO run_log(id, user_id, title, limitday, complete, distance, timelog) VALUES((SELECT MAX(id) + 1 FROM run_log), :userId, :title, :limitday, false, :distance, :timelog)";

  /** SQL 1件削除 */
  private static final String SQL_DELETE_ONE = "DELETE FROM run_log WHERE id = :id";

  /** SQL 1件更新 */
  private static final String SQL_UPDATE_ONE = "UPDATE run_log SET complete = true WHERE id = :id";

  /** 予想更新件数(ハードコーディング防止用) */
  private static final int EXPECTED_UPDATE_COUNT = 1;

  @Autowired
  private NamedParameterJdbcTemplate jdbc;


  public List<Map<String, Object>> findAll(String userId) {
    // クエリのパラメータを設定するマップ
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("userId", userId);


    System.out.println("[findAll]:" + params);
    List<Map<String, Object>> resultList = jdbc.queryForList(SQL_SELECT_ALL, params);
    System.out.println("[findAll]:"+resultList);
    return resultList;
  }

  public int save(RunLogData runLogData) throws SQLException {
    // クエリのパラメータを設定するマップ
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("userId", runLogData.getUserId());
    params.put("title", runLogData.getTitle());
    params.put("limitday", runLogData.getLimitday());
    params.put("distance", runLogData.getDistance());
    params.put("timelog", runLogData.getTimelog());

    // クエリを実行し、更新された行数を取得
    int updateRow = jdbc.update(SQL_INSERT_ONE, params);
    if (updateRow != EXPECTED_UPDATE_COUNT) {
      throw new SQLException("更新に失敗しました 件数:" + updateRow);
    }
    return updateRow;
  }

  public int delete(int id) throws SQLException {
    // クエリのパラメータを設定するマップ
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("id", id);

    // クエリを実行し、更新された行数を取得
    int updateRow = jdbc.update(SQL_DELETE_ONE, params);
    if (updateRow != EXPECTED_UPDATE_COUNT) {
      // 更新件数が異常な場合は例外をスロー
      throw new SQLException("更新に失敗しました 件数:" + updateRow);
    }
    return updateRow;
  }

  public int update(int id) throws SQLException {
    // クエリのパラメータを設定するマップ
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("id", id);

    // クエリを実行し、更新された行数を取得
    int updateRow = jdbc.update(SQL_UPDATE_ONE, params);
    if (updateRow != EXPECTED_UPDATE_COUNT) {
      // 更新件数が異常な場合は例外をスロー
      throw new SQLException("更新に失敗しました 件数:" + updateRow);
    }
    return updateRow;
  }

}
