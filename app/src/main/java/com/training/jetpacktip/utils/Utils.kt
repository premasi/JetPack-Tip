package com.training.jetpacktip.utils

fun calculateTotalTip(value: String, sliderValue: Int, value1: Int): Int {
    var result = 0
    if(value.isNotEmpty() && sliderValue != 0){
        val valueInt = value.toInt()
        result = (valueInt * sliderValue)/100
        result /= value1
    } else if(value.isNotEmpty() && sliderValue == 0){
        val valueInt = value.toInt()
        result = valueInt / value1
    }

    return result
}

fun checkNull(value: String): Int{
    if(value.isEmpty()){
        return 0
    } else {
        return value.toInt()
    }
}

fun formatRupiah(biaya: Int): String{
    return "%,d".format(biaya).replace(',','.')
}
