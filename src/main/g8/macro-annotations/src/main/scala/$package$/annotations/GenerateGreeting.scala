package $package$.annotations

import paradise3.api.expander

import scala.annotation.StaticAnnotation

@expander("$package$.handlers.GenerateGreetingHandler")
final class generateGreeting extends StaticAnnotation
