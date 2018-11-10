package com.mygdx.game

import com.badlogic.gdx.graphics.Color.BLACK
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.MathUtils.random
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.DelayedRemovalArray
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.game.Entities.Flappee
import com.mygdx.game.Entities.Flower
import com.mygdx.game.Utils.Constants
import com.mygdx.game.Utils.Constants.Companion.MAX_SPEED_PER_SECOND
import com.mygdx.game.Utils.Constants.Companion.WORLD_HEIGHT
import com.mygdx.game.Utils.Constants.Companion.WORLD_WIDTH
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.log.info

class GameScreen : KtxScreen {

    private val shapeRenderer = ShapeRenderer()
    private val viewport: FitViewport = FitViewport(WORLD_WIDTH, WORLD_HEIGHT)
    private val batch = SpriteBatch()
    private val flapee = Flappee(Vector2(WORLD_WIDTH / 4, WORLD_HEIGHT / 2))
    private val flowers = DelayedRemovalArray<Flower>()

    override fun render(delta: Float) {
        viewport.apply()
        clearScreen(BLACK.r, BLACK.g, BLACK.b)
        batch.projectionMatrix = viewport.camera.combined

        flapee.update(delta)
        checkIfNewFlowerIsNeeded()
        removeFlowersIfPassed()
        for (flower in flowers) flower.update(delta)

        with(shapeRenderer) {
            setAutoShapeType(true)
            projectionMatrix = viewport.camera.combined
            begin()
            flapee.drawDebug(this)
            for (flower in flowers) flower.drawDebug(this)
            end()
        }
    }

    private fun checkIfNewFlowerIsNeeded() {
        if (flowers.size == 0) createNewFlower()
        else if (flowers.peek().position.x < WORLD_WIDTH - Constants.GAP_BETWEEN_FLOWERS)
            createNewFlower()
    }

    private fun createNewFlower() {
        flowers.add(Flower(Vector2(
                WORLD_WIDTH + Constants.WIDTH,
                random(Constants.HEIGHT_OFFSET))))
    }

    private fun removeFlowersIfPassed() {
        if (flowers.size > 0 && flowers.first().position.x < -Constants.WIDTH) {
            flowers.begin()
            flowers.removeValue(flowers.first(), true)
            flowers.end()
            info { flowers.size.toString() }
        }
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }
}