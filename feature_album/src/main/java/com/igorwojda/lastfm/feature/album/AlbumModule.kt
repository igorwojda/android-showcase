package com.igorwojda.lastfm.feature.album

import com.igorwojda.lastfm.feature.album.data.di.albumDataModule
import com.igorwojda.lastfm.feature.album.domain.di.albumDomainModule
import com.igorwojda.lastfm.feature.album.presentation.di.albumPresentationModule
import org.kodein.di.Kodein

val albumModule = Kodein.Module("albumModule") {
    import(albumPresentationModule)
    import(albumDomainModule)
    import(albumDataModule)
}
