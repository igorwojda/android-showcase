package com.igorwojda.showcase.feature.album.data.model

import com.igorwojda.showcase.feature.album.data.DataFixtures
import com.igorwojda.showcase.feature.album.data.model.network.toDomainModel
import com.igorwojda.showcase.feature.album.domain.model.AlbumWikiDomainModel
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class AlbumWikiDataModelTest {

    @Test
    fun `maps to AlbumWikiDomainModel`() {
        // given
        val published = "published"
        val summary = "summary"
        val cut = DataFixtures.getAlbumWikiDataModel(published, summary)

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel shouldBeEqualTo AlbumWikiDomainModel(published, summary)
    }
}
