package com.example.notesbanale.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesbanale.Models.Note
import com.example.notesbanale.R
import kotlin.random.Random

class NotesAdapter(private val context: Context, val listener: NotesitemclickListener) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val NotesList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        )
    }



    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val currentNote = NotesList[position]

        holder.title.text = currentNote.title
        holder.title.isSelected=true

        holder.note.text = currentNote.note


        holder.date.text = currentNote.date
        holder.date.isSelected=true

        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(RandomColour(),null))

        holder.notes_layout.setOnClickListener {
            listener.onItemClicked(NotesList[holder.adapterPosition])
        }

        holder.notes_layout.setOnLongClickListener {
            listener.onLongItemClicked(NotesList[holder.adapterPosition],holder.notes_layout)
            true
        }

    }

    override fun getItemCount(): Int {
        return NotesList.size
    }

    fun updateList(newList : List<Note>){

        fullList.clear()
        fullList.addAll(newList)

        NotesList.clear()
        NotesList.addAll(fullList)
        notifyDataSetChanged()

    }

    fun filterlist(search : String){

        NotesList.clear()

            for (item in fullList){

                if (item.title?.lowercase()?.contains(search.lowercase())==true ||
                    item.note?.lowercase()?.contains(search.lowercase())==true){

                    NotesList.add(item)

                }
            }

        notifyDataSetChanged()

    }

    fun RandomColour() : Int{

        val list = ArrayList<Int>()
        list.add(R.color.Notecol1)
        list.add(R.color.Notecol2)
        list.add(R.color.Notecol3)
        list.add(R.color.Notecol4)
        list.add(R.color.Notecol5)
        list.add(R.color.Notecol6)
        list.add(R.color.purple_200)
        list.add(R.color.purple_500)
        list.add(R.color.purple_700)
        list.add(R.color.teal_200)
        list.add(R.color.teal_700)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]

    }


    inner class NoteViewHolder(itemVie : View) : RecyclerView.ViewHolder(itemVie){

        val notes_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val note = itemView.findViewById<TextView>(R.id.tv_notes)
        val date = itemView.findViewById<TextView>(R.id.tv_date)

    }

    interface  NotesitemclickListener{

        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note,cardView: CardView)

    }



}