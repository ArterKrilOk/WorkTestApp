package space.pixelsg.worktestapp.ui.summary

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import space.pixelsg.worktestapp.common.BaseActivity
import space.pixelsg.worktestapp.common.BaseViewModelFactory
import space.pixelsg.worktestapp.databinding.ActivitySummaryBinding
import space.pixelsg.worktestapp.ui.UiUtils.repeatOnStarted
import space.pixelsg.worktestapp.ui.summary.SummaryViewModel.Companion.EMPTY_JOB_ID


class SummaryActivity : BaseActivity<SummaryViewModel>() {
    private lateinit var binding: ActivitySummaryBinding

    private val clipboardManager by lazy {
        getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        repeatOnStarted {
            viewModel.state.collect {
                when (it) {
                    SummaryViewModel.LoadState.LOADING -> {
                        binding.progressCircular.isVisible = true
                        binding.nestedView.isVisible = false
                        binding.errorView.isVisible = false
                    }
                    SummaryViewModel.LoadState.ERROR -> {
                        binding.progressCircular.isVisible = false
                        binding.nestedView.isVisible = false
                        binding.errorView.isVisible = true
                    }
                    SummaryViewModel.LoadState.LOADED -> {
                        binding.progressCircular.isVisible = false
                        binding.nestedView.isVisible = true
                        binding.errorView.isVisible = false
                    }
                }
            }
        }
        repeatOnStarted {
            viewModel.jobSummary.collect {
                binding.nameView.text = it.name
                binding.phoneView.text = it.phone
                binding.descriptionView.text = it.description
                binding.siteView.text = it.website
                //Hide unused views
                binding.mapView.isVisible = it.lat != 0.0 && it.lon != 0.0
                binding.phoneView.isVisible = it.phone.isNotEmpty()
                binding.siteView.isVisible = it.website.isNotEmpty()
                //Load images
                Glide
                    .with(binding.imageView)
                    .load(it.img)
                    .centerCrop()
                    .into(binding.imageView)
                Glide
                    .with(binding.mapImageView)
                    .load(MapImage.mapImage(it.lat, it.lon))
                    .centerCrop()
                    .into(binding.mapImageView)
                //Set listeners
                binding.siteView.setOnClickListener { _ ->
                    //Also you can simply open url ACTION_VIEW intent...
                    clipboardManager.setPrimaryClip(ClipData.newPlainText("site", it.website))
                }
                binding.phoneView.setOnClickListener { _ ->
                    //Also you can simply open phone app with this telephone
                    clipboardManager.setPrimaryClip(ClipData.newPlainText("tel", it.phone))
                }
            }
        }
    }

    override fun provideViewModel() = ViewModelProvider(
        viewModelStore,
        BaseViewModelFactory(
            app,
            intent.getIntExtra(JOB_ID, EMPTY_JOB_ID)
        )
    )[SummaryViewModel::class.java]

    companion object {
        const val JOB_ID = "job-id"
    }
}