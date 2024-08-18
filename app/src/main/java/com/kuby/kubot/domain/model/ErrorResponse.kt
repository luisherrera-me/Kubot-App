package com.kuby.kubot.domain.model

data class ErrorResponse(
    val statusCode: Int = 500,
    val message: String = "Something went wrong"
)
