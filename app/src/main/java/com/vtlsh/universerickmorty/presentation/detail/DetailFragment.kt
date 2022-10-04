package com.vtlsh.universerickmorty.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vtlsh.universerickmorty.databinding.FragmentDetailBinding
import com.vtlsh.universerickmorty.presentation.MainViewModel
import com.vtlsh.universerickmorty.util.getEpisode
import com.vtlsh.universerickmorty.util.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val args by navArgs<DetailFragmentArgs>()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        val episodeId = args.item?.episode?.first()
        viewModel.getEpisode(getEpisode(episodeId))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        observe()
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val urlImage = args.item?.image
        binding.ivDetail.transitionName = urlImage

        urlImage?.let {
            binding.ivDetail.load(it, loadOnlyFromCache = true) {
                startPostponedEnterTransition()
            }
        }
        binding.apply {
            btnBackToMainList.setOnClickListener { findNavController().popBackStack() }

            val detailBackground = args.item?.status?.gradient?.let { resources.getDrawable(it) }
            clCharacterDetail.background = detailBackground

            txtNameDetail.text = args.item?.name
            txtSpeciesDetail.text = args.item?.species?.value
            txtStatusDetail.text = args.item?.status?.value
            txtLastLocationDetail.text = args.item?.location?.name
        }
    }

    private fun observe(){
        viewModel.episodeRemoteData.observe(viewLifecycleOwner) { episode ->
            binding.txtFirstSeenDetail.text = episode?.name ?: "..."
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}