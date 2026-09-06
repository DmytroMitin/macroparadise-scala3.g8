#!/usr/bin/env bash
set -euo pipefail

template_root=$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd -P)
g8_root="$template_root/src/main/g8"

required_files=(
  ".java-version"
  "default.properties"
  "README.md"
  "build.sbt"
  "project/build.properties"
  "project/plugins.sbt"
  'macro-annotations/src/main/scala/$package$/annotations/GenerateGreeting.scala'
  'macro-handlers/src/main/scala/$package$/handlers/GenerateGreetingHandler.scala'
  'core/src/main/scala/$package$/core/Main.scala'
)

for relative_path in "${required_files[@]}"; do
  if [[ ! -f "$g8_root/$relative_path" ]]; then
    echo "missing required template file: $relative_path" >&2
    exit 1
  fi
done

if [[ $(wc -c < "$g8_root/.java-version") -ne 3 ]] || ! rg -q '^25$' "$g8_root/.java-version"; then
  echo "template .java-version must contain exactly 25" >&2
  exit 1
fi

if rg -n '0\.2\.0|SNAPSHOT|publishLocal|macroparadise-scala3/(?!\.g8)' "$g8_root" --pcre2; then
  echo "template contains a forbidden unpublished or checkout-local reference" >&2
  exit 1
fi

rg -q '^scala_version=3\.9\.0$' "$g8_root/default.properties"
rg -q '^sbt_version=1\.12\.15$' "$g8_root/default.properties"
rg -q '^macroparadise_version=0\.1\.1$' "$g8_root/default.properties"
rg -q 'sbt-macroparadise.*(0\.1\.1|\$macroparadise_version\$)' "$g8_root/project/plugins.sbt"
rg -q 'CrossVersion\.full' "$g8_root/build.sbt"
rg -q 'MacroParadiseIntegration\.precompiledProjects' "$g8_root/build.sbt"
rg -q 'macroParadiseCompilerProductVersion' "$g8_root/build.sbt"
rg -q 'dependsOn\(macroAnnotations % "provided->compile"\)' "$g8_root/build.sbt"

if rg -n '@compileTimeOnly' "$g8_root"; then
  echo "template marker must not use @compileTimeOnly" >&2
  exit 1
fi

if find "$g8_root/\$package\$" -type f -print -quit 2>/dev/null | rg -q .; then
  echo "Scala sources must be nested under their sbt subprojects" >&2
  exit 1
fi

echo "TEMPLATE_SOURCE_CONTRACT=PASS"
