plugins {
    anticheat.`java-conventions`
    alias(libs.plugins.shadow)
}

version = "1.0.0"

dependencies {
    implementation(project(":loader:plugin:common"))
    implementation(project(":loader:plugin:platforms:bukkit"))
}

tasks {
    jar {
        enabled = false
    }

    shadowJar {
        archiveFileName = "${rootProject.name}-Loader-${version}.jar"
        archiveClassifier = null
        destinationDirectory = rootProject.layout.buildDirectory

        manifest {
            attributes["paperweight-mappings-namespace"] = "mojang"
        }
    }

    assemble {
        dependsOn(shadowJar)
    }
}