package com.example.currencyconversion.models

data class CurrencyData(
  val base: String = "",
  val rates: Map<String, Double> = mutableMapOf()
)