package com.thechefz.chefzlibrary.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String?.value () : String{
    return this?: ""
}

fun String.toDateFormat(originalFormat:String,targetFormat:String):String{
    try {
        val originalDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        val targetDateFormat =SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return targetDateFormat.format(originalDateFormat.parse(this)?: Date())
    }catch (e:Exception){
        return this
    }
}