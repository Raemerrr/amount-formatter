plugins {
    id("java")
    id("maven-publish")
    id("signing")
}

group = "io.github.raemerrr"
version = "0.0.1"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set("Amount Formatter")
                description.set("A simple library to format amounts")
                url.set("https://github.com/Raemerrr/amount-formatter")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                        distribution.set("repo")
                    }
                }

                developers {
                    developer {
                        id.set("Raemerrr")
                        name.set("Raemin Kang")
                        email.set("foals18@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/Raemerrr/amount-formatter.git")
                    developerConnection.set("scm:git:ssh://git@github.com:Raemerrr/amount-formatter.git")
                    url.set("https://github.com/Raemerrr/amount-formatter")
                }
            }
        }
    }

    repositories {
        maven {
            name = "OSSRH"
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = project.findProperty("ossrhUsername") as String? ?: System.getenv("OSSRH_USERNAME")
                password = project.findProperty("ossrhPassword") as String? ?: System.getenv("OSSRH_PASSWORD")
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(
            System.getenv("GPG_PRIVATE_KEY"),
            System.getenv("GPG_PASSPHRASE")
    )
    sign(publishing.publications["mavenJava"])
}

tasks.test {
    useJUnitPlatform()
}
