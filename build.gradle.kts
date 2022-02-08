val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val rsocketVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.6.10"
}

group = "com.example"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-websockets:$ktorVersion")
    implementation("io.rsocket.kotlin:rsocket-core:$rsocketVersion")
    implementation("io.rsocket.kotlin:rsocket-transport-ktor-server:$rsocketVersion")

    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")
    testImplementation("io.ktor:ktor-client-okhttp:$ktorVersion")
    testImplementation("io.rsocket.kotlin:rsocket-transport-ktor-client:$rsocketVersion")
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}