package kanri.service;

import java.util.Map;

public class ManagerJoinRequest {

    private String manager_Id; // 관리자 ID
    private String password; // 비밀번호
    private String confirmPassword; // 비밀번호 확인
    private String registerCode; // 등록 코드

    // --- Getter & Setter ---
    // 관리자 ID 가져오기 / 管理者IDを取得する
    public String getManager_Id() {
        return manager_Id;
    }

    // 관리자 ID 설정하기 / 管理者IDを設定する
    public void setManager_Id(String manager_Id) {
        this.manager_Id = manager_Id;
    }

    // 비밀번호 가져오기 / パスワードを取得する
    public String getPassword() {
        return password;
    }

    // 비밀번호 설정하기 / パスワードを設定する
    public void setPassword(String password) {
        this.password = password;
    }

    // 비밀번호 확인 가져오기 / パスワード確認を取得する
    public String getConfirmPassword() {
        return confirmPassword;
    }

    // 비밀번호 확인 설정하기 / パスワード確認を設定する
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    // 등록 코드 가져오기 / 登録コードを取得する
    public String getRegisterCode() {
        return registerCode;
    }

    // 등록 코드 설정하기 / 登録コードを設定する
    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    // --- 비밀번호 일치 검사 ---
    // パスワード一致確認
    // 비밀번호와 비밀번호 확인이 일치하는지 검사 / パスワードとパスワード確認が一致するか確認する
    public boolean isPasswordEqualToConfirm() {
        return password != null && password.equals(confirmPassword);
    }

    // --- 유효성 검사 ---
    // 入力検証
    public void validate(Map<String, Boolean> errors) {
        checkEmpty(errors, manager_Id, "manager_Id"); // 관리자 ID 필드가 비어있는지 검사 / 管理者IDフィールドが空かどうかを確認する
        checkEmpty(errors, password, "password"); // 비밀번호 필드가 비어있는지 검사 / パスワードフィールドが空かどうかを確認する
        checkEmpty(errors, confirmPassword, "confirmPassword"); // 비밀번호 확인 필드가 비어있는지 검사 / パスワード確認フィールドが空かどうかを確認する
        checkEmpty(errors, registerCode, "registerCode"); // 등록 코드 필드가 비어있는지 검사 / 登録コードフィールドが空かどうかを確認する

        // 비밀번호가 일치하는지 확인 / パスワードが一致するか確認する
        if (!errors.containsKey("confirmPassword") && !isPasswordEqualToConfirm()) {
            errors.put("notMatch", Boolean.TRUE); // 비밀번호가 일치하지 않으면 오류 추가 / パスワードが一致しない場合、エラーを追加
        }

        // 등록 코드가 "0000"이 아닌지 확인 / 登録コードが「0000」ではないか確認する
        if (!"0000".equals(registerCode)) {
            errors.put("registerCode", Boolean.TRUE); // 등록 코드 오류 추가 / 登録コードエラーを追加
        }
    }

    // --- 빈 값 검사 ---
    // 空欄チェック
    private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) { // 값이 null이거나 공백인 경우 / 値がnullまたは空白の場合
            errors.put(fieldName, Boolean.TRUE); // 오류 추가 / エラーを追加
        }
    }
}
