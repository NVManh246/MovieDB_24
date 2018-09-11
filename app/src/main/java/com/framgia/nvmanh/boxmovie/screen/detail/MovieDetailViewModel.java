package com.framgia.nvmanh.boxmovie.screen.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.widget.Toast;

import com.framgia.nvmanh.boxmovie.BuildConfig;
import com.framgia.nvmanh.boxmovie.R;
import com.framgia.nvmanh.boxmovie.data.model.MovieDetail;
import com.framgia.nvmanh.boxmovie.data.model.Review;
import com.framgia.nvmanh.boxmovie.data.model.ReviewResults;
import com.framgia.nvmanh.boxmovie.data.source.MoviesRepository;
import com.framgia.nvmanh.boxmovie.screen.overview.OverViewActivity;
import com.framgia.nvmanh.boxmovie.ultis.schedulers.BaseSchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MovieDetailViewModel {

    private static final int MAX_SIZE_REVIRES = 10;
    private static final int PAGE_REVIRES = 2;

    public ObservableField<MovieDetail> observableMovieDetail = new ObservableField<>();
    public ObservableField<CastAdapter> observableCastAdapter = new ObservableField<>();
    public ObservableField<VideoAdapter> observableVideoAdapter = new ObservableField<>();
    public ObservableField<ReviewAdapter> observableReviewAdapter = new ObservableField<>();
    public ObservableField<RecommandationAdapter> observableRecommandAdapter = new ObservableField<>();
    public ObservableBoolean isLoading = new ObservableBoolean(false);
    public ObservableBoolean isLoadingReview = new ObservableBoolean(false);
    public ObservableBoolean isFavorite = new ObservableBoolean(false);
    public ObservableBoolean isError = new ObservableBoolean(false);
    public ObservableBoolean isErrorReview = new ObservableBoolean(false);
    public ObservableBoolean isLastReview = new ObservableBoolean(false);

    private Context mContext;
    private MovieDetail mMovieDetail;
    private MoviesRepository mMoviesRepository;
    private BaseSchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;
    private List<Review> mReviews;
    private ReviewAdapter mReviewAdapter;
    private int mMovieId;
    private int mPageReview = PAGE_REVIRES;

    public MovieDetailViewModel(Context context, MoviesRepository moviesRepository,
                                BaseSchedulerProvider schedulerProvider) {
        mContext = context;
        mMoviesRepository = moviesRepository;
        mSchedulerProvider = schedulerProvider;
        mCompositeDisposable = new CompositeDisposable();
        mReviews = new ArrayList<>();
        mReviewAdapter = new ReviewAdapter(mReviews);
        observableReviewAdapter.set(mReviewAdapter);
    }

    public void start(int movieId){
        if(movieId != 0){
            loadMovieDetail(movieId);
            mMovieId = movieId;
        } else {
            isError.set(true);
        }
    }

    public void stop(){
        mReviews.clear();
        mPageReview = PAGE_REVIRES;
        isLoading.set(false);
        isLoadingReview.set(false);
        isError.set(false);
        isErrorReview.set(false);
        isLastReview.set(false);
    }

    private void loadMovieDetail(int movieId){
        isLoading.set(true);
        Disposable disposable = mMoviesRepository.getMovieDetail(BuildConfig.API_KEY, movieId)
                .observeOn(mSchedulerProvider.ui())
                .subscribeOn(mSchedulerProvider.io())
                .subscribe(new Consumer<MovieDetail>() {
                    @Override
                    public void accept(MovieDetail movieDetail) throws Exception {
                        mMovieDetail = movieDetail;
                        observableMovieDetail.set(movieDetail);
                        observableCastAdapter.set(new CastAdapter(mContext,
                                movieDetail.getCastResults().getCasts()));
                        observableVideoAdapter.set(new VideoAdapter(mContext,
                                movieDetail.getVideoResults().getVideos()));
                        observableRecommandAdapter.set(new RecommandationAdapter(mContext,
                                movieDetail.getRecommandations().getMovies()));
                        List<Review> reviews = movieDetail.getReviewsResults().getReviews();
                        mReviews.addAll(reviews);
                        if(reviews.size() < MAX_SIZE_REVIRES) {
                            isLastReview.set(true);
                        }
                        isFavorite.set(mMoviesRepository.isFavorite(movieId));
                        mReviewAdapter.notifyDataSetChanged();
                        isLoading.set(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        isLoading.set(false);
                        isError.set(true);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    public void loadMoreReview(){
        isLoadingReview.set(true);
        Disposable disposable = mMoviesRepository.getReviews(BuildConfig.API_KEY, mMovieId, mPageReview)
                .observeOn(mSchedulerProvider.ui())
                .subscribeOn(mSchedulerProvider.io())
                .subscribe(new Consumer<ReviewResults>() {
                    @Override
                    public void accept(ReviewResults reviewResults) throws Exception {
                        List<Review> reviews = reviewResults.getReviews();
                        if (reviews.size() < MAX_SIZE_REVIRES) {
                            mPageReview++;
                            int oldSize = mReviews.size();
                            mReviews.addAll(reviews);
                            mReviewAdapter.notifyItemRangeChanged(oldSize, mReviews.size());
                            isLoadingReview.set(false);
                            isErrorReview.set(false);
                            isLastReview.set(true);
                        } else {
                            mPageReview--;
                            isLastReview.set(true);
                            isLoadingReview.set(false);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        isErrorReview.set(true);
                        isLoadingReview.set(false);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    public void onClickFavourite(){
        if(isFavorite.get()){
            mMoviesRepository.removeMovie(mMovieId);
            showMsg(mContext.getString(R.string.msg_remove_to_wishlist));
            isFavorite.set(false);
        } else {
            mMoviesRepository.addMovie(mMovieDetail.getMovie());
            showMsg(mContext.getString(R.string.msg_add_to_wishlist));
            isFavorite.set(true);
        }
    }

    public void onClickViewMore(){
        Intent intent = OverViewActivity.getOverViewIntent(mContext, mMovieDetail);
        mContext.startActivity(intent);
    }

    public void retry() {
        isError.set(false);
        loadMovieDetail(mMovieId);
    }

    private void showMsg(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
