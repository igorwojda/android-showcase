Project Analysis:
- Architecture: Clean Architecture with modular design, MVVM+MVI patterns
- Tech Stack: Kotlin 2.2+, Jetpack Compose, Koin DI, Retrofit, Room, etc.
- Build System: Convention plugins, version catalogs, type-safe accessors

Key Standards:
- SOLID principles and Clean Architecture enforcement
- Centralized logging with LogTags object
- Lazy initialization for performance optimization
- Immutable state management with Compose
- Modular feature-based architecture

Development Practices:
- Convention plugins for shared build logic
- Comprehensive code quality checks (detekt, spotless, konsist)
- Automated CI/CD with GitHub Actions
- Keep README up to date

General:
- Don’t hardcode colors (use colors defined in application theme)
- Create Previews for composable
- Create or update unit tests
- Add dependencies should be managed by gradle versions catalog (defined in gradle\libs.versions.toml file)
- Add new files to git repo
- After making changes verify if gradle build succeeds
- After making changes verify if quality checks are passing (spotless, detekt, konsist, unit tests)
- Don’t hardcore dependency versions. Retrieve Gradle plugin and dependency version form toml file
- Always add new files to existing repository
