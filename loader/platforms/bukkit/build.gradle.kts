plugins {
    anticheat.`java-conventions`
    `anticheat-loader`.`java-conventions`
}

repositories {
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    implementation(project(":loader:common"))
    compileOnly(libs.paper)
}