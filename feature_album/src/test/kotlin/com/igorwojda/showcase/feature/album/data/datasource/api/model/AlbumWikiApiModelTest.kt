package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.DataFixtures
import com.igorwojda.showcase.feature.album.domain.model.AlbumWiki
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class AlbumWikiApiModelTest {

    @Test
    fun `map to AlbumWikiDomainModel`() {
        // given
        val published = "published"
        val summary = "summary"
        val cut = DataFixtures.ApiModel.getAlbumWiki(published, summary)

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel shouldBeEqualTo AlbumWiki(published, summary)
    }
}
