plugins {
    val kotlinVersion = "1.4.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "1.0-RC-dev-28"
}

group = "layou233.mcbot"
version = "0.2.0"

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://mirrors.huaweicloud.com/repository/maven")
}

dependencies {
    compileOnly("com.squareup.okhttp3:okhttp:4.10.0-RC1")
    testImplementation("com.squareup.okhttp3:okhttp:4.10.0-RC1")
}
