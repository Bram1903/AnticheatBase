pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "AnticheatBase"

include(":loader:plugin:shared")
include(":loader:plugin:common")
include(":loader:plugin:platforms:bukkit")

include(":plugin:api")
include(":plugin:common")
include(":plugin:platforms:bukkit")
//include(":plugin:platforms:velocity")
//include(":plugin:platforms:bungeecord")
//include(":plugin:platforms:sponge")

include(":plugin:tests:api-bukkit-test-plugin")
include(":plugin:tests:api-velocity-test-plugin")