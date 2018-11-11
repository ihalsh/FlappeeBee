package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.game.FlappeeBeeGame.Companion.bgTexture
import com.mygdx.game.FlappeeBeeGame.Companion.playPressedTexture
import com.mygdx.game.FlappeeBeeGame.Companion.playTexture
import com.mygdx.game.FlappeeBeeGame.Companion.titleTexture
import com.mygdx.game.Utils.Constants.Companion.WORLD_HEIGHT
import com.mygdx.game.Utils.Constants.Companion.WORLD_WIDTH
import ktx.app.KtxGame
import ktx.app.KtxScreen

class StartScreen(private val game: KtxGame<Screen>) : KtxScreen {

    private val stage = Stage(FitViewport(WORLD_WIDTH, WORLD_HEIGHT))

    override fun show() {
        Gdx.input.inputProcessor = stage

        val background = Image(bgTexture)

        val title = Image(titleTexture)
                .apply { setPosition(WORLD_WIDTH / 2, 3 * WORLD_HEIGHT / 4, Align.center) }

        val play = ImageButton(TextureRegionDrawable(TextureRegion(playTexture)),
                TextureRegionDrawable(TextureRegion(playPressedTexture)))
                .apply {
                    setPosition(WORLD_WIDTH / 2, WORLD_HEIGHT / 4, Align.center)
                    addListener(object : ActorGestureListener() {
                        override fun tap(event: InputEvent, x: Float, y: Float, count: Int, button: Int) {
                            super.tap(event, x, y, count, button)
                            game.setScreen<GameScreen>()
                            dispose()
                        }
                    })
                }

        with(stage) {
            addActor(background)
            addActor(play)
            addActor(title)
        }
    }

    override fun render(delta: Float) {
        stage.act(delta)
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun dispose() {
        stage.dispose()
        playTexture.dispose()
        playPressedTexture.dispose()
        titleTexture.dispose()
    }
}