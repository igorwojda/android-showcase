package com.igorwojda.lastfm.feature.album.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.base.presentation.extension.observeNotNull
import com.igorwojda.minimercari.feature.base.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_album_detail.*
import org.kodein.di.generic.instance
import timber.log.Timber
import kotlin.reflect.KProperty

class AlbumDetailFragment : BaseFragment() {
    companion object {
        fun newInstance(albumId: String) = AlbumDetailFragment().apply {
            this.albumId = albumId
        }
    }

    override val layoutResourceId = R.layout.fragment_album_detail

    private var albumId by FragmentArgumentDelegate<String>()

    private val viewModelFactory: AlbumDetailsViewModelFactory by instance(arg = "2")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.e("AAA AlbumDetailFragment albumId $albumId")
        super.onViewCreated(view, savedInstanceState)

        ViewModelProviders.of(this, viewModelFactory).get(AlbumDetailsViewModel::class.java).apply {
            observeNotNull(albumLiveData, ::onAlbumLiveData)
        }
    }

    private fun onAlbumLiveData(albumDomainModel: AlbumDomainModel) {
        albumIdTextView.text = albumDomainModel.id.toString()
        albumTitleTextView.text = albumDomainModel.title
    }
}

class FragmentArgumentDelegate<T : Any> : kotlin.properties.ReadWriteProperty<Fragment, T> {
    var value: T? = null

    override operator fun getValue(thisRef: Fragment, property: kotlin.reflect.KProperty<*>): T {
        if (value == null) {
            val args = thisRef.arguments
                ?: throw IllegalStateException("Cannot read property ${property.name} if no arguments have been set")
            @Suppress("UNCHECKED_CAST")
            value = args[property.name] as T
        }
        return value ?: throw IllegalStateException("Property ${property.name} could not be read")
    }

//    override operator fun setValue(fragment: Fragment, property: KProperty<*>, value: T) {
//        val key = property.name
//        fragment.arguments = (fragment.arguments ?: Bundle()).also { it.putAll(bundleOf(key to value)) }
//    }

    override operator fun setValue(fragment: Fragment, property: KProperty<*>, value: T) {
        val key = property.name
        val args = fragment.arguments ?: Bundle()
        args.putAll(bundleOf(key to value))
        fragment.arguments = args
    }
}
