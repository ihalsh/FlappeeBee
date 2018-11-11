package com.mygdx.game

import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mygdx.game.Utils.Constants.Companion.BACKGROUND
import com.mygdx.game.Utils.Constants.Companion.BEE
import com.mygdx.game.Utils.Constants.Companion.FLOWER_BOTTOM
import com.mygdx.game.Utils.Constants.Companion.FLOWER_TOP
import com.mygdx.game.Utils.Constants.Companion.FRAME_DURATION
import com.mygdx.game.Utils.Constants.Companion.PLAY
import com.mygdx.game.Utils.Constants.Companion.PLAY_PRESSED
import com.mygdx.game.Utils.Constants.Companion.TILE_HEIGHT
import com.mygdx.game.Utils.Constants.Companion.TILE_WIDTH
import com.mygdx.game.Utils.Constants.Companion.TITLE
import ktx.app.KtxGame

class FlappeeBeeGame : KtxGame<Screen>() {

    companion object {

        val assetManager by lazy { AssetManager() }
        val bgTexture: Texture by lazy { assetManager.get(BACKGROUND, Texture::class.java) }
        val flowerBottomTexture: Texture by lazy { assetManager.get(FLOWER_BOTTOM, Texture::class.java) }
        val flowerTopTexture: Texture by lazy { assetManager.get(FLOWER_TOP, Texture::class.java) }
        val playTexture: Texture by lazy { assetManager.get(PLAY, Texture::class.java) }
        val playPressedTexture: Texture by lazy { assetManager.get(PLAY_PRESSED, Texture::class.java) }
        val titleTexture: Texture by lazy { assetManager.get(TITLE, Texture::class.java) }
        private val beeTexture: Texture by lazy { assetManager.get(BEE, Texture::class.java) }

        val animation: Animation<TextureRegion> by lazy {
            Animation(FRAME_DURATION,
                    TextureRegion(beeTexture).split(TILE_WIDTH, TILE_HEIGHT)[0][0],
                    TextureRegion(beeTexture).split(TILE_WIDTH, TILE_HEIGHT)[0][1])
                    .apply { playMode = Animation.PlayMode.LOOP }
        }
    }

    override fun create() {

        assetsInit()

        // Registering screens
        addScreen(GameScreen())
        addScreen(StartScreen(this))

        // Changing current screen
        setScreen<StartScreen>()
//        setScreen<GameScreen>()

    }

    private fun assetsInit() {
        // Create new AssetManager
        // (assuming here that this method will be called only once per game lifecycle)
        with(assetManager) {
            load(BEE, Texture::class.java)
            load(FLOWER_BOTTOM, Texture::class.java)
            load(FLOWER_TOP, Texture::class.java)
            load(BACKGROUND, Texture::class.java)
            load(PLAY, Texture::class.java)
            load(PLAY_PRESSED, Texture::class.java)
            load(TITLE, Texture::class.java)
            finishLoading() // Make sure that it is loaded
        }
    }

    override fun dispose() {
        assetManager.dispose()
    }
}