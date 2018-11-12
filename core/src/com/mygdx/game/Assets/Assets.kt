package com.mygdx.game.Assets

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetErrorListener
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.BitmapFontLoader
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.Logger
import com.mygdx.game.Utils.Constants
import com.mygdx.game.Utils.Constants.Companion.BACKGROUND
import com.mygdx.game.Utils.Constants.Companion.BEE
import com.mygdx.game.Utils.Constants.Companion.FLAPPEE_BEE_ATLAS_GAME
import com.mygdx.game.Utils.Constants.Companion.FLAPPEE_BEE_ATLAS_SPLASH
import com.mygdx.game.Utils.Constants.Companion.FLOWER_BOTTOM
import com.mygdx.game.Utils.Constants.Companion.FLOWER_TOP
import com.mygdx.game.Utils.Constants.Companion.PLAY
import com.mygdx.game.Utils.Constants.Companion.PLAY_PRESSED
import com.mygdx.game.Utils.Constants.Companion.TITLE
import sun.security.krb5.internal.KDCOptions.with
import java.awt.SystemColor.info

object Assets : Disposable, AssetErrorListener {

    val assetManager: AssetManager = AssetManager().apply { setErrorListener(Assets) }

    lateinit var bgTexture: TextureAtlas.AtlasRegion
    lateinit var titleTexture: TextureAtlas.AtlasRegion
    lateinit var playTexture: TextureAtlas.AtlasRegion
    lateinit var playPressedTexture: TextureAtlas.AtlasRegion
    lateinit var flowerBottomTexture: TextureAtlas.AtlasRegion
    lateinit var flowerTopTexture: TextureAtlas.AtlasRegion
    lateinit var animation: Animation<TextureRegion>

    private val logger = ktx.log.logger<Assets>()

    fun loadSplashAssets() {
        with(assetManager) {
            logger.level = Logger.INFO
            load(FLAPPEE_BEE_ATLAS_SPLASH, TextureAtlas::class.java)
            finishLoading()
        }

        val atlas = assetManager.get<TextureAtlas>(FLAPPEE_BEE_ATLAS_SPLASH)

        bgTexture = atlas.findRegion(BACKGROUND)
        playTexture = atlas.findRegion(PLAY)
        playPressedTexture = atlas.findRegion(PLAY_PRESSED)
        titleTexture = atlas.findRegion(TITLE)
    }

    fun loadAssets() {

        val bitmapFontParameter = BitmapFontLoader.BitmapFontParameter().apply { atlasName = FLAPPEE_BEE_ATLAS_GAME }

        with(assetManager) {
            logger.level = Logger.INFO
            load(FLAPPEE_BEE_ATLAS_GAME, TextureAtlas::class.java)
            load("score.fnt", BitmapFont::class.java, bitmapFontParameter)
            finishLoading()
        }

        val atlas = assetManager.get<TextureAtlas>(FLAPPEE_BEE_ATLAS_GAME)

        flowerBottomTexture = atlas.findRegion(FLOWER_BOTTOM)
        flowerTopTexture = atlas.findRegion(FLOWER_TOP)
        animation = Animation(Constants.FRAME_DURATION,
                atlas.findRegion(BEE).split(Constants.TILE_WIDTH, Constants.TILE_HEIGHT)[0][0],
                atlas.findRegion(BEE).split(Constants.TILE_WIDTH, Constants.TILE_HEIGHT)[0][1])
                .apply { playMode = Animation.PlayMode.LOOP }

    }

    override fun dispose() {
        assetManager.dispose()
        logger.info { "Assets disposed...Ok" }
    }

    override fun error(asset: AssetDescriptor<*>, throwable: Throwable) {
        logger.error(throwable) { "Couldn't load asset: ${asset.fileName}" }
    }
}
