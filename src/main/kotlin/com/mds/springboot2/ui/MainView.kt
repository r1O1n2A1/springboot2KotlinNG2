package com.mds.springboot2.ui

import com.vaadin.annotations.HtmlImport
import com.vaadin.annotations.PreserveOnRefresh
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.Button
import com.vaadin.ui.VerticalLayout

@SpringUI(path="/mainview")
@PreserveOnRefresh
class MainView() : VerticalLayout() {
    init {

    }
}