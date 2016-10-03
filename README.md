sbt-slf4j
=========

| Service                   | Status |
| ------------------------- | ------ |
| Travis (Linux CI)         | [![Build Status](https://travis-ci.org/larsrh/sbt-slf4j.svg?branch=fork)](https://travis-ci.org/larsrh/sbt-slf4j) |

_Note:_ This is a fork of [sbt-slf4j](https://github.com/sbt-slf4j/sbt-slf4j).
Credit goes to the original authors (see link).

This is an SLF4J backend that binds to an underlying SBT log.

To use this plugin, add the following to your build -

## project/plugins.sbt

```scala
libraryDependencies += "com.vast.sbt" %% "sbt-sl4fj" % "0.2.1"
```

In tasks that call out to libraries that use slf4j, wrap the task implementation with the following

```scala

import sbt._
import com.vast.sbtlogger.SbtLogger._

object SomeBuild {

 lazy val someTaskKey = taskKey[Unit]("Does some sort of work")

 lazy val someBuildSettings = Seq(
    someTaskKey := {
      withLogger(streams.value.log) {
        ...
        //do some work
        ...
      }
    }
 )
}

```

This will bind the task-scoped streams log to slf4j for the thread executing the task and any threads spawned from it.
Since SBT will close the stream after the task body itself finishes execution, it's your responsibility to ensure that
any spwaned threads complete before the main task body does - otherwise, you'll get an exception stating the
stream has already been closed.
