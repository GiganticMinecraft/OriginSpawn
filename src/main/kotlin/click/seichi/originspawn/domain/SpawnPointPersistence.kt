package click.seichi.originspawn.domain

interface SpawnPointPersistence {
    fun get(): SpawnPoint

    fun save(spawnPoint: SpawnPoint)
}