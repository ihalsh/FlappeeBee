package com.mygdx.game

import com.badlogic.gdx.Screen
import com.mygdx.game.Assets.Assets
import ktx.app.KtxGame
import ktx.log.info

class FlappeeBeeGame : KtxGame<Screen>() {

    override fun create() {

        // Registering screens
        addScreen(LoadingScreen(this))
        addScreen(StartScreen(this))
        addScreen(GameScreen())

        // Changing current screen
        setScreen<StartScreen>()
    }

    override fun dispose() {
        Assets.dispose()
    }
}