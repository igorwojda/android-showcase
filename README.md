# Project description
This project is simple Android application showcase for an app with long [SDLC](https://en.wikipedia.org/wiki/Systems_development_life_cycle).

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
single module, so layer dependencies can't be enforced simply by defining module dependencies (defining 3 multiple modules for each feature
would be an overkill). Fortunately we can preserve proper layer dependencies by using custom lints checks to keep dependency rule in place -
create lint check that verifies if no dependencies from `data`/`presentation` layer are used in `domain` layer. Depending on the application
scale we can `data` layer can be spited into `network` and `cache`.

## MVVM
MVVM (presentation layer) utilises Android Architecture components (`ViewModel` + `LiveData`). ViewModel uses Kotlin coroutines to retrieve data
using background thread, while `LiveData` is responsible for delivering data to a View (`Fragment`) in Lifecycle aware manner.



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
