// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(ClassPaths.gradlePlugin)
        classpath(ClassPaths.kotlinPlugin)
        classpath(ClassPaths.koinPlugin)
        classpath(ClassPaths.navigationSafeArgsPlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}