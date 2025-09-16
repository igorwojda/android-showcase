package com.igorwojda.showcase.feature.album.data.mapper

import com.igorwojda.showcase.feature.album.data.datasource.api.model.ImageSizeApiModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.ImageSizeRoomModel
import com.igorwojda.showcase.feature.album.domain.enum.ImageSize
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ImageSizeMapperTest {
    private val cut = ImageSizeMapper()

    @Test
    fun `apiToDomain maps image size correctly`() {
        // when & then
        cut.apiToDomain(ImageSizeApiModel.LARGE) shouldBeEqualTo ImageSize.LARGE
        cut.apiToDomain(ImageSizeApiModel.MEDIUM) shouldBeEqualTo ImageSize.MEDIUM
        cut.apiToDomain(ImageSizeApiModel.SMALL) shouldBeEqualTo ImageSize.SMALL
    }

    @Test
    fun `apiToRoom maps image size correctly`() {
        // when & then
        cut.apiToRoom(ImageSizeApiModel.LARGE) shouldBeEqualTo ImageSizeRoomModel.LARGE
        cut.apiToRoom(ImageSizeApiModel.MEDIUM) shouldBeEqualTo ImageSizeRoomModel.MEDIUM
        cut.apiToRoom(ImageSizeApiModel.SMALL) shouldBeEqualTo ImageSizeRoomModel.SMALL
    }

    @Test
    fun `roomToDomain maps image size correctly`() {
        // when & then
        cut.roomToDomain(ImageSizeRoomModel.LARGE) shouldBeEqualTo ImageSize.LARGE
        cut.roomToDomain(ImageSizeRoomModel.MEDIUM) shouldBeEqualTo ImageSize.MEDIUM
        cut.roomToDomain(ImageSizeRoomModel.SMALL) shouldBeEqualTo ImageSize.SMALL
    }
}
