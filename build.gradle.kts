import com.github.spotbugs.snom.Confidence
import com.github.spotbugs.snom.Effort
import com.github.spotbugs.snom.SpotBugsTask
import info.solidsoft.gradle.pitest.PitestTask

plugins {
    id("java")
    application
    checkstyle
    jacoco
    id("com.github.spotbugs") version "6.0.25"
    id("info.solidsoft.pitest") version "1.15.0"
}

application {
    mainClass = "ui.Main"
}

group = "nu.csse.sqe"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-suite")
    testImplementation("io.cucumber:cucumber-java:7.18.1")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.18.1")
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.8.6")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(11)
    }
}

tasks.compileJava {
    options.release = 11
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

jacoco {
    toolVersion = "0.8.12"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        xml.required = true
        csv.required = false
        html.required = true
    }
}

checkstyle {
    toolVersion = "10.12.0"
    configFile = file("config/checkstyle/checkstyle.xml")
    isIgnoreFailures = false
}

tasks.named<Checkstyle>("checkstyleTest") {
    enabled = false
}

spotbugs {
    ignoreFailures = false
    showStackTraces = true
    showProgress = true
    effort = Effort.DEFAULT
    reportLevel = Confidence.DEFAULT
    maxHeapSize = "1g"
}

tasks.named<SpotBugsTask>("spotbugsTest") {
    enabled = false
}

tasks.spotbugsMain {
    reports.create("html") {
        required = true
        outputLocation = layout.buildDirectory.file("reports/spotbugs/spotbugs.html")
        setStylesheet("fancy-hist.xsl")
    }
}

pitest {
    targetClasses.set(listOf("domain.*"))
    targetTests.set(listOf("domain.*"))
    junit5PluginVersion.set("1.2.1")
    threads.set(4)
    outputFormats.set(listOf("HTML", "XML"))
    timestampedReports.set(false)
}
