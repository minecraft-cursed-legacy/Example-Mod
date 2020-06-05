package io.github.minecraftcursedlegacy.example;

import java.io.IOException;

import io.github.minecraftcursedlegacy.api.config.Configs;
import io.github.minecraftcursedlegacy.api.registry.Id;
import net.fabricmc.api.ModInitializer;
import tk.valoeghese.zoesteriaconfig.api.container.WritableConfig;
import tk.valoeghese.zoesteriaconfig.api.template.ConfigTemplate;

public class ExampleMod implements ModInitializer {
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");

		// example config
		try {
			config = Configs.loadOrCreate(new Id("modid", "example"),
					ConfigTemplate.builder()
					.addContainer("exampleContainer", container -> container.addDataEntry("someData", "0.5"))
					.build());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		System.out.println(config.getDoubleValue("exampleContainer.someData"));
	}

	private static WritableConfig config;
}
