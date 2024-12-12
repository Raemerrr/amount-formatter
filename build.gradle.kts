import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("java")
    id("com.vanniktech.maven.publish") version "0.28.0"
    id("signing")
}

rootProject.description = "A simple library to format amounts"

signing {
    isRequired = System.getenv("OSSRH_USERNAME") != null
    useInMemoryPgpKeys(
            System.getenv("GPG_PRIVATE_KEY"),
            System.getenv("GPG_PASSPHRASE")
    )
    sign(publishing.publications)
}

mavenPublishing {
    coordinates(
            groupId = "io.github.raemerrr",
            artifactId = "amount-formatter",
            version = System.getenv("VERSION")
    )

    pom {
        name.set(project.rootProject.name)
        description.set(project.description)
        inceptionYear.set("2024")
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

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
}
