package finki.mips.moviesapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import finki.mips.moviesapplication.R

class ActorAdapter(private val actors: ArrayList<String> = ArrayList()) :
    RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_actor_item, parent, false)
        return ActorViewHolder(view)
    }

    override fun getItemCount() = actors.size

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) =
        holder.bind(actors[position])

    fun updateActors(actors: List<String>) {
        this.actors.clear()
        this.actors.addAll(actors)
        notifyDataSetChanged()
    }

    class ActorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tvName: TextView = view.findViewById(R.id.tvName)

        fun bind(actor: String) {
            tvName.text = actor
        }
    }

}