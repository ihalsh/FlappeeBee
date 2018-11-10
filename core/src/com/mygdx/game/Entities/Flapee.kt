package com.mygdx.game.Entities

import com.badlogic.gdx.Gdx.input
import com.badlogic.gdx.Input.Keys.SPACE
import com.badlogic.gdx.graphics.Color.WHITE
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Utils.Constants.Companion.COLLISION_RADIUS
import com.mygdx.game.Utils.Constants.Companion.DIVE_ACCEL
import com.mygdx.game.Utils.Constants.Companion.FLY_ACCEL
import com.mygdx.game.Utils.Constants.Companion.WORLD_HEIGHT
import ktx.graphics.circle

class Flappee(private val position: Vector2 = Vector2()) {

    fun update(delta: Float) {
        position.y -= DIVE_ACCEL
        if (input.isKeyPressed(SPACE))
            position.set(Vector2(position.x, position.y + FLY_ACCEL))
        blockFlappeeLeavingTheWorld()
    }

    private fun blockFlappeeLeavingTheWorld() {
        position.set(position.x, MathUtils.clamp(position.y,
                0f + COLLISION_RADIUS,
                WORLD_HEIGHT - COLLISION_RADIUS))
    }

    fun drawDebug(shapeRenderer: ShapeRenderer) {
        with(shapeRenderer) {
            set(ShapeRenderer.ShapeType.Line)
            color = WHITE
            circle(position, COLLISION_RADIUS)
        }
    }
}