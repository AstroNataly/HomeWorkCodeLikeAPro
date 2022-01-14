package ru.netology.nmedia.dto

data class Post(
    val id: Int = 0,
    val author: String = "",
    val content: String = "",
    val published: String = "",
    val likedByMe: Boolean = false,
    val countLikes: Int = 0,
    val shareByMe: Boolean = true,
    val countShares: Int = 0,
    val countViews: Int = 0,



)
