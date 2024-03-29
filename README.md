# OriginSpawn

This plugin enables players to spawn beyond multi worlds.

## Commands

All commands are the subtypes of `/originspawn`, and executed only from in-game.

### General

These commands require `originspawn.spawn` permission. The default permission is `true`.

| Command | Description                               |
|---------|-------------------------------------------|
| (empty) | Spawn the command sender at the location. |
| show    | Shows the spawn location.                 |
| help    | Shows the help messages.                  |

### Admin

These commands require `originspawn.admin` permission. The default permission is `OP`.

| Command | Description                                           | 
|---------|-------------------------------------------------------|
| update  | Update location where the command sender is standing. |
| reload  | Reload the location from config file.                 |      

## Settings(config.yml)

| Key                        | Description                                                                    | Default |
|----------------------------|--------------------------------------------------------------------------------|---------|
| always-force-players-spawn | Whether teleport the players to the spawn point when they join to your server. | false   |

## Development

* Kotlin
* Spigot 1.12.2

## LICENSE

[GPL v3](./LICENSE)