package com.mygdx.game

import com.badlogic.gdx.graphics.Color.BLACK
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.game.Entities.Flappee
import com.mygdx.game.Utils.Constants.Companion.WORLD_HEIGHT
import com.mygdx.game.Utils.Constants.Companion.WORLD_WIDTH
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.graphics.use

class GameScreen : KtxScreen {

    private val shapeRenderer = ShapeRenderer()
    private val viewport: FitViewport = FitViewport(WORLD_WIDTH, WORLD_HEIGHT)
    private val batch = SpriteBatch()
    private val flapee = Flappee(Vector2(WORLD_WIDTH / 4, WORLD_HEIGHT / 2))

    override fun render(delta: Float) {
        viewport.apply()
        clearScreen(BLACK.r, BLACK.g, BLACK.b)
        batch.projectionMatrix = viewport.camera.combined

        flapee.update(delta)

        with(shapeRenderer) {
            setAutoShapeType(true)
            projectionMatrix = viewport.camera.combined
            begin()
            flapee.drawDebug(this)
            end()
        }
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }
}