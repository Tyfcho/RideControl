plugins {
    kotlin("jvm") version "2.1.20-Beta2"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "me.tyfcho"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
    maven("https://ci.mg-dev.eu/plugin/repository/everything/") {
        name = "mg-dev"
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.bergerkiller.bukkit:TCCoasters:1.21.4-v1-SNAPSHOT")
    implementation("org.yaml:snakeyaml:1.30")
    implementation("com.bergerkiller.bukkit:BKCommonLib:1.21.4-v1-SNAPSHOT")
    implementation("cloud.commandframework:cloud-bukkit:1.5.0")
    implementation("cloud.commandframework:cloud-kotlin-extensions:1.5.0")
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}
