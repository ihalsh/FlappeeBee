package com.mygdx.game

import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mygdx.game.Utils.Constants.Companion.BACKGROUND
import com.mygdx.game.Utils.Constants.Companion.BEE
import com.mygdx.game.Utils.Constants.Companion.FLOWER_BOTTOM
import com.mygdx.game.Utils.Constants.Companion.FLOWER_TOP
import com.mygdx.game.Utils.Constants.Companion.TILE_HEIGHT
import com.mygdx.game.Utils.Constants.Companion.TILE_WIDTH
import ktx.app.KtxGame

class FlappeeBeeGame : KtxGame<Screen>() {

    companion object {
        lateinit var assetManager: AssetManager
        lateinit var flappeeTexture: Texture
        lateinit var bgTexture: Texture
        lateinit var flowerBottomTexture: Texture
        lateinit var flowerTopTexture: Texture
        lateinit var beeTexture: Texture
        lateinit var firstBeeTexture: TextureRegion
        lateinit var secondBeeTexture: TextureRegion
    }

    override fun create() {
        // Registering GameScreen in the game object
        // it will be accessible through GameScreen class:
        addScreen(GameScreen())

        // Changing current screen to the registered instance of the
        // GameScreen class:
        setScreen<GameScreen>()

        // Create new AssetManager
        // (assuming here that this method will be called only once per game lifecycle)
        assetManager = AssetManager()
        with(assetManager) {
            load(BEE, Texture::class.java) // Preload the texture
            load(FLOWER_BOTTOM, Texture::class.java)
            load(FLOWER_TOP, Texture::class.java)
            load(BACKGROUND, Texture::class.java)
            finishLoading() // Make sure that it is loaded
            // Cache it into a variable
            flappeeTexture = get(BEE, Texture::class.java)
            bgTexture = get(BACKGROUND, Texture::class.java)
            flowerBottomTexture = get(FLOWER_BOTTOM, Texture::class.java)
            flowerTopTexture = get(FLOWER_TOP, Texture::class.java)
            beeTexture = get(BEE, Texture::class.java)
        }
        firstBeeTexture = TextureRegion(beeTexture).split(TILE_WIDTH,TILE_HEIGHT)[0][0]
        secondBeeTexture = TextureRegion(beeTexture).split(TILE_WIDTH,TILE_HEIGHT)[0][1]

    }

    override fun dispose() {
        assetManager.dispose()
    }
}