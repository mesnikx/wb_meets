package com.example.first_week_creating_ui_kit.ui.utils

data class Meeting(
    val title: String,
    val dateAndPlace: String,
    val isOver: Boolean = false,
    val imageUrl: String? = null,
    val chips: List<String> = listOf()
)