package fastcampus.aop.part5.chapter07.presentation.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fastcampus.aop.part5.chapter07.databinding.FragmentMyPageBinding
import fastcampus.aop.part5.chapter07.domain.model.ReviewedMovie
import fastcampus.aop.part5.chapter07.extension.dip
import fastcampus.aop.part5.chapter07.extension.toGone
import fastcampus.aop.part5.chapter07.extension.toVisible
import fastcampus.aop.part5.chapter07.presentation.home.GridSpacingItemDecoration
import org.koin.android.scope.ScopeFragment

class MyPageFragment : ScopeFragment(), MyPageContract.View {

    override val presenter: MyPageContract.Presenter by inject()

    private var binding: FragmentMyPageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMyPageBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindView()
        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showLoadingIndicator() {
        binding?.progressBar?.toVisible()
    }

    override fun hideLoadingIndicator() {
        binding?.progressBar?.toGone()
    }

    override fun showNoDataDescription(message: String) {
        binding?.recyclerView?.toGone()
        binding?.descriptionTextView?.toVisible()
        binding?.descriptionTextView?.text = message
    }

    override fun showErrorDescription(message: String) {
        binding?.recyclerView?.toGone()
        binding?.descriptionTextView?.toVisible()
        binding?.descriptionTextView?.text = message
    }

    override fun showReviewedMovies(reviewedMovies: List<ReviewedMovie>) {
        (binding?.recyclerView?.adapter as? MyPageAdapter)?.apply {
            this.reviewedMovies = reviewedMovies
            notifyDataSetChanged()
        }
    }

    private fun initViews() {
        binding?.recyclerView?.apply {
            adapter = MyPageAdapter()
            layoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
            addItemDecoration(GridSpacingItemDecoration(3, dip(6f)))
        }
    }

    private fun bindView() {
        (binding?.recyclerView?.adapter as? MyPageAdapter)?.apply {
            onMovieClickListener = { movie ->
                val action = MyPageFragmentDirections.toMovieReviewsAction(movie)
                findNavController().navigate(action)
            }
        }
    }
}
