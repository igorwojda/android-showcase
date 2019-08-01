package com.igorwojda.showcase.app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import com.igorwojda.base.presentation.activity.BaseActivity
import com.igorwojda.showcase.R
import com.igorwojda.showcase.app.gateway.AlbumGateway
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_common1.*
import org.kodein.di.generic.instance

class MainActivity : BaseActivity() {

    override val layoutResourceId = R.layout.activity_main

    private val albumGateway: AlbumGateway by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBottomNavigation()

        if (savedInstanceState == null) {
            replaceMenuContainer(albumGateway.createAlbumSearchFragment())
        }
    }

    private fun setupBottomNavigation() {
        val selectItem = true

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottomMenuHome -> {
                    replaceMenuContainer(BlogFragment())
                    selectItem
                }
                R.id.bottomMenuFavourites -> {
                    replaceMenuContainer(ChapterFragment())
                    selectItem
                }
                R.id.bottomMenuProfile -> {
                    replaceMenuContainer(StoreFragment())
                    selectItem
                }
                else -> {
                    !selectItem
                }
            }
        }
    }

    private fun replaceMenuContainer(fragment: Fragment) {
        supportFragmentManager.transaction { replace(R.id.menuContainer, fragment) }
    }
}

class BlogFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_common1, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvCommon.text = "Store Fragment"
        commonLayout.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
    }
}


class ChapterFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_common1, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        tvCommon.text = "Chapter Fragment"
        commonLayout.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))

        super.onActivityCreated(savedInstanceState)
    }
}


class StoreFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_common1, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvCommon.text = "Blog Fragment"
        commonLayout.setBackgroundColor(resources.getColor(android.R.color.holo_orange_dark))

    }
}
