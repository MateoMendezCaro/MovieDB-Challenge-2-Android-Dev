package com.app.moviedb.details.viewmodel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.domain.movies.uc.GetMovieByIdUC
import com.app.domain.series.uc.GetSeriesByIdUC
import com.app.moviedb.details.mapper.DetailUIMapper
import com.app.moviedb.details.model.DetailUI
import com.app.moviedb.details.model.DetailUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getMovieByIdUC: GetMovieByIdUC,
    private val getSeriesByIdUC: GetSeriesByIdUC,
    private val detailUIMapper: DetailUIMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUIState>(DetailUIState.Loading)
    val uiState: StateFlow<DetailUIState> = _uiState

    fun loadDetail(id: String, isMovie: Boolean) {
        viewModelScope.launch {
            Log.d("DetailViewModel", "loadDetail called with id: $id, isMovie: $isMovie")
            _uiState.value = DetailUIState.Loading
            try {
                val detailFlow: Flow<DetailUI?> = if (isMovie) {
                    getMovieByIdUC(id).let { detailUIMapper.mapFromMovie(it) }
                } else {
                    getSeriesByIdUC(id).let { detailUIMapper.mapFromSeries(it) }
                }
                detailFlow.collect { detail ->
                    if (detail != null) {
                        Log.d("DetailViewModel", "Detail found: $detail")
                        _uiState.value = DetailUIState.Success(detail)
                    } else {
                        Log.d("DetailViewModel", "No detail found, setting error state")
                        _uiState.value = DetailUIState.Error
                    }
                }
            } catch (e: Exception) {
                Log.e("DetailViewModel", "Error loading detail", e)
                _uiState.value = DetailUIState.Error
            }
        }
    }
}