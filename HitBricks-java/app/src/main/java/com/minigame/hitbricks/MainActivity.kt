package com.minigame.hitbricks

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.apprichtap.haptic.RichTapUtils
import com.apprichtap.haptic.base.PrebakedEffectId
import com.minigame.Interface.GameEventSink
import com.minigame.hitbricks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), GameEventSink {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 进入全屏模式
        supportActionBar?.hide()
        hideStatusBar()

        RichTapUtils.getInstance().init(this)

        binding.gameScreen.setGameEventSink(this)
        binding.btnStartGame.setOnClickListener {
            if (binding.gameScreen.isGameOver) {
                binding.gameScreen.resetGame()
                binding.gameScreen.startGame()
            } else {
                binding.gameScreen.startGame()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        RichTapUtils.getInstance().quit()
    }

    private fun hideStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //window.insetsController?.hide(WindowInsets.Type.statusBars())
            window.insetsController?.hide(WindowInsets.Type.systemBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }

    override fun onGameEvent(type: Int): Unit {
        when (type) {
            GameEventSink.GAME_PLAYING -> {
                binding.btnStartGame.visibility = View.GONE
            }
            GameEventSink.GAME_PAUSED, GameEventSink.GAME_OVER -> {
                runOnUiThread {
                    binding.btnStartGame.visibility = View.VISIBLE
                }
            }
            GameEventSink.EVENT_BALL_HIT_BRICK, GameEventSink.EVENT_BALL_HIT_WALL,
            GameEventSink.EVENT_BALL_HIT_BOARD -> {
                RichTapUtils.getInstance().playExtPrebaked(PrebakedEffectId.RT_SOFT_CLICK, 200)
            }
            GameEventSink.EVENT_BULLET_HIT_BRICK -> {
                RichTapUtils.getInstance().playExtPrebaked(PrebakedEffectId.RT_BOMB, 200)
            }
            GameEventSink.EVENT_BALL_HIT_WALL_DIE -> {
                RichTapUtils.getInstance().playExtPrebaked(PrebakedEffectId.RT_FAILURE, 255)
            }
        }
    }
}