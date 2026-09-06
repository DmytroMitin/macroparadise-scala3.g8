# Macro-Paradise Scala 3 Giter8 template

This repository contains a small starter for the experimental
[Macro-Paradise Scala 3](https://github.com/DmytroMitin/macroparadise-scala3)
compiler plugin.

The public GitHub template has been successfully exercised with:

```sh
sbt new DmytroMitin/macroparadise-scala3.g8
```

The generated default project has been run successfully from the CLI and from
IntelliJ with Build and Run delegated to sbt, producing `Hello, Greeter!`.

The generated build requires JDK 25 and sbt 1.12.15. It defaults to exact
Scala 3.9.0 and also accepts exact Scala 3.3.8 or 3.8.4. All Macro-Paradise
dependencies use the released public `0.1.1` line.

The generated project contains three separate sbt projects:

- `macro-annotations/` defines a user-owned marker annotation;
- `macro-handlers/` compiles an external handler;
- `core/` is the ordinary consumer and prints a greeting produced by a
  generated method.

The marker-only project uses `provided->compile`: its API is present while the
consumer compiles and absent at runtime. The handler is a compile-time tool and
is not placed on the consumer's normal runtime dependency graph. Macro-Paradise
remains experimental; pin the exact Scala and Macro-Paradise versions shown by
the template.

## JDK selection hint

The root `.java-version` helps maintainers select JDK 25, and the template
copies the same hint into generated projects. The actual contract is Java
feature version 25, not one vendor or patch; the generated build requirement
remains authoritative.

With jenv, register a JDK 25 installation and ensure its `25` alias exists:

```sh
jenv add /path/to/jdk-25
jenv versions
```

SDKMAN users can instead select any available JDK 25 candidate:

```sh
sdk list java
sdk install java <a-JDK-25-candidate>
sdk use java <the-same-JDK-25-candidate>
```

No `.sdkmanrc` is checked in because SDKMAN candidate identifiers normally pin
a specific vendor and patch. Users may create one locally with `sdk env init`
if they prefer that workflow.

## Local template check

Run the source-contract check with:

```sh
bash scripts/verify-template.sh
```

The template itself is under `src/main/g8/` using the conventional Giter8
source layout.
