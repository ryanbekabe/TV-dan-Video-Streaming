package com.hanyajasa.tvdanvideostreaming2

enum class VideoSource { YOUTUBE, VIDIO, LK21 }

data class VideoItem(
    val id: String,
    val title: String,
    val channel: String,
    val duration: String,
    val thumbnailUrl: String,
    val source: VideoSource = VideoSource.YOUTUBE,
    val videoUrl: String? = null
) {
    val youtubeUrl: String get() = "https://www.youtube.com/watch?v=$id"
    val embedUrl: String get() = "https://www.youtube.com/embed/$id?autoplay=1&controls=1&fs=1&rel=0"
    val thumbnailHqUrl: String get() = when (source) {
        VideoSource.YOUTUBE -> "https://img.youtube.com/vi/$id/hqdefault.jpg"
        else -> thumbnailUrl
    }
}

object VideoRepository {
    val featuredVideos = listOf(
        VideoItem(
            id = "papa-zola-movie-2025",
            title = "Papa Zola Movie (2025)",
            channel = "LK21",
            duration = "1h 30m",
            thumbnailUrl = "https://files.catbox.moe/u0f5p6.jpg", // Placeholder or actual thumbnail if known
            source = VideoSource.LK21,
            videoUrl = "https://tv10.lk21official.cc/papa-zola-movie-2025"
        ),
        VideoItem(
            id = "papa-zola-movie-2025",
            title = "Papa Zola: The Movie",
            channel = "LK21",
            duration = "1:51:00",
            thumbnailUrl = "https://tv10.lk21official.cc/wp-content/uploads/2024/07/papa-zola-movie-2025.jpg",
            source = VideoSource.LK21,
            videoUrl = "https://tv10.lk21official.cc/papa-zola-movie-2025"
        ),
        VideoItem(
            id = "204",
            title = "SCTV Live Streaming",
            channel = "SCTV",
            duration = "LIVE",
            thumbnailUrl = "https://thumbor.prod.vidiocdn.com/jG_X_9V9V_9V9V_9V9V_9V9V_9V=/640x360/filters:quality(70)/vidio-web-main-client/assets/images/placeholder-live.jpg",
            source = VideoSource.VIDIO,
            videoUrl = "https://www.vidio.com/live/204-sctv"
        ),
        VideoItem(
            id = "dQw4w9WgXcQ",
            title = "Rick Astley - Never Gonna Give You Up",
            channel = "Rick Astley",
            duration = "3:33",
            thumbnailUrl = "https://img.youtube.com/vi/dQw4w9WgXcQ/hqdefault.jpg"
        )
    )

    val trendingVideos = listOf(
        VideoItem(
            id = "733",
            title = "GGS TV - 24 Jam Non-stop",
            channel = "GGS TV",
            duration = "LIVE",
            thumbnailUrl = "https://thumbor.prod.vidiocdn.com/jG_X_9V9V_9V9V_9V9V_9V9V_9V=/640x360/filters:quality(70)/vidio-web-main-client/assets/images/placeholder-live.jpg",
            source = VideoSource.VIDIO,
            videoUrl = "https://www.vidio.com/live/733-ggs-tv"
        ),
        VideoItem(
            id = "kJQP7kiw5Fk",
            title = "Luis Fonsi - Despacito ft. Daddy Yankee",
            channel = "Luis Fonsi",
            duration = "4:42",
            thumbnailUrl = "https://img.youtube.com/vi/kJQP7kiw5Fk/hqdefault.jpg"
        )
    )
}
