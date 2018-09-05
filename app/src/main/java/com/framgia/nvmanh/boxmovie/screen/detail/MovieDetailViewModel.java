package com.framgia.nvmanh.boxmovie.screen.detail;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.framgia.nvmanh.boxmovie.BuildConfig;
import com.framgia.nvmanh.boxmovie.data.model.MovieDetail;
import com.framgia.nvmanh.boxmovie.data.model.Review;
import com.framgia.nvmanh.boxmovie.data.model.ReviewResults;
import com.framgia.nvmanh.boxmovie.data.source.MoviesRepository;
import com.framgia.nvmanh.boxmovie.ultis.schedulers.BaseSchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MovieDetailViewModel {

    public ObservableField<MovieDetail> observableMovieDetail = new ObservableField<>();
    public ObservableField<CastAdapter> observableCastAdapter = new ObservableField<>();
    public ObservableField<VideoAdapter> observableVideoAdapter = new ObservableField<>();
    public ObservableField<ReviewAdapter> observableReviewAdapter = new ObservableField<>();
    public ObservableBoolean isLoading = new ObservableBoolean(false);
    public ObservableBoolean isError = new ObservableBoolean(false);
    public ObservableBoolean isLastReview = new ObservableBoolean(false);

    private Context mContext;
    private MoviesRepository mMoviesRepository;
    private BaseSchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;
    private List<Review> mReviews;
    private ReviewAdapter mReviewAdapter;
    private int mMovieId;
    private int mPageReview = 2;

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

    private void loadMovieDetail(int movieId){
        isLoading.set(true);
        Disposable disposable = mMoviesRepository.getMovieDetail(BuildConfig.API_KEY, movieId)
                .observeOn(mSchedulerProvider.ui())
                .subscribeOn(mSchedulerProvider.io())
                .subscribe(new Consumer<MovieDetail>() {
                    @Override
                    public void accept(MovieDetail movieDetail) throws Exception {
                        observableMovieDetail.set(movieDetail);
                        observableCastAdapter
                                .set(new CastAdapter(movieDetail.getCastResults().getCasts()));
                        observableVideoAdapter.set(new VideoAdapter(mContext,
                                movieDetail.getVideoResults().getVideos()));
                        mReviews.addAll(movieDetail.getReviewsResults().getReviews());
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
        isLoading.set(true);
        Disposable disposable = mMoviesRepository.getReviews(BuildConfig.API_KEY, mMovieId, mPageReview)
                .observeOn(mSchedulerProvider.ui())
                .subscribeOn(mSchedulerProvider.io())
                .subscribe(new Consumer<ReviewResults>() {
                    @Override
                    public void accept(ReviewResults reviewResults) throws Exception {
                        List<Review> reviews = reviewResults.getReviews();
                        if (reviews.size() != 0) {
                            mPageReview++;
                            int oldSize = mReviews.size();
                            mReviews.addAll(reviewResults.getReviews());
                            mReviewAdapter.notifyItemRangeChanged(oldSize, mReviews.size());
                            isLoading.set(false);
                        } else {
                            isLastReview.set(true);
                            isLoading.set(false);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        isError.set(true);
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
