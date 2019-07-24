package com.igorwojda.showcase.feature.album

import android.content.Context
import com.igorwojda.showcase.app.gateway.AlbumGateway
import com.igorwojda.showcase.feature.album.presentation.albumsearch.AlbumSearchActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

object AlbumGatewayImpl : AlbumGateway, KodeinAware {

    override val kodein by Kodein.lazy {
        import(albumModule)
    }

    override fun navigateToAlbumSearch(context: Context) {
        kodeinTrigger?.trigger()
        AlbumSearchActivity.start(context)
    }
}
