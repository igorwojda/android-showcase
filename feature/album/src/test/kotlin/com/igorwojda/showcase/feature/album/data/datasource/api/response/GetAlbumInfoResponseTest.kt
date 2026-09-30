package com.igorwojda.showcase.feature.album.data.datasource.api.response

import com.igorwojda.showcase.feature.album.data.datasource.api.model.TagApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.TagListApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.TrackApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.TrackListApiModel
import kotlinx.serialization.json.Json
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class GetAlbumInfoResponseTest {
    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun `deserializes track array as track list`() {
        // given
        val body = """{"album": {"name": "album", "artist": "artist", "tracks": {"track": [{"name": "track1"}, {"name": "track2"}]}}}"""

        // when
        val album = json.decodeFromString<GetAlbumInfoResponse>(body).album

        // then
        album.tracks shouldBeEqualTo TrackListApiModel(listOf(TrackApiModel("track1"), TrackApiModel("track2")))
    }

    @Test
    fun `deserializes tag array as tag list`() {
        // given
        val body = """{"album": {"name": "album", "artist": "artist", "tags": {"tag": [{"name": "tag1"}, {"name": "tag2"}]}}}"""

        // when
        val album = json.decodeFromString<GetAlbumInfoResponse>(body).album

        // then
        album.tags shouldBeEqualTo TagListApiModel(listOf(TagApiModel("tag1"), TagApiModel("tag2")))
    }

    @Test
    fun `deserializes single track object as track list`() {
        // given
        val body = """{"album": {"name": "album", "artist": "artist", "tracks": {"track": {"name": "track", "duration": 1}}}}"""

        // when
        val album = json.decodeFromString<GetAlbumInfoResponse>(body).album

        // then
        album.tracks shouldBeEqualTo TrackListApiModel(listOf(TrackApiModel("track", 1)))
    }

    @Test
    fun `deserializes single tag object as tag list`() {
        // given
        val body = """{"album": {"name": "album", "artist": "artist", "tags": {"tag": {"name": "tag"}}}}"""

        // when
        val album = json.decodeFromString<GetAlbumInfoResponse>(body).album

        // then
        album.tags shouldBeEqualTo TagListApiModel(listOf(TagApiModel("tag")))
    }

    @Test
    fun `deserializes empty string tracks as empty track list`() {
        // given
        val body = """{"album": {"name": "album", "artist": "artist", "tracks": ""}}"""

        // when
        val album = json.decodeFromString<GetAlbumInfoResponse>(body).album

        // then
        album.tracks shouldBeEqualTo TrackListApiModel(emptyList())
    }

    @Test
    fun `deserializes empty string tags as empty tag list`() {
        // given
        val body = """{"album": {"name": "album", "artist": "artist", "tags": ""}}"""

        // when
        val album = json.decodeFromString<GetAlbumInfoResponse>(body).album

        // then
        album.tags shouldBeEqualTo TagListApiModel(emptyList())
    }
}
