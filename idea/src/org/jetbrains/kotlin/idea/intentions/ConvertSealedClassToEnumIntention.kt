/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.idea.intentions

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.util.TextRange
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.refactoring.util.CommonRefactoringUtil
import org.jetbrains.kotlin.asJava.unwrapped
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.idea.caches.resolve.resolveToDescriptor
import org.jetbrains.kotlin.idea.refactoring.runSynchronouslyWithProgress
import org.jetbrains.kotlin.idea.search.declarationsSearch.HierarchySearchRequest
import org.jetbrains.kotlin.idea.search.declarationsSearch.searchInheritors
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtObjectDeclaration
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.psi.KtSuperTypeCallEntry
import org.jetbrains.kotlin.psi.psiUtil.containingClassOrObject
import org.jetbrains.kotlin.resolve.descriptorUtil.getSuperClassNotAny

class ConvertSealedClassToEnumIntention : SelfTargetingRangeIntention<KtClass>(KtClass::class.java, "Convert to enum class") {
    override fun applicabilityRange(element: KtClass): TextRange? {
        if (!element.isSealed()) return null

        val classDescriptor = element.resolveToDescriptor() as ClassDescriptor
        if (classDescriptor.getSuperClassNotAny() != null) return null

        return element.nameIdentifier?.textRange
    }

    override fun applyTo(element: KtClass, editor: Editor?) {
        val project = element.project

        val subclasses = project.runSynchronouslyWithProgress("Searching inheritors...", true) {
            HierarchySearchRequest(element, element.useScope, false)
                    .searchInheritors()
                    .asSequence()
                    .mapNotNull { it.unwrapped }
                    .toList()
        } ?: return

        if (subclasses.any {
            it !is KtObjectDeclaration || it.containingClassOrObject != element || it.getSuperTypeListEntries().size != 1
        }) {
            return CommonRefactoringUtil.showErrorHint(
                    project,
                    editor,
                    "All inheritors must be nested objects of the class itself and may not inherit from other classes or interfaces",
                    text,
                    null)
        }

        val needSemicolon = element.declarations.size > subclasses.size

        val psiFactory = KtPsiFactory(element)

        val comma = psiFactory.createComma()
        val semicolon = psiFactory.createSemicolon()

        val constructorCallNeeded = element.hasExplicitPrimaryConstructor() || element.getSecondaryConstructors().isNotEmpty()
        val entriesToAdd = subclasses.mapIndexed { i, subclass ->
            subclass as KtObjectDeclaration

            val entryText = buildString {
                append(subclass.name)
                if (constructorCallNeeded) {
                    append((subclass.getSuperTypeListEntries().firstOrNull() as? KtSuperTypeCallEntry)?.valueArgumentList?.text ?: "()")
                }
            }
            val entry = psiFactory.createEnumEntry(entryText)

            subclass.getBody()?.let { body -> entry.add(body) }

            if (i < subclasses.lastIndex) {
                entry.add(comma)
            }
            else if (needSemicolon) {
                entry.add(semicolon)
            }

            entry
        }

        subclasses.forEach { it.delete() }

        element.removeModifier(KtTokens.SEALED_KEYWORD)
        element.addModifier(KtTokens.ENUM_KEYWORD)

        if (entriesToAdd.isNotEmpty()) {
            val firstEntry = entriesToAdd
                    .reversed()
                    .map { element.addDeclarationBefore(it, null) }
                    .last()
            // TODO: Add formatter rule
            firstEntry.parent.addBefore(psiFactory.createNewLine(), firstEntry)
        }
        else if (needSemicolon) {
            element.declarations.firstOrNull()?.let { anchor ->
                val delimiter = anchor.parent.addBefore(semicolon, anchor)
                CodeStyleManager.getInstance(project).reformat(delimiter)
            }
        }
    }
}