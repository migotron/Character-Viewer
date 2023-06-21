package com.example.characterviewerapplication.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.characterviewerapplication.R
import com.example.characterviewerapplication.data.network.model.CharacterModel
import com.example.characterviewerapplication.databinding.FragmentDetailBinding
import com.example.characterviewerapplication.domain.utilities.Const.Companion.ICON_BASE_URL
import com.example.characterviewerapplication.domain.utilities.UtilityManager.isTablet
import com.example.characterviewerapplication.presentation.ui.viewModels.CharacterViewModel

/**
 * Fragment detail class containing image and description of characters
 */
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val sharedCharacterViewModel: CharacterViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isTablet(resources)) {
            sharedCharacterViewModel.selectedCharacter.observe(viewLifecycleOwner) { character ->
                displayCharacter(character)
            }
        } else {
            val character: CharacterModel? = arguments?.getParcelable("character")
            character?.let { displayCharacter(it) }
        }
    }

    private fun displayCharacter(character: CharacterModel) {
        binding.titleTextView.text = character.text
        loadImageWithPlaceholder(ICON_BASE_URL + character.Icon?.url, binding.imageView)
    }


    private fun loadImageWithPlaceholder(url: String?, imageView: ImageView) {
        if (url != null) {
            Glide.with(imageView)
                .load(url)

                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions().override(50, 50))
                .centerCrop()
                .error(R.drawable.ic_launcher_foreground)
                .into(imageView)
        }
    }
}
