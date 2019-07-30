# Project description
[![CircleCI](https://circleci.com/gh/igorwojda/android-showcase.svg?style=shield)](https://circleci.com/gh/igorwojda/android-showcase)
[![codebeat badge](https://codebeat.co/badges/7f632064-0be5-450f-b29f-f0e1460582ab)](https://codebeat.co/projects/github-com-igorwojda-android-showcase-master)
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.3.41-blue.svg)](http://kotlinlang.org/)

Showcase is a sample project that follows modern approach to Android application development. The goal of the project is
to present modular, scalable, testable and maintainable architecture (suitable for large teams and
[SDLC](https://en.wikipedia.org/wiki/Systems_development_life_cycle)). This project is entirely written using Kotlin.

# Project characteristics
This project bring to table set of best practices, tools and solutions:

* 100% [Kotlin](https://kotlinlang.org/)
* CA + MVVM (Clean Architecture + Model-View-ViewModel)
* [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
* Dynamic feature modules
* Dependency Injection ([Kodein](https://github.com/Kodein-Framework/Kodein-DI))
* [Android Architecture components](https://developer.android.com/topic/libraries/architecture)
* [AndroidX](https://developer.android.com/jetpack/androidx)
* [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) and mocks
* [Material Design Components for Android](https://www.material.io/develop/android/)
* Takes advantage of
  [many popular libraries](https://github.com/igorwojda/android-showcase/blob/master/buildSrc/src/main/kotlin/LibraryDependency.kt),
*  All relevant static analysis tools ([detekt](https://github.com/arturbosch/detekt)
   [ktlint](https://github.com/pinterest/ktlint), [Android lint](https://developer.android.com/studio/write/lint),
   [codebeat](https://codebeat.co))
* Follows [Android style guide](<https://developer.android.com/kotlin/style-guide>) and [Kotlin coding conventions](<https://kotlinlang.org/docs/reference/coding-conventions.html>)
* Gradle dependency autocompletion

# Contribute
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

