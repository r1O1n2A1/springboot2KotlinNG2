package com.mds.springboot2.ui

import com.mds.springboot2.customMessage.CustomAppMessage
import com.mds.springboot2.customMessage.CustomAppMessageFactory
import com.vaadin.annotations.PreserveOnRefresh
import com.vaadin.annotations.Push
import com.vaadin.spring.annotation.SpringUI
import com.mds.springboot2.kafka.*
import com.vaadin.event.ShortcutAction
import com.vaadin.server.Sizeable
import com.vaadin.server.VaadinRequest
import com.vaadin.shared.communication.PushMode
import com.vaadin.ui.*
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by ronan on 15/11/17.
 */
@SpringUI(path="/")        // define Vaadin UI
@PreserveOnRefresh  // keeps the session when refresh
@Push(PushMode.AUTOMATIC)
class ChatUI: UI(), KafkaConnectorListener {

    // use lateinit to avoid null checks when referencing the property
    // convenient if properties initialized through dependency injection
    // or setup method of a unit test
    // accessing lateinit property before it has been initialized
    //      ==> throws exception
    lateinit var user: String   // name of user that is chatting
    val chatDisplay = ChatDisplay()
    val userLabel = Label()

    @Autowired
    lateinit var kafkaConnector: KafkaConnector

    override fun init(request: VaadinRequest?) {
        kafkaConnector.addListener(this)
        content = VerticalLayout().apply {
            setSizeFull()
            addComponents(chatDisplay, createInputs())
            setExpandRatio(chatDisplay, 1F)
        }
        askForUsername()

    }

    private fun askForUsername() {
        addWindow(Window("your user:").apply {
            isModal = true
            isClosable = false
            isResizable = false
            content = VerticalLayout().apply {
                val nameField = TextField().apply {
                    focus()
                }
                addComponents(nameField)
                addComponent(Button("ok").apply {
                    setClickShortcut(ShortcutAction.KeyCode.ENTER)
                    addClickListener {
                        user = nameField.value
                        if (!user.isNullOrEmpty()) {
                            close()
                            userLabel.value = user
                            CustomAppMessageFactory
                                    .infoCustomAppMessage(CustomAppMessage.USER_ENTERED, arrayOf(user))
                        }
                    }
                })
            }
            center()
        })
    }


    private fun createInputs(): Component {
        return HorizontalLayout().apply {
            setWidth(100F,Sizeable.Unit.PERCENTAGE)
            val messageField = TextField().apply {
                setWidth(100F, Sizeable.Unit.PERCENTAGE)
            }
            val button = Button("send").apply {
                setClickShortcut(ShortcutAction.KeyCode.ENTER)
                addClickListener {
                    kafkaConnector.send(user, messageField.value)
                    messageField.apply {
                        clear()
                        focus()
                    }
                }
            }
            addComponents(userLabel, messageField, button)
            setComponentAlignment(userLabel, Alignment.MIDDLE_LEFT)
            setExpandRatio(messageField, 1F)
        }
    }

    override fun chatMessage(user: String, message: String) {
        access{ chatDisplay.addMessage(user, message) }
    }

    override fun detach() {
        kafkaConnector.removeListener(this)
        super.detach()
        CustomAppMessageFactory.infoCustomAppMessage(CustomAppMessage.SESSION_ENDED, arrayOf(user))
    }
}
