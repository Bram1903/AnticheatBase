plugins {
    java
}

tasks {
    processResources {
        inputs.properties(
            "version" to rootProject.ext["loaderVersionNoHash"].toString()
        )

        filesMatching(listOf("plugin.yml")) {
            expand(
                "version" to rootProject.ext["loaderVersionNoHash"].toString()
            )
        }
    }
}