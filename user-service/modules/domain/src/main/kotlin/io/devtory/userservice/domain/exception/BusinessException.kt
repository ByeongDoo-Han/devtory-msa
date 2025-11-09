package io.devtory.userservice.domain.exception

class BusinessException(
    val errorCode: ErrorCode
):RuntimeException(errorCode.message)