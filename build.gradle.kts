import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("java")
    id("com.vanniktech.maven.publish") version "0.28.0"
    id("signing")
}

group = "io.github.raemerrr"
version = System.getenv("VERSION")

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
}

repositories {
    mavenCentral()
}

dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates("io.github.raemerrr", "amount-formatter", System.getenv("VERSION"))

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

signing {
    useInMemoryPgpKeys(
            System.getenv("GPG_PRIVATE_KEY"),
            System.getenv("GPG_PASSPHRASE")
    )
    sign(publishing.publications)
}

tasks.test {
    useJUnitPlatform()
}
