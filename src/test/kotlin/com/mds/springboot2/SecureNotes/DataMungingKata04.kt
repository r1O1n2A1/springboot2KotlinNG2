package com.mds.springboot2.SecureNotes

import org.junit.Test
import java.io.File

class DataMungingKata04{
    @Test
    fun loadFileDataMunging() {
        val entry = File("src/main/resources/static/weather.dat")
                .readLines()
                .drop(8).take(30)
                .map {
                    it.replace("*","")
                            .split(Regex(" +"))
                }
                .map { Weather(it[1], it[2].toFloat() , it[3].toFloat())}
                .minBy {it.max - it.min}
    }

}

data class Weather(val day: String, val max: Float, val min: Float)
