package logic

import org.json.JSONArray
import java.net.URL

open class JsonReader {
    lateinit var dataUrl: URL
    lateinit var replacementUrl: URL
    lateinit var dataArray: JSONArray
    lateinit var replacementArray: JSONArray


    fun getURL(input1: String, input2: String) {
        dataUrl = URL(input1)
        replacementUrl = URL(input2)
    }


    fun getStringArray() {
        dataArray = JSONArray(dataUrl.readText())
        replacementArray = JSONArray(replacementUrl.readText())
    }


}