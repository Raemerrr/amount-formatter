plugins {
    id("java")
    id("maven-publish")
    id("signing")
}

group = "io.github.raemerrr"
version = System.getenv("VERSION")
rootProject.description = "A simple library to format amounts"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withJavadocJar()
    withSourcesJar()
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

signing {
    isRequired = System.getenv("OSSRH_USERNAME") != null
    useInMemoryPgpKeys(
            System.getenv("GPG_PRIVATE_KEY"),
            System.getenv("GPG_PASSPHRASE")
    )
    sign(publishing.publications)
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {

            from(components["java"])

            pom {

                name.set("AmountFormatter")
                description.set(project.description)
                url.set("https://github.com/Raemerrr/amount-formatter")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/Raemerrr/amount-formatter/blob/main/LICENSE")
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
            name = "MavenCentral"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = findProperty("ossrhUsername") as String? ?: System.getenv("OSSRH_USERNAME")
                password = findProperty("ossrhPassword") as String? ?: System.getenv("OSSRH_PASSWORD")
            }
        }
    }
}
