import org.json.JSONArray
import java.net.URL
import java.io.File

//Проект реализован линейной архитектурой, тк не увидел смысла в другой

fun main() {
    // Загрузка данных из json файлов через гитхаб
    val dataUrl = URL("https://raw.githubusercontent.com/thewhitesoft/student-2023-assignment/main/data.json")
    val replacementUrl = URL("https://raw.githubusercontent.com/thewhitesoft/student-2023-assignment/main/replacement.json")
    // Берем текст из полученных данных
    val dataString = dataUrl.readText()
    val replacementString = replacementUrl.readText()

    // Кладем полученые данные в массив
    val dataArray = JSONArray(dataString)
    val replacementArray = JSONArray(replacementString)

    // Создаем мапу для хранения уникальных замен в обратном порядке, тк мы учитываем последний вариант при повторах replacement
    val clearReplacements = mutableMapOf<String, String?>()
    for (i in replacementArray.length() - 1 downTo 0) {
        val replacementObj = replacementArray.getJSONObject(i)
        // получаем объект из массива с двумя полями
        val replacement = replacementObj.optString("replacement")
        val source = replacementObj.optString("source")

        if (source != "null") {
            // Если source не равно "null", то добавляем замену в мапу
            clearReplacements[replacement] = source
        }
    }

    // Проходимся по данным и заменяем фрагменты, если есть соответствующая замена в мапе
    val newDataArray = JSONArray()
    for (i in 0 until dataArray.length()) {
        var line = dataArray.optString(i) //берем строчку текста из data
        for ((replacement, source) in clearReplacements) {
            if (source != null) {
                // Выполняем замену в строке данных
                line = line.replace(replacement, source)
            } else {
                // Убираем лишнее слово из строки данных
                line = line.replace(replacement, "")
            }
        }

        // Добавляем обновленную строку в новый массив данных
        newDataArray.put(line)
    }

    // Создаем JSON-объект для записи в файл result.json
    val resultJsonArray = JSONArray(newDataArray)

    // Записываем JSON-объект в файл result.json
    val resultFile = File("result.json")
    resultFile.writeText(resultJsonArray.toString(4))

    println("Результаты были записаны в файл result.json")
}


// Код далек от идеала, буду очень рад, если в обратной связи вы укажете, какие методы бы упростили задачу