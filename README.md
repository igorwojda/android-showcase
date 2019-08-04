# Project description
[![CircleCI](https://circleci.com/gh/igorwojda/android-showcase.svg?style=shield)](https://circleci.com/gh/igorwojda/android-showcase)
[![codebeat badge](https://codebeat.co/badges/7f632064-0be5-450f-b29f-f0e1460582ab)](https://codebeat.co/projects/github-com-igorwojda-android-showcase-master)
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.3.41-blue.svg)](http://kotlinlang.org/)

Showcase is a sample project that presents modern approach to [Kotlin](https://kotlinlang.org/)
[Android](https://en.wikipedia.org/wiki/Android_(operating_system))  
application development. The goal of the project is to provide modular, scalable, maintainable and testable architecture
(suitable for lager teams long
[System Development Life Cycle](https://en.wikipedia.org/wiki/Systems_development_life_cycle)). This project takes
advantage of many popular libraries and tools of the Android ecosystem including static analysis and
[CI](https://en.wikipedia.org/wiki/Continuous_integration) setup.

# Project characteristics
This project bring to table set of best practices, tools and solutions:

* 100% [Kotlin](https://kotlinlang.org/)
* Modern architecture (Clean Architecture + Model-View-ViewModel)
* Dynamic feature modules
* [Jetpack](https://developer.android.com/jetpack) ([AndroidX](https://developer.android.com/jetpack/androidx))
* Dependency Injection
* Testing
* CI configuration
* Static analysis tools
* Modern UI
* [Android style guide](<https://developer.android.com/kotlin/style-guide>) and
  [Kotlin coding conventions](<https://kotlinlang.org/docs/reference/coding-conventions.html>)

# Tech-stack

* Core
  * Android SDK
  * Kotlin + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* Libraries
  * Kodein
  * Retrofit
  * AndroidX
  * Architecture components
  * Material components
  * Stetho
  * Android KTX
  * [...](https://github.com/igorwojda/android-showcase/blob/master/buildSrc/src/main/kotlin/LibraryDependency.kt)
* Architecture
  * Feature modules
  * Clean Architecture (at module level)
  * Model-View-ViewModel (presentation layer)
  * [Android Architecture components](https://developer.android.com/topic/libraries/architecture)
  * Dependency Injection ([Kodein](https://github.com/Kodein-Framework/Kodein-DI))
* Presentation layer
  * [Material Design Components](https://www.material.io/develop/android/)
  * [Shared element transitions](https://android-developers.googleblog.com/2018/02/continuous-shared-element-transitions.html)
  * [Adaptive Icons](https://developer.android.com/guide/practices/ui_guidelines/icon_design_adaptive)
* Testing
  * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing)
  * [Mockito](https://github.com/mockito/mockito) + [Mockito-Kotlin](https://github.com/nhaarman/mockito-kotlin)
  * Fluent assertions ( [Kluent](https://github.com/MarkusAmshove/Kluent))
* Gradle
  * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
  * Multi module configuration
  * Gradle dependency autocompletion
  * Custom gradle tasks
  * 3rd party plugins (eg. [Gradle versions plugin](https://github.com/ben-manes/gradle-versions-plugin))
* CI
  * Pipeline configuration ([CircleCI](https://circleci.com/))
  * Static analysis tools ([detekt](https://github.com/arturbosch/detekt) [ktlint](https://github.com/pinterest/ktlint),
    [Android lint](https://developer.android.com/studio/write/lint), [codebeat](https://codebeat.co))
  * Tests
  * Build app

# Improvements

 There are many things I personally want to try, play with and add to `android-show-case` project:
* Support for DayNight MaterialTheme
* Android Dynamic delivery
* Caching layer (memory + disk)
* Add Room
* UI tests (including CI pipeline emulator configuration)
* Data binding
* Custom lint, ktlint and detekt tasks
* Script to update all dependencies in the project, create PR to run all checks
* Continuous deployment (automatically publish app to Google play store using CI)
* …
* 70+ other things (seriously 🤓🤣)


# ‍Contribute
Feedback and new contributions are welcome whether it's through bug reports or new PRs.

# Author

[![Follow me](https://github.com/igorwojda/android-showcase/raw/master/misc/image/avatar.png)](https://twitter.com/igorwojda)

[![Follow me](https://img.shields.io/twitter/follow/igorwojda?style=social)](https://twitter.com/igorwojda)

# License
```
MIT License

Copyright (c) 2019 Igor Wojda

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```

