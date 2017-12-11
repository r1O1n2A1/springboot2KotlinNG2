package com.mds.springboot2.util

import java.util.function.Predicate

class PatternUtil {
    object calcul {
        fun totalValue(listOfInteger: List<Integer>, selector: Predicate<Integer>): Int {
            var result = 0
            for( integer in listOfInteger) {
                result += integer.toInt()
            }
            return result
        }
    }
}