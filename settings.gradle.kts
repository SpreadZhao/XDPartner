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
//include(":XDPBusiness:XDPMe")
//include(":XDPBusiness:XDPLogin")
//include(":XDPBusiness:XDPAddNote")
//include(":XDPBusiness:XDPAdd")
//include(":XDPBusiness:XDPFriendList")
//include(":XDPBusiness:XDPSearch")
//include(":XDPBusiness:XDPMessage")
//include(":XDPBusiness:XDPAddFriend")
include(":XDPBusiness:XDPPersonDetail")
