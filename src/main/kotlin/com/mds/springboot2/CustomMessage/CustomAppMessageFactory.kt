package com.mds.springboot2.CustomMessage

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CustomAppMessageFactory {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(CustomAppMessageFactory::class.java)
        fun errorCustomAppException(myMessage: CustomAppMessage, vararg param: String) {
            logger.error(String.format(myMessage.customExcep!!,param))
        }
        fun warnCustomAppException(myMessage: CustomAppMessage, vararg param: String) {
            logger.warn(myMessage.customExcep?.let { String.format(it,param) })
        }
        fun infoCustomAppException(myMessage: CustomAppMessage, vararg param: String ) {
            logger.info(myMessage.customExcep?.let { String.format(it,param) })
        }
    }
}