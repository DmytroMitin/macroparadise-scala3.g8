# $name$

A minimal external-handler project generated from the Macro-Paradise Scala 3
Giter8 template.

Requirements:

- JDK 25
- sbt $sbt_version$
- exact Scala $scala_version$
- released Macro-Paradise $macroparadise_version$

The build has three projects: `macro-annotations` owns the marker annotation,
`macro-handlers` compiles the precompiled handler, and `core` consumes the
marker normally. The handler does not appear on the ordinary `core` runtime
dependency graph.

Compile and run the example:

```sh
sbt "core/run"
```

Expected output:

```text
Hello, Greeter!
```

Macro-Paradise is experimental. This starter deliberately pins released
coordinates and an exact supported Scala compiler line.
