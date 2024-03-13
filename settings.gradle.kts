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

rootProject.name = "XDPartner"
include(":XDPapp")
include(":XDPLib")

include(":XDPBusiness:XDPMe")
include(":XDPBusiness:XDPLogin")
include(":XDPNetwork")
include(":XDPBusiness:XDPAddNote")
include(":XDPBusiness:XDPAdd")
include(":XDPBusiness:XDPFriendList")
include(":XDPBusiness:XDPSearch")
include(":XDPBusiness:XDPMessage")
include(":XDPBusiness:XDPAddFriend")
