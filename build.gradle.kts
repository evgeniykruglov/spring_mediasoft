plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.4")
    implementation("org.springframework.boot:spring-boot-starter:2.5.4")
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("org.springframework:spring-web:5.3.10")
    compileOnly("org.projectlombok:lombok:1.18.20")
    annotationProcessor("org.projectlombok:lombok:1.18.20")
    implementation("org.flywaydb:flyway-core:7.15.0")
}