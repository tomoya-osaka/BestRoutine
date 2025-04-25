-- ユーザーマスタ
CREATE TABLE m_user (
    user_id INT PRIMARY KEY,
    username VARCHAR(50),
    email VARCHAR(100),
    password VARCHAR(100)
);

INSERT INTO m_user (user_id, username, email, password) VALUES
(1, '仮ユーザー', 'test@example.com', 'password123');

-- ルーティンマスタ
CREATE TABLE m_routine (
    routine_id INT PRIMARY KEY,
    routine_name VARCHAR(50)
);

INSERT INTO m_routine (routine_id, routine_name) VALUES
(1, 'モーニングルーティン'),
(2, 'ナイトルーティン');

-- ルーティン項目テーブル
CREATE TABLE t_routine_item (
    item_id INT PRIMARY KEY,
    user_id INT,
    routine_id INT,
    item_text VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES m_user(user_id),
    FOREIGN KEY (routine_id) REFERENCES m_routine(routine_id)
);

-- チェック達成状況
CREATE TABLE t_routine_achievement (
    achievement_id INT PRIMARY KEY,
    user_id INT,
    submit_date DATE,
    is_all_checked BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES m_user(user_id)
);