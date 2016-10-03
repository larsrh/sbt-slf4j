import scala.collection.mutable.ListBuffer
import sbt._

class MockLogger extends Logger {
  val messages: ListBuffer[String] = ListBuffer()

  def trace(t: => Throwable): Unit = ()
  def success(message: => String): Unit = messages += message
  def log(level: Level.Value, message: => String): Unit = messages += message
}
