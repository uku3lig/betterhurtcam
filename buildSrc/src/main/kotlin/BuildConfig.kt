object BuildConfig {
    const val MINECRAFT_VERSION: String = "26.3"
    const val FABRIC_LOADER_VERSION: String = "0.19.5"
    const val NEOFORGE_VERSION: String = "26.3.0.0-beta"
    const val UKULIB_VERSION: String = "2.2.0+26.3"

    const val MOD_VERSION: String = "1.14.0"

    const val MODRINTH_PROJECT_ID: String = "o4y0N2hu"

    fun createVersionString(): String {
        return "$MOD_VERSION+mc$MINECRAFT_VERSION"
    }
}