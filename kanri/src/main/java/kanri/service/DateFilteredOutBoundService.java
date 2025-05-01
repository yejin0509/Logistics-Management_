package kanri.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kanri.dao.OutBoundDao;
import kanri.model.OutBound;

public class DateFilteredOutBoundService {

    // OutBoundDao 객체 생성
    // OutBoundDaoオブジェクトの作成
    private OutBoundDao dao = new OutBoundDao();

    // 날짜로 필터링된 OutBound 목록을 가져오는 메소드
    // 日付でフィルタリングされたOutBoundリストを取得するメソッド
    public OutBoundPage getFilteredList(Date out_Date) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            // 주어진 날짜에 해당하는 OutBound 목록 조회
            // 指定された日付に該当するOutBoundリストの取得
            List<OutBound> content = dao.selectByDate(conn, out_Date);
            // 해당 날짜에 해당하는 OutBound의 총 개수 조회
            // 指定された日付に該当するOutBoundの総数の取得
            int total = dao.selectCountByDate(conn, out_Date); // 총 데이터 개수 조회
            // ページごとのデータの総数を取得
            int size = 10; // 페이지당 데이터 개수 설정 (필요시 변경 가능)
            // ページごとのデータの数を設定（必要に応じて変更可能）
            int currentPage = 1; // 현재 페이지 번호 설정
            // 現在のページ番号を設定

            // OutBoundPage 객체 생성 후 반환
            // OutBoundPageオブジェクトを作成して返す
            return new OutBoundPage(total, currentPage, size, content);
        } catch (SQLException e) {
            // 예외 발생 시 런타임 예외를 던짐
            // 例外が発生した場合、ランタイム例外をスロー
            throw new RuntimeException(e);
        }
    }
}
//
//
//public OutBoundPage getFilteredList(String OutDate) {
//    try (Connection conn = ConnectionProvider.getConnection()) {
//        // 주어진 OutDate에 해당하는 OutBound 목록 조회
//        // 指定されたOutDateに該当するOutBoundリストの取得
//        List<OutBound> content = dao.selectByDate(conn, OutDate); 
//        // 해당 OutDate에 해당하는 OutBound의 총 개수 조회
//        // 指定されたOutDateに該当するOutBoundの総数の取得
//        int total = dao.selectCountByDate(conn, OutDate); // 총 데이터 개수 조회
//        // ページごとのデータの総数を取得
//        int size = 10; // 페이지당 데이터 개수 설정 (필요시 변경 가능)
//        // ページごとのデータの数を設定（必要に応じて変更可能）
//        int currentPage = 1; // 현재 페이지 번호 설정
//        // 現在のページ番号を設定
//
//        // OutBoundPage 객체 생성 후 반환 (생성자 호출 수정)
//        // OutBoundPageオブジェクトを作成して返す（コンストラクタの呼び出し修正）
//        return new OutBoundPage(total, currentPage, size, content); // 생성자 호출 수정
//    } catch (SQLException e) {
//        // 예외 발생 시 런타임 예외를 던짐
//        // 例外が発生した場合、ランタイム例外をスロー
//        throw new RuntimeException(e);
//    }
//}
