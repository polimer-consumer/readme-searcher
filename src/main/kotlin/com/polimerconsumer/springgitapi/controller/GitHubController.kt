package com.polimerconsumer.springgitapi.controller

import com.polimerconsumer.springgitapi.client.GitHubClient
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import reactor.core.publisher.Mono
import java.nio.charset.StandardCharsets
import java.util.Base64

@Controller
class GitHubController(private val gitHubClient: GitHubClient) {

    @GetMapping("/")
    fun index(): String {
        return "index"
    }

    @PostMapping("/repositories")
    fun getRepositories(@RequestParam orgLink: String, @RequestParam accessToken: String, model: Model): Mono<String> {
        val orgName = orgLink.substringAfterLast("/")
        val repositories = gitHubClient.fetchRepositories(orgName, accessToken)

        val enrichedRepositories = repositories.flatMap { repository ->
            gitHubClient.fetchReadMe(repository.owner.login, repository.name, accessToken)
                .map { content ->
                    content.contains("hello", ignoreCase = true)
                }
                .defaultIfEmpty(false)
                .map { containsHello ->
                    repository.copy(isHighlighted = containsHello)
                }
        }

        return enrichedRepositories.collectList().doOnNext { model.addAttribute("repositories", it) }
            .thenReturn("repositories")
    }
}
