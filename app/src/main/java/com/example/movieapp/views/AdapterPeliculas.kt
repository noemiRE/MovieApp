package com.example.movieapp.views

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.R
import com.example.movieapp.core.Constantes
import com.example.movieapp.models.PeliculaModel
import com.example.movieapp.viewmodels.PeliculasViewModel

class AdapterPeliculas(
    val context: Context,
    var listaPeliculas: List<PeliculaModel>
): RecyclerView.Adapter<AdapterPeliculas.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cvPelicula = itemView.findViewById(R.id.cvPeliculas) as CardView
        val ivPoster = itemView.findViewById(R.id.ivPoster) as ImageView
        val pcIndicator = itemView.findViewById(R.id.circular_progress) as CircularProgressIndicator
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPeliculas.ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_peliculas, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: AdapterPeliculas.ViewHolder, position: Int) {
        val pelicula = listaPeliculas[position]

        Glide
            .with(context)
            .load("${Constantes.BASE_URL_IMAGEN}${pelicula.poster}")
            .apply(RequestOptions().override(Constantes.IMAGEN_ANCHO, Constantes.IMAGEN_ALTO))
            .into(holder.ivPoster)

        holder.pcIndicator.maxProgress = Constantes.MAX_CALIFICACION
        holder.pcIndicator.setCurrentProgress(pelicula.votoPromedio.toDouble())
    }

    override fun getItemCount(): Int {
        return listaPeliculas.size
    }
}