package com.mygdx.game.Entities

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Assets.Assets.flowerBottomTexture
import com.mygdx.game.Assets.Assets.flowerTopTexture
import com.mygdx.game.Utils.Constants.Companion.COLLISION_CIRCLE_RADIUS
import com.mygdx.game.Utils.Constants.Companion.COLLISION_RECTANGLE_HEIGHT
import com.mygdx.game.Utils.Constants.Companion.COLLISION_RECTANGLE_WIDTH
import com.mygdx.game.Utils.Constants.Companion.DISTANCE_BETWEEN_FLOOR_AND_CEILING
import com.mygdx.game.Utils.Constants.Companion.MAX_SPEED_PER_SECOND

class Flower(val position: Vector2 = Vector2(), var pointClaimed: Boolean = false) {

    private val floorCollisionRectangle: Rectangle = Rectangle(
            position.x,
            position.y,
            COLLISION_RECTANGLE_WIDTH,
            COLLISION_RECTANGLE_HEIGHT)
    private val ceilingCollisionRectangle: Rectangle = Rectangle(
            position.x,
            position.y + floorCollisionRectangle.height + DISTANCE_BETWEEN_FLOOR_AND_CEILING,
            COLLISION_RECTANGLE_WIDTH,
            COLLISION_RECTANGLE_HEIGHT)
    private val floorCollisionCircle: Circle = Circle(
            position.x + floorCollisionRectangle.width / 2,
            position.y + floorCollisionRectangle.height,
            COLLISION_CIRCLE_RADIUS)
    private val ceilingCollisionCircle: Circle = Circle(
            position.x + floorCollisionRectangle.width / 2,
            position.y + floorCollisionRectangle.height + DISTANCE_BETWEEN_FLOOR_AND_CEILING,
            COLLISION_CIRCLE_RADIUS)

    fun update(delta: Float) {
        position.x -= delta * MAX_SPEED_PER_SECOND
        floorCollisionCircle.setX(position.x + floorCollisionRectangle.width / 2)
        floorCollisionRectangle.setX(position.x)
        ceilingCollisionRectangle.setX(position.x)
        ceilingCollisionCircle.setX(position.x + floorCollisionRectangle.width / 2)
    }

    fun isFlappeeColliding(flappee: Flappee): Boolean {
        val flappeeCollisionCircle = Circle(flappee.position, COLLISION_CIRCLE_RADIUS)
        return Intersector.overlaps(flappeeCollisionCircle,
                ceilingCollisionCircle) ||
                Intersector.overlaps(flappeeCollisionCircle,
                        floorCollisionCircle) ||
                Intersector.overlaps(flappeeCollisionCircle,
                        ceilingCollisionRectangle) ||
                Intersector.overlaps(flappeeCollisionCircle,
                        floorCollisionRectangle)
    }

    fun draw(batch: SpriteBatch) {
        batch.draw(flowerBottomTexture,
                (floorCollisionCircle.x - flowerBottomTexture.regionWidth / 2),
                position.y + COLLISION_CIRCLE_RADIUS
        )
        batch.draw(flowerTopTexture,
                (floorCollisionCircle.x - flowerTopTexture.regionWidth / 2),
                position.y + flowerTopTexture.regionHeight + DISTANCE_BETWEEN_FLOOR_AND_CEILING
                        - COLLISION_CIRCLE_RADIUS)
    }

    fun drawDebug(shapeRenderer: ShapeRenderer) {

        shapeRenderer.rect(
                floorCollisionRectangle.x,
                floorCollisionRectangle.y,
                floorCollisionRectangle.width,
                floorCollisionRectangle.height
        )
        shapeRenderer.circle(
                floorCollisionCircle.x,
                floorCollisionCircle.y,
                floorCollisionCircle.radius
        )
        shapeRenderer.rect(
                ceilingCollisionRectangle.x,
                ceilingCollisionRectangle.y,
                ceilingCollisionRectangle.width,
                ceilingCollisionRectangle.height
        )
        shapeRenderer.circle(
                ceilingCollisionCircle.x,
                ceilingCollisionCircle.y,
                ceilingCollisionCircle.radius)
    }

}