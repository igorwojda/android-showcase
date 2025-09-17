package com.igorwojda.showcase.feature.album.data.mapper

import com.igorwojda.showcase.feature.album.data.datasource.api.model.ImageApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.ImageSizeApiModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.ImageRoomModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.ImageSizeRoomModel
import com.igorwojda.showcase.feature.album.domain.enum.ImageSize
import com.igorwojda.showcase.feature.album.domain.model.Image
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ImageMapperTest {
    private val imageSizeMapper: ImageSizeMapper = mockk()
    private val sut = ImageMapper(imageSizeMapper)

    @Test
    fun `apiToDomain maps image correctly`() {
        // given
        val apiModel = ImageApiModel("https://example.com/image.jpg", ImageSizeApiModel.LARGE)
        every { imageSizeMapper.apiToDomain(ImageSizeApiModel.LARGE) } returns ImageSize.LARGE

        // when
        val result = sut.apiToDomain(apiModel)

        // then
        result shouldBeEqualTo Image("https://example.com/image.jpg", ImageSize.LARGE)
    }

    @Test
    fun `apiToRoom maps image correctly`() {
        // given
        val apiModel = ImageApiModel("https://example.com/image.jpg", ImageSizeApiModel.LARGE)
        every { imageSizeMapper.apiToRoom(ImageSizeApiModel.LARGE) } returns ImageSizeRoomModel.LARGE

        // when
        val result = sut.apiToRoom(apiModel)

        // then
        result shouldBeEqualTo ImageRoomModel("https://example.com/image.jpg", ImageSizeRoomModel.LARGE)
    }

    @Test
    fun `roomToDomain maps image correctly`() {
        // given
        val roomModel = ImageRoomModel("https://example.com/image.jpg", ImageSizeRoomModel.LARGE)
        every { imageSizeMapper.roomToDomain(ImageSizeRoomModel.LARGE) } returns ImageSize.LARGE

        // when
        val result = sut.roomToDomain(roomModel)

        // then
        result shouldBeEqualTo Image("https://example.com/image.jpg", ImageSize.LARGE)
    }
}
