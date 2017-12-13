package com.mds.springboot2.ui

import com.vaadin.shared.ui.ContentMode
import com.vaadin.ui.Label
import com.vaadin.ui.Panel
import com.vaadin.ui.VerticalLayout

/**
 * Class which display chat messages<br>
 * @author by ronan on 12/11/17.<br>
 * @date 10/11/2017
 */
class ChatDisplay:Panel() {
    val text: Label
    init {
        setSizeFull()
        text = Label().apply { contentMode = ContentMode.HTML }
        content = VerticalLayout().apply { addComponent(text) }
    }

    fun addMessage(user: String, message: String) {
        text.value = when {
            text.value.isNullOrEmpty() -> "<em>$user:</em> $message"
            else -> text.value + "<br><em>$user:</em> $message"
        }
        scrollTop = Int.MAX_VALUE
    }
}