package com.example.first_week_creating_ui_kit.domain.data

import java.util.UUID

data class CommunityData(
    val communityId: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String? = null,
    val imageUrl: String? = null,
    val numberOfSubs: Int,
    val meetingData: List<MeetingData> = emptyList()
) {
    companion object {
        fun getDefault(): CommunityData {
            return CommunityData(
                communityId = "",
                title = "",
                description = null,
                imageUrl = null,
                numberOfSubs = 0,
                meetingData = emptyList()
            )
        }
    }
}
