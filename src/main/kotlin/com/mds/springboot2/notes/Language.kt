package com.mds.springboot2.notes

import com.mds.springboot2.CustomException.LanguageException

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
    /*
     * singleton factory to language in lower case
     */
    object LowerLanguageFactory {
        fun createLanguageLower(str: String?):String? {
            if(str == "french") return Language.FRENCH.name.toLowerCase()
            else if(str == "english") return Language.ENGLISH.name.toLowerCase()
            else throw LanguageException("Wrong choice off language")
        }
    }
}