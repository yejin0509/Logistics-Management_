/**
 * 
 */
package kanri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import kanri.model.OutBound;

/**
 * @author User
 *
 */
public class OutBoundDao {

	public int selectCount(Connection conn) throws SQLException{ // 매개변수, 역할군, 반환 자료형
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT COUNT (*) FROM OUTBOUND");
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		
	}

	public List<OutBound> select(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM OUTBOUND");
			List<OutBound> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertOutBoundDao(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);

		}
	}

	private OutBound convertOutBoundDao(ResultSet rs) throws SQLException {
		return new OutBound(rs.getString("client_Id"), rs.getString("product_Id"), toDate(rs.getTimestamp("out_Date")),
				rs.getInt("out_Count"), rs.getInt("price"));
	}
	
	public List<OutBound> selectByDate(Connection conn, Date out_Date) throws SQLException {
	    String sql = "SELECT * FROM OUTBOUND WHERE OUT_DATE = TO_DATE(?, 'YYYY-MM-DD') ORDER BY OUT_DATE DESC";	//이거 파라미터 값 나중에 다시 한번 확인 해봐 안되면
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	pstmt.setString(1,  sdf.format(out_Date));
	    	
	    	
	        ResultSet rs = pstmt.executeQuery();
	        List<OutBound> result = new ArrayList<>();
	        while (rs.next()) {
	            result.add(convertOutBoundDao(rs));
	        }
	        return result;
	    }
	}

	public int selectCountByDate(Connection conn, Date out_Date) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM OUTBOUND WHERE OUT_DATE = TO_DATE(?, 'YYYY-MM-DD')";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	pstmt.setDate(1, new java.sql.Date(out_Date.getTime()));
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1);
	        }
	        return 0;
	    }
	}


	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	private Timestamp toTimestamp(java.sql.Date date) {
		return new Timestamp(date.getTime());
	}
}
