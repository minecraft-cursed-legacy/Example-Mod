buildscript {
	repositories {
		mavenCentral()
		maven("https://maven.fabricmc.net/") { name = "Fabric" }
		maven("https://jitpack.io/") { name = "Jitpack" }
		maven("https://storage.googleapis.com/devan-maven/") { name = "HalfOf2" }
	}
	dependencies {
		classpath("com.github.Chocohead:Fabric-Loom:d823377")
	}
}
plugins { java }
apply { plugin("fabric-loom") }
base {
	val archivesBaseNameTwo: String by project
	archivesBaseName = archivesBaseNameTwo
}
val modVersion: String by project
version = modVersion
val mavenGroup: String by project
group = mavenGroup
repositories {
	maven("https://jitpack.io/") { name = "Jitpack" }
	maven("https://storage.googleapis.com/devan-maven/") { name = "HalfOf2" }
}
dependencies {
	val minecraftVersion: String by project
	"minecraft"("com.mojang:minecraft:$minecraftVersion")
	val plasmaBuild: String by project
	"mappings"("io.github.minecraft-cursed-legacy:plasma:b1.7.3-build.$plasmaBuild")
	val loaderVersion: String by project
	"modImplementation"("io.github.minecraft-cursed-legacy:cursed-fabric-loader:$loaderVersion") {
		isTransitive = false
	}
	val apiVersion: String by project
	"modImplementation"("io.github.minecraft-cursed-legacy:cursed-legacy-api:$apiVersion")
}
tasks {
	val javaVersion = JavaVersion.VERSION_1_8.toString()
	withType<JavaCompile> {
		options.encoding = "UTF-8"
		sourceCompatibility = javaVersion
		targetCompatibility = javaVersion
	}
	processResources {
		duplicatesStrategy = DuplicatesStrategy.INCLUDE
		inputs.property("version", project.version)
		from(sourceSets["main"].resources.srcDirs) {
			include("fabric.mod.json")
			expand(mutableMapOf("version" to project.version))
		}
		from(sourceSets["main"].resources.srcDirs) { exclude("fabric.mod.json") }
	}
	val sourcesJar by creating(Jar::class) {
		archiveClassifier.set("sources")
		from(sourceSets.main.get().allSource)
	}
	artifacts { archives(sourcesJar) }
}