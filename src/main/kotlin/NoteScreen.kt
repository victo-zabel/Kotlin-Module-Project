import java.util.Scanner

class NoteScreen {
    private val archives = mutableListOf<Archive>()
    private val menu = MenuChoice()

    private val scanner = Scanner(System.`in`)

    fun start(){
        menu.showMenu(
            name = "Список архивов",
            options = archives,
            onCreate = {createArchive()},
            onSelect = {index -> openArchive(archives[index])},
            isExit = true
        )
    }

    private fun createArchive(){
        val name = validInput("Введите название архива")
        archives.add(Archive(name))
        println("Архив успешно создан")
    }

    private fun openArchive(archive: Archive){
         menu.showMenu(
             name = "Архив: ${archive.name}",
             options = archive.notes,
             onCreate = {createNote(archive)},
             onSelect = {index -> showNote(archive.notes[index])}
         )
    }

    private fun createNote(archive: Archive){
        val title = validInput("Введите название заметки")
        val content = validInput("Введите текст заметки")
        archive.notes.add(Note(title, content))
        println("Заметка успешно добавлена в ${archive.name}")
    }

    private fun showNote(note: Note){
        println("ЗАМЕТКА: ${note.title}")
        println(note.content)
    }

    fun validInput(text: String): String {
        while (true){
            println(text)
            val input = scanner.nextLine().trim()
            if (input.isNotEmpty()) return input
            println("Ввод не может остаться пустым. Введите имя")
        }
    }
}