package kanri.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kanri.dao.InBoundDao;
import kanri.model.InBound;

public class DateFilteredInBoundService {

    // InBoundDao 객체 생성
    // InBoundDaoオブジェクトの作成
    private InBoundDao dao = new InBoundDao();

    // 날짜로 필터링된 InBound 목록을 가져오는 메소드
    // 日付でフィルタリングされたInBoundリストを取得するメソッド
    public InBoundPage getFilteredList(Date in_Date) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            // 주어진 날짜에 해당하는 InBound 목록 조회
            // 指定された日付に該当するInBoundリストの取得
            List<InBound> content = dao.selectByDate(conn, in_Date);
            // 해당 날짜에 해당하는 InBound의 총 개수 조회
            // 指定された日付に該当するInBoundの総数の取得
            int total = dao.selectCountByDate(conn, in_Date);
            int size = 10; // 페이지당 데이터 개수 설정 (필요시 변경 가능)
            // ページごとのデータの数を設定（必要に応じて変更可能）
            int currentPage = 1; // 현재 페이지 번호 설정
            // 現在のページ番号を設定

            // InBoundPage 객체 생성 후 반환 (페이징 없이 전체 출력)
            // InBoundPageオブジェクトを作成して返す（ページングなしで全出力）
            return new InBoundPage(total, currentPage, size, content);
        } catch (SQLException e) {
            // 예외 발생 시 런타임 예외를 던짐
            // 例外が発生した場合、ランタイム例外をスロー
            throw new RuntimeException(e);
        }
    }
}
