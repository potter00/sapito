package mx.itson.sapito

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import mx.itson.sapito.entities.Location
import mx.itson.sapito.utilerias.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    var context : Context = this
    var map : GoogleMap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    fun getLocation(latitude:String, longitude:String){
        val Call : Call<Location> = RetrofitUtil.getApi()!!.getLocation(latitude, longitude, true)

        Call.enqueue(object : Callback<Location>{
            override fun onResponse(call: Call<Location>, response: Response<Location>) {
                val location : Location? = response.body()
                Log.i("elevacion",location?.elevation.toString())
                Toast.makeText(context, "La elevacion es de " + location?.elevation,
                    Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Location>, t: Throwable) {
                Log.e("Error...", t.message.toString())
            }

        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        try{
            map = googleMap
            map?.mapType = GoogleMap.MAP_TYPE_HYBRID

            val latLng = LatLng(27.9681409, -110.9189332)
            map?.addMarker(MarkerOptions().position(latLng).draggable(true))
            map?.moveCamera(CameraUpdateFactory.newLatLng(latLng))
            map?.animateCamera(CameraUpdateFactory.zoomTo(12f))

            map?.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
                override fun onMarkerDrag(marker: Marker) {}

                override fun onMarkerDragEnd(marker: Marker) {
                    val latLng = marker.position

                    getLocation(latLng.latitude.toString(), latLng.longitude.toString())
                }

                override fun onMarkerDragStart(p0: Marker) {}

            })

            map?.setOnMapClickListener { latLng ->
                val lat = latLng.latitude.toString()
                val lgn = latLng.longitude.toString()
                Log.i("Latitud",lat)
                Log.i("loongitud",lgn )
                getLocation(lat,lgn)
            }
        }catch(ex : Exception){
            Log.e("Error loading map", ex.toString())
        }
    }









}