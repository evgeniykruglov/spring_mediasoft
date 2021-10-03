plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.5")
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    testImplementation("junit", "junit", "4.12")
    implementation ("org.springframework.boot:spring-boot-starter:2.5.4")
    implementation("org.springframework:spring-web:5.3.10")
    compileOnly("org.projectlombok:lombok:1.18.20")
    annotationProcessor("org.projectlombok:lombok:1.18.20")
}