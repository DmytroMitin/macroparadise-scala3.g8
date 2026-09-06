# $name$

A minimal external-handler project generated from the Macro-Paradise Scala 3
Giter8 template.

Requirements:

- JDK 25
- sbt $sbt_version$
- exact Scala $scala_version$
- released Macro-Paradise $macroparadise_version$

The generated `.java-version` contains `25` as a version-manager convenience.
The build itself requires Java feature version 25 without pinning one vendor or
patch. jenv users need a registered `25` alias; SDKMAN users can select any JDK
25 candidate. No `.sdkmanrc` is included because its candidate identifier would
normally overconstrain that feature-version contract.

The build has three projects: `macro-annotations` owns the marker annotation,
`macro-handlers` compiles the precompiled handler, and `core` consumes the
marker with the marker-only `provided->compile` mapping. The marker is present
while `core` compiles but is absent from its ordinary runtime classpath. The
handler is also a compile-time tool and does not appear on the ordinary `core`
runtime dependency graph.

If you later split markers or handlers across multiple producer projects, see
the product integration guide for the multi-local helper overload.

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

For IntelliJ, explicitly select JDK 25 for both the project SDK and the JVM
launching sbt, then delegate packaged-handler Build and Run actions to sbt.
The `.java-version` hint does not configure those IDE settings automatically,
and native JPS is not the qualified external-handler compilation path.
