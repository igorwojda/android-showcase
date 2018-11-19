# Project description
This project is simple Android application showcase for an app with long [SDLC](https://en.wikipedia.org/wiki/Systems_development_life_cycle).

# About the demo design
Demo design has few things that are contradicting with current android UI standards/guidelines. First of all usage of application icon in Toolbar
is not recommended since Android Lolipop. Also top-tabbed navigation (Men/All/Woman) was recently replaced with BottomBar navigation as
preferred way of top level navigation in the app. Most of the apps would have some kind of spacing from the screen edge to floating
buttons (sell), because may lead to usability issues (phones like Samsung galaxy S8 Edge have bended screens so app may look wired and
many phones have software navigation bar that may be accidentally clicked)

# Project characteristics
* Heavy usage of Kotlin
* Clean Architecture + Model-View-ViewModel
* Feature modules
* Unit Tests
* Kotlin Coroutines (as alternative for RxJava)
* Utilise many popular libraries from Android ecosystem
* Takes advantage of most popular analysis tools
* Gradle Script Kotlin
* Gradle dependency autocompletion
* AndroidX support libraries

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
Some architectural decisions may look like overkill for such small project, however they will scale very well for a project with long live
cycle that is maintained by larger team.

## Feature modules
Each feature is contained in separate module. This allows to easily maintain particular feature, move feature to different
project, or just delete it. Project utilizes domain centric
[clean architecture](http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) at feature level. This means that each
feature follows [dependency rule](https://proandroiddev.com/clean-architecture-data-flow-dependency-rule-615ffdd79e29) having its own
set of layers (`presentation`/`domain`/`data`). Downside of this approach is the fact that all layers (for a given feature) exists in a
single module.

## MVVM
MVVM (presentation layer) utilises Android Architecture components (`ViewModel` + `LiveData`). ViewModel uses Kotlin coroutines to retrieve data
using background thread, while `LiveData` is responsible for delivering data to a View (`Fragment`) in Lifecycle aware manner.

## Architecture extension
Architecture of this project can be scalded further up depending on he project needs. If we want to delay UseCase execution we could
introduce additional `Request` and `Response` objects for each `UseCase`. To deal with data caching we could introduce more layers eg.
split `data` layer into `network`, `memory` and `disk` layers containing own data models (`ProductNetworkModel` / `ProductMemoryModel` /
`ProductDiskModel`). To introduce multiple types of items in RecyclerViews and easily share them across app we could take advantage of
[AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates) library etc.

As a result of using CA we gain clean layer separation for feature contains multiple files, however this lead to large amount of files.
To safe precious developer time we can create a feature template using AS or some template language (after entering feature name all files
and packages will be quickly generated for new feature)

Also since all layers of CA are inside single module layer dependencies can't be enforced simply by defining module dependencies. We
need to define additional lint check to make sure that dependency rule is protected.

Finally due to proper layer separation we can easily swap libraries by modifying only small part fo the application eg. Retrofit can
be used instead of Fuel - only data layer will be affected, no additional code change is required in other layers (presentation/domain)

# Gradle
## Gradle Kotlin DLS
[Kotlin Gradle DSL](https://github.com/gradle/kotlin-dsl) provides statically-typed approach that allows many real-time code checks and
better IDE support.

##  Centralized dependencies
Gradle does pretty good job regarding to [dependency management](https://docs.gradle.org/current/userguide/introduction_dependency_management.html).
For projects that include multiple modules, we also need to structure our project in proper way so simple tasks like upgrading library
version in 20 modules are easy to accomplish (single place code change). To achieve this project utilizes [project-level properties](app/src/main/kotlin)
that are shared across all modules.

## Dependency code completion
All dependencies in the project are defined in Gradle [buildSrc](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources)
directory. Upon discovery of the directory, Gradle automatically compiles code in `buildSrc` and puts it in the classpath of our build
script. We can easily access various kinds of dependencies in our build scripts, have a full code completion and do not worry about
misspelled, hardcoded dependency strings:

- [LibraryDependency](buildSrc\src\main\kotlin\LibraryDependency.kt) - class contains all dependencies of the libraries used in project
- [ModuleDependency](buildSrc\src\main\kotlin\LibraryDependency.kt) - class contains all dependencies of project modules
- [GradleDependency](buildSrc\src\main\kotlin\LibraryDependency.kt) - class contains all dependencies of Gradle plugins.

Here is a code snippet from build script:

```kotlin
//Module depends on base feature module
implementation(project(ModuleDependency.featureBase))

//We want to use these libraries in this module
implementation(LibraryDependency.supportAppCompact)
implementation(LibraryDependency.timber)
```

[module level]
This allows to unify libraries versions across project and easily share them across all the modules.

# Color management
When the project gets bigger and requires more colors, it is getting really confusing and less traceable how to use the same colors in
different contexts for different views. Solution for this problem is spiting collars into application color and color palette:
* palette colors are named by color name (using [name the color tool](http://chir.ag/projects/name-that-color/#BF473B)) eg. `blue`,
`magenta`. They can only be used to define application colors.
* application colors are named by their function, from general function to more specific e.g. `buttonPrimaryEnabled`, `buttonPrimaryText`.
They must always use color from the palette instead of defining new color and they can be widely used across all application.

More info about
https://blog.novatec-gmbh.de/name-android-colors-palettes/

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

# Missing puzzles
Fuel networking library turned out to be quite tricky to test as opposed to Retrofit, so tests for `data` layer are missing
Also DI is missing to Dagger+Jetifier bug in combination with AndroidX dependencies (
[Dagger-An exception occurred: java.util.NoSuchElementException](https://github.com/google/dagger/issues/1245))
