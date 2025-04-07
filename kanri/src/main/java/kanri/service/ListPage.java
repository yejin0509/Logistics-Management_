package kanri.service;

import java.util.List;

import kanri.model.Inventory;

public class ListPage {
	private int total;
	private int current_Page;
	private List<Inventory> content;
	private int total_Pages;
	private int start_Page;
	private int end_Page;

	public ListPage(int total, int current_Page, int size, List<Inventory> content) {
		this.total = total;
		this.current_Page = current_Page;
		this.content = content;
		if (total == 0) {
			total_Pages = 0;
			start_Page = 0;
			end_Page = 0;
		} else {
			total_Pages = total / size;
			if (total % size > 0) {
				total_Pages++;
			}
			int modVal = current_Page % 5;
			start_Page = current_Page / 5 * 5 + 1;
			if (modVal == 0)
				start_Page -= 5;

			end_Page = start_Page + 4;
			if (end_Page > total_Pages)
				end_Page = total_Pages;
		}
	}

	public int getTotal() {
		return total;
	}

	public boolean hasNoArticles() {
		return total == 0;
	}

	public boolean hasArticles() {
		return total > 0;
	}

	public int getCurrentPage() {
		return current_Page;
	}

	public int getTotalPages() {
		return total_Pages;
	}

	public List<Inventory> getContent() {
		return content;
	}

	public int getStartPage() {
		return start_Page;
	}

	public int getEndPage() {
		return end_Page;
	}
}
