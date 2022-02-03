import java.sql.DriverManager
import java.sql.Connection
import java.util.Scanner
import java.io.PrintWriter
import java.io.File
import java.util.Calendar
import java.util.InputMismatchException

object Main {
  def main(args: Array[String]): Unit = {
    // connect to the database named "mysql" on the localhost
    val driver = "com.mysql.jdbc.Driver"
    val url =
      "jdbc:mysql://localhost:3306/todo" // Modify for whatever port you are running your DB on
    val username = "root"
    val password = "database1234" // Update to include your password
    var scanner = new Scanner(System.in)
    var connection: Connection = null
    val log = new PrintWriter(new File("query.log"))
    // log.write(Calendar.getInstance().getTimeInMillis + " - Executing  \n")

    // val addTaskSQL = "INSERT INTO scala_tasks(task_desc, date) VALUES ("+ task_desc + ", " + date + ");"

    try {
      // make the connection
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      // Terminal run program
      menuchoices()

//var endprogram = false
//while (!endprogram){
// if (string == add)
  //add
  //if else
    //if else
      //if else
        //if (string == exit)
          //endprogram = true
  //  }










      var menuChoice = scanner.nextInt()
      scanner.nextLine()
      println(menuChoice)
      var endProgram = false
      while (!endProgram) {
        menuChoice match {
          case 1 =>
            addtask(connection, scanner, log)
            println("Success")
            menuchoices()
            menuChoice = scanner.nextInt()
            scanner.nextLine()
          case 2 =>
            viewTask(connection, log)
            menuchoices()
            menuChoice = scanner.nextInt()
            scanner.nextLine()
          case 3 =>
            editTask(connection, scanner, log)
            println("Success")
            menuchoices()
            menuChoice = scanner.nextInt()
            scanner.nextLine()
          case 4 =>
            deleteTask(connection, scanner, log)
            println("Success")
            menuchoices()
            menuChoice = scanner.nextInt()
            scanner.nextLine()
          case 5 => endProgram = true
          case _ =>
            println("Invalid Choice")
            println("Please choose between 1-5")
            menuchoices()
            menuChoice = scanner.nextInt()
        }
      }
      println("Goodbye")
      // create the statement, and run the select query

    } catch {
      case e: Exception => e.printStackTrace
    }

    connection.close()
    log.close()
  }

// adds a new task to sql database, uses connection and scanner to receive data to be sent to sql.

  def addtask(
      connection: Connection,
      scanner: Scanner,
      log: PrintWriter
  ): Unit = {
    println("==================================================")
    val statement = connection.createStatement()
    println("Enter the task description")
    var task_desc = scanner.nextLine()
    task_desc = "'" + task_desc + "'"
    var task_date = 0
    println("Enter the date (MMDDYYYY)")
    try {
      task_date = scanner.nextInt()
      scanner.nextLine()
    } catch {
      case e: InputMismatchException =>
        println("Invalid choice, Enter date in MMDDYY")
    }

    val resultSet = statement.executeUpdate(
      s"INSERT INTO scala_tasks(task_desc, task_date) VALUES ($task_desc, $task_date);"
    )
    log.write(
      Calendar
        .getInstance()
        .getTimeInMillis + " - Executing INSERT INTO scala_tasks(task_desc, task_date) VALUES ($task_desc, $task_date); \n"
    )
    // Change query to your table
    println("==================================================")
  }

  //iteratively prints each individual row from SQL table

  def viewTask(connection: Connection, log: PrintWriter): Unit = {
    val statement = connection.createStatement()
    println("==================================================")
    val resultSet = statement.executeQuery("SELECT * FROM scala_tasks;")
    while (resultSet.next()) {
      print(
        resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet
          .getString(3)
      )
      println()
      log.write(
        Calendar
          .getInstance()
          .getTimeInMillis + " - Executing SELECT * FROM scala_tasks; \n"
      )
    }
    println("==================================================")
  }

  //allows user to edit the task description of a row, chosen by task ID
  def editTask(
      connection: Connection,
      scanner: Scanner,
      log: PrintWriter
  ): Unit = {
    println("==================================================")
    val statement = connection.createStatement()
    println("Which task would you like to edit? (Choose by TASK ID)")
    var editTask_id = scanner.nextInt()
    scanner.nextLine()
    println("What should the new task description be?")
    var task_desc = scanner.nextLine()
    task_desc = "'" + task_desc + "'"
    val resultSet = statement.executeUpdate(
      s"UPDATE scala_tasks SET task_desc = $task_desc  WHERE task_id = $editTask_id; "
    )

    log.write(
      Calendar
        .getInstance()
        .getTimeInMillis + " - Executing UPDATE scala_tasks SET task_desc = $task_desc  WHERE task_id = $editTask_id; \n"
    )
    println("==================================================")
  }

  //allows user to delete the task description of a row, chosen by taskID
  def deleteTask(
      connection: Connection,
      scanner: Scanner,
      log: PrintWriter
  ): Unit = {
    println("==================================================")
    val statement = connection.createStatement()
    println("Which task would you like to delete? (Choose by TASK ID)")
    var deleteTask_id = scanner.nextInt()
    scanner.nextLine()
    val resultSet = statement.executeUpdate(
      s"DELETE FROM scala_tasks WHERE task_id = $deleteTask_id; "
    )
    log.write(
      Calendar
        .getInstance()
        .getTimeInMillis + " - Executing DELETE FROM scala_tasks WHERE task_id = $deleteTask_id; \n"
    )
    println("==================================================")
  }

  def menuchoices(): Unit = {
    println("==================================================")
    println("What would you like to do?")
    println("1. Add new task")
    println("2. View existing tasks")
    println("3. Edit task")
    println("4. Delete task")
    println("5. Exit")
    println("==================================================")
  }
}
