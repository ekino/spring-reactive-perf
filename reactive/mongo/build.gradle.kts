plugins {
    `java-library`
    id("com.avast.gradle.docker-compose") version "0.10.4"
}

dependencies {
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-webflux")
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-data-mongodb-reactive")

    compileOnly(group = "org.projectlombok", name = "lombok")
    annotationProcessor(group = "org.projectlombok", name = "lombok")
}

dockerCompose {
    isRequiredBy(tasks.named("bootRun"))
    forceRecreate = true
    dockerComposeWorkingDirectory = "../../docker/mongo"
}
