package com.mygdx.game.Entities

import com.badlogic.gdx.graphics.Color.WHITE
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Utils.Constants.Companion.COLLISION_RADIUS
import ktx.graphics.circle

class Flappee(private val position: Vector2 = Vector2()) {

    fun drawDebug(shapeRenderer: ShapeRenderer) {
        with(shapeRenderer) {
            set(ShapeRenderer.ShapeType.Line)
            color = WHITE
            circle(position, COLLISION_RADIUS)
        }
    }
}