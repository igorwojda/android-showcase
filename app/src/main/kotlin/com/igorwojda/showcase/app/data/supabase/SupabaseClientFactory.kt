package com.igorwojda.showcase.app.data.supabase

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage

/**
 * Factory that creates and configures the [SupabaseClient] singleton.
 *
 * Install only the modules your app actually needs.
 * Unused modules can be removed to reduce APK size and initialization time.
 */
object SupabaseClientFactory {
    fun create(
        supabaseUrl: String,
        supabaseAnonKey: String,
    ): SupabaseClient =
        createSupabaseClient(supabaseUrl, supabaseAnonKey) {
            install(Postgrest)
            install(Auth)
            install(Realtime)
            install(Storage)
        }
}
