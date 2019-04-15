plugins {
    `java-library`
}

dependencies {
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-webflux")

    compileOnly(group = "org.projectlombok", name = "lombok")
    annotationProcessor(group = "org.projectlombok", name = "lombok")
}
