plugins {
    alias(libs.plugins.moddevgradle)
}

val mcVersion = libs.versions.minecraft.get()
val modId = rootProject.property("mod_id") as String

base {
    archivesName.set("${rootProject.property("archives_base_name")}-${rootProject.property("mod_version")}+mc${mcVersion}-NeoForge")
}

neoForge {
    version = libs.versions.neoforge.get()

    parchment {
        mappingsVersion = libs.versions.parchment.get()
        minecraftVersion = mcVersion
    }

    mods {
        create(modId) {
            sourceSet(sourceSets.main.get())
        }
    }

    runs {
        create("client") {
            client()
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }

        create("server") {
            server()
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }
    }
}

repositories {
    maven("https://maven.parchmentmc.org")
    maven("https://maven.caffeinemc.net/releases") // Sodium API
}

dependencies {
    // https://maven.caffeinemc.net/#/releases/net/caffeinemc/sodium-neoforge-api
    compileOnly("net.caffeinemc:sodium-neoforge-api:${rootProject.property("sodium")}+mc${mcVersion}")
}

tasks.processResources {
    filesMatching("META-INF/neoforge.mods.toml") {
        expand(mapOf(
            "mod_id" to modId,
            "mod_name" to rootProject.property("mod_name"),
            "mod_version" to rootProject.property("mod_version"),
            "mod_description" to rootProject.property("mod_description"),
            "mod_authors" to rootProject.property("mod_authors"),
            "mod_license" to rootProject.property("mod_license"),
            "neoforge_version" to libs.versions.neoforge.get(),
            "minecraft_version_constraint" to rootProject.property("minecraft_version_constraint_forge")
        ))
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release = 21
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.jar {
    from("LICENSE") {
        rename { it }
    }
}