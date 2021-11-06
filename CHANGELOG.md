# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog].

## [v1.2.3-1.16.5] - 2021-11-06
### Added
- Added spitful llama model
- Added update checker
- Added mod logo for mod list
- Added support for [Catalogue] and [Configured] mods
### Changed
- Familiar horse element now works for donkeys and mules
- Updated local gradle setup
### Fixed
- Fixed foal model floating above the ground

## [v1.2.2-1.16.5] - 2021-10-31
### Added
- Added familiar horse model based on [Familiar Horses] mod

## [v1.2.1-1.16.5] - 2021-10-20
### Added
- Added player animations element, mostly taken from [Console Experience] mod, including:
    - Eating animation for both eating and drinking
    - Rowing animation when in a boat
    - Riding animation when on a horses
    - Animation for inspecting hand held items, such as compass and clock
### Changed
- Renamed zombie knees element to humanoid knees, now also includes piglin-like mobs
- Made humanoid knees look better when walking and riding

## [v1.2-1.16.5] - 2021-09-10
### Added
- Re-added arm flailing enderman model
- Re-added wobbly creeper model
- Re-added playful doggy model
- Re-added zombie knees model

## [v1.2b6-1.16.5] - 2021-07-24
### Added
- Re-added jiggly liquidy slime model
- Re-added magma cube burger model
- Re-added wiggly villager nose model
### Changed
- Removed unnecessary accessor mixins

## [v1.2b5-1.16.5] - 2021-07-22
### Added
- Re-added flowy ocelot tail model
- Re-added curly cat tail model

## [v1.2b4-1.16.5] - 2021-07-22
### Added
- Re-added wiggly iron golem nose model
### Changed
- Updated [Puzzles Lib] to v1.0.10
- Individual mobs can now be blacklisted in the config file from having any model changes applied to them
- Renamed a few model elements

## [v1.2b3-1.16.5] - 2021-07-03
### Added
- Re-added spider knees model
- Re-added animated snow man stick model
- Re-added cow udder model
### Changed
- Removed access transformer in favor of accessor mixins

## [v1.2b2-1.16.5] - 2021-06-30
### Added
- Re-added kneeling sheep model
### Changed
- Updated [Puzzles Lib] to v1.0.6
- Ambient sounds some mobs may play an animation for can now be customized to enable support for modded variants of vanilla mobs
- New models can now also be applied to render layers

## [v1.2b1-1.16.5] - 2021-06-25
### Added
- Re-added oinky pig model
- Re-added bucka chicken model
- Re-added ghast tentacles model
- Re-added squid tentacles model
### Changed
- New dependency: [Puzzles Lib]
- Big internal rewrite, animations will be added back over time
- Sound based animations should now play for the correct mob much more reliably

[Keep a Changelog]: https://keepachangelog.com/en/1.0.0/
[Puzzles Lib]: https://www.curseforge.com/minecraft/mc-mods/puzzles-lib
[Console Experience]: https://www.curseforge.com/minecraft/mc-mods/console-hud
[Familiar Horses]: https://www.curseforge.com/minecraft/mc-mods/familiar-horses
[Catalogue]: https://www.curseforge.com/minecraft/mc-mods/catalogue
[Configured]: https://www.curseforge.com/minecraft/mc-mods/configured
