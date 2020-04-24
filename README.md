# Minecraft Cursed POMF Example Mod

The (unofficial) [Fabric](https://fabricmc.net/) home for 1.2.5 and Beta 1.7.3 - if you want the newer versions see [here](https://github.com/FabricMC/fabric-example-mod).

## Setup
1. Edit build.gradle and mod.json to suit your needs.
    * The "mixins" object can be removed from mod.json if you do not need to use mixins.
    * Please remember to replace all occurrences of "modid" with your own mod ID.
2. Run the following command:

```
./gradlew eclipse
```

### Words of Warning
Building your mod would typically be done with the following command:

```
./gradlew build
```

Currently this is not working however, so you'll have to work on adding more quirks and features to your mod in dev until it is.


## License
This template is available under the MIT license. Feel free to learn from it and incorporate it in your own projects.
