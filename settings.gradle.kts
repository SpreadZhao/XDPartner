pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

val includeChildProjects: (String) -> Unit = { parentProject ->
    File(rootProject.projectDir.absolutePath + parentProject.replace(":", File.separator)).listFiles()?.forEach { file ->
        if (file.canRead() && file.isDirectory && !file.isHidden) {
            include("$parentProject:${file.name}")
        }
    }
}

rootProject.name = "XDPartner"
include(":XDPapp")
include(":XDPLib")
include(":XDPNetwork")
includeChildProjects(":XDPBusiness")