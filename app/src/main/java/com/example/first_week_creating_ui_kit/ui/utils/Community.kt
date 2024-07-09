package com.example.first_week_creating_ui_kit.ui.utils

import java.util.UUID

data class Community(
    val communityId: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String? = null,
    val imageUrl: String? = null,
    val numberOfSubs: Int,
    val meetings: List<Meeting> = emptyList()
) {
    companion object {
        fun getDefault(): Community {
            return Community(
                communityId = "",
                title = "",
                description = null,
                imageUrl = null,
                numberOfSubs = 0,
                meetings = emptyList()
            )
        }
    }
}
