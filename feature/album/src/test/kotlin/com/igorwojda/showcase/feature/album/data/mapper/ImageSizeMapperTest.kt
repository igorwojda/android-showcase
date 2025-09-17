package com.igorwojda.showcase.feature.album.data.mapper

import com.igorwojda.showcase.feature.album.data.datasource.api.model.ImageSizeApiModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.ImageSizeRoomModel
import com.igorwojda.showcase.feature.album.domain.enum.ImageSize
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ImageSizeMapperTest {
    private val sut = ImageSizeMapper()

    @Test
    fun `apiToDomain maps image size correctly`() {
        // when & then
        sut.apiToDomain(ImageSizeApiModel.LARGE) shouldBeEqualTo ImageSize.LARGE
        sut.apiToDomain(ImageSizeApiModel.MEDIUM) shouldBeEqualTo ImageSize.MEDIUM
        sut.apiToDomain(ImageSizeApiModel.SMALL) shouldBeEqualTo ImageSize.SMALL
    }

    @Test
    fun `apiToRoom maps image size correctly`() {
        // when & then
        sut.apiToRoom(ImageSizeApiModel.LARGE) shouldBeEqualTo ImageSizeRoomModel.LARGE
        sut.apiToRoom(ImageSizeApiModel.MEDIUM) shouldBeEqualTo ImageSizeRoomModel.MEDIUM
        sut.apiToRoom(ImageSizeApiModel.SMALL) shouldBeEqualTo ImageSizeRoomModel.SMALL
    }

    @Test
    fun `roomToDomain maps image size correctly`() {
        // when & then
        sut.roomToDomain(ImageSizeRoomModel.LARGE) shouldBeEqualTo ImageSize.LARGE
        sut.roomToDomain(ImageSizeRoomModel.MEDIUM) shouldBeEqualTo ImageSize.MEDIUM
        sut.roomToDomain(ImageSizeRoomModel.SMALL) shouldBeEqualTo ImageSize.SMALL
    }
}
