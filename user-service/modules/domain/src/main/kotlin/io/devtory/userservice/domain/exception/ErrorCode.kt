package io.devtory.userservice.domain.exception

enum class ErrorCode(val message: String) {
    USER_WITHDRAWN_ALREADY("이미 탈퇴한 회원입니다."),
    USER_ACTIVE_ALREADY("이미 활성화된 회원입니다."),
    USER_INACTIVE_ALREADY("이미 비활성화된 회원입니다."),
    USER_NICKNAME_MUST_BE_DIFFERENT("새 닉네임이 기존과 같습니다."),

    PASSWORD_MUST_BE_DIFFERENT("비밀번호는 기존 비밀번호와 달라야 합니다."),
    PASSWORD_LENGTH_EIGHT_TO_TWENTY("비밀번호는 8~20 자리여야 합니다."),
    PASSWORD_MUST_HAS_DIGIT("숫자가 1개 이상 포함되어야 합니다."),
    PASSWORD_MUST_HAS_LETTER("글자가 1개 이상 포함되어야 합니다."),
    PASSWORD_MUST_HAS_SPECIAL_LETTER("특수문자가 1개 이상 포함되어야 합니다.")
}