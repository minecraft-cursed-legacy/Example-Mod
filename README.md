# Minecraft Cursed Legacy Example Mod

The (unofficial) [Fabric](https://fabricmc.net/) home for 1.2.5 and Beta 1.7.3 - if you want the newer versions see [here](https://github.com/FabricMC/fabric-example-mod).

## Setup
1. Edit build.gradle and mod.json to suit your needs.
    * The "mixins" object can be removed from mod.json if you do not need to use mixins.
    * Please remember to replace all occurrences of "modid" with your own mod ID.
2. Run the following command, replacing "eclipse" with the command your specific ide if you are not using eclipse:

```
./gradlew eclipse
```

NOTE: if you want sources (recommended), instead run

```
./gradlew rebuildLVT genSources eclipse
```

Once your mod is complete and ready for use it can be compiled and reobfuscated with:

```
./gradlew build
```

## License

This template is available under the CC0 license. Feel free to learn from it and incorporate it in your own projects.
