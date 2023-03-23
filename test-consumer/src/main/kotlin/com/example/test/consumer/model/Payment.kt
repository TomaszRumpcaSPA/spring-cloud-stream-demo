package com.example.test.consumer.model

import java.math.BigDecimal
import java.time.LocalDate

data class Payment(
    val amount: BigDecimal,
    val payer: String,
    val remittanceInfo: String,
    val receiver: String,
    val bookingDate : LocalDate,
    val e2eId: String,
)