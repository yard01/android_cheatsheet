package com.github.yard01.map_example
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.map_layout.*

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var locationManager: LocationManager
    private  lateinit var progressBar: ProgressBar

    private var locationListener = object: LocationListener {
        override fun onLocationChanged(location: Location?) {
            Log.d("loch", "On Changed")
            progressBar.visibility = ProgressBar.INVISIBLE
            if (location != null) {
                val latLng = LatLng(location.latitude, location.longitude)
                mMap.addMarker(MarkerOptions().position(latLng).title("Your Location Marker"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
            }
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

        }

        override fun onProviderEnabled(p0: String?) {

        }

        override fun onProviderDisabled(p0: String?) {
            progressBar.visibility = ProgressBar.INVISIBLE
        }
    }

    companion object {
        val FRAGMENT_CLASSNAME = "fragment_classname"
        val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
    }

    private fun isPermissionGranted(): Boolean {
            return (PermissionChecker.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PermissionChecker.PERMISSION_GRANTED
                 || PermissionChecker.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PermissionChecker.PERMISSION_GRANTED);
    }


    fun getEnabledLocation(): Location? {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            requestLocationPermission()
            return null

        }

        //var location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        //if (location != null) return location else location = locationManager.getLastKnownLocation(
        //    LocationManager.NETWORK_PROVIDER
        //)
        //if (location != null) return location else location = locationManager.getLastKnownLocation(
        //    LocationManager.PASSIVE_PROVIDER
        //)

        //if (location == null) {
        progressBar.visibility = ProgressBar.VISIBLE
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) locationManager.requestSingleUpdate(
                LocationManager.GPS_PROVIDER,
                locationListener,
                Looper.getMainLooper()
        ) else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) locationManager.requestSingleUpdate(
                LocationManager.NETWORK_PROVIDER,
                locationListener,
                Looper.getMainLooper()
        )
        //}
        return null
    }

    fun forceRequestLocation() {
        var locationManager: LocationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    private fun isLocationServiceEnabled(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun buildNoLocationServiceDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage(this.getString(R.string.open_location_settings_quest))
            .setCancelable(false)
            .setPositiveButton("ОК") { dialog, id ->  startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }

            .setNegativeButton("No") { dialog, id ->  dialog.cancel()
            }

        val alert: AlertDialog = builder.create()
        alert.show()
    }

    private fun requestPermissionDialog() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
        ); //MY_PERMISSIONS_REQUEST_READ_CONTACTS

    }
    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )) {
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.

            Snackbar.make(
                this.map_frame_layout,
                this.getString(R.string.need_location_permission),
                Snackbar.LENGTH_LONG
            ).addCallback(object: Snackbar.Callback() {
                override fun onDismissed(snackbar: Snackbar, event: Int) {

                    requestPermissionDialog()
                }
            }) .show() //.takeUnless {  false } .run { Log.d("snack","snack!") }
            //requestPermissionDialog()
        } else {
//                // No explanation needed; request the permission
            requestPermissionDialog()

//                        Toast.makeText(this, "Request Permissions", Toast.LENGTH_LONG);
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_layout)
        progressBar = this.map_progressBar
        locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        this.showLocationButton.setOnClickListener {
            if (!isLocationServiceEnabled()) buildNoLocationServiceDialog() else {
                getEnabledLocation()
                //Snackbar.make(
                //    this.map_frame_layout,
                //    "https://.com",
                //    Snackbar.LENGTH_LONG
                //).show()
                //val sydney = LatLng(-34.0, 151.0)
                //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
            }
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
    }
}