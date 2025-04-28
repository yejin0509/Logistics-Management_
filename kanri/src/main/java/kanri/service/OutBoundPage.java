package kanri.service;
import java.util.List;


import kanri.model.OutBound;

public class OutBoundPage {

	private int total;
	private int currentPage;
	private List<OutBound> content;
	private int totalPages;
	private int startPage;
	private int endPage;

	public OutBoundPage(int total, int currentPage, int size, List<OutBound> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if (total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total / size;
			if (total % size > 0) {
				totalPages++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if (modVal == 0)
				startPage -= 5;

			endPage = startPage + 4;
			if (endPage > totalPages)
				endPage = totalPages;
		}
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	public boolean hasNotOutBoundHistory() {
		return total == 0;
	}

	public boolean hasOutBoundHistory() {
		return total > 0;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @return the content
	 */
	public List<OutBound> getContent() {
		return content;
	}

	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * @return the startPage
	 */
	public int getStartPage() {
		return startPage;
	}

	/**
	 * @return the endPage
	 */
	public int getEndPage() {
		return endPage;
	}
}
