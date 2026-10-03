package com.igorwojda.showcase.app.di

import com.igorwojda.showcase.app.data.retrofit.retrofitModule
import com.igorwojda.showcase.app.data.supabase.supabaseModule
import org.koin.dsl.module

/** Aggregates app infrastructure; features are registered by ShowcaseApplication. */
val appModule =
    module {
        includes(retrofitModule)
        includes(supabaseModule)
    }
