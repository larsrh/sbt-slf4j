organization := "info.hupel.fork.com.vast.sbt"
name := "sbt-slf4j"

description := "An SLF4j backend that utilizes the SBT TaskStreams log."

sbtPlugin := true

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.19"

homepage := Some(url("https://github.com/larsrh/sbt-slf4j"))

licenses += ("Apache 2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html"))

startYear := Some(2014)

pomExtra := (
  <developers>
    <developer>
      <id>dpratt@vast.com</id>
      <name>David Pratt</name>
      <url>https://github.com/vast-engineering</url>
    </developer>
    <developer>
      <id>larsrh</id>
      <name>Lars Hupel</name>
      <url>http://lars.hupel.info</url>
    </developer>
  </developers>
  <scm>
    <connection>scm:git:github.com/larsrh/sbt-slf4j.git</connection>
    <developerConnection>scm:git:git@github.com:larsrh/sbt-slf4j.git</developerConnection>
    <url>https://github.com/larsrh/sbt-slf4j</url>
  </scm>
)


// Release stuff

import ReleaseTransformations._

credentials += Credentials(
  Option(System.getProperty("build.publish.credentials")) map (new File(_)) getOrElse (Path.userHome / ".ivy2" / ".credentials")
)

sonatypeProfileName := "info.hupel"

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  ReleaseStep(action = Command.process("publishSigned", _)),
  setNextVersion,
  commitNextVersion,
  ReleaseStep(action = Command.process("sonatypeReleaseAll", _))
)
