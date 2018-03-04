package com.mds.springboot2.customMessage

import com.mds.springboot2.extension.customMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CustomAppMessageFactory {
    companion object {
        val stringToFormat: String = "%s"
        var formattedMessage: String = ""
        private val logger: Logger = LoggerFactory.getLogger(CustomAppMessageFactory::class.java)

        fun errorCustomAppMessage(myMessage: CustomAppMessage, args: Array<String>) {
            logger.error(formattedMessage.customMessage(myMessage, args))
        }

        fun warnCustomAppMessage(myMessage: CustomAppMessage, args: Array<String>) {
            logger.warn(formattedMessage.customMessage(myMessage, args))
        }

        fun infoCustomAppMessage(myMessage: CustomAppMessage, args: Array<String>) {
            logger.info(formattedMessage.customMessage(myMessage, args))
        }
    }
}






