package kanri.service;

import java.util.List;

import kanri.model.InBound;

public class InBoundPage {

    private int total; // 총 데이터 개수
    // 総データ数
    private int currentPage; // 현재 페이지 번호
    // 現在のページ番号
    private List<InBound> content; // 페이지에 표시될 데이터 목록
    // ページに表示されるデータリスト
    private int totalPages; // 전체 페이지 개수
    // 総ページ数
    private int startPage; // 시작 페이지 번호
    // 開始ページ番号
    private int endPage; // 끝 페이지 번호
    // 終了ページ番号

    public InBoundPage(int total, int currentPage, int size, List<InBound> content) {
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
     * @return the total - 총 데이터 개수
     * @return 総データ数
     */
    public int getTotal() {
        return total;
    }

    // 데이터가 없을 때
    // データがないとき
    public boolean hasNotInBoundHistory() {
        return total == 0;
    }

    // 데이터가 있을 때
    // データがあるとき
    public boolean hasInBoundHistory() {
        return total > 0;
    }

    /**
     * @return the currentPage - 현재 페이지 번호
     * @return 現在のページ番号
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @return the content - 페이지에 표시될 데이터 목록
     * @return ページに表示されるデータリスト
     */
    public List<InBound> getContent() {
        return content;
    }

    /**
     * @return the totalPages - 전체 페이지 개수
     * @return 総ページ数
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * @return the startPage - 시작 페이지 번호
     * @return 開始ページ番号
     */
    public int getStartPage() {
        return startPage;
    }

    /**
     * @return the endPage - 끝 페이지 번호
     * @return 終了ページ番号
     */
    public int getEndPage() {
        return endPage;
    }
}
