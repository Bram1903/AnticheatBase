plugins {
    anticheat.`java-conventions`
    `anticheat-plugin`.`java-conventions`
    alias(libs.plugins.shadow)
    alias(libs.plugins.run.paper)
}

repositories {
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    implementation(project(":plugin:common"))
    compileOnly(project(":loader:plugin:shared"))
    compileOnly(libs.paper)
}

tasks {
    jar {
        enabled = false
    }

    shadowJar {
        archiveFileName = "${rootProject.name}-Bukkit-${rootProject.ext["acVersionNoHash"]}.jar"
        archiveClassifier = null
        destinationDirectory = rootProject.layout.buildDirectory
        exclude("META-INF/maven/**")

        manifest {
            attributes["paperweight-mappings-namespace"] = "mojang"
        }
    }

    assemble {
        dependsOn(shadowJar)
    }

    // 1.8.8 - 1.16.5 = Java 8
    // 1.17           = Java 16
    // 1.18 - 1.20.4  = Java 17
    // 1-20.5+        = Java 21
    val version = "1.21.10"
    val javaVersion = JavaLanguageVersion.of(21)

    val jvmArgsExternal = listOf(
        "-Dcom.mojang.eula.agree=true",
        "-DPaper.IgnoreJavaVersion=true"
    )

    val sharedPlugins = runPaper.downloadPluginsSpec {
        url("https://cdn.modrinth.com/data/HYKaKraK/versions/qeKYxQaR/packetevents-spigot-2.10.0.jar")
        url("https://github.com/ViaVersion/ViaVersion/releases/download/5.5.1/ViaVersion-5.5.1.jar")
        url("https://github.com/ViaVersion/ViaBackwards/releases/download/5.5.1/ViaBackwards-5.5.1.jar")
    }

    runServer {
        minecraftVersion(version)
        runDirectory = rootDir.resolve("run/plugin/paper/$version")

        javaLauncher = project.javaToolchains.launcherFor {
            languageVersion = javaVersion
        }

        downloadPlugins {
            from(sharedPlugins)
            url("https://github.com/EssentialsX/Essentials/releases/download/2.21.2/EssentialsX-2.21.2.jar")
            url("https://download.luckperms.net/1602/bukkit/loader/LuckPerms-Bukkit-5.5.15.jar")
        }

        jvmArgs = jvmArgsExternal
    }

    runPaper.folia.registerTask {
        minecraftVersion(version)
        runDirectory = rootDir.resolve("run/plugin/folia/$version")

        javaLauncher = project.javaToolchains.launcherFor {
            languageVersion = javaVersion
        }

        downloadPlugins {
            from(sharedPlugins)
        }

        jvmArgs = jvmArgsExternal
    }
}