plugins {
    anticheat.`java-conventions`
    `anticheat-loader-plugin`.`java-conventions`
}

repositories {
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    implementation(project(":loader:plugin:common"))
    compileOnly(libs.paper)
}