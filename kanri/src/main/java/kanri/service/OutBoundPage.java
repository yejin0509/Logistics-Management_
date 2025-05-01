package kanri.service;
import java.util.List;

import kanri.model.OutBound;

public class OutBoundPage {

    private int total; // 전체 데이터 개수 / 全データ数
    private int currentPage; // 현재 페이지 번호 / 現在のページ番号
    private List<OutBound> content; // 데이터 리스트 / データリスト
    private int totalPages; // 총 페이지 수 / 総ページ数
    private int startPage; // 시작 페이지 번호 / 開始ページ番号
    private int endPage; // 끝 페이지 번호 / 終了ページ番号

    public OutBoundPage(int total, int currentPage, int size, List<OutBound> content) {
        this.total = total;
        this.currentPage = currentPage;
        this.content = content;
        if (total == 0) { // 데이터가 없을 경우 처리 / データがない場合の処理
            totalPages = 0;
            startPage = 0;
            endPage = 0;
        } else {
            totalPages = total / size; // 총 페이지 수 계산 / 総ページ数を計算
            if (total % size > 0) { // 나머지가 있을 경우 페이지 수 1 증가 / 残りがある場合、ページ数を1増やす
                totalPages++;
            }
            int modVal = currentPage % 5; // 페이지 그룹 계산 / ページグループを計算
            startPage = currentPage / 5 * 5 + 1; // 그룹의 시작 페이지 / グループの開始ページ
            if (modVal == 0) // 그룹이 정확히 나누어지면 5페이지 뒤로 / グループがちょうど分割されている場合は5ページ後ろに
                startPage -= 5;

            endPage = startPage + 4; // 그룹의 마지막 페이지 / グループの終了ページ
            if (endPage > totalPages) // 마지막 페이지가 총 페이지 수보다 크면 조정 / 終了ページが総ページ数を超える場合、調整
                endPage = totalPages;
        }
    }

    /**
     * 전체 데이터 개수를 반환 / 全データ数を返す
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * 출력할 데이터가 없는지 여부를 반환 / 表示するデータがないかどうかを返す
     * @return true if no data, false otherwise / データがない場合はtrue、それ以外はfalse
     */
    public boolean hasNotOutBoundHistory() {
        return total == 0;
    }

    /**
     * 출력할 데이터가 있는지 여부를 반환 / 表示するデータがあるかどうかを返す
     * @return true if data exists, false otherwise / データがある場合はtrue、それ以外はfalse
     */
    public boolean hasOutBoundHistory() {
        return total > 0;
    }

    /**
     * 현재 페이지 번호를 반환 / 現在のページ番号を返す
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * 데이터 리스트를 반환 / データリストを返す
     * @return the content
     */
    public List<OutBound> getContent() {
        return content;
    }

    /**
     * 총 페이지 수를 반환 / 総ページ数を返す
     * @return the totalPages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * 시작 페이지 번호를 반환 / 開始ページ番号を返す
     * @return the startPage
     */
    public int getStartPage() {
        return startPage;
    }

    /**
     * 끝 페이지 번호를 반환 / 終了ページ番号を返す
     * @return the endPage
     */
    public int getEndPage() {
        return endPage;
    }
}
