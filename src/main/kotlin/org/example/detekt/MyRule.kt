package org.example.detekt

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtProperty

class IgnoreHeightMagicNumber(config: Config) : Rule(config) {
    override val issue: Issue
        get() = Issue(javaClass.simpleName, Severity.Style, "Height magic number", Debt())

    override fun visitProperty(property: KtProperty) {
        super.visitProperty(property)
        // Check if the property name contains 'height' and is a DP value
        if (property.name?.contains("height", ignoreCase = true) == true && isDpValue(property)) {
            // Ignore magic number checks for this property
            return
        }
    }

    private fun isDpValue(property: KtProperty): Boolean {
        // Implement logic to determine if the property value is in DP
        // This might involve checking the property's initializer or type
        // For example, check if it ends with 'dp'
        return property.initializer?.text?.endsWith("dp") == true
    }
}
