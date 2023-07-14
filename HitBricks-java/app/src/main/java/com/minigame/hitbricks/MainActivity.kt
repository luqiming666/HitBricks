package com.minigame.hitbricks

import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.minigame.gameView.GameView

class MainActivity : AppCompatActivity() {

    private var gameView: GameView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameView = GameView(this)
        setContentView(gameView)

        // 进入全屏模式
        supportActionBar?.hide()
        hideStatusBar()

        gameView!!.startGame()
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_MENU -> if (gameView!!.gameState === 1) { //1表示游戏处于等待开始状态(暂停状态或者刚进入游戏等待开始状态)
                //开始游戏
                gameView!!.startGame()
            } else if (gameView!!.gameState === 2) { //2表示游戏处于正在进行状态
                //暂停游戏
                gameView!!.pauseGame()
            } else { //游戏处于结束状态
                //游戏重置
                gameView!!.resetGame()
            }
            KeyEvent.KEYCODE_BACK -> {
                //连续按两次退出游戏
                //exitByDoubleClick()
            }
            else -> {}
        }
        return false
    }
}