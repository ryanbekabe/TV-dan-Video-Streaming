package com.hanyajasa.tvdanvideostreaming2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.webkit.JavascriptInterface
import androidx.activity.ComponentActivity
import androidx.activity.addCallback

class YoutubePlayerActivity : ComponentActivity() {

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Full screen immersive
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        )

        webView = WebView(this)
        setContentView(webView)

        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            mediaPlaybackRequiresUserGesture = false
            allowContentAccess = true
            loadWithOverviewMode = true
            useWideViewPort = true
            builtInZoomControls = false
        }

        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        }

        webView.addJavascriptInterface(YoutubeJsInterface(), "YoutubeJsInterface")

        val videoId = intent.getStringExtra(EXTRA_VIDEO_ID) ?: run {
            finish(); return
        }
        val videoTitle = intent.getStringExtra(EXTRA_VIDEO_TITLE) ?: ""

        webView.loadDataWithBaseURL(
            "https://www.youtube.com",
            buildYouTubeHtml(videoId, videoTitle),
            "text/html",
            "utf-8",
            null
        )

        onBackPressedDispatcher.addCallback(this) {
            if (webView.canGoBack()) webView.goBack()
            else finish()
        }
    }

    private inner class YoutubeJsInterface {
        @JavascriptInterface
        fun onVideoError(errorCode: Int) {
            if (errorCode == 100 || errorCode == 101 || errorCode == 150 || errorCode == 152) {
                runOnUiThread {
                    showVideoErrorDialog()
                }
            }
        }
    }

    private fun showVideoErrorDialog() {
        AlertDialog.Builder(this)
            .setTitle("Video tidak tersedia")
            .setMessage("Video ini tidak dapat diputar di dalam aplikasi. Anda dapat membukanya di aplikasi YouTube.")
            .setPositiveButton("Buka di YouTube") { _, _ ->
                val videoId = intent.getStringExtra(EXTRA_VIDEO_ID)
                val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId))
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + videoId))
                try {
                    startActivity(appIntent)
                } catch (ex: Exception) {
                    startActivity(webIntent)
                }
                finish()
            }
            .setNegativeButton("Tutup") { _, _ ->
                finish()
            }
            .setCancelable(false)
            .show()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun buildYouTubeHtml(videoId: String, title: String): String = """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <style>
                * { margin: 0; padding: 0; box-sizing: border-box; }
                body { background: #000; width: 100vw; height: 100vh; overflow: hidden; }
                #player { width: 100%; height: 100%; }
            </style>
        </head>
        <body>
            <div id="player"></div>
            <script src="https://www.youtube.com/iframe_api"></script>
            <script>
                var player;
                function onYouTubeIframeAPIReady() {
                    player = new YT.Player('player', {
                        width: '100%',
                        height: '100%',
                        videoId: '$videoId',
                        playerVars: {
                            autoplay: 1,
                            controls: 1,
                            modestbranding: 1,
                            rel: 0,
                            fs: 1,
                            playsinline: 0
                        },
                        events: {
                            onReady: function(e) { e.target.playVideo(); },
                            onError: function(e) { window.YoutubeJsInterface.onVideoError(e.data); }
                        }
                    });
                }
            </script>
        </body>
        </html>
    """.trimIndent()

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
    }

    override fun onDestroy() {
        webView.destroy()
        super.onDestroy()
    }

    companion object {
        const val EXTRA_VIDEO_ID = "extra_video_id"
        const val EXTRA_VIDEO_TITLE = "extra_video_title"
    }
}
