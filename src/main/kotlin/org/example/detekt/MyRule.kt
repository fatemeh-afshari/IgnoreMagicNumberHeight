package org.example.detekt

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtProperty

class IgnoreHeightMagicNumber(config: Config) : Rule(config) {
    override val issue: Issue
        get() = Issue(javaClass.simpleName, Severity.Style, "Height magic number", Debt.FIVE_MINS)

    override fun visitKtFile(file: KtFile) {
        file.declarations.forEach { declaration ->
            if (declaration is KtProperty && declaration.name?.contains(
                    "height",
                    ignoreCase = true
                ) == true && declaration.initializer?.text?.endsWith("dp") == true
            ) {
                return
            }
        }
        super.visitKtFile(file)
    }

    override fun visitProperty(property: KtProperty) {
        if (property.name?.contains("height", ignoreCase = true) == true && isDpValue(property)) {
            // Ignore magic number checks for this property
            return
        }
        super.visitProperty(property)
        // Check if the property name contains 'height' and is a DP value
    }

    private fun isDpValue(property: KtProperty): Boolean {
        // Implement logic to determine if the property value is in DP
        // This might involve checking the property's initializer or type
        // For example, check if it ends with 'dp'
        return property.initializer?.text?.endsWith("dp") == true
    }
}
