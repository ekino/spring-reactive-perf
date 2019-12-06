plugins {
    `java-library`
    scala
}

dependencies {
    implementation(group = "org.scala-lang", name = "scala-library", version = "2.12.7")
    implementation(group = "io.gatling", name = "gatling-app", version = "3.3.1")
    implementation(group = "io.gatling.highcharts", name = "gatling-charts-highcharts", version = "3.3.1")
}

tasks {

    create<JavaExec>("loadTest") {
        description = "Run Gatling tests"
        val reportDir = File("$buildDir/reports/gatling")
        reportDir.deleteRecursively()
        reportDir.mkdirs()

        systemProperties = System.getProperties() as Map<String, Object>

        classpath = sourceSets["test"].runtimeClasspath + configurations.testCompile.get() + configurations.compile.get()

        val app = systemProperties["simulation"] as String?

        main = "io.gatling.app.Gatling"
        args = listOf(
                "-s", app ?: "MvcSimulation",         // -- simulation
                "-rf", "$buildDir/reports/gatling"    // -- results-folder
        )

        doLast {
            val type = when (app) {
                "R2dbcSimulation", "WebFluxSimulation", "RMongoSimulation" -> "reactive"
                else -> "mvc"
            }

            val subType = when (app) {
                "R2dbcSimulation" -> "r2dbc-postgre"
                "WebFluxSimulation" -> "webflux"
                "RMongoSimulation" -> "mongo"
                "MvcSimulation" -> "classic"
                "PostgreSimulation" -> "postgre"
                else -> ""
            }

            val users = systemProperties["users"] as String?
            val requestCount = systemProperties["requests"] as String?
            val minDelay = systemProperties["minDelay"] as String?
            val maxDelay = systemProperties["maxDelay"] as String?
            val strategy = if (systemProperties["ramp"] as Boolean? == false) "atonce" else "ramp"
            val reportPath = "${parent?.projectDir}/reports/$type/$subType/$users-$requestCount${getDelays(minDelay, maxDelay)}-$strategy"

            reportDir
                    .takeIf { it.exists() && it.listFiles().isNotEmpty() }?.listFiles()?.get(0)
                    ?.copyRecursively(File(reportPath), true)

            println("Report saved at $reportPath/index.html")
        }
    }
}

fun getDelays(maxDelay: String?, minDelay: String?): String {
    var delays = ""
    if (minDelay != null) {
        delays += "-$minDelay"
    }
    if (maxDelay != null) {
        delays += "-$maxDelay"
    }
    return delays
}
