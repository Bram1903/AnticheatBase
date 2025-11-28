plugins {
    anticheat.`java-conventions`
    `ac-version`
}

dependencies {
    implementation(project(":plugin:api"))
    compileOnly(libs.packetevents.api)
    compileOnly(libs.bundles.adventure)
    compileOnly(libs.bundles.adventure.serializers)
}

tasks {
    withType<JavaCompile> {
        dependsOn(generateVersionsFile)
    }

    generateVersionsFile {
        packageName = "com.deathmotion.anticheatbase.common.util"
    }
}