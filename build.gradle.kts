plugins {
    `kotlin-dsl`
    id("org.springframework.boot") version "2.2.0.M3"
}

allprojects {
    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/snapshot") }
        maven { url = uri("http://repo.spring.io/libs-snapshot") }
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
