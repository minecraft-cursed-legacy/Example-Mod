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
	val realArchivesBaseName: String by project
	archivesBaseName = realArchivesBaseName
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
	//In order to change the versions see the gradle.properties file.
	val minecraftVersion: String by project
	"minecraft"("com.mojang:minecraft:$minecraftVersion")
	val plasmaBuild: String by project
	"mappings"("io.github.minecraft-cursed-legacy:plasma:b1.7.3-build.$plasmaBuild")
	val loaderVersion: String by project
	"modImplementation"("io.github.minecraft-cursed-legacy:cursed-fabric-loader:$loaderVersion") {
		isTransitive = false
	}
	// You technically don't need the API, but it's extremely useful for not having to write the same code in every mod.
	val apiVersion: String by project
	"modImplementation"("io.github.minecraft-cursed-legacy:cursed-legacy-api:$apiVersion")
}
tasks {
	val javaVersion = JavaVersion.VERSION_1_8.toString()
	withType<JavaCompile> {
		// ensure that the encoding is set to UTF-8, no matter what the system default is
		// this fixes some edge cases with special characters not displaying correctly
		// see http://yodaconditions.net/blog/fix-for-java-file-encoding-problems-with-gradle.html
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
	// Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
	// if it is present.
	// If you remove this task, sources will not be generated.
	val sourcesJar by creating(Jar::class) {
		archiveClassifier.set("sources")
		from(sourceSets.main.get().allSource)
	}
	artifacts { archives(sourcesJar) }
}