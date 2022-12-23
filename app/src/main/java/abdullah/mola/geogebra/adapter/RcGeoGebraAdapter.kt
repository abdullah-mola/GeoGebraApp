package abdullah.mola.geogebra.adapter

import abdullah.mola.geogebra.data.local.Hit
import abdullah.mola.geogebra.databinding.CvGeogebraBinding
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RcGeoGebraAdapter @Inject constructor() :
    RecyclerView.Adapter<RcGeoGebraAdapter.GeoGebraVh>() {
    var currentItem: Hit? = null
    private var dataList: List<Hit> = listOf()
    private lateinit var click: (geoGebraItem: Hit) -> Unit
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeoGebraVh {
        val binding = CvGeogebraBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GeoGebraVh(binding)
    }

    override fun onBindViewHolder(holder: GeoGebraVh, position: Int) {

        currentItem = dataList[position]
        holder.binding.apply {
            TvCreator.text = currentItem!!.creator.creator_name
            var url = currentItem!!.thumbUrl
            var thumbUrl = url.replace("-thumb$1.png", "-thumb.png")
            tvTitle.text = currentItem!!.title

            Glide.with(holder.binding.root).load(thumbUrl).into(thumbnail)
            holder.binding.root.setOnClickListener { click(dataList[position]) }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun swapData(data: List<Hit>) {
        this.dataList = data
        notifyDataSetChanged()
    }


    @Singleton
    fun click(callback: (geoGebra: Hit) -> Unit) {
        this.click = callback
    }


    class GeoGebraVh(val binding: CvGeogebraBinding) : RecyclerView.ViewHolder(binding.root)

}
