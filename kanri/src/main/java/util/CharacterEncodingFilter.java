package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

    private String encoding;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        // 요청의 문자 인코딩을 설정 / リクエストの文字エンコーディングを設定
        req.setCharacterEncoding(encoding); 
        // 필터 체인 계속 실행 / フィルターチェーンを続けて実行
        chain.doFilter(req, res); 
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        // TODO Auto-generated method stub
        // 필터 초기화 시 인코딩을 설정 / フィルタの初期化時にエンコーディングを設定
        encoding = config.getInitParameter("encoding");
        if (encoding == null) {
            encoding = "UTF-8"; // 기본 인코딩 설정 / デフォルトのエンコーディング設定
        }
    }

    @Override
    public void destroy() {
        // 필터 종료 시 특별한 처리는 없음 / フィルター終了時に特別な処理はなし
    }
}
