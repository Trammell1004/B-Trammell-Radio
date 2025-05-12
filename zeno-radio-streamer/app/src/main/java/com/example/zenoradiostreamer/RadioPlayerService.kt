import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class RadioPlayerService : Service() {

    private var mediaPlayer: MediaPlayer? = null
    private val radioUrl = "https://your-zeno.fm-stream-url" // Replace with your Zeno.fm stream URL

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer?.setOnPreparedListener {
            mediaPlayer?.start()
        }
        mediaPlayer?.setOnErrorListener { mp, what, extra ->
            Log.e("RadioPlayerService", "Error occurred: what=$what, extra=$extra")
            stopSelf()
            true
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!mediaPlayer!!.isPlaying) {
            try {
                mediaPlayer?.reset()
                mediaPlayer?.setDataSource(radioUrl)
                mediaPlayer?.prepareAsync()
            } catch (e: Exception) {
                Log.e("RadioPlayerService", "Error setting data source", e)
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}