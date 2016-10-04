package generators

import java.io.*
import templates.*
import templates.Family.*

private val COMMON_AUTOGENERATED_WARNING: String = """//
// NOTE THIS FILE IS AUTO-GENERATED by the GenerateStandardLib.kt
// See: https://github.com/JetBrains/kotlin/tree/master/libraries/stdlib
//"""

/**
 * Generates methods in the standard library which are mostly identical
 * but just using a different input kind.
 *
 * Kinda like mimicking source macros here, but this avoids the inefficiency of type conversions
 * at runtime.
 */
fun main(args: Array<String>) {
    require(args.size == 1) { "Expecting Kotlin project home path as an argument" }

    val outDir = File(File(args[0]), "libraries/stdlib/src/generated")
    require(outDir.exists()) { "$outDir doesn't exist!" }

    val jsCoreDir = File(args[0], "js/js.libraries/src/core")
    require(jsCoreDir.exists()) { "$jsCoreDir doesn't exist!" }

    generateCollectionsAPI(outDir)
    generateCollectionsJsAPI(jsCoreDir)

}

fun List<GenericFunction>.writeTo(file: File, builder: GenericFunction.() -> String) {
    println("Generating file: $file")
    val its = FileWriter(file)

    its.use {
        its.append("package kotlin.collections\n\n")
        its.append("$COMMON_AUTOGENERATED_WARNING\n\n")
        for (t in this.sortedBy { it.signature }) {
            its.append(t.builder())
        }
    }
}

fun List<ConcreteFunction>.writeTo(outDir: File, sourceFile: SourceFile) {
    val file = File(outDir, sourceFile.fileName)
    println("Generating file: $file")
    val its = FileWriter(file)

    its.use {
        if (sourceFile.multifile) {
            its.append("@file:kotlin.jvm.JvmMultifileClass\n")
        }
        its.append("@file:kotlin.jvm.JvmName(\"${sourceFile.jvmClassName}\")\n\n")
        its.append("package ${sourceFile.packageName ?: "kotlin"}\n\n")
        its.append("$COMMON_AUTOGENERATED_WARNING\n\n")
        its.append("import kotlin.comparisons.*\n")

        for (f in this) {
            f.textBuilder(its)
        }
    }
}
