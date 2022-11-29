package space.pixelsg.worktestapp.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import space.pixelsg.worktestapp.R
import space.pixelsg.worktestapp.common.BaseActivity
import space.pixelsg.worktestapp.common.BaseViewModelFactory
import space.pixelsg.worktestapp.databinding.ActivityListBinding
import space.pixelsg.worktestapp.ui.UiUtils.repeatOnStarted
import space.pixelsg.worktestapp.ui.list.adapter.ItemAdapter
import space.pixelsg.worktestapp.ui.summary.SummaryActivity

class ListActivity : BaseActivity<ListViewModel>() {
    private lateinit var binding: ActivityListBinding

    private val itemAdapter by lazy {
        ItemAdapter {
            startActivity(Intent(this, SummaryActivity::class.java).apply {
                putExtra(SummaryActivity.JOB_ID, it)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = itemAdapter
    }

    override fun onStart() {
        super.onStart()

        repeatOnStarted {
            viewModel.pagingItems.collectLatest {
                itemAdapter.submitData(it)
            }
        }
        repeatOnStarted {
            itemAdapter.loadStateFlow.collect {
                when {
                    it.refresh is LoadState.Error && itemAdapter.itemCount == 0 -> {
                        binding.recyclerView.isVisible = false
                        binding.progressCircular.isVisible = false
                        binding.errorView.isVisible = true
                    }
                    it.refresh is LoadState.Loading && itemAdapter.itemCount == 0 -> {
                        binding.recyclerView.isVisible = false
                        binding.progressCircular.isVisible = true
                        binding.errorView.isVisible = false
                    }
                    else -> {
                        binding.recyclerView.isVisible = true
                        binding.progressCircular.isVisible = false
                        binding.errorView.isVisible = false
                    }
                }
            }
        }
    }

    override fun provideViewModel() = ViewModelProvider(
        viewModelStore,
        BaseViewModelFactory(
            app
        )
    )[ListViewModel::class.java]
}