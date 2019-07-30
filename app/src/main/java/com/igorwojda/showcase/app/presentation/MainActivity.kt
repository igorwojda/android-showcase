package com.igorwojda.showcase.app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import com.igorwojda.showcase.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_common1.*

class MainActivity : AppCompatActivity() {

//    override val layoutResourceId = R.layout.activity_main

//    private val albumGateway: AlbumGateway by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setupBottomNavigation()

//        albumGateway.navigateToAlbumSearch(this)

        if (savedInstanceState == null) {
            replaceContainer(BlogFragment())
        }
    }

    private fun setupBottomNavigation() {
        val selectItem = true
        val dontSelectItem = false

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottomMenuHome -> {
                    replaceContainer(BlogFragment())
                    selectItem
                }
                R.id.bottomMenuFavourites -> {
                    replaceContainer(ChapterFragment())
                    selectItem
                }
                R.id.bottomMenuProfile -> {
                    replaceContainer(StoreFragment())
//                    toast(R.string.coming_soon)
                    selectItem // don't select item
                }
                else -> {
                    dontSelectItem
                }
            }
        }
    }

    protected fun replaceContainer(fragment: Fragment) {
        supportFragmentManager.transaction { replace(R.id.container2, fragment) }
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
