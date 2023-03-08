package com.example.kalkulatrix

import androidx.annotation.StringRes

enum class Operation(@StringRes val operator: Int) {
    Equals(R.string.equals_btn),
    Reset(R.string.reset_calc),
    Addition(R.string.plus_operator),
    Subtraction(R.string.minus_operator),
    Multiplication(R.string.times_operator),
    Division(R.string.divide_operator),
    Percentage(R.string.percentage_operator),
}