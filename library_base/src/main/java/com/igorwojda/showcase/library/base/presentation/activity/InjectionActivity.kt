package com.igorwojda.showcase.library.base.presentation.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.igorwojda.showcase.library.base.BuildConfig
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.kodein
import org.kodein.di.android.retainedKodein
import org.kodein.di.generic.kcontext

abstract class InjectionActivity : AppCompatActivity(), KodeinAware {

    private val parentKodein by kodein()

    @SuppressWarnings("LeakingThisInConstructor")
    final override val kodeinContext = kcontext<AppCompatActivity>(this)

    // Using retainedKodein will not recreate Kodein when the Activity restarts
    final override val kodein: Kodein by retainedKodein {
        extend(parentKodein)
    }

    /*
    Dependency resolution for debug builds:
    By defining kodeinTrigger we can eagerly retrieve all dependencies in onCreate method. This allow us to be sure
    that all dependencies have correctly been retrieved (there were no non-declared dependencies and no dependency
    loops)

    Dependency resolution for release builds:
    By not using kodeinTrigger all dependencies will be resolved lazily. This allow to save some resources and speed up
    the application by retrieving dependencies only when they are needed/used.

    More:
    https://github.com/Kodein-Framework/Kodein-DI/blob/master/doc/android.adoc#using-a-trigger
    http://kodein.org/Kodein-DI/?latest/android#_using_a_trigger

     */
    final override val kodeinTrigger: KodeinTrigger? by lazy {
        if (BuildConfig.DEBUG) KodeinTrigger() else super.kodeinTrigger
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        kodeinTrigger?.trigger()
    }
}
