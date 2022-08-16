package click.seichi.originspawn.domain

import click.seichi.originspawn.SpawnPoint

interface SpawnPointPersistence {
    fun get(): SpawnPoint

    fun load()

    fun save(spawnPoint: SpawnPoint)
}