plugins {
    `kotlin-dsl`
    id("org.springframework.boot") version "2.6.1"
}

allprojects {
    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/snapshot") }
        maven { url = uri("https://repo.spring.io/libs-snapshot") }
    }
}

subprojects {
    apply {
        if (project.name != "gatling-tests") {
            plugin("io.spring.dependency-management")
            plugin("org.springframework.boot")
        }
    }
}
