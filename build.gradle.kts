import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.gradle.node.yarn.task.YarnTask
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.github.node-gradle.node")
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}

group = "dev.bug"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-logging")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("org.liquibase:liquibase-core:4.15.0")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks {

    test {
        useJUnitPlatform()
        testLogging {
            events = setOf(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED)
        }
    }

    val install = create<YarnTask>("install-dependencies") {
        workingDir.set(file("${project.projectDir}/src/main/webapp"))
        args.set(listOf("install"))
    }

    val build = create<YarnTask>("build-frontend") {
        dependsOn(install)
        workingDir.set(file("${project.projectDir}/src/main/webapp"))
        args.set(listOf("build"))
    }

    val cleanup = create<Delete>("cleanup-frontend") {
        delete("${project.projectDir}/src/main/webapp/build")
    }

    val copy = create<Copy>("copy-frontend") {
        dependsOn(build)
        from("${project.projectDir}/src/main/webapp/build")
        into("${rootDir}/build/resources/main/static/.")
    }

    compileJava {
        dependsOn(copy)
    }

    clean {
        dependsOn(cleanup)
    }
}

node {
    download.set(true)
    version.set("18.7.0")
    yarnVersion.set("1.22.17")
}