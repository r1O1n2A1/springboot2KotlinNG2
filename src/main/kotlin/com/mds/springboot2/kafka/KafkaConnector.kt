package com.mds.springboot2.kafka

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

// All communication with kafka is wrapped in a singleton
@Component
class KafkaConnector() {
    companion object {
        val logger:Logger = LoggerFactory.getLogger(KafkaConnector::class.java)
    }
    val listeners = mutableListOf<KafkaConnectorListener>()

    fun addListener(listener: KafkaConnectorListener) = listeners.add(listener)

    fun removeListener(listener: KafkaConnectorListener) = listeners.remove(listener)

    @Autowired
    lateinit var kafka: KafkaTemplate<String, String>

    fun send(user: String, message: String) {
        logger.info("$user sending message: \"$message\"")
        kafka.send("ronan-chat",user,message)
    }

    // this method is called every time there is an incoming message
    // in kafka from any client
    // ?: elvis operator if the expression to the left is not
    // null => returns it, otherwise right
    @KafkaListener(topics = arrayOf("ronan-chat"))
    fun receive(consumerRecord: ConsumerRecord<String?, String?>) {
        val key : String = consumerRecord.key() ?: "???"
        val value: String = consumerRecord.value() ?: "???"
        logger.info("got kafka record with key \"$key\" and value \"$value\"")
        listeners.forEach { listener -> listener.chatMessage(key, value) }
    }
}