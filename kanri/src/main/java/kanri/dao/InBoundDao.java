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
import kanri.model.InBound;

/**
 * @author User
 *
 */
public class InBoundDao {

	public int selectCount(Connection conn) throws SQLException{ // 매개변수, 역할군, 반환 자료형
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT COUNT (*) FROM INBOUND");
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		
	}

	public List<InBound> select(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM INBOUND");
			List<InBound> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertInBoundDao(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);

		}
	}

	private InBound convertInBoundDao(ResultSet rs) throws SQLException {
		return new InBound(rs.getString("manager_Id"), rs.getString("product_Id"), toDate(rs.getTimestamp("in_Date")),
				rs.getInt("in_Count"), rs.getInt("price"));
	}
	
	public List<InBound> selectByDate(Connection conn, Date in_Date) throws SQLException {
		String sql = "SELECT * FROM INBOUND WHERE IN_DATE = TO_DATE(?, 'YYYY-MM-DD') ORDER BY IN_DATE DESC";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	pstmt.setString(1,  sdf.format(in_Date));
	    	
	    	
	        ResultSet rs = pstmt.executeQuery();
	        List<InBound> result = new ArrayList<>();
	        while (rs.next()) {
	            result.add(convertInBoundDao(rs));
	        }
	        
	        return result;
	    }
	}

	public int selectCountByDate(Connection conn, Date in_Date) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM INBOUND WHERE IN_DATE = TO_DATE(?, 'yyyy-MM-dd')";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	pstmt.setDate(1, new java.sql.Date(in_Date.getTime()));
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
