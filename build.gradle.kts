/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * Learn more about Gradle by exploring our samples at https://docs.gradle.org/6.7.1/samples
 */
plugins {
    antlr
    application
}

repositories {
    mavenCentral()
}

dependencies {
    antlr("org.antlr:antlr4:4.9")
}

tasks.generateGrammarSource {
    arguments = arguments + listOf("-visitor")
}

application {
    mainClass.set("TarpitInterpreter")
}
