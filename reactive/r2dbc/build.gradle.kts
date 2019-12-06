plugins {
    `java-library`
    id("com.avast.gradle.docker-compose") version "0.10.4"
}

dependencies {
    implementation(platform("org.springframework.boot.experimental:spring-boot-bom-r2dbc:0.1.0.M3"))
    implementation(group = "org.springframework.boot.experimental", name = "spring-boot-starter-data-r2dbc")
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-webflux")
    implementation(group = "io.r2dbc", name = "r2dbc-postgresql")
    implementation(group = "io.r2dbc", name = "r2dbc-spi")

    compileOnly(group = "org.projectlombok", name = "lombok")
    annotationProcessor(group = "org.projectlombok", name = "lombok")
}

dockerCompose {
    isRequiredBy(tasks.named("bootRun"))
    forceRecreate = true
    dockerComposeWorkingDirectory = "../../docker/postgre"
}
