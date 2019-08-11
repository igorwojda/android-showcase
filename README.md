# Project description
[![CircleCI](https://circleci.com/gh/igorwojda/android-showcase.svg?style=shield)](https://circleci.com/gh/igorwojda/android-showcase)
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.3.41-blue.svg)](http://kotlinlang.org/)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

[![codebeat badge](https://codebeat.co/badges/7f632064-0be5-450f-b29f-f0e1460582ab)](https://codebeat.co/projects/github-com-igorwojda-android-showcase-master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a7ef0746703e4c81b0e4af2c46e2885e)](https://app.codacy.com/app/igorwojda/android-showcase?utm_source=github.com&utm_medium=referral&utm_content=igorwojda/android-showcase&utm_campaign=Badge_Grade_Dashboard)
[![CodeFactor](https://www.codefactor.io/repository/github/igorwojda/android-showcase/badge)](https://www.codefactor.io/repository/github/igorwojda/android-showcase)


Showcase is a sample project that presents modern, 2019 approach to
[Android](https://en.wikipedia.org/wiki/Android_(operating_system)) application development using
[Kotlin](https://kotlinlang.org/) and latest tech-stack.

The goal of the project is to demonstrate best practices, provide guidance and present flexible application architecture
(modular, scalable, maintainable and testable architecture suitable for growing teams and longer
[application lifecycle](https://en.wikipedia.org/wiki/Application_lifecycle_management)). Many of the project design
decisions follows or even extends official Google recommendations.

`Android showcase` is being heavily maintained to match up to date industry standards.

## Project characteristics

This project bring to table set of best practices, tools and solutions:

* 100% [Kotlin](https://kotlinlang.org/)
* Modern architecture (feature modules, Clean Architecture, Model-View-ViewModel)
* [Android Jetpack](https://developer.android.com/jetpack)
* CI pipeline
* Testing
* Static analysis tools
* Dependency Injection
* Material design

## Tech-stack

Min API level is set to [`21`](https://android-arsenal.com/api?level=21), so presented approach is suitable for over
[85% of devices](https://developer.android.com/about/dashboards) running Android. This project takes advantage of many
popular libraries and tools of the Android ecosystem. Most of the libraries are in stable version, unless there is a
good reason to use non-stable dependency.

* Tech-stack
    * [Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
    * [Kodein](https://kodein.org/Kodein-DI/)
    * [Retrofit](https://square.github.io/retrofit/)
    * [Jetpack](https://developer.android.com/jetpack)
    * [Lottie](http://airbnb.io/lottie)
    * [Stetho](http://facebook.github.io/stetho/)
    * [and more...](https://github.com/igorwojda/android-showcase/blob/master/buildSrc/src/main/kotlin/LibraryDependency.kt)
* Architecture
    * Clean Architecture (at module level)
    * MVVM (presentation layer)
    * [Dynamic feature modules](https://developer.android.com/studio/projects/dynamic-delivery)
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation), [SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args) plugin)
* Tests
    * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit](https://junit.org/junit4/))
    * [Mockito](https://github.com/mockito/mockito) + [Mockito-Kotlin](https://github.com/nhaarman/mockito-kotlin)
    * [Kluent](https://github.com/MarkusAmshove/Kluent)
* Gradle
    * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
    * Custom tasks
    * Plugins ([Ktlint](https://github.com/JLLeitschuh/ktlint-gradle), [Detekt](https://github.com/arturbosch/detekt#with-gradle), [Versions](https://github.com/ben-manes/gradle-versions-plugin), [SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args))


## Architecture

### Module dependencies

This is simplified diagram of dependencies between gradle modules. Notice that due usage of Android
`dynamic-feature` module dependencies are reversed (feature modules are depending on `app` module, not other way
around).

![module_dependencies](https://github.com/igorwojda/android-showcase/blob/master/misc/image/module_dependencies.png?raw=true)

### Feature structure

Each feature is isolated into separate module to provide better separation of concerns in the codebase. This allows for
feature to be developed in isolation, independently from other features. Each feature module contains own set of the
Clean Architecture layers (`Presentation`/`Domain`/`Data`).

![feature_structure](https://github.com/igorwojda/android-showcase/blob/master/misc/image/feature_structure.png?raw=true)

### Data flow

![app_data_flow](https://github.com/igorwojda/android-showcase/blob/master/misc/image/app_data_flow.png?raw=true)

## Ci pipeline

[CI config](.circleci/config.yml) allows to execute various jobs in parallel eg. app build will not be stared until all
static checks and tests complete successfully.

![ci_pipeline.jpg](misc/image/ci_pipeline.jpg)

## What this project does not cover?
The interface of the app utilises some of modern material design components, however is deliberately kept simple to
focus on architecture.

## Future improvements

* Android Dynamic delivery
* Caching layer (memory + disk)
* Add Room
* UI tests (including CI pipeline emulator configuration)
* Data binding
* Add Custom lint, ktlint and detekt tasks
* Add script to update all dependencies in the project, create PR to run all checks
* Continuous deployment (automatically publish app to Google play store using CI)
* Support for DayNight MaterialTheme
* and more…

## Contribute
Feedback and new contributions are welcome whether it's through bug reports or new PRs.

## Even more inspiration

Other Android repositories that are worth checking out:

* [Android Architecture Blueprints v2](https://github.com/googlesamples/android-architecture) - showcase of different
  Android architecture approaches
* [Android sunflower](https://github.com/googlesamples/android-sunflower) complete JetPack sample covering all libraries
* [GithubBrowserSample](https://github.com/googlesamples/android-architecture-components) - multiple small projects
  demonstrating usage of Android Architecture Components
* [Plaid](https://github.com/android/plaid) - showcase of Android material design
* [Clean Architecture boilerplate](https://github.com/bufferapp/android-clean-architecture-boilerplate) - contains nice
  diagrams of Clean Architecture layers

## Author

[![Follow me](https://github.com/igorwojda/android-showcase/raw/master/misc/image/avatar.png)](https://twitter.com/igorwojda)

[![Follow me](https://img.shields.io/twitter/follow/igorwojda?style=social)](https://twitter.com/igorwojda)

## License
```
MIT License

Copyright (c) 2019 Igor Wojda

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```

