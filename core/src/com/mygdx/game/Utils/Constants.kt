package com.mygdx.game.Utils

class Constants {

    companion object {
        //General
        const val WORLD_WIDTH = 480f
        const val WORLD_HEIGHT = 640f

        //Flapee
        const val COLLISION_RADIUS = 24f
        const val DIVE_ACCEL = 1.5f
        const val FLY_ACCEL = 15f

        //Flower
        const val COLLISION_RECTANGLE_WIDTH = 13f
        const val COLLISION_RECTANGLE_HEIGHT = 447f
        const val COLLISION_CIRCLE_RADIUS = 33f
        const val MAX_SPEED_PER_SECOND = 100f
        const val WIDTH = COLLISION_CIRCLE_RADIUS * 2
        const val GAP_BETWEEN_FLOWERS = 200f
        const val HEIGHT_OFFSET = -400f
        const val DISTANCE_BETWEEN_FLOOR_AND_CEILING = 225f
    }
}