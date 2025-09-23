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
        implementation("org.slf4j:slf4j-simple:1.7.36")
        testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }
}

tasks.test {
    useJUnitPlatform()
}