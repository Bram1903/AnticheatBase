plugins {
    anticheat.`java-conventions`
    `ac-version`
}

dependencies {
    implementation(project(":api"))
}

tasks {
    withType<JavaCompile> {
        dependsOn(generateVersionsFile)
    }

    generateVersionsFile {
        packageName = "com.deathmotion.anticheatbase.common.util"
    }
}