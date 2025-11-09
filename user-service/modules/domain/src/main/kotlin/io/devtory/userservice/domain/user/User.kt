package io.devtory.userservice.domain.user

import com.fasterxml.jackson.annotation.JsonFormat
import io.devtory.userservice.domain.exception.BusinessException
import io.devtory.userservice.domain.exception.ErrorCode
import java.time.LocalDateTime

data class User(
    val id: Long? = null,
    val email: String,
    val password: String,
    val nickname: String,
    val profileImageUrl: String,
    val status: UserActiveStatus = UserActiveStatus.ACTIVE,
    @get:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @get:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as User
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    fun changePassword(newPassword: String): User {
        require(newPassword.isNotBlank() && this.password != newPassword)
        validatePassword(newPassword)
        return this.copy(password = newPassword)
        // 기존 패스워드와 같은지 다른지
        // 패스워드가 10자 이상인지,
        // 특수문자, 영어 , 숫자가 포함되어 있는지
    }

    private fun validatePassword(password: String) {
        if (password.length !in 8..20) throw BusinessException(ErrorCode.PASSWORD_LENGTH_EIGHT_TO_TWENTY)
        if (!password.any { it.isLetter() }) throw BusinessException(ErrorCode.PASSWORD_MUST_HAS_LETTER)
        if (!password.any { it.isDigit() }) throw BusinessException(ErrorCode.PASSWORD_MUST_HAS_DIGIT)
        if (password.all { it.isLetterOrDigit() }) throw BusinessException(ErrorCode.PASSWORD_MUST_HAS_SPECIAL_LETTER)
    }

    private fun validateWithdrawn() {
        if (this.status == UserActiveStatus.WITHDRAWN) throw BusinessException(ErrorCode.USER_WITHDRAWN_ALREADY)
    }

    fun changeNickname(newNickname: String) {
        if (this.nickname == newNickname) throw BusinessException(ErrorCode.USER_NICKNAME_MUST_BE_DIFFERENT)
        // 비속어가 포함되어 있는지
        // 기존 닉네임과 겹치지 않은지 (이건 겹쳐도 될 수 있다고 생각)
        // 닉네임 변경 주기 혹은 횟수 (너무 잦은 변경 막음)
    }

    fun withdraw(): User {
        validateWithdrawn()
        return this.copy(status = UserActiveStatus.WITHDRAWN)
    }

    fun active(): User {
        validateWithdrawn()
        if (this.status == UserActiveStatus.ACTIVE) throw BusinessException(ErrorCode.USER_ACTIVE_ALREADY)
        return this.copy(status = UserActiveStatus.ACTIVE)
    }

    fun inactive(): User {
        validateWithdrawn()
        if (this.status == UserActiveStatus.INACTIVE) throw BusinessException(ErrorCode.USER_INACTIVE_ALREADY)
        return this.copy(status = UserActiveStatus.INACTIVE)
    }
}

enum class UserActiveStatus(val value: String) {
    ACTIVE("활성"),
    INACTIVE("비활성"),
    WITHDRAWN("탈퇴")
}