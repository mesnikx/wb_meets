package com.example.domain.domain.entities

import java.util.UUID

data class MeetingData(
    val meetingId: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String? = null,
    val dateAndPlace: String,
    val mapUrl: String? = "https://s3-alpha-sig.figma.com/img/a7d0/b7a1/73dfa50190eed292a52792c6d52bb4be?Expires=1721606400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=Lbp~3M0cO0QqU4lp~FXgS4hYwsMVN97j2OZ3HVxb8dEnfLglnfSrPAkaAzJfYEpb69jK3ownyv8GlElutrbD8Ae3vdiQjXpFbOoK-3sgXTVMdTNHCDC7yyRnqwxiCN-9OLFYuwlzvRem139gTzBSrgQ4h0~2T1Gf-XE7I29MM6n3SpJ-xLwwpHaOnDMFG35KkPwHIMVl~RQOSb3CNPrf2CLrbrcuTeLGJdoItKkuEobXERZjHBVTh4PvhxdXMmHiRKykksWEEYGc1UmbH7x~oY1EVQx2UTob2aMF4ro~eu57F8-JthhN3Cd8t9o9Tyi92ZIayuZyICVx9Q7bMzgMoQ__",
    val isOver: Boolean = false,
    val meetingGuests: List<String> = listOf(),
    val imageUrl: String? = null,
    val chips: List<String> = listOf(),
    val communityDataId: String = CommunityData.getDefault().communityId,
) {
    companion object {
        fun getDefault(): MeetingData {
            return MeetingData(
                meetingId = "",
                title = "",
                description = "",
                dateAndPlace = "",
                isOver = false,
                imageUrl = null,
                chips = emptyList(),
                communityDataId = ""
            )
        }
    }
}
