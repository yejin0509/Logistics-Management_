package kanri.service;

import java.util.List;

import kanri.model.Article;

/**
 * Article 페이지네이션을 관리하는 클래스
 * このクラスは、Articleページネーションを管理します
 */
public class ArticlePage {
    private int total;            // 총 게시물 수 / 総記事数
    private int currentPage;      // 현재 페이지 번호 / 現在のページ番号
    private List<Article> content; // 현재 페이지에 표시할 콘텐츠 / 現在のページに表示するコンテンツ
    private int totalPages;       // 전체 페이지 수 / 総ページ数
    private int startPage;        // 페이지 그룹의 시작 페이지 / ページグループの開始ページ
    private int endPage;          // 페이지 그룹의 끝 페이지 / ページグループの終了ページ

    /**
     * 생성자: 페이지네이션을 계산하여 초기화합니다.
     * コンストラクタ：ページネーションを計算して初期化します
     * @param total 전체 게시물 수 / 総記事数
     * @param currentPage 현재 페이지 번호 / 現在のページ番号
     * @param size 페이지당 표시할 게시물 수 / 1ページあたりの表示記事数
     * @param content 현재 페이지에 해당하는 콘텐츠 / 現在のページに該当するコンテンツ
     */
    public ArticlePage(int total, int currentPage, int size, List<Article> content) {
        this.total = total;
        this.currentPage = currentPage;
        this.content = content;
        
        if (total == 0) {
            totalPages = 0;
            startPage = 0;
            endPage = 0;
        } else {
            // 전체 페이지 수 계산 / 総ページ数を計算
            totalPages = total / size;
            if (total % size > 0) {
                totalPages++;
            }

            // 현재 페이지가 속한 페이지 그룹의 시작 페이지와 끝 페이지 계산 / 現在のページが所属するページグループの開始ページと終了ページを計算
            int modVal = currentPage % 5;
            startPage = currentPage / 5 * 5 + 1;
            if (modVal == 0)
                startPage -= 5;

            endPage = startPage + 4;
            if (endPage > totalPages)
                endPage = totalPages;
        }
    }

    public int getTotal() {
        return total; // 총 게시물 수 반환 / 総記事数を返す
    }

    public boolean hasNoArticles() {
        return total == 0; // 게시물이 없으면 true / 記事がなければtrue
    }

    public boolean hasArticles() {
        return total > 0; // 게시물이 있으면 true / 記事があればtrue
    }

    public int getCurrentPage() {
        return currentPage; // 현재 페이지 반환 / 現在のページを返す
    }

    public int getTotalPages() {
        return totalPages; // 총 페이지 수 반환 / 総ページ数を返す
    }

    public List<Article> getContent() {
        return content; // 현재 페이지에 표시할 콘텐츠 반환 / 現在のページに表示するコンテンツを返す
    }

    public int getStartPage() {
        return startPage; // 시작 페이지 반환 / 開始ページを返す
    }

    public int getEndPage() {
        return endPage; // 끝 페이지 반환 / 終了ページを返す
    }
}
