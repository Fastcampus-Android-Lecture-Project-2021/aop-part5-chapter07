package fastcampus.aop.part5.chapter07.data.preference

interface PreferenceManager {

    fun getString(key: String): String?

    fun putString(key: String, value: String)
}
