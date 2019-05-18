plugins {
    `java-library`
    id("com.avast.gradle.docker-compose") version "0.9.4"
}

dependencies {
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-web")
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-data-jdbc")
    implementation(group = "org.postgresql", name = "postgresql")

    compileOnly(group = "org.projectlombok", name = "lombok")
    annotationProcessor(group = "org.projectlombok", name = "lombok")
}

dockerCompose {
    val bootRun by tasks
    isRequiredBy(bootRun)
    forceRecreate = true
    dockerComposeWorkingDirectory = "../docker/postgre"
}
