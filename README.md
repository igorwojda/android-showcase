# 💎 Android Showcase 2.0

[![Kotlin Version](https://img.shields.io/badge/Kotlin-1.7.x-blue.svg)](https://kotlinlang.org)
[![AGP](https://img.shields.io/badge/AGP-7.x-blue?style=flat)](https://developer.android.com/studio/releases/gradle-plugin)
[![Gradle](https://img.shields.io/badge/Gradle-7.x-blue?style=flat)](https://gradle.org)

[![codebeat badge](https://codebeat.co/badges/e9f1a825-b5bd-4c7a-aadc-7c8d0cf59310)](https://codebeat.co/projects/github-com-igorwojda-android-showcase-main)
[![CodeFactor](https://www.codefactor.io/repository/github/igorwojda/android-showcase/badge)](https://www.codefactor.io/repository/github/igorwojda/android-showcase)

Android Showcase project presents a modern approach to
[Android](https://en.wikipedia.org/wiki/Android_(operating_system)) application development. It is a complete sample of
a fully functional Android application.

Project is utilizing modular, scalable, maintainable, and testable [architecture](#architecture), leading
[tech-stack](Tech-stack) and demonstrates the best development practices.

This application may look simple, but it has all the pieces that will provide the rock-solid foundation for the larger
application suitable for bigger teams during extended
[application lifecycle](https://en.wikipedia.org/wiki/Application_lifecycle_management).

This project is being maintained to stay up to date with leading industry standards. Please check
the [CONTRIBUTING](CONTRIBUTING.md) page if you want to help.

## Application Scope

The `android-showcase` displays information about music albums. The data is loaded from
the [Last.fm Music Discovery API](https://www.last.fm/api).

The app has a few screens located in multiple feature modules:

- Album list screen - displays list of albums
- Album detail screen - display information about the selected album
- Profile screen - empty (WiP)
- Favourites screen - empty (WiP)
<br/><br/>
<img src="misc/image/application_anim.gif" width="336" hspace="20">

## Tech-Stack

This project takes advantage of best practices, and many popular libraries and tools in the Android ecosystem. Most of
the libraries are in the stable version unless there is a good reason to use non-stable dependency.

* Tech-stack
  * [100% Kotlin](https://kotlinlang.org/)
    + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations
    + [Kotlin Flow](https://kotlinlang.org/docs/flow.html) - data flow across all app layers, including views
    + [Kotlin Symbol Processing](https://kotlinlang.org/docs/ksp-overview.html) - enable compiler plugins
    + [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html) - parse [JSON](https://www.json.org/json-en.html)
  * [Retrofit](https://square.github.io/retrofit/) - networking
  * [Jetpack](https://developer.android.com/jetpack)
    * [Compose](https://developer.android.com/jetpack/compose) - modern, native UI kit 
    * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - in-app navigation
    * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when
      lifecycle state changes
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related
      data in a lifecycle-aware way
    * [Room](https://developer.android.com/jetpack/androidx/releases/room) - store offline cache
  * [Koin](https://insert-koin.io/) - dependency injection (dependency retrieval)
  * [Coil](https://github.com/coil-kt/coil) - image loading library
  * [Lottie](http://airbnb.io/lottie) - animation library
* Modern Architecture
  * Clean Architecture (at feature module level)
  * Single activity architecture
    using [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
  * MVVM + MVI (presentation layer)
  * [Android Architecture components](https://developer.android.com/topic/libraries/architecture)
    ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    , [Kotlin Flow](https://kotlinlang.org/docs/flow.html)
    , [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
  * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions
* UI
  * [JetPack Compose](https://developer.android.com/jetpack/compose) - modern, native UI kit
  * [Material design](https://material.io/design)
  * Reactive UI
* CI
  * [GitHub Actions](https://github.com/features/actions)
  * Automatic PR verification including tests, linters, and 3rd online tools
* Testing
  * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit 5](https://junit.org/junit5/) via
    [android-junit5](https://github.com/mannodermaus/android-junit5))
  * [UI Tests](https://en.wikipedia.org/wiki/Graphical_user_interface_testing) ([Espresso](https://developer.android.com/training/testing/espresso))
  * [Mockk](https://mockk.io/) - mocking framework
  * [Kluent](https://github.com/MarkusAmshove/Kluent) - assertion framework
* Static analysis tools
  * [Ktlint](https://github.com/pinterest/ktlint) - verify code formatting
  * [Detekt](https://github.com/arturbosch/detekt#with-gradle) - verify code complexity and code smells
  * [Androd Lint](http://tools.android.com/tips/lint) - verify Android platform usage
* Gradle
  * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - define build scripts
  * Custom tasks
  * [Gradle Plugins](https://plugins.gradle.org/)
    * [Android Gradle](https://developer.android.com/studio/releases/gradle-plugin) - standard Android Plugins
    * [Test Logger](https://github.com/radarsh/gradle-test-logger-plugin) - format test logs
    * [SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args) - pass data between navigation destinations
    * [Android-junit5](https://github.com/mannodermaus/android-junit5) - use [JUnit 5](https://junit.org/junit5/) with Android
  * [Versions catalog](https://docs.gradle.org/current/userguide/platforms.html#sub:version-catalog) - define dependencies
  * [Type safe accessors](https://docs.gradle.org/7.0/release-notes.html)
* GitHub Apps
  * [Revonate](https://github.com/renovatebot/renovate) - dependency update boot

## Architecture

By dividing a problem into smaller and easier to solve sub-problems, we can reduce the complexity of designing and
maintaining a large system. Each module is independent build-block serving a clear purpose. We can think about each
feature as the reusable component, equivalent of [microservice](https://en.wikipedia.org/wiki/Microservices) or private
library.

The modularized code-base approach provides a few benefits:

- reusability - enable code sharing and building multiple apps from the same foundation. Apps should be a sum of their
- features where the features are organized as separate modules.
- [separation of concerns](https://en.wikipedia.org/wiki/Separation_of_concerns) - each module has a clear API.
  Feature-related classes live in different modules and can't be referenced without explicit module dependency. We
  strictly control what is exposed to other parts of your codebase.
- features can be developed in parallel eg. by different teams
- each feature can be developed in isolation, independently from other features
- faster build time

### Module Types And Module Dependencies

This diagram presents dependencies between project modules (Gradle sub-projects).

![module_dependencies](https://github.com/igorwojda/android-showcase/blob/main/misc/image/module_dependencies.png?raw=true)

We have three kinds of modules in the application:

- `app` module - this is the main module. It contains code that wires multiple modules together (dependency injection
  setup, `NavHostActivity`, etc.) and fundamental application configuration (retrofit configuration, required
  permissions setup, custom application class, etc.).
- application-specific `library_x` modules that some of the features could depend on. This is helpful if you want to
  share some assets or code only between a few feature modules (currently app has no such modules)
- feature modules - the most common type of module containing all code related to a given feature.

### Feature Module Structure

`Clean architecture` is the "core architecture" of the application, so each `feature module` contains its own set of
Clean architecture layers:

![module_dependencies_layers](https://github.com/igorwojda/android-showcase/blob/main/misc/image/module_dependencies_layers.png?raw=true)

> Notice that the `app` module and `library_x` modules structure differs a bit from the feature module structure.

Each feature module contains non-layer components and 3 layers with a distinct set of responsibilities.

![feature_structure](https://github.com/igorwojda/android-showcase/blob/main/misc/image/feature_structure.png?raw=true)

#### Presentation Layer

This layer is closest to what the user sees on the screen. The `presentation` layer is a mix of `MVVM` (
Jetpack `ViewModel` used to preserve data across activity restart) and
`MVI` (`actions` modify the `common state` of the view and then a new state is edited to a view via `LiveData` to be
rendered).

> `common state` (for each view) approach derives from
> [Unidirectional Data Flow](https://en.wikipedia.org/wiki/Unidirectional_Data_Flow_(computer_science)) and [Redux
> principles](https://redux.js.org/introduction/three-principles).

Components:

- **View (Fragment)** - presents data on the screen and passes user interactions to View Model. Views are hard to test,
  so they should be as simple as possible.
- **ViewModel** - dispatches (through [Kotlin Flow](https://kotlinlang.org/docs/flow.html)) state changes to the view
  and deals with user interactions (these view models are not simply
  [POJO classes](https://en.wikipedia.org/wiki/Plain_old_Java_object)).
- **ViewState** - common state for a single view
- **NavManager** - singleton that facilitates handling all navigation events inside `NavHostActivity` (instead of
  separately, inside each view)

#### Domain Layer

This is the core layer of the application. Notice that the `domain` layer is independent of any other layers. This
allows making domain models and business logic independent from other layers. In other words, changes in other layers
will not affect `domain` layer eg. changing the database (`data` layer) or screen UI (`presentation` layer) ideally will
not result in any code change withing the `domain` layer.

Components:

- **UseCase** - contains business logic
- **DomainModel** - defines the core structure of the data that will be used within the application. This is the source
  of truth for application data.
- **Repository interface** - required to keep the `domain` layer independent from
  the `data layer` ([Dependency inversion](https://en.wikipedia.org/wiki/Dependency_inversion_principle)).

#### Data Layer

Manages application data. Connect to data sources and provide data through repository to the `domain` layer eg. retrieve
data from the internet and cache the data in disk cache (when device is offline).

Components:

- **Repository** is exposing data to the `domain` layer. Depending on the application structure and quality of the
  external APIs repository can also merge, filter, and transform the data. These operations intend to create
  high-quality data source for the `domain` layer, not to perform any business logic (`domain` layer `use case`
  responsibility).

- **Mapper** - maps `data model` to `domain model` (to keep `domain` layer independent from the `data` layer).
- **RetrofitService** - defines a set of API endpoints.
- **DataModel** - defines the structure of the data retrieved from the network and contains annotations, so Retrofit (
  Moshi) understands how to parse this network data (XML, JSON, Binary...) this data into objects.

### Data Flow

The below diagram presents application data flow when a user interacts with the `album list screen`:

![app_data_flow](https://github.com/igorwojda/android-showcase/blob/main/misc/image/app_data_flow.png?raw=true)

## Dependency Management

Gradle [versions catalog](https://docs.gradle.org/current/userguide/platforms.html#sub:version-catalog) is used as a
centralized dependency management third-party dependency coordinates (group, artifact, version) are shared across all
modules (Gradle projects and subprojects).

All of the dependencies are stored in the [libs.versions.toml](./gradle/libs.versions.toml) file (default location).
The [TOML](https://toml.io/en/) file consists of a few major sections:

- `[versions]` - declare versions that can be referenced by all dependencies
- `[libraries]` - declare the aliases to library coordinates
- `[bundles]` - declare dependency bundles (groups)
- `[plugins]` - declare Gradle plugin dependencies

Each feature module depends on the `feature_base` module, so dependencies are shared without the need to add them
explicitly in each feature module.

Project enables the `TYPESAFE_PROJECT_ACCESSORS` experimental Gradle feature to generate type safe accessors to refer
other projects.

```kotlin
// Before
implementation(project(":feature_album"))

// After
implementation(projects.featureAlbum)
```

## CI Pipeline

CI is utilizing [GitHub Actions](https://github.com/features/actions). Complete GitHub Actions config is located in
the [.github/workflows](.github/workflows) folder.

### PR Verification

Series of workflows run (in parallel) for every opened PR and after merging PR to the `main` branch:

* `./gradlew lintDebug` - runs Android lint
* `./gradlew detektCheck` - runs detekt and ktlint
* `./gradlew testDebugUnitTest` - run unit tests
* `./gradlew connectedCheck` - run UI tests
* `./gradlew :app:bundleDebug` - create app bundle

## Design Decisions

Read related articles to have a better understanding of underlying design decisions and various trade-offs.

* [Multiple ways of defining Clean Architecture layers](https://proandroiddev.com/multiple-ways-of-defining-clean-architecture-layers-bbb70afa5d4a)
* More coming soon

## Gradle Update

`./gradlew wrapper --gradle-version=1.2.3`

## What This Project Does Not Cover?

The interface of the app utilizes some of the modern material design components, however, is deliberately kept simple to
focus on application architecture and project config.

## Upcoming Improvements

Checklist of all
upcoming [enhancements](https://github.com/igorwojda/android-showcase/issues?q=is%3Aissue+is%3Aopen+sort%3Aupdated-desc+label%3Aenhancement)
.

## Getting Started

There are a few ways to open this project.

### Android Studio

1. `Android Studio` -> `File` -> `New` -> `From Version control` -> `Git`
2. Enter `https://github.com/igorwojda/android-showcase.git` into URL field and press `Clone` button

### Command-line + Android Studio

1. Run `git clone https://github.com/igorwojda/android-showcase.git` command to clone the project
2. Open `Android Studio` and select `File | Open...` from the menu. Select cloned directory and press `Open` button

## Inspiration

Here are few additional resources.

### Cheatsheet

- [Core App Quality Checklist](https://developer.android.com/quality) - learn about building the high-quality app
- [Android Ecosystem Cheat Sheet](https://github.com/igorwojda/android-ecosystem-cheat-sheet) - board containing 200+
  most important tools
- [Kotlin Coroutines - Use Cases on Android](https://github.com/LukasLechnerDev/Kotlin-Coroutine-Use-Cases-on-Android) -
  most popular coroutine usages

### Android projects

Other high-quality projects will help you to find solutions that work for your project (random order):

- [Iosched](https://github.com/google/iosched) - official Android application from google IO 2019
- [Android Architecture Blueprints v2](https://github.com/googlesamples/android-architecture) - a showcase of various
  Android architecture approaches
- [Now Android](https://github.com/android/nowinandroid) - fully functional Android app built entirely with Kotlin and Jetpack Compose
- [Android sunflower](https://github.com/googlesamples/android-sunflower) complete `Jetpack` sample covering all
  libraries
- [GithubBrowserSample](https://github.com/googlesamples/android-architecture-components) - multiple small projects
  demonstrating usage of Android Architecture Components
- [Plaid](https://github.com/android/plaid) - a showcase of Android material design
- [Clean Architecture boilerplate](https://github.com/bufferapp/android-clean-architecture-boilerplate) - contains nice
  diagrams of Clean Architecture layers
- [Android samples](https://github.com/android) - official Android samples repository
- [Roxie](https://github.com/ww-tech/roxie) - solid example of `common state` approach together with very good
  documentation
- [Kotlin Android template](https://github.com/cortinico/kotlin-android-template) - the template that lets you create
  preconfigured Android Kotlin project in a few seconds.
- [whatsApp-clone-compose](https://github.com/getStream/whatsApp-clone-compose/) - WhatsApp clone app built with Jetpack
  Compose and Stream Chat SDK for Compose
- [compose-samples](https://github.com/android/compose-samples) - repository contains a set of individual Android Studio
  projects to help you learn about Compose in Android

## Known issues

- [Dynamic feature module](https://developer.android.com/studio/projects/dynamic-delivery) is not supported by
  ANDROID_TEST_USES_UNIFIED_TEST_PLATFORM yet.
- ktlint `FileName` rule has to be disabled, becasue it is not compatible with fie containg a single extension 
  [ISSUE-1657](https://github.com/pinterest/ktlint/issues/1657)  
- Delegate import is not provided when a variable has the same name as
  Delegate ([KTIJ-17403](https://youtrack.jetbrains.com/issue/KTIJ-17403))
- Correct code is marked as an error in `build.gradle.kts` files when using `libs` from the Gradle Version Catalog
  ([KTIJ-19370](https://youtrack.jetbrains.com/issue/KTIJ-19370),
  [KTIJ-19585](https://youtrack.jetbrains.com/issue/KTIJ-19585))
- [ktlint import-ordering](https://github.com/pinterest/ktlint/blob/master/ktlint-ruleset-standard/src/main/kotlin/com/pinterest/ktlint/ruleset/standard/ImportOrderingRule.kt)
  rule conflicts with IDE default formatting rule, so it have to be [disabled](.editorconfig).
  and [KTIJ-16847](https://youtrack.jetbrains.com/issue/KTIJ-16847))
- False positive "Unused symbol" for a custom Android application class referenced in AndroidManifest.xml
  file ([KT-27971](https://youtrack.jetbrains.net/issue/KT-27971))
- Android lint complains about exceeding access rights to
  ArchTaskExecutor ([Issue 79189568](https://issuetracker.google.com/u/0/issues/79189568))
- JUnit 5 does not support tests with suspended modifier ([Issue 1914](https://github.com/junit-team/junit5/issues/1914))

## Contribute

Want to contribute? Check the [Contributing](CONTRIBUTING.md) docs.

## Author

[![Follow me](https://github.com/igorwojda/android-showcase/raw/main/misc/image/avatar.png)](https://twitter.com/igorwojda)

[![Follow me](https://img.shields.io/twitter/follow/igorwojda?style=social)](https://twitter.com/igorwojda)

## License

```
MIT License

Copyright (c) 2019 Igor Wojda

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF  TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```

## Animations License

Flowing animations are distributed under `Creative Commons License 2.0`:

- [Error screen](https://lottiefiles.com/8049-error-screen) by Chetan Potnuru
- [Building Screen](https://lottiefiles.com/1271-building-screen) by Carolina Cajazeira
