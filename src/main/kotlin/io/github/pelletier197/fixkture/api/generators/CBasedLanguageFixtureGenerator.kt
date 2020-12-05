package io.github.pelletier197.fixkture.api.generators

import io.github.pelletier197.fixkture.domain.PsiElementInstantiationStatementBuilderContext
import io.github.pelletier197.fixkture.domain.InstantiationStatementGenerator
import io.github.pelletier197.fixkture.domain.RecursiveClassInstantiationStatementGeneratorFactory

abstract class CBasedLanguageFixtureGenerator : FixtureGenerator {
    override fun generateFixture(context: FixtureGenerationContext) {
        val project = context.project
        val targetClass = selectTargetTargetClass(project) ?: return

        val statementGenerator = RecursiveClassInstantiationStatementGeneratorFactory().createInstantiationStatement(
                context = PsiElementInstantiationStatementBuilderContext(
                        targetElement = targetClass,
                        constructorSelector = { psiClass -> selectTargetConstructor(psiClass, project) }
                )
        )

        generateFixture(
                statementGenerator = statementGenerator,
                context = context
        )
    }

    abstract fun generateFixture(statementGenerator: InstantiationStatementGenerator, context: FixtureGenerationContext)
}