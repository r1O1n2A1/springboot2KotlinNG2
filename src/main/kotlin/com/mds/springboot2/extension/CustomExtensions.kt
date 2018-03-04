package com.mds.springboot2.extension

import com.mds.springboot2.customMessage.CustomAppMessage
import com.mds.springboot2.customMessage.CustomAppMessageFactory

fun String.customMessage(myMessage: CustomAppMessage, args: Array<String>):String {
    var toFormat = this
    for (i in 0 until args.size) {
        toFormat += myMessage.message?.replace(CustomAppMessageFactory.stringToFormat, args[i])
    }
    return toFormat
}
