package com.example.currencyconversion.models

import java.util.stream.DoubleStream

data class CurrencyData(
  val base_code: String = "",
  val target_code: String = "",
  val conversion_rate: Double = 0.0,
  val conversion_result: Double = 0.0
)