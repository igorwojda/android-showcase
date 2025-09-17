package com.igorwojda.showcase.feature.album.data.mapper

import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.ImageApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.ImageSizeApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.TagApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.TagListApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.TrackApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.TrackListApiModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumRoomModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.ImageRoomModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.ImageSizeRoomModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.TagRoomModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.TrackRoomModel
import com.igorwojda.showcase.feature.album.domain.enum.ImageSize
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.model.Image
import com.igorwojda.showcase.feature.album.domain.model.Tag
import com.igorwojda.showcase.feature.album.domain.model.Track
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class AlbumMapperTest {
    private val imageMapper: ImageMapper = mockk()
    private val trackMapper: TrackMapper = mockk()
    private val tagMapper: TagMapper = mockk()

    private val sut =
        AlbumMapper(
            imageMapper,
            trackMapper,
            tagMapper,
        )

    @Test
    fun `apiToRoom maps album correctly`() {
        // given
        val apiModel =
            AlbumApiModel(
                mbId = "test-id",
                name = "Test Album",
                artist = "Test Artist",
                images =
                    listOf(
                        ImageApiModel("https://example.com/image.jpg", ImageSizeApiModel.LARGE),
                    ),
                tracks =
                    TrackListApiModel(
                        listOf(TrackApiModel("Track 1", 180)),
                    ),
                tags =
                    TagListApiModel(
                        listOf(TagApiModel("rock")),
                    ),
            )

        every { imageMapper.apiToRoom(any()) } returns ImageRoomModel("https://example.com/image.jpg", ImageSizeRoomModel.LARGE)
        every { trackMapper.apiToRoom(any()) } returns TrackRoomModel("Track 1", 180)
        every { tagMapper.apiToRoom(any()) } returns TagRoomModel("rock")

        // when
        val result = sut.apiToRoom(apiModel)

        // then
        result shouldBeEqualTo
            AlbumRoomModel(
                mbId = "test-id",
                name = "Test Album",
                artist = "Test Artist",
                images = listOf(ImageRoomModel("https://example.com/image.jpg", ImageSizeRoomModel.LARGE)),
                tracks = listOf(TrackRoomModel("Track 1", 180)),
                tags = listOf(TagRoomModel("rock")),
            )
    }

    @Test
    fun `apiToDomain maps album correctly`() {
        // given
        val apiModel =
            AlbumApiModel(
                mbId = "test-id",
                name = "Test Album",
                artist = "Test Artist",
                images =
                    listOf(
                        ImageApiModel("https://example.com/image.jpg", ImageSizeApiModel.LARGE),
                    ),
                tracks =
                    TrackListApiModel(
                        listOf(TrackApiModel("Track 1", 180)),
                    ),
                tags =
                    TagListApiModel(
                        listOf(TagApiModel("rock")),
                    ),
            )

        every { imageMapper.apiToDomain(any()) } returns Image("https://example.com/image.jpg", ImageSize.LARGE)
        every { trackMapper.apiToDomain(any()) } returns Track("Track 1", 180)
        every { tagMapper.apiToDomain(any()) } returns Tag("rock")

        // when
        val result = sut.apiToDomain(apiModel)

        // then
        result shouldBeEqualTo
            Album(
                mbId = "test-id",
                name = "Test Album",
                artist = "Test Artist",
                images = listOf(Image("https://example.com/image.jpg", ImageSize.LARGE)),
                tracks = listOf(Track("Track 1", 180)),
                tags = listOf(Tag("rock")),
            )
    }

    @Test
    fun `roomToDomain maps album correctly`() {
        // given
        val roomModel =
            AlbumRoomModel(
                id = 1,
                mbId = "test-id",
                name = "Test Album",
                artist = "Test Artist",
                images = listOf(ImageRoomModel("https://example.com/image.jpg", ImageSizeRoomModel.LARGE)),
                tracks = listOf(TrackRoomModel("Track 1", 180)),
                tags = listOf(TagRoomModel("rock")),
            )

        every { imageMapper.roomToDomain(any()) } returns Image("https://example.com/image.jpg", ImageSize.LARGE)
        every { trackMapper.roomToDomain(any()) } returns Track("Track 1", 180)
        every { tagMapper.roomToDomain(any()) } returns Tag("rock")

        // when
        val result = sut.roomToDomain(roomModel)

        // then
        result shouldBeEqualTo
            Album(
                name = "Test Album",
                artist = "Test Artist",
                mbId = "test-id",
                images = listOf(Image("https://example.com/image.jpg", ImageSize.LARGE)),
                tracks = listOf(Track("Track 1", 180)),
                tags = listOf(Tag("rock")),
            )
    }
}
