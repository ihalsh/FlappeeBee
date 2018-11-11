package com.mygdx.game

import com.badlogic.gdx.graphics.Color.BLACK
import com.badlogic.gdx.graphics.Color.WHITE
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils.random
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.DelayedRemovalArray
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.game.Entities.Flappee
import com.mygdx.game.Entities.Flower
import com.mygdx.game.FlappeeBeeGame.Companion.bgTexture
import com.mygdx.game.Utils.Constants
import com.mygdx.game.Utils.Constants.Companion.COLLISION_RECTANGLE_WIDTH
import com.mygdx.game.Utils.Constants.Companion.HEIGHT_OFFSET
import com.mygdx.game.Utils.Constants.Companion.WORLD_HEIGHT
import com.mygdx.game.Utils.Constants.Companion.WORLD_WIDTH
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.graphics.use
import ktx.log.info

class GameScreen : KtxScreen {

    private val shapeRenderer = ShapeRenderer()
    private val viewport = FitViewport(WORLD_WIDTH, WORLD_HEIGHT)
    private val batch = SpriteBatch()
    private val bitmapFont = BitmapFont()
    private val glyphLayout = GlyphLayout()

    private val flappee = Flappee(Vector2(WORLD_WIDTH / 4, WORLD_HEIGHT / 2))
    private val flowers = DelayedRemovalArray<Flower>()
    private var score = 0
    private var maxScore = 0

    private fun restart() {
        flappee.position.set(Vector2(WORLD_WIDTH / 4, WORLD_HEIGHT / 2))
        flappee.animationTimer = 0f
        flowers.clear()
        score = 0
        info { "Game restarted." }
    }

    override fun render(delta: Float) {
        flappee.update(delta)
        checkIfNewFlowerIsNeeded()
        updateScore()
        draw()
        removeFlowersIfPassed()
        for (flower in flowers) {
            flower.update(delta)
            //Check for collision
            if (flower.isFlappeeColliding(flappee)) restart()
        }
    }

    private fun draw() {
        viewport.apply()
        clearScreen(BLACK.r, BLACK.g, BLACK.b)
        batch.projectionMatrix = viewport.camera.combined

        batch.use {
            drawBackground(it)
            drawFlowers(it)
            flappee.draw(it)
            drawScore(it)
        }
        with(shapeRenderer) {
            setAutoShapeType(true)
            projectionMatrix = viewport.camera.combined
            begin()
//            flappee.drawDebug(this)
//            for (flower in flowers) flower.drawDebug(this)
            end()
        }


    }

    private fun drawScore(batch: SpriteBatch) {
        val scoreString = "Score: $score\nBest score: $maxScore"
        glyphLayout.setText(bitmapFont, scoreString, WHITE, 0f, Align.left, false)
        bitmapFont.draw(batch,
                scoreString,
                COLLISION_RECTANGLE_WIDTH,
                viewport.worldHeight - COLLISION_RECTANGLE_WIDTH)
    }

    private fun drawBackground(batch: SpriteBatch) {
        batch.draw(bgTexture, 0f, 0f)
    }

    private fun drawFlowers(batch: SpriteBatch) {
        for (flower in flowers) flower.draw(batch)
    }

    private fun updateScore() {
        val flower = flowers.first()
        if (flower.position.x < flappee.position.x && !flower.pointClaimed) {
            flower.pointClaimed = true
            score++
            maxScore = Math.max(score, maxScore)
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
                random(-50f, HEIGHT_OFFSET))))
    }

    private fun removeFlowersIfPassed() {
        if (flowers.size > 0 && flowers.first().position.x < -Constants.WIDTH) {
            flowers.begin()
            flowers.removeValue(flowers.first(), true)
            flowers.end()
        }
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun dispose() {
        shapeRenderer.dispose()
        batch.dispose()
        bitmapFont.dispose()
    }
}