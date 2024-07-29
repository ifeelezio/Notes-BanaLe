package com.example.notesbanale

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
//import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notesbanale.Models.Note
import com.example.notesbanale.databinding.ActivityAddNoteBinding
import java.text.SimpleDateFormat
import java.util.Date

@Suppress("DEPRECATION", "CAST_NEVER_SUCCEEDS")
class AddNote : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    private lateinit var note: Note
    private lateinit var old_notes: Note
    var isUpdate = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        //val title = findViewById<EditText>(R.id.title)
        //val note = findViewById<EditText>(R.id.et_note)

        try {
            old_notes = intent.getSerializableExtra("current_note") as Note
            binding.etTitle.setText(old_notes.title)
            binding.etNote.setText(old_notes.note)
            isUpdate = true
        }

        catch ( e : Exception){
            e.printStackTrace()
        }

        binding.imgCheck.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val note_desc = binding.etNote.text.toString()
           //Toast.makeText(this, "$title $notes", Toast.LENGTH_SHORT).show()
            //Log.d("testing123","$title $notes")

            if (title.isNotEmpty() || note_desc.isNotEmpty()){
                val formatter = SimpleDateFormat("EEE, d MMM yyy HH:mm a")
                val time = formatter.format(Date())
                Log.d("testing123","$time")

                if(isUpdate) {
                    note =Note(old_notes.id,title,note_desc,formatter.format(Date()))


                }else{
                    note= Note(null,title,note_desc,formatter.format(Date()))
                }
                val intent= Intent()
                intent.putExtra("note",note)

                setResult(Activity.RESULT_OK,intent)
                finish()


            }

            else{
                Toast.makeText(this@AddNote,"Please enter some data ",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
        binding.imgBackArrow.setOnClickListener(){
            onBackPressed()
        }
    }
}