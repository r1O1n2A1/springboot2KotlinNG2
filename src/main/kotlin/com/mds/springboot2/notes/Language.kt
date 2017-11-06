package com.mds.springboot2.notes

/**
 * Created by ronan on 06/11/17.
 * Enum of Languages used in our app
 */
enum class Language {
    FRENCH,ENGLISH;

    fun toLanguageTag() {
        name.toLowerCase().subSequence(0,2)
    }

    companion object {

    }
}