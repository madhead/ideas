package me.madhead.ideas.github.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.client.statement.request
import io.ktor.http.HttpHeaders
import io.ktor.http.Url
import io.ktor.util.toMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.madhead.ideas.github.model.Issue

class IssuesRequest(
    url: Url,
    headers: Map<String, List<String>>,
    httpClient: HttpClient,
) : Request(url, headers, httpClient) {
    operator fun invoke(): Flow<Issue> {
        var request = this;
        var response: Response<List<Issue>, IssuesRequest>

        return flow {
            do {
                response = request.execute()
                response.data.forEach { emit(it) }
                response.next?.let {
                    request = it
                }
            } while (response.next != null)
        }
    }

    private suspend fun execute(): Response<List<Issue>, IssuesRequest> {
        val response = httpClient.get {
            url(this@IssuesRequest.url)
            header(HttpHeaders.Accept, "application/vnd.github.squirrel-girl-preview")
        }
        val data: List<Issue> = response.body()
        val nextRequest = response.next?.let {
            IssuesRequest(
                url = it,
                headers = response.request.headers.toMap(),
                httpClient = httpClient
            )
        }

        return Response(
            data = data,
            next = nextRequest,
        )
    }
}
