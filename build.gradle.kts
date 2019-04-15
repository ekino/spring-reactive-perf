plugins {
    `kotlin-dsl`
    id("org.springframework.boot") version "2.1.4.RELEASE" apply false
}

allprojects {
    repositories {
        mavenCentral()
        maven { setUrl("http://repo.spring.io/libs-snapshot") }
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


