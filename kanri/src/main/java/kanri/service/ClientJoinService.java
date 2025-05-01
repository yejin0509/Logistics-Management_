package kanri.service; 

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import kanri.dao.ClientDao;
import kanri.model.Client;

public class ClientJoinService {

    // ClientDao 객체 생성
    // ClientDaoオブジェクトの作成
    private ClientDao clientDao = new ClientDao();

    // 회원가입을 처리하는 메소드
    // 会員登録を処理するメソッド
    public void join(ClientJoinRequest joinRequest) {
        Connection conn = null;

        try {
            // 데이터베이스 연결
            // データベース接続
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false); // 트랜잭션을 수동으로 처리

            // 아이디 중복 검사
            // IDの重複チェック
            Client existingclient = clientDao.selectById(conn, joinRequest.getClient_Id());
            if (existingclient != null) {
                // 중복 아이디가 존재하면 트랜잭션을 롤백하고 예외를 던짐
                // 重複IDが存在する場合、トランザクションをロールバックし、例外をスロー
                JdbcUtil.rollback(conn);
                throw new DuplicateIdException();
            }

            // 새로운 client 객체 생성
            // 新しいClientオブジェクトの作成
            Client newclient = new Client(
                joinRequest.getClient_Id(),
                joinRequest.getPassword(),
                joinRequest.getCompany(),
                joinRequest.getC_Number(),
                joinRequest.getAddress()
            );

            // 클라이언트 정보 DB에 저장
            // クライアント情報をデータベースに保存
            clientDao.insertClient(conn, newclient);
            conn.commit();  // 트랜잭션 커밋

        } catch (SQLException e) {
            // 예외 발생 시 트랜잭션 롤백
            // 例外が発生した場合、トランザクションをロールバック
            JdbcUtil.rollback(conn);
            throw new RuntimeException(e); // 예외를 런타임 예외로 던짐
            // 例外をランタイム例外としてスロー
        } finally {
            // 연결 종료
            // 接続終了
            JdbcUtil.close(conn);
        }
    }
}
