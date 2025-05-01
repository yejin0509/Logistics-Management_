package kanri.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import kanri.dao.ManagerDao;
import kanri.model.Manager;

public class ManagerJoinService {

    private ManagerDao managerDao = new ManagerDao(); // ManagerDao 객체 생성 / ManagerDaoオブジェクトを作成

    public void join(ManagerJoinRequest joinRequest) {
        Connection conn = null;

        try {
            conn = ConnectionProvider.getConnection(); // DB 연결 / データベース接続
            conn.setAutoCommit(false); // 자동 커밋 해제 / 自動コミットを解除

            // ID 중복 검사 / ID重複検査
            Manager existing = managerDao.selectById(conn, joinRequest.getManager_Id()); 
            if (existing != null) { // 이미 존재하는 ID가 있으면 예외 처리 / 既存のIDがあれば例外処理
                JdbcUtil.rollback(conn); // 롤백 / ロールバック
                throw new DuplicateIdException(); // 중복 ID 예외 발생 / 重複ID例外を発生させる
            }

            // 관리자 객체 생성 후 저장 / 管理者オブジェクトを作成し保存
            Manager newManager = new Manager(
                joinRequest.getManager_Id(), // 관리자 ID / 管理者ID
                joinRequest.getPassword() // 비밀번호 / パスワード
            );

            managerDao.insert(conn, newManager); // 관리자 정보 DB에 저장 / 管理者情報をデータベースに保存
            conn.commit(); // 커밋 / コミット

        } catch (SQLException e) {
            JdbcUtil.rollback(conn); // 예외 발생 시 롤백 / 例外が発生した場合、ロールバック
            throw new RuntimeException(e); // 예외 던지기 / 例外を投げる
        } finally {
            JdbcUtil.close(conn); // 커넥션 종료 / コネクションを閉じる
        }
    }
}
