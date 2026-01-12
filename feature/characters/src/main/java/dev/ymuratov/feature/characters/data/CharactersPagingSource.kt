package dev.ymuratov.feature.characters.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Optional
import dev.ymuratov.core.network.GetCharactersQuery

class CharactersPagingSource(
    private val apolloClient: ApolloClient,
    private val searchQuery: String,
) : PagingSource<Int, GetCharactersQuery.Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetCharactersQuery.Result> {
        val page = params.key ?: 1
        return try {
            val response = apolloClient.query(
                GetCharactersQuery(page = Optional.present(page), name = Optional.present(searchQuery))
            ).execute()
            val characters =
                response.data?.characters ?: return LoadResult.Page(data = emptyList(), prevKey = null, nextKey = null)
            val items = characters.results?.filterNotNull().orEmpty()
            LoadResult.Page(data = items, prevKey = characters.info?.prev, nextKey = characters.info?.next)
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GetCharactersQuery.Result>): Int? {
        return state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1) ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }
    }
}