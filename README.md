# Macro-Paradise Scala 3 Giter8 template

This repository contains a small starter for the experimental
[Macro-Paradise Scala 3](https://github.com/DmytroMitin/macroparadise-scala3)
compiler plugin.

After this template is published and independently checked from GitHub, the
intended command will be:

```sh
sbt new DmytroMitin/macroparadise-scala3.g8
```

Remote-command verification is still pending. Until that check succeeds, use
this repository only as a locally qualified template source.

The generated build requires JDK 25 and sbt 1.12.15. It defaults to exact
Scala 3.9.0 and also accepts exact Scala 3.3.8 or 3.8.4. All Macro-Paradise
dependencies use the released public `0.1.1` line.

The generated project contains three separate sbt projects:

- `macro-annotations/` defines a user-owned marker annotation;
- `macro-handlers/` compiles an external handler;
- `core/` is the ordinary consumer and prints a greeting produced by a
  generated method.

The handler is a compile-time tool and is not placed on the consumer's normal
runtime dependency graph. Macro-Paradise remains experimental; pin the exact
Scala and Macro-Paradise versions shown by the template.

## Local template check

Run the source-contract check with:

```sh
bash scripts/verify-template.sh
```

The template itself is under `src/main/g8/` using the conventional Giter8
source layout.
