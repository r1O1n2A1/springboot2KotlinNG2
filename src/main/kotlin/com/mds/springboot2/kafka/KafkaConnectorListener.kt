package com.mds.springboot2.kafka

/**
 * Created by ronan on 15/11/17.
 */
interface KafkaConnectorListener {
    fun chatMessage(user: String, message: String)
}