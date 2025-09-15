package com.igorwojda.showcase.buildlogic.ext

import com.android.build.api.dsl.Packaging

fun Packaging.excludeLicenseAndMetaFiles() {
    resources.excludes += setOf(
        "META-INF/AL2.0",
        "META-INF/licenses/**",
        "**/attach_hotspot_windows.dll",
        "META-INF/LGPL2.1",
    )
}
