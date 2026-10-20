# ClearDespawn (NeoForge)

Unofficial NeoForge port of [ClearDespawn](https://modrinth.com/mod/cleardespawn) by StrikerRocker.
Please don't report issues to StrikerRocker, use this repo's issue tracker.

Makes items blink when they're about to despawn, and blink faster as time runs out.

- Blinking starts 20 seconds before an item despawns
- Items blink faster the closer they get (can be turned off)

Both settings are in `config/cleardespawn-client.toml`, or in game through the mod list.

Client-side only, no dependencies, fine to put in modpacks. Same config file and options as the original,
so existing configs keep working.

| Minecraft | NeoForge | Java |
|---|---|---|
| 1.21 | 21.0.167 or newer | 21 |
| 1.21.1 | 21.1.252 or newer | 21 |
| 1.21.4 | 21.4.161 or newer | 21 |
| 1.21.5 | 21.5.101 or newer | 21 |
| 1.21.8 | 21.8.57 or newer | 21 |
| 1.21.10 | 21.10.64 or newer | 21 |
| 1.21.11 | 21.11.48 or newer | 21 |
| 26.1.2 | 26.1.2.122 or newer | 25 |
| 26.2 | 26.2.0.93 or newer | 25 |
| 26.3 | 26.3.0.94 or newer | 25 |

## Install
Put the jar for your Minecraft version in the `mods` folder of a NeoForge instance. Servers do not need it.

## Build
Each Minecraft version is its own Gradle project under `versions/`.

```
cd versions/26.3
./gradlew build
```

The jar lands in `versions/<version>/build/libs/`.

## Licence
MIT, same as the original. See [LICENSE](LICENSE).
