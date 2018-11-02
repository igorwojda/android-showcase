# Project description
This project is simple Android application showcase.

# Project characteristics
* App code is 100% Kotlin
* Gradle Script Kotlin
* Coroutines (as alternative for RxJava & LiveData)
* Feature modules
* Clean architecture
* Utilise most popular [libraries](buildSrc\src\main\kotlin\LibraryDependency.kt)
* Takes advantage of multiple static code analysis tools
* Gradle dependency autocompletion

# Architecture
Some architectural decisions may look like overkill for such small project, however they will scale well for a project with long live
cycle maintained by larger team.

Each feature is contained in separate module. This gives ability to easily maintain particular feature, move feature to different
project, or just delete existing feature. Project utilizes domain centric
[clean architecture](http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) at feature level. This means that each
feature follows [dependency rule](https://proandroiddev.com/clean-architecture-data-flow-dependency-rule-615ffdd79e29) having its own
layers (presentation/domain/data).

# Static analysis
Project includes all modern [static code analisys](https://en.wikipedia.org/wiki/Static_program_analysis) tools for Kotlin
 ([ktlint](https://github.com/shyiko/ktlint), [detekt](https://github.com/arturbosch/detekt)) and Android platform
 ([Android lint](https://developer.android.com/studio/write/lint)). Each of those tools is focusing on different area of static analysis.
  `ktlint` checks code formatting, `detekt` deals with code smells, complexity, performance checks and finally `lint` verifies behaviours
   specific to android platform.

To have better understanding, the difference between those tools let's look at sample checks performed by each tool:

`ktlint` - guards the code formatting eg. each function parameter must be on a separate line / no space allowed after colon in variable declaration line

`detekt` - guards code complexity, correctness and security eg. method is to long / property may be const

`Android lint` - guards Android platform specific rules eg. unused android resource / activity not declared in manifest

Project contains custom gradle task that can run all of the checks at once - `./gradlew staticCheck`.

BTW: [Checkstyle](http://checkstyle.sourceforge.net/), [PMD](https://pmd.github.io/) and [FindBugs](http://findbugs.sourceforge.net/)
don't work with Kotlin (only Java), so they are not included in this project.

## Android lint
`./gradlew lint` - run lint check

## ktlint
ktlint is integrated via [Ktlint Gradle](https://github.com/jlleitschuh/ktlint-gradle) witch is gradle plugin over the `ktlint` project.

`./gradlew ktlintCheck` - run ktlint check

`./gradlew ktlintFormat` - runs the ktlint formatter on all kotlin sources in this project.

Ktlint follows `Android Kotlin Style Guide`. For `Android Studio` to be compliant with `Android Kotlin Style Guide` we need to run one
of these tasks that will update code formatting settings:

`./gradlew ktlintApplyToIdea` - The task generates IntelliJ IDEA (or Android Studio) Kotlin style files in the project .idea/ folder.

`./gradlew ktlintApplyToIdeaGlobally` - The task generates IntelliJ IDEA (or Android Studio) Kotlin style files in the user home IDEA
(or Android Studio) settings folder.

## detekt
`./gradlew detekt` - run detekt check
