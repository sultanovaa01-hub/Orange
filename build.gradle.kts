plugins {
    id("java")
   id("io.qameta.allure") version "2.12.0"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
configurations.all {
    exclude(group = "org.slf4j", module = "slf4j-simple")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("com.codeborne:selenide:7.16.0")
    implementation("com.github.javafaker:javafaker:1.0.2")

    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    testCompileOnly("org.projectlombok:lombok:1.18.36")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.36")

    implementation("org.slf4j:slf4j-simple:2.0.9")
    implementation("org.aeonbits.owner:owner:1.0.12")

    implementation("io.qameta.allure:allure-java-commons:2.25.0")
    testImplementation("io.qameta.allure:allure-selenide:2.25.0")
    testImplementation("io.qameta.allure:allure-junit5:2.25.0")

    implementation("io.rest-assured:rest-assured:6.0.0")

    implementation("org.apache.logging.log4j:log4j-core:2.25.3")
    implementation("org.apache.logging.log4j:log4j-api:2.25.3")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.25.3")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.21.3")

    testImplementation("org.assertj:assertj-core:3.27.7")

    implementation("org.postgresql:postgresql:42.7.11")

    implementation("commons-dbutils:commons-dbutils:1.8.1")
}

allure {
    version.set("2.25.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Test>("testUser") {
    useJUnitPlatform {
        includeTags("user")
    }
    testClassesDirs = sourceSets["test"].output.classesDirs
    classpath = sourceSets["test"].runtimeClasspath
}