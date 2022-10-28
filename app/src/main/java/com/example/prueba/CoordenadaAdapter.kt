package com.example.prueba

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class CoordenadaAdapter(private val mContext: Context,
    private var coordList: ArrayList<Coordenada>,
) : RecyclerView.Adapter<CoordenadaAdapter.CoordViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoordenadaAdapter.CoordViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_coordenada,
            parent, false
        )

        return CoordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CoordenadaAdapter.CoordViewHolder, position: Int) {


        holder.latTtxt.text = coordList[position].latitude.toString()
        holder.lngTxt.text = coordList[position].longitud.toString()
        val geocoder = Geocoder(mContext, Locale.getDefault())
        val addresses: List<Address>?
        val address: Address?
        var fulladdress = ""
        addresses = geocoder.getFromLocation(coordList[position].latitude,coordList[position].longitud, 1)

        if (addresses.isNotEmpty()) {
            address = addresses[0]
            fulladdress = address.getAddressLine(0)
            var city = address.getLocality();
            var state = address.getAdminArea();
            var country = address.getCountryName();

            var postalCode = address.getPostalCode();
            holder.address.text = fulladdress
            var knownName = address.getFeatureName();
        } else{
            fulladdress = "Ubicación no disponible"
        }
    }

    override fun getItemCount(): Int {

        return coordList.size
    }

    fun getAddress(lat: Double, lng: Double){

    }



    class CoordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val latTtxt: TextView = itemView.findViewById(R.id.idLongitude)
        val lngTxt: TextView = itemView.findViewById(R.id.idLatitude)
        val address: TextView = itemView.findViewById(R.id.idAddress)
    }
}