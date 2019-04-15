plugins {
    `java-library`
    id("com.avast.gradle.docker-compose") version "0.9.1"
}

dependencies {
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-webflux")
    implementation(group = "org.springframework.data", name = "spring-data-r2dbc", version = "1.0.0.M1")
    implementation(group = "io.r2dbc", name = "r2dbc-postgresql", version = "1.0.0.M7")
    implementation(group = "io.r2dbc", name = "r2dbc-spi", version = "1.0.0.M6")

    compileOnly(group = "org.projectlombok", name = "lombok")
    annotationProcessor(group = "org.projectlombok", name = "lombok")
}

dockerCompose {
    val bootRun by tasks
    isRequiredBy(bootRun)
    forceRecreate = true
    dockerComposeWorkingDirectory = "../../docker/postgre"
}
