# Supabase and error handling

App infrastructure is organized under `app/data/retrofit` (client module and `interceptor` package), `app/data/supabase` (client factory and module), and `app/di` (root Koin module). `ShowcaseApplication` loads the root app module and feature modules.

Configure unquoted `supabaseUrl` and `supabaseAnonKey` in your user Gradle properties or with `-P`. The client is lazy: existing Last.fm screens run without Supabase configuration. Only use the public anon/publishable key in Android; enforce access with RLS. Never bundle a service-role key.

The app registers Auth, Postgrest, Realtime and Storage via Koin. Feature Data modules using SDK types must declare their own Supabase BOM/module dependencies. Inject `SupabaseClient` into the data source and wrap both the request and decoding:

```kotlin
suspend fun load(): Result<List<Item>> = supabaseResult {
    client.from("items").select().decodeList<Item>()
}
```

Domain knows only `Result` and `AppError`. `dataResult` catches Exceptions at the repository/data boundary, classifies HTTP/Supabase and IO errors, and rethrows CancellationException. JVM Errors propagate. DB/decoding errors become Unknown. Do not use runCatching around suspend calls without preserving cancellation.

Presentation maps errors to English/Vietnamese resources. Album screens retain operation parameters and offer a dialog plus a persistent retry button for Network/Server. Unauthorized requires reauthentication or permission changes; Unknown does not blindly retry. Dismissal survives configuration changes, and a failed retry creates a new error screen/dialog. No automatic retry is applied to writes.

Album repositories use cache only for network failures; empty list cache produces Network failure. HTTP errors do not fall back to cache. Retrofit delivers every HTTP status including 5xx and preserves cancellation.
