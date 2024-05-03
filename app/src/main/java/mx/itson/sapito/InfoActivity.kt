package mx.itson.sapito

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class InfoActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_info)

        val textViewTempContent = findViewById<TextView>(R.id.text_view_temp_content)
        val textViewElevation = findViewById<TextView>(R.id.text_view_elevation)
        val textViewWindSpeed = findViewById<TextView>(R.id.text_view_wind_speed)
        val textViewWindDirection = findViewById<TextView>(R.id.text_view_wind_direction)
        val textViewWeatherCode = findViewById<TextView>(R.id.text_view_weather)
        val linearLayout = findViewById<LinearLayout>(R.id.layout)

        val temperature = intent.getFloatExtra("temperature", 0.0f)
        val elevation = intent.getFloatExtra("elevation", 0.0f)
        val windSpeed = intent.getFloatExtra("windSpeed", 0.0f)
        val windDirection = intent.getFloatExtra("windDirection", 0.0f)
        val weatherCode = intent.getIntExtra("weatherCode", 0)

        textViewTempContent.setText("${temperature} °C")
        textViewElevation.setText(elevation.toString())
        textViewWindSpeed.setText("${windSpeed} kmh")
        textViewWindDirection.setText("${windDirection} °")
        textViewWeatherCode.setText(weatherCode.toString())

        var text = ""
        var image = R.drawable.soleado

        when(weatherCode) {
            0 -> {
                text = getString(R.string.Clear_sky)
                image = R.drawable.soleado
            }
            in 1..3-> {
                text = getString(R.string.Partly_cloud)
                image = R.drawable.partly_cloudy
            }
            in 45..48-> {
                text = getString(R.string.Fog)
                image = R.drawable.fog
            }
            in 51..55-> {
                text = getString(R.string.Drizzle)
                image = R.drawable.drizzle
            }
            in 56..57-> {
                text = getString(R.string.Freezing_drizzle)
                image = R.drawable.freezing_drizzle
            }
            in 61..65-> {
                text = getString(R.string.Rain)
                image = R.drawable.rain
            }
            in 66..67-> {
                text = getString(R.string.Freezing_rain)
                image = R.drawable.freezing_rain
            }
            in 71..75-> {
                text = getString(R.string.Snow_fall)
                image = R.drawable.snow_fall
            }
            77-> {
                text = getString(R.string.Snow_grains)
                image = R.drawable.snow_grains
            }
            in 80..82-> {
                text = getString(R.string.Rain_shower)
                image = R.drawable.rain_showers
            }
            in 85..86-> {
                text = getString(R.string.Snow_shower)
                image = R.drawable.snow_showers
            }
            95-> {
                text = getString(R.string.Thunderstorm)
                image = R.drawable.thunderstorm
            }
            in 96..99-> {
                text = getString(R.string.SnH_Thunderstorm)
                image = R.drawable.thunderstorm_heavy_hail
            }
        }

        textViewWeatherCode.setText(text)
        linearLayout.setBackgroundResource(image)
    }
}