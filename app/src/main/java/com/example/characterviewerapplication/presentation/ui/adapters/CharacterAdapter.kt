package com.example.characterviewerapplication.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.characterviewerapplication.data.network.model.CharacterModel
import com.example.characterviewerapplication.databinding.ItemCharacterBinding

/**
 * List Adpater class for Characters
 */
class CharacterAdapter(
    private var characterList: List<CharacterModel>,
    private val onItemClick: (CharacterModel) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(inflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characterList[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int = characterList.size

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val character = characterList[position]
                    onItemClick(character)
                }
            }
        }

        fun bind(character: CharacterModel) {
            var name = filterNameFromTitle(character.text)
            binding.titleCharacter.text = name
            // Set other properties or update UI as needed
        }

        /**
         * Method to filter name from title field from API Response
         */
        private fun filterNameFromTitle(titleDescription: String): String {
            val hyphenIndex = titleDescription.indexOf('-')
            val filteredName = if (hyphenIndex != -1) {
                titleDescription.substring(0, hyphenIndex).trim()
            } else {
                titleDescription.trim()
            }
            return filteredName
        }
    }
}
