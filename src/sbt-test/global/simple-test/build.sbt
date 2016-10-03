import com.vast.sbtlogger.SbtLogger._

name := "simple-test"

val msg = "Hello slf4j!"
val logger = org.slf4j.LoggerFactory.getLogger("simple-test-build")

TaskKey[Unit]("testSLF4J") := {
  val mocked = new MockLogger
  withLogger(mocked) {
    logger.info(msg)
  }
  if (!mocked.messages.contains(msg))
    sys.error("failed to log")
}

TaskKey[Unit]("failSLF4J") := {
  // this should fail if no logger is set
  logger.info(msg)
}
