package com.igorwojda.lastfm.feature.album

import com.igorwojda.lastfm.feature.album.data.albumDataModule
import com.igorwojda.lastfm.feature.album.domain.albumDomainModule
import com.igorwojda.lastfm.feature.album.presentation.albumPresentationModule
import org.kodein.di.Kodein

val albumModule = Kodein.Module("albumModule") {
    import(albumPresentationModule)
    import(albumDomainModule)
    import(albumDataModule)
}
