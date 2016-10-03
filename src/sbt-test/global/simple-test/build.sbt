import com.vast.sbtlogger.SbtLogger._

name := "simple-test"

val msg = "Hello slf4j!"

TaskKey[Unit]("testSLF4J") := {
  val mocked = new MockLogger
  withLogger(mocked) {
    val logger = org.slf4j.LoggerFactory.getLogger("simple-test-build")
    logger.info(msg)
  }
  if (!mocked.messages.contains(msg))
    sys.error("failed to log")
}

TaskKey[Unit]("failSLF4J") := {
  val mocked = new MockLogger
  val logger = org.slf4j.LoggerFactory.getLogger("simple-test-build")
  logger.info(msg)
  if (!mocked.messages.contains(msg))
    sys.error("failed to log")
}
