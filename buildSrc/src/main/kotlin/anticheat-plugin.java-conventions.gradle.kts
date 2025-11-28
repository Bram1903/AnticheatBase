plugins {
    java
}

tasks {
    processResources {
        inputs.properties(
            "version" to rootProject.ext["acVersionNoHash"].toString()
        )

        filesMatching(listOf("plugin.yml")) {
            expand(
                "version" to rootProject.ext["acVersionNoHash"].toString()
            )
        }
    }
}