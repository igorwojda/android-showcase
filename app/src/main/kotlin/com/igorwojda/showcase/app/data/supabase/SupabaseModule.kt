package com.igorwojda.showcase.app.data.supabase

import com.igorwojda.showcase.app.BuildConfig
import org.koin.dsl.module

/**
 * Koin module responsible for initializing the Supabase client.
 * Provides: SupabaseClient singleton configured with project URL and anon key.
 */
val supabaseModule =
    module {
        single {
            SupabaseClientFactory.create(
                supabaseUrl = BuildConfig.GRADLE_SUPABASE_URL,
                supabaseAnonKey = BuildConfig.GRADLE_SUPABASE_ANON_KEY,
            )
        }
    }
