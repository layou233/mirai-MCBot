plugins {
    val kotlinVersion = "1.4.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
//    id("application")
    id("net.mamoe.mirai-console") version "2.4-M1"
}

group = "layou233.mcbot"
version = "0.2.0"
//var mainClassName :String = "layou233.mcbot.test.RunMiraiKt"

repositories {
    mavenCentral()
    mavenLocal()
    maven(url = "https://dl.bintray.com/karlatemp/misc")
    jcenter()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://mirrors.huaweicloud.com/repository/maven")
}

dependencies {
    compileOnly("com.squareup.okhttp3:okhttp:4.10.0-RC1")
    //runtimeOnly("net.mamoe:mirai-login-solver-selenium:1.0-dev-16")
    testImplementation("com.squareup.okhttp3:okhttp:4.10.0-RC1")
}
