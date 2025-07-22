-- 開発用にデータ削除を追加 : リリース時は消す
DROP TABLE IF EXISTS task_t;
DROP TABLE IF EXISTS user_m;
DROP TABLE IF EXISTS run_log;

/* ユーザマスタ */
CREATE TABLE IF NOT EXISTS
  user_m (
    user_id VARCHAR(50) PRIMARY KEY,
    PASSWORD VARCHAR(100),
    user_name VARCHAR(50),
    ROLE VARCHAR(50),
    enabled BOOLEAN
  );

/* タスクテーブル */
CREATE TABLE IF NOT EXISTS
  task_t (
    id INT PRIMARY KEY,
    user_id VARCHAR(50),
    title VARCHAR(50),
    limitday DATE,
    complete BOOLEAN
  );

CREATE TABLE IF NOT EXISTS
  run_log (
    id INT PRIMARY KEY,
    user_id VARCHAR(50),
    title VARCHAR(50),
    limitday DATE,
    complete BOOLEAN,
    distance REAL,
    timelog REAL
  );
