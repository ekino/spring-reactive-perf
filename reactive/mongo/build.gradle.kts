plugins {
    `java-library`
    id("com.avast.gradle.docker-compose") version "0.9.4"
}

dependencies {
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-webflux")
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-data-mongodb-reactive")

    compileOnly(group = "org.projectlombok", name = "lombok")
    annotationProcessor(group = "org.projectlombok", name = "lombok")
}

dockerCompose {
    val bootRun by tasks
    isRequiredBy(bootRun)
    forceRecreate = true
    dockerComposeWorkingDirectory = "../../docker/mongo"
}
