package com.example.characterviewerapplication.presentation.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.characterviewerapplication.R
import com.example.characterviewerapplication.data.network.model.CharacterModel
import com.example.characterviewerapplication.databinding.FragmentListBinding
import com.example.characterviewerapplication.domain.utilities.ToastManager
import com.example.characterviewerapplication.domain.utilities.UtilityManager.isTablet
import com.example.characterviewerapplication.presentation.ui.adapters.CharacterAdapter
import com.example.characterviewerapplication.presentation.ui.viewModels.CharacterViewModel

/**
 * Characters Listing screen in vertical view
 */
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharacterAdapter
    private val viewModel: CharacterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getCharactersListValues()

        viewModel.selectedCharacterResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                viewModel.filterCharacterList("") // Filter with an empty query to show all characters initially
            } else {
                // Handle the error case
            }
        })

        viewModel.isShowProgress.observe(viewLifecycleOwner, Observer { showProgress ->
            binding.progressBar.visibility = if (showProgress) View.VISIBLE else View.GONE
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            context?.let { ToastManager.showShortToast(it, errorMessage) }
        })

        viewModel.searchResults.observe(viewLifecycleOwner, Observer {
            setupRecyclerView(it)
        })

        binding.searchCharacter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed in this case
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.filterCharacterList(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                // Not needed in this case
            }
        })
    }

    /**
     * Method to set up list view for all characters list
     */
    private fun setupRecyclerView(characterList: List<CharacterModel>?) {
        if (characterList != null) {
            adapter = CharacterAdapter(characterList) { character ->
                if (isTablet(resources)) {
                    viewModel.selectCharacter(character)
                } else {
                    val bundle = Bundle().apply {
                        putParcelable("character", character)
                    }
                    findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
                }
            }
            recyclerView.adapter = adapter
        }
    }
}
