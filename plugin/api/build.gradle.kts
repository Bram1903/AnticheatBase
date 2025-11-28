plugins {
    anticheat.`java-conventions`
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks {
    jar {
        archiveFileName = "${rootProject.name}API-${rootProject.ext["acVersionNoHash"]}.jar"
        archiveClassifier = null
    }

    named<Jar>("javadocJar") {
        archiveFileName.set("${rootProject.name}API-${rootProject.ext["acVersionNoHash"]}-javadoc.jar")
    }

    named<Jar>("sourcesJar") {
        archiveFileName.set("${rootProject.name}API-${rootProject.ext["acVersionNoHash"]}-sources.jar")
    }

    javadoc {
        title = "AntiHealthIndicator API v${rootProject.ext["acVersionNoHash"]}"
        options.encoding = Charsets.UTF_8.name()
        options {
            (this as CoreJavadocOptions).addBooleanOption("Xdoclint:none", true)
        }
    }
}