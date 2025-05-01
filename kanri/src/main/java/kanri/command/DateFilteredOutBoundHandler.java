package kanri.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kanri.service.DateFilteredOutBoundService;
import kanri.service.OutBoundPage;
import mvc.command.CommandHandler;

public class DateFilteredOutBoundHandler implements CommandHandler {

    private DateFilteredOutBoundService service = new DateFilteredOutBoundService();
    // 날짜 기준 출고 목록 서비스 객체 생성
    // 日付でフィルタリングする出庫リストサービスのインスタンス生成（ひづけ で フィルタリング する しゅっこ）

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String out_Date = req.getParameter("out_Date");
        // 요청 파라미터에서 출고 날짜 가져오기
        // リクエストパラメータから出庫日を取得（しゅっこび を しゅとく）

        if (out_Date == null || out_Date.trim().isEmpty()) {
            req.setAttribute("error", "주문 날짜를 선택해주세요.");		
            // 날짜 선택하지 않았을 경우 에러 메시지 설정
            // 日付が未選択の場合、エラーメッセージを設定（ひづけ が みせんたく）

            return "/WEB-INF/view/customerScan2.jsp"; 
            // 출고 검색 페이지로 이동
            // 出庫スキャンページに戻る（しゅっこ スキャン ページ に もどる）
        }

        SimpleDateFormat date_Format = new SimpleDateFormat("yyyy-MM-dd");
        // 날짜 포맷 지정
        // 日付フォーマットを指定（ひづけ フォーマット を してい）

        Date out_dateDate = date_Format.parse(out_Date);
        // 문자열 날짜를 Date 객체로 변환
        // 文字列の日付をDate型に変換（もじれつ の ひづけ を デート がた に へんかん）

        OutBoundPage outBoundPage = service.getFilteredList(out_dateDate);
        // 필터링된 출고 데이터 가져오기
        // フィルターされた出庫データを取得（しゅっこ データ）

        req.setAttribute("OutBoundPage", outBoundPage);
        // JSP에서 사용하도록 request에 데이터 저장
        // JSPで使えるようにリクエストに保存（ほぞん）

        return "/WEB-INF/view/customerScan2.jsp";	
        // 결과를 보여줄 페이지로 이동
        // 結果ページへ移動（けっか ページ へ いどう）

        // 경로도 확인합시다. 한 페이지에서 사용할 것인지, 아니면 다른 페이지 만들 것인지
        // パスも確認しましょう。一つのページで使うか、別のページを作るか（かくにん・べつの）
    }
}
