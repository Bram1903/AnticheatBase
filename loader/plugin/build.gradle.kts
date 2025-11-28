plugins {
    anticheat.`java-conventions`
    alias(libs.plugins.shadow)
    alias(libs.plugins.run.paper)
    alias(libs.plugins.run.velocity)
}

dependencies {
    compileOnly(project(":loader:plugin:shared"))
    implementation(project(":loader:plugin:common"))
    implementation(project(":loader:plugin:platforms:bukkit"))
}

tasks {
    jar {
        enabled = false
    }

    shadowJar {
        archiveFileName = "${rootProject.name}-Loader-${rootProject.ext["loaderVersionNoHash"].toString()}.jar"
        archiveClassifier = null
        destinationDirectory = rootProject.layout.buildDirectory

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
        "-Dcom.mojang.eula.agree=true"
    )

    val sharedBukkitPlugins = runPaper.downloadPluginsSpec {
        url("https://cdn.modrinth.com/data/HYKaKraK/versions/7igcjlxa/packetevents-spigot-2.10.1.jar")
        url("https://github.com/ViaVersion/ViaVersion/releases/download/5.5.1/ViaVersion-5.5.1.jar")
        url("https://github.com/ViaVersion/ViaBackwards/releases/download/5.5.1/ViaBackwards-5.5.1.jar")
    }

    runServer {
        minecraftVersion(version)
        runDirectory = rootDir.resolve("run/loader/paper/$version")

        javaLauncher = project.javaToolchains.launcherFor {
            languageVersion = javaVersion
        }

        //serverJar(file("./1.8.8.jar"))

        downloadPlugins {
            from(sharedBukkitPlugins)
            url("https://github.com/EssentialsX/Essentials/releases/download/2.21.2/EssentialsX-2.21.2.jar")
            url("https://download.luckperms.net/1602/bukkit/loader/LuckPerms-Bukkit-5.5.15.jar")
        }

        jvmArgs = jvmArgsExternal
    }

    runPaper.folia.registerTask {
        minecraftVersion(version)
        runDirectory = rootDir.resolve("run/loader/folia/$version")

        javaLauncher = project.javaToolchains.launcherFor {
            languageVersion = javaVersion
        }

        downloadPlugins {
            from(sharedBukkitPlugins)
        }

        jvmArgs = jvmArgsExternal
    }

    runVelocity {
        velocityVersion("3.3.0-SNAPSHOT")
        runDirectory = rootDir.resolve("run/loader/velocity/")

        javaLauncher = project.javaToolchains.launcherFor {
            languageVersion = javaVersion
        }

        downloadPlugins {
            url("https://ci.codemc.io/job/retrooper/job/packetevents/lastSuccessfulBuild/artifact/velocity/build/libs/packetevents-velocity-2.6.1-SNAPSHOT.jar")
            url("https://ci.lucko.me/job/spark/418/artifact/spark-velocity/build/libs/spark-1.10.73-velocity.jar")
        }
    }
}