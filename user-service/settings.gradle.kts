rootProject.name = "user-service"

include("modules:bootstrap")
include("modules:domain")
include("modules:application")
include("modules:infrastructure")

pluginManagement {
    val kotlinVersion : String by settings
    val springDependencyManagerVersion : String by settings
    val springBootVersion : String by settings

    resolutionStrategy{
        eachPlugin{
            when (requested.id.id){
                "io.spring.dependency-management" -> useVersion(springDependencyManagerVersion)
                "org.springframework.boot" -> useVersion(springBootVersion)
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)
                "org.gradle.core" -> useVersion(kotlinVersion)
            }
        }
    }

    repositories {
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
        maven ("https://repo.spring.io/release")
        maven ("https://repo.spring.io/milestone/")
        maven ("https:/jitpack.io")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
include("modules:common")
findProject(":modules:common")?.name = "common"
