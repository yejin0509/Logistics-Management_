package kanri.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kanri.dao.OutBoundDao;
import kanri.model.OutBound;

public class DateFilteredOutBoundService {

    private OutBoundDao dao = new OutBoundDao();

    public OutBoundPage getFilteredList(Date out_Date) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            List<OutBound> content = dao.selectByDate(conn, out_Date);
            int total = dao.selectCountByDate(conn, out_Date); // 총 데이터 개수 조회
            int size = 10; // 페이지당 데이터 개수 설정 (필요시 변경 가능)
            int currentPage = 1; // 현재 페이지 번호 설정

            return new OutBoundPage(total, currentPage, size, content);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
//
//
//public OutBoundPage getFilteredList(String OutDate) {
//    try (Connection conn = ConnectionProvider.getConnection()) {
//        List<OutBound> content = dao.selectByDate(conn, OutDate); 
//        int total = dao.selectCountByDate(conn, OutDate); // 총 데이터 개수 조회
//        int size = 10; // 페이지당 데이터 개수 설정 (필요시 변경 가능)
//        int currentPage = 1; // 현재 페이지 번호 설정
//
//        return new OutBoundPage(total, currentPage, size, content); // 생성자 호출 수정
//    } catch (SQLException e) {
//        throw new RuntimeException(e);
//    }
//}