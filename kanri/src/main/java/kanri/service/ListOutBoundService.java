package kanri.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kanri.dao.OutBoundDao;
import kanri.model.OutBound;

public class ListOutBoundService {

    private OutBoundDao outBoundDao = new OutBoundDao(); // OutBoundDao 객체 초기화
    private int size = 10; // 페이지당 데이터 수 (기본 10개)

    // OutBound 페이지 정보 가져오기
    // OutBoundページ情報を取得するメソッド
    public OutBoundPage getOutBoundPage(int pageNum) {
        
        int firstRow = 0; // 첫 번째 행 (시작 행)
        int endRow = 0;   // 마지막 행 (끝 행)
        List<OutBound> content = null; // OutBound 객체 목록

        try (Connection conn = ConnectionProvider.getConnection()) { // 데이터베이스 연결
            int total = outBoundDao.selectCount(conn); // 전체 레코드 수 조회
            if (total > 0) { // 총 데이터가 존재하는 경우
                firstRow = (pageNum - 1) * size + 1; // 첫 번째 행 계산
                endRow = firstRow + size - 1; // 마지막 행 계산
                content = outBoundDao.select(conn); // OutBound 목록 조회
            }
            return new OutBoundPage(total, pageNum, size, content); // 페이지 객체 반환
        } catch (SQLException e) {
            throw new RuntimeException(e); // 예외 발생 시 RuntimeException 던지기
        }
    }
}
