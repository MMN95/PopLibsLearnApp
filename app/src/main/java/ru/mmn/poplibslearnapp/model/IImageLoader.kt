package ru.mmn.poplibslearnapp.model

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}