package com.mds.springboot2.notes

import com.mds.springboot2.CustomException.LanguageException

/**
 * Created by ronan on 06/11/17.
 * Enum of Languages used in our app
 */
enum class Language(val country: String) {
    FRANCE("French"),UK("English");

    fun toLanguageTag(): CharSequence {
        return name.toLowerCase().subSequence(0,2)
    }

    fun toUpper(): String = this.country.toUpperCase().substring(0,2)

}