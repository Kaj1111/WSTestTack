package logic

import org.json.JSONArray
import java.io.File

class JsonReplace: JsonRefactor(), ResultInterface {
    var newDataArray = JSONArray()


    override fun getResult(){
        val resultFile = File("result.json")
        val jsonArray = StringBuilder("[\n")
        newDataArray.forEachIndexed { index, element ->
            jsonArray.append("  \"${element}\"")
            if (index < newDataArray.length() - 1) {
                jsonArray.append(",\n")
            }
        }
        jsonArray.append("\n]")

        resultFile.writeText(jsonArray.toString())
    }


    fun replaceJsonField() {
        val temp = JSONArray()
        for (i in 0 until dataArray.length()) {
            var line = dataArray.optString(i)                        //берем строчку текста из data
            for ((replacement, source) in clearReplacements) {
                if (source != null) {
                    line = line.replace(replacement, source)         // Выполняем замену в строке данных
                } else {
                    line = line.replace(replacement, "")    // Убираем лишнее слово из строки данных
                }
            }
            if (line.isNotEmpty()) {
                temp.put(line)                                      // Добавляем обновленную строку в новый массив данных
            }
        }

        for (i in 0 until temp.length()) {
            val line = temp.optString(i)                            // Берем строчку текста из data
            if (line.any { it != '"' }) {                           // Проверяем, содержит ли строка символы, кроме ""
                newDataArray.put(line)                              // Добавляем строку без ""
            }
        }
    }


}