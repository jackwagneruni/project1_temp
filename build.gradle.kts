plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    dependencies {
        implementation("com.jayway.jsonpath:json-path:2.7.0")

        testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }
}

tasks.test {
    useJUnitPlatform()
}