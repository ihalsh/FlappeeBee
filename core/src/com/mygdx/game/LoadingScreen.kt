package com.mygdx.game

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Color.BLACK
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.game.Assets.Assets
import com.mygdx.game.Assets.Assets.initAssets
import com.mygdx.game.Assets.Assets.loadAssets
import com.mygdx.game.Utils.Constants.Companion.PROGRESS_BAR_HEIGHT
import com.mygdx.game.Utils.Constants.Companion.PROGRESS_BAR_WIDTH
import com.mygdx.game.Utils.Constants.Companion.WORLD_HEIGHT
import com.mygdx.game.Utils.Constants.Companion.WORLD_WIDTH
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.log.info

class LoadingScreen(private val game: FlappeeBeeGame) : KtxScreen {

    private val shapeRenderer = ShapeRenderer()
    private val viewport = FitViewport(WORLD_WIDTH, WORLD_HEIGHT)
    private var progress = 0f

    override fun show() {
        //Load rest of the assets
        loadAssets()
    }

    override fun render(delta: Float) {
        update()
        viewport.apply()
        clearScreen(BLACK.r, BLACK.g, BLACK.b)
        draw()
    }

    private fun update() {
        if (Assets.assetManager.update()) {
            //Init rest of the assets
            initAssets()
            game.setScreen<GameScreen>()
        } else {
            progress = Assets.assetManager.progress
            info { "Progress: ${progress*100}%" }
        }
    }

    private fun draw() {
        with(shapeRenderer) {
            projectionMatrix = viewport.camera.combined
            begin(ShapeRenderer.ShapeType.Filled)
            color = Color.WHITE
            rect((WORLD_WIDTH - PROGRESS_BAR_WIDTH) / 2,
                    (WORLD_HEIGHT - PROGRESS_BAR_HEIGHT) / 2,
                    progress * PROGRESS_BAR_WIDTH,
                    PROGRESS_BAR_HEIGHT)
            end()
        }
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun dispose() {
        shapeRenderer.dispose()
    }
}
