package com.github.yard01.map_example
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.map_layout.*
import java.util.*

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var locationManager: LocationManager
    private lateinit var progressBar: ProgressBar
    private lateinit var geocoder: Geocoder

    private fun markOnMap(location: Location, color: Float = BitmapDescriptorFactory.HUE_RED) {
        if (!this::mMap.isInitialized) return
        val latLng = LatLng(location.latitude, location.longitude)

        mMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.defaultMarker(color))
                .title("${location.provider}: ${location.latitude.toString()}, ${location.longitude}")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))

    }

    private var locationListener = object: LocationListener {
        override fun onLocationChanged(location: Location?) {
            locationManager.removeUpdates(this)

            if (!this@MapActivity::mMap.isInitialized) return
            Log.d("loch", "On Changed")
            progressBar.visibility = ProgressBar.INVISIBLE
            if (location != null) {
                markOnMap(location)
                /*var addrList = geocoder.getFromLocation(location.latitude, location.longitude, 1)

               addrList.forEach {
                   Toast.makeText(
                       this@MapActivity,
                       "${it.countryName}, ${it.locality}",
                       Toast.LENGTH_SHORT
                   ).show()
                   //Log.d("addr", "${it.countryName}, ${it.locality}")
               }


                addrList = geocoder.getFromLocation(location.latitude, location.longitude, 1)

                //Places
                Places.initialize(
                    this@MapActivity,
                    this@MapActivity.getString(R.string.google_map_api_key)
                )

                // Create a new PlacesClient instance
                val placesClient = Places.createClient(this@MapActivity)

                if (ActivityCompat.checkSelfPermission(
                        this@MapActivity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                // Define a Place ID.
                val placeId = "INSERT_PLACE_ID_HERE"

// Specify the fields to return.
                val placeFields = listOf(Place.Field.ID, Place.Field.NAME)

// Construct a request object, passing the place ID and fields array.
                val request = FetchPlaceRequest.newInstance(placeId, placeFields)
                //val placeFields = Arrays.asList(
                //    Place.Field.NAME,
                //    Place.Field.RATING,
                //    Place.Field.PHOTO_METADATAS
                //)
                val p: Place? = null
                //placesClient.fetchPlace(request)
                placesClient.findCurrentPlace(FindCurrentPlaceRequest.newInstance(placeFields)).addOnSuccessListener {
                        response: FindCurrentPlaceResponse ->
                    val place = response.placeLikelihoods

                    Log.i("place", "Place found: ${place[0].place.name} , ${place[0].place.id}")
                }.addOnFailureListener { exception: Exception ->
                    if (exception is ApiException) {
                        Log.e("place", "Place not found: ${exception.message}")
                        val statusCode = exception.statusCode
                        //TODO("Handle error with given status code")
                    }
                }*/
            }
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
            Log.d("loch", "On Status Changed")
        }

        override fun onProviderEnabled(p0: String?) {
            Log.d("loch", "On Provider Enabled")
        }

        override fun onProviderDisabled(p0: String?) {
            progressBar.visibility = ProgressBar.INVISIBLE
            Log.d("loch", "On Provider Disabled")
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


    fun getEnabledLocation() {

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
            return

        }

        var location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if (location != null) markOnMap(location, BitmapDescriptorFactory.HUE_AZURE)
        
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        if (location != null) markOnMap(location, BitmapDescriptorFactory.HUE_GREEN)

        location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
        if (location != null) markOnMap(location, BitmapDescriptorFactory.HUE_ORANGE)

        //if (location != null) return location else location = locationManager.getLastKnownLocation(
        //    LocationManager.NETWORK_PROVIDER
        //)
        //if (location != null) return location else location = locationManager.getLastKnownLocation(
        //    LocationManager.PASSIVE_PROVIDER
        //)

        //if (location == null) {
        progressBar.visibility = ProgressBar.VISIBLE
        /*
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0,
            0f,
            locationListener,
            Looper.getMainLooper()
        )*/

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
            ).addCallback(object : Snackbar.Callback() {
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
        geocoder = Geocoder(this, Locale.getDefault())
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
        //Places.initialize( this@MapActivity, this@MapActivity.getString(R.string.google_map_api_key), true)
        //mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
    }
}