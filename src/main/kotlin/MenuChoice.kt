import java.util.Scanner

class MenuChoice{
    private val scanner = Scanner(System.`in`)

    fun showMenu(
        name: String,
        options: List<Any>,
        onCreate: () -> Unit,
        onSelect: (Int) -> Unit,
        isExit: Boolean = false
    ){
        while (true) {
            println(name)
            println("0. Создать ${if (name == "Список архивов") "архив" else "заметку"}")

            options.forEachIndexed { index, item ->
                println("${index + 1}. $item")
            }

            val exit = if (isExit) "Выход" else "Назад"
            println("${options.size + 1}. $exit")

            val input = scanner.nextLine()
            val choice = input.toIntOrNull()

            if (choice == null){
                println("Ошибка. Должно быть числовое значение")
                continue
            }

            when (choice) {
                0 -> onCreate()
                in 1..options.size -> onSelect(choice - 1)
                options.size + 1 -> return
                else -> println("Ошибка. Такого варианта нет, выберете еще раз")
            }
        }
    }
}