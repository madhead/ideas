package me.madhead.ideas.github.api

import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import io.ktor.http.HttpHeaders
import io.ktor.http.URLBuilder
import io.ktor.util.toMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.madhead.ideas.github.model.Issue

class IssuesRequest(
    url: URLBuilder,
    headers: Map<String, List<String>>,
    httpClient: HttpClient,
) : Request<List<Issue>>(
    url, headers, httpClient
) {
    suspend operator fun invoke(): Flow<Issue> {
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
        val response = httpClient.get<HttpResponse>(url.build()) {
            header(HttpHeaders.Accept, "application/vnd.github.squirrel-girl-preview")
        }
        val data = response.receive<List<Issue>>()
        val nextRequest = response.next?.let {
            IssuesRequest(
                url = URLBuilder(it),
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
