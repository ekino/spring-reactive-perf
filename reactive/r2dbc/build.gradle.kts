plugins {
    `java-library`
    id("com.avast.gradle.docker-compose") version "0.9.4"
}

dependencies {
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-webflux")
    implementation(group = "org.springframework.boot.experimental", name = "spring-boot-starter-data-r2dbc")
    implementation(group = "io.r2dbc", name = "r2dbc-postgresql")

    compileOnly(group = "org.projectlombok", name = "lombok")
    annotationProcessor(group = "org.projectlombok", name = "lombok")
}

dockerCompose {
    val bootRun by tasks
    isRequiredBy(bootRun)
    forceRecreate = true
    dockerComposeWorkingDirectory = "../../docker/postgre"
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot.experimental:spring-boot-dependencies-r2dbc:0.1.0.BUILD-SNAPSHOT")
    }
}
