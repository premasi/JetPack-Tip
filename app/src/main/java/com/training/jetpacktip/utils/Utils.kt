package com.training.jetpacktip.utils

fun calculateTotalTip(value: String, sliderValue: Int, value1: Int): Int {
    var result = 0
    if(value.isNotEmpty() && sliderValue != 0){
        val valueInt = value.toInt()
        result = (valueInt * sliderValue)/100
        result /= value1
    }

    return result
}
