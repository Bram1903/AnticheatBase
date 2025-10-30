pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "AnticheatBase"

include(":api")
include(":common")
include(":platforms:bukkit")
include(":platforms:velocity")
include(":tests:api-bukkit-test-plugin")
include(":tests:api-velocity-test-plugin")