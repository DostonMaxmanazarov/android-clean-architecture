package uz.mobilesoft.cleanarchitecture

fun main() {
    val s = "+998 99 000".filter { it.isDigit() }
    println(s)
}