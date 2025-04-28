package kanri.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import kanri.dao.InBoundDao;
import kanri.model.InBound;

public class DateFilteredInBoundService {

    private InBoundDao dao = new InBoundDao();

    public InBoundPage getFilteredList(Date in_Date) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            List<InBound> content = dao.selectByDate(conn, in_Date);
            int total = dao.selectCountByDate(conn, in_Date);
            int size = 10; // 페이지당 데이터 개수 설정 (필요시 변경 가능)
            int currentPage = 1; // 현재 페이지 번호 설정

            return new InBoundPage(total, currentPage, size, content); // 페이징 없이 전체 출력
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
