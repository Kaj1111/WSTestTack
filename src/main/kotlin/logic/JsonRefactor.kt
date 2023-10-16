package logic

open class JsonRefactor: JsonReader(), RefactorInterface {
    var clearReplacements = mutableMapOf<String, String?>()


    override fun refactorJsonArray() {
        for (i in replacementArray.length() - 1 downTo 0) {
            val replacementObj = replacementArray.getJSONObject(i)      // получаем объект из массива с двумя полями
            val replacement = replacementObj.optString("replacement")
            val source = replacementObj.optString("source")

            if (source != "null" && replacement !in clearReplacements) {
                clearReplacements[replacement] = source                 // Если source не равно null", то добавляем замену в мапу
            }
        }
    }


}