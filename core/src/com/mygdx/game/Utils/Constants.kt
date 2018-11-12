package com.mygdx.game.Utils

class Constants {

    companion object {
        //General
        const val WORLD_WIDTH = 480f
        const val WORLD_HEIGHT = 640f
        const val PROGRESS_BAR_WIDTH = 100f
        const val PROGRESS_BAR_HEIGHT = 25f

        //Textures
        const val BACKGROUND = "bg.png"
        const val FLOWER_TOP = "flowerTop.png"
        const val FLOWER_BOTTOM = "flowerBottom.png"
        const val BEE = "bee.png"
        const val PLAY = "play.png"
        const val PLAY_PRESSED = "playPress.png"
        const val TITLE = "title.png"

        //Flapee
        const val COLLISION_RADIUS = 24f
        const val DIVE_ACCEL = 1f
        const val FLY_ACCEL = 5f
        const val TILE_WIDTH = 118
        const val TILE_HEIGHT = 118
        const val FRAME_DURATION = 0.125f

        //Flower
        const val COLLISION_RECTANGLE_WIDTH = 13f
        const val COLLISION_RECTANGLE_HEIGHT = 447f
        const val COLLISION_CIRCLE_RADIUS = 33f
        const val MAX_SPEED_PER_SECOND = 100f
        const val WIDTH = COLLISION_CIRCLE_RADIUS * 2
        const val GAP_BETWEEN_FLOWERS = 200f
        const val HEIGHT_OFFSET = -400f
        const val DISTANCE_BETWEEN_FLOOR_AND_CEILING = 300f//225f
    }
}