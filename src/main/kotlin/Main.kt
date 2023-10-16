import logic.JsonReplace



fun main() {
    val hackedJson = JsonReplace()
    val data = "https://raw.githubusercontent.com/thewhitesoft/student-2023-assignment/main/data.json"
    val replacement = "https://raw.githubusercontent.com/thewhitesoft/student-2023-assignment/main/replacement.json"

    hackedJson.getURL(data,replacement)
    hackedJson.getStringArray()
    hackedJson.refactorJsonArray()
    hackedJson.replaceJsonField()
    hackedJson.getResult()
    println("finish")
}
