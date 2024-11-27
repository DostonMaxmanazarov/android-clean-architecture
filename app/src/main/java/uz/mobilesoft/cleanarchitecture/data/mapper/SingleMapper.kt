package uz.mobilesoft.cleanarchitecture.data.mapper

fun interface SingleMapper<T, R> {
    operator fun invoke(value: T): R
}