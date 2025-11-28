import org.spongepowered.gradle.plugin.config.PluginLoaders
import org.spongepowered.plugin.metadata.model.PluginDependency

plugins {
    anticheat.`java-conventions`
    `anticheat-plugin`.`java-conventions`
    alias(libs.plugins.shadow)
    alias(libs.plugins.spongeGradle)
}

repositories {
    maven {
        name = "sponge"
        url = uri("https://repo.spongepowered.org/repository/maven-public/")
    }
}

dependencies {
    implementation(project(":plugin:common"))
    compileOnly(libs.sponge)
}

sponge {
    apiVersion("8.1.0")
    license("GPL3")
    loader {
        name(PluginLoaders.JAVA_PLAIN)
        version("1.0")
    }
    plugin("anticheatbase") {
        displayName("AnticheatBase")
        entrypoint("com.deathmotion.anticheatbase.sponge.AHISponge")
        description("Prevent health indicators from being displayed on the client")
        version(project.version.toString())
        contributor("Bram") {
            description("Author")
        }
        dependencies {
            dependency("spongeapi") {
                loadOrder(PluginDependency.LoadOrder.AFTER)
                optional(false)
            }
            dependency("packetevents") {
                loadOrder(PluginDependency.LoadOrder.AFTER)
                version("2.10.0")
                optional(false)
            }
        }
    }
}

tasks {
    jar {
        enabled = false
    }

    shadowJar {
        archiveFileName = "${rootProject.name}-Sponge-${rootProject.ext["versionNoHash"]}.jar"
        archiveClassifier = null
        destinationDirectory = rootProject.layout.buildDirectory
        exclude("META-INF/maven/**")
    }

    assemble {
        dependsOn(shadowJar)
    }
}
