package com.mds.springboot2.CustomMessage

/**
 * Created by ronan on 06/11/17.
 */
enum class CustomAppMessage(val customExcep: String?) {
    CUSTOMER_NOT_FOUND("Customer has not been found"),
    CUSTOMERS_NOT_FOUND("Customers have not been found"),
    LANGUAGE_NOT_FOUND("Language has not been found"),
    SESSION_ENDED("Session has ended for %s"),
    USER_ENTERED("user entered: %s")
}
