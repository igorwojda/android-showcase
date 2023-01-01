package com.igorwojda.showcase.base.presentation.activity

import android.os.Bundle
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import timber.log.Timber

abstract class BaseActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId) {

    companion object {
        val Fragment.mainActivity: BaseActivity
            get() = activity as BaseActivity

        const val DESTINATION_ALBUM_LIST_LABEL = "AlbumListFragment"
    }

    /** Declaring the app bar components in the BaseActivity to be able to access them from other fragments and avoid cyclic dependencies*/
    var appBarLayout: LinearLayout? = null
    var mainAppToolbar: MaterialToolbar? = null
    var searchTextInputEditText: TextInputEditText? = null
    var searchLayout: LinearLayoutCompat? = null
    var searchTextInputLayout: TextInputLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // The window will not be resized when virtual keyboard is shown (bottom navigation bar will be
        // hidden under virtual keyboard)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        Timber.d("onCreate ${javaClass.simpleName}")
    }
}
