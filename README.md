# Project description
This project is simple Android application showcase.

# Project characteristics
* Heavy usage of [Kotlin programming language](https://kotlinlang.org/)
* Gradle Script Kotlin
* Kotlin Coroutines (as alternative for RxJava)
* Feature modules
* Clean architecture + MVVM
* Utilise many popular [libraries](buildSrc\src\main\kotlin\LibraryDependency.kt)
* Takes advantage of multiple static code analysis tools
* Gradle dependency autocompletion

# Kotlin
Project takes full advantage of Kotlin language by maximizing it's usage across project:
* Android code application is written in Kotlin (100%  code is Kotlin)
* Gradle build scripts (eg. [top level](build.gradle.kts), [module level](app/build.gradle.kts) etc.) are written in Kotlin utilising [Kotlin Gradle DSL](https://github.com/gradle/kotlin-dsl)
* CI server ([Teamcity](https://www.jetbrains.com/teamcity/)) [configuration script](.teamcity\settings.kts) is
 written in [Kotlin DSL](https://confluence.jetbrains.com/display/TCD18/Kotlin+DSL)

Heavy usage of Kotlin allows to o speed up development process, decrease learning curve and improves project maintainability.

# CI configuration
CI configuration is stored in the repository. This approach allows to easily update CI build configuration and validate it's correctness together
with each PR.

# Architecture
Some architectural decisions may look like overkill for such small project, however they will scale well for a project with long live
cycle maintained by larger team.

Each feature is contained in separate module. This gives ability to easily maintain particular feature, move feature to different
project, or just delete it. Project utilizes domain centric
[clean architecture](http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) at feature level. This means that each
feature follows [dependency rule](https://proandroiddev.com/clean-architecture-data-flow-dependency-rule-615ffdd79e29) having its own
set of layers (`presentation`/`domain`/`data`). Downside of this approach is the fact that all layers (for a given feature) exists in a single
module, so layer dependencies can't be enforced simply by defining module dependencies (defining 3 multiple modules for each feature
would be an overkill). Fortunately we can preserve proper layer dependencies by using custom lints checks to keep dependency rule in place -
create lint check that verifies if no dependencies from `data`/`presentation` layer are used in `domain` layer. Depending on the application
scale we can `data` layer can be spited into `network` and `cache`.

# Gradle
## Gradle Kotlin DLS
[Kotlin Gradle DSL](https://github.com/gradle/kotlin-dsl) aims to provide Gradle users with a rich, flexible and statically-typed approach
that allows to develop build logic in conjunction with the best IDE and tooling experience possible.

##  Dependencies
For projects that include multiple modules, it is useful to define [properties](app/src/main/kotlin) at the project level and share them across all modules.
Centralized dependency approach allows to easily add new dependencies and update existing ones eg. change library version.

[module level]
This allows to unify libraries versions across project and easily share them across all the modules.

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
