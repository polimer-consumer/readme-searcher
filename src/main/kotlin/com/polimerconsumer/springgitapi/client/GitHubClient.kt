package com.polimerconsumer.springgitapi.client

import com.polimerconsumer.springgitapi.model.ReadmeResponse
import com.polimerconsumer.springgitapi.model.Repository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import com.google.common.io.BaseEncoding
import java.nio.charset.StandardCharsets

@Component
class GitHubClient(private val webClient: WebClient) {
    private val logger = LoggerFactory.getLogger(GitHubClient::class.java)

    fun fetchRepositories(orgName: String, accessToken: String): Flux<Repository> {
        return webClient.get()
            .uri("/orgs/$orgName/repos")
            .header("Accept", "application/vnd.github+json")
            .headers { it.setBearerAuth(accessToken) }
            .retrieve()
            .bodyToFlux(Repository::class.java)
    }

    fun fetchReadMe(owner: String, repo: String, accessToken: String): Mono<String> {
        return webClient.get()
            .uri("/repos/$owner/$repo/readme")
            .headers { it.setBearerAuth(accessToken) }
            .header("Accept", "application/vnd.github.v3.raw")
            .retrieve()
            .bodyToMono(String::class.java)
            .onErrorResume { e ->
                println("Error fetching README for $repo: ${e.message}")
                Mono.empty()
            }
    }
}
