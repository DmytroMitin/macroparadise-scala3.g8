package $package$.handlers

import dotty.tools.dotc.core.Contexts.Context
import paradise3.api.{ExpansionInput, ExpansionOutcome, ParadiseAnnotationExpander}
import paradise3.api.helpers.ExpansionHelpers

final class GenerateGreetingHandler extends ParadiseAnnotationExpander:
  val annotationName: String = "$package$.annotations.generateGreeting"

  def expand(input: ExpansionInput)(using Context): ExpansionOutcome =
    ExpansionHelpers.withAnnotatedClassView(input): view =>
      ExpansionHelpers.addStringMethodToClass(
        input,
        methodName = "generatedGreeting",
        value = "Hello, " + view.className + "!"
      )
