package inmyword.server.exception;


import lombok.Getter;

public enum ExceptionCode {

    EXIST_EMAIL(409,"이미 존재하는 이메일입니다."),
    EXIST_DISPLAY_NAME(409,"이미 존재하는 이메일입니다.");


    @Getter private final String message;
    @Getter private final int code;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
