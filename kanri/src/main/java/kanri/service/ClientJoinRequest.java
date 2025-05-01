package kanri.service;

import java.util.Map;

public class ClientJoinRequest {

    // 클라이언트 회원가입 정보 관련 필드들
    // クライアントの登録情報に関連するフィールド
    private String client_Id;  // 클라이언트 ID
    private String password;   // 비밀번호
    private String confirmPassword;  // 비밀번호 확인
    private String company;    // 회사명
    private String c_Number;   // 연락처
    private String address;    // 주소
    private boolean tosAgree;  // 이용 약관 동의 여부
    private boolean privacyAgree; // 개인정보 처리방침 동의 여부

    // --- Getter & Setter (함수명: 카멜표기법) ---
    // --- Getter & Setter (関数名: キャメルケース) ---
    // 클라이언트 ID getter
    // クライアントIDのゲッター
    public String getClient_Id() {
        return client_Id;
    }

    // 클라이언트 ID setter
    // クライアントIDのセッター
    public void setClient_Id(String client_Id) {
        this.client_Id = client_Id;
    }

    // 비밀번호 getter
    // パスワードのゲッター
    public String getPassword() {
        return password;
    }

    // 비밀번호 setter
    // パスワードのセッター
    public void setPassword(String password) {
        this.password = password;
    }

    // 비밀번호 확인 getter
    // パスワード確認のゲッター
    public String getConfirmPassword() {
        return confirmPassword;
    }

    // 비밀번호 확인 setter
    // パスワード確認のセッター
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    // 회사명 getter
    // 会社名のゲッター
    public String getCompany() {
        return company;
    }

    // 회사명 setter
    // 会社名のセッター
    public void setCompany(String company) {
        this.company = company;
    }

    // 연락처 getter
    // 連絡先のゲッター
    public String getC_Number() {
        return c_Number;
    }

    // 연락처 setter
    // 連絡先のセッター
    public void setC_Number(String c_Number) {
        this.c_Number = c_Number;
    }

    // 주소 getter
    // 住所のゲッター
    public String getAddress() {
        return address;
    }

    // 주소 setter
    // 住所のセッター
    public void setAddress(String address) {
        this.address = address;
    }

    // 이용 약관 동의 여부 getter
    // 利用規約同意のゲッター
    public boolean isTosAgree() {
        return tosAgree;
    }

    // 이용 약관 동의 여부 setter
    // 利用規約同意のセッター
    public void setTosAgree(boolean tosAgree) {
        this.tosAgree = tosAgree;
    }

    // 개인정보 처리방침 동의 여부 getter
    // 個人情報取り扱い同意のゲッター
    public boolean isPrivacyAgree() {
        return privacyAgree;
    }

    // 개인정보 처리방침 동의 여부 setter
    // 個人情報取り扱い同意のセッター
    public void setPrivacyAgree(boolean privacyAgree) {
        this.privacyAgree = privacyAgree;
    }

    // --- 비밀번호 확인 ---
    // --- パスワード確認 ---
    // 비밀번호와 비밀번호 확인이 일치하는지 확인하는 메소드
    // パスワードとパスワード確認が一致しているか確認するメソッド
    public boolean isPasswordEqualToConfirm() {
        return password != null && password.equals(confirmPassword);  // 두 값이 같으면 true 반환
        // 2つの値が一致すればtrueを返す
    }

    // --- 유효성 검사 ---
    // --- 有効性チェック ---
    // 회원가입 시 입력된 값들이 유효한지 확인하는 메소드
    // 会員登録時に入力された値が有効か確認するメソッド
    public void validate(Map<String, Boolean> errors) {
        // 각 필드가 비어 있는지 체크
        // 各フィールドが空でないかチェックする
        checkEmpty(errors, client_Id, "client_Id");
        checkEmpty(errors, password, "password");
        checkEmpty(errors, confirmPassword, "confirmPassword");
        checkEmpty(errors, company, "company");
        checkEmpty(errors, c_Number, "c_Number");
        checkEmpty(errors, address, "address");

        // 비밀번호 확인이 일치하는지 체크
        // パスワード確認が一致しているかチェック
        if (!errors.containsKey("confirmPassword")) {
            if (!isPasswordEqualToConfirm()) {
                errors.put("notMatch", Boolean.TRUE);  // 비밀번호 불일치 시 에러 추가
                // パスワードが一致しない場合、エラーを追加
            }
        }

        // 이용 약관 및 개인정보 처리방침 동의 여부 체크
        // 利用規約および個人情報取り扱い同意のチェック
        if (!tosAgree) {
            errors.put("tosAgree", Boolean.TRUE);  // 동의하지 않으면 에러 추가
            // 同意しない場合、エラーを追加
        }
        if (!privacyAgree) {
            errors.put("privacyAgree", Boolean.TRUE);  // 동의하지 않으면 에러 추가
            // 同意しない場合、エラーを追加
        }
    }

    // 필드가 비어 있는지 확인하는 메소드
    // フィールドが空でないか確認するメソッド
    private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            errors.put(fieldName, Boolean.TRUE);  // 비어 있으면 에러 추가
            // 空の場合、エラーを追加
        }
    }
}
