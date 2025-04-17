package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.dao.ArticleDao;
import article.model.Article;
import jdbc.connection.ConnectionProvider;

public class ListArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private int size =10;

	public ArticlePage getArticlePage(int pageNum) {
		int firstRow =0;
		int endRow = 0;
		List<Article> content = null;
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = articleDao.selectCount(conn);
			if(total>0) {
				firstRow=(pageNum-1)*size+1;
				endRow=firstRow + size-1;
				content = articleDao.select(conn, firstRow, endRow);
				
			}
			return new ArticlePage(total, pageNum, size, content);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
