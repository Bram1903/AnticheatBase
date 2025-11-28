val acVersionFull = "1.0.0"
val loaderVersion = "1.0.0"
val snapshot = true

fun getVersionMeta(includeHash: Boolean): String {
    if (!snapshot) {
        return ""
    }

    var commitHash = ""
    if (includeHash && file(".git").isDirectory) {
        val result = providers.exec {
            commandLine("git", "rev-parse", "--short", "HEAD")
        }.standardOutput.asText.get().trim()

        commitHash = "+$result"
    }

    return "$commitHash-SNAPSHOT"
}

version = "$acVersionFull${getVersionMeta(true)}"
ext["acVersionNoHash"] = "$acVersionFull${getVersionMeta(false)}"
ext["loaderVersionNoHash"] = loaderVersion