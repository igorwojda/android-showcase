# Developer Readme

## Detekt

- [Detekt configuration](https://detekt.dev/docs/introduction/configurations/) contains link to `default-detekt-config.yml`.

## Known Issues

- AboutLibraries
  - AboutLibraries `12.2.4` Gradle plugin does nto include test dependencies https://github.com/mikepenz/AboutLibraries/issues/1238
  - AboutLibraries `13.0.0-rc01` Gradle plugin required Kotlin 2.2.0 https://github.com/mikepenz/AboutLibraries/issues/1237
- Gradle 
  - Gradle `9.0` - Generated type-safe version catalogs accessors for `projcts` are not avialable inside `build-logic` module
  - Gradle `9.0` - Generated type-safe version catalogs accessors for `libs` are not accessible from precompiled script plugin e.g. add("implementation", libs.koin). Workaround is to use `implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))`.
- Mockk 
  - Unable to mock some methods with implicit `continuation`
  parameter in the `AlbumListViewModelTest` class ([Issue-957](https://github.com/mockk/mockk/issues/957))
- Detekt
  - The `UnnecessaryParentheses` rule was disabled https://github.com/detekt/detekt/issues/8668
- Kotlin Plugin
  - Auto-import (an import intention) for delegate does not work if the variable has the same name https://youtrack.jetbrains.com/issue/KTIJ-17403
- Android Studio 
  - False positive "Unused symbol" for a custom Android application class referenced in `AndroidManifest.xml`
  file ([KT-27971](https://youtrack.jetbrains.net/issue/KT-27971))
- Coil 
  - No way to automatically retry image load, so some images may not be loaded when connection speed
  is low ([Issue 132](https://github.com/coil-kt/coil/issues/132))
