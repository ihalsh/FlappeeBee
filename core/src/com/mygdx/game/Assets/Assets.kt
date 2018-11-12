package com.mygdx.game.Assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.Logger.INFO
import com.mygdx.game.Utils.Constants
import com.mygdx.game.Utils.Constants.Companion.BACKGROUND
import com.mygdx.game.Utils.Constants.Companion.BEE
import com.mygdx.game.Utils.Constants.Companion.FLOWER_BOTTOM
import com.mygdx.game.Utils.Constants.Companion.FLOWER_TOP
import com.mygdx.game.Utils.Constants.Companion.PLAY
import com.mygdx.game.Utils.Constants.Companion.PLAY_PRESSED
import com.mygdx.game.Utils.Constants.Companion.TITLE
import ktx.log.info

object Assets : Disposable {

    val assetManager: AssetManager = AssetManager()

    lateinit var bgTexture: Texture
    lateinit var titleTexture: Texture
    lateinit var playTexture: Texture
    lateinit var playPressedTexture: Texture
    lateinit var flowerBottomTexture: Texture
    lateinit var flowerTopTexture: Texture
    private lateinit var beeTexture: Texture
    lateinit var animation: Animation<TextureRegion>

    fun loadSplashAssets() {
        assetManager.apply {
            logger.level = INFO
            load(BACKGROUND, Texture::class.java)
            load(PLAY, Texture::class.java)
            load(PLAY_PRESSED, Texture::class.java)
            load(TITLE, Texture::class.java)
            finishLoading()
        }
        initSplashAssets()
    }

    fun loadAssets() {
        assetManager.apply {
            logger.level = INFO
            load(FLOWER_BOTTOM, Texture::class.java)
            load(FLOWER_TOP, Texture::class.java)
            load(BEE, Texture::class.java)
        }
    }

    private fun initSplashAssets() {
        bgTexture = assetManager.get(Constants.BACKGROUND, Texture::class.java)
        playTexture = assetManager.get(Constants.PLAY, Texture::class.java)
        playPressedTexture = assetManager.get(Constants.PLAY_PRESSED, Texture::class.java)
        titleTexture = assetManager.get(Constants.TITLE, Texture::class.java)

    }

    fun initAssets() {
        flowerBottomTexture = assetManager.get(Constants.FLOWER_BOTTOM, Texture::class.java)
        flowerTopTexture = assetManager.get(Constants.FLOWER_TOP, Texture::class.java)
        beeTexture = assetManager.get(Constants.BEE, Texture::class.java)
        animation = Animation(Constants.FRAME_DURATION,
                TextureRegion(beeTexture).split(Constants.TILE_WIDTH, Constants.TILE_HEIGHT)[0][0],
                TextureRegion(beeTexture).split(Constants.TILE_WIDTH, Constants.TILE_HEIGHT)[0][1])
                .apply { playMode = Animation.PlayMode.LOOP }
    }

    override fun dispose() {
        assetManager.dispose()
        bgTexture.dispose()
        titleTexture.dispose()
        playTexture.dispose()
        playPressedTexture.dispose()
        flowerBottomTexture.dispose()
        flowerTopTexture.dispose()
        beeTexture.dispose()
        info { "Assets disposed...Ok" }
    }
}
