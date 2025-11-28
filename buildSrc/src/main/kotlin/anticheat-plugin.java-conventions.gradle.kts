plugins {
    java
}

tasks {
    processResources {
        inputs.properties(
            "version" to rootProject.ext["versionNoHash"].toString()
        )

        filesMatching(listOf("plugin.yml")) {
            expand(
                "version" to rootProject.ext["versionNoHash"].toString()
            )
        }
    }
}