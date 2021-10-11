package com.igorwojda.showcase.feature.album.data.network.model

import com.igorwojda.showcase.feature.album.data.DataFixtures
import com.igorwojda.showcase.feature.album.domain.model.AlbumWiki
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class AlbumWikiNetworkDataModelTest {

    @Test
    fun `maps to AlbumWikiDomainModel`() {
        // given
        val published = "published"
        val summary = "summary"
        val cut = DataFixtures.getAlbumWikiDataModel(published, summary)

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel shouldBeEqualTo AlbumWiki(published, summary)
    }
}
