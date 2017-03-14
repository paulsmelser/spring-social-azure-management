package org.springframework.social.microsoft.azure.http.client;

import static java.util.Collections.singletonList;

import java.net.URI;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestResource {
	private RestTemplate restTemplate;
	private String resourceBaseUri;

	public HttpRequestBuilder request(){
		return new HttpRequestBuilder(this, this.resourceBaseUri);
	}

	public HttpRequestBuilder request(String msRequestId, String msCorrelationId){
		return new HttpRequestBuilder(this, this.resourceBaseUri, msRequestId, msCorrelationId);
	}
	public HttpRequestBuilder request(MediaType mediaType){
		return new HttpRequestBuilder(this, this.resourceBaseUri).header(HttpHeaders.CONTENT_TYPE, singletonList(mediaType.toString()));
	}

	RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public RestResource(RestTemplate restTemplate, String resourceBaseUri) {
		this.restTemplate = restTemplate;
		this.resourceBaseUri = resourceBaseUri;
	}

	<T> ResponseEntity<T> get(URI url, ParameterizedTypeReference<T> responseType) {
		return execute(url, HttpMethod.GET, null, responseType);
	}
	<T> ResponseEntity<T> get(URI url, ParameterizedTypeReference<T> responseType, HttpHeaders headers) {
		return execute(url, HttpMethod.GET, new HttpEntity<>(headers), responseType);
	}

	<T> ResponseEntity<T> get(URI url, Class<T> responseType) {
		return execute(url, HttpMethod.GET, null, responseType);
	}

	<T> ResponseEntity<T> get(URI url, Class<T> responseType, HttpHeaders header) {
		return execute(url, HttpMethod.GET, new HttpEntity<>(header), responseType);
	}

	<T> ResponseEntity<T> post(URI url, ParameterizedTypeReference<T> responseType) {
		return execute(url, HttpMethod.POST, HttpEntity.EMPTY, responseType);
	}

	<T> ResponseEntity<T> post(URI url, ParameterizedTypeReference<T> responseType, HttpHeaders headers) {
		return execute(url, HttpMethod.POST, new HttpEntity<Object>(headers), responseType);
	}

	<T, R> ResponseEntity<R> post(URI uri, T entity, Class<R> responseType) {
		return execute(uri, HttpMethod.POST, new HttpEntity<>(entity), responseType);
	}

	<T, R> ResponseEntity<R> post(URI uri, HttpEntity<T> entity, Class<R> responseType) {
		return execute(uri, HttpMethod.POST, entity, responseType);
	}

	<T, R> ResponseEntity<R> patch(URI uri, HttpEntity<T> entity, Class<R> responseType) {
		return execute(uri, HttpMethod.PATCH, entity, responseType);
	}

	<T, R> ResponseEntity<R> put(URI uri, HttpEntity<T> entity, Class<R> responseType) {
		return execute(uri, HttpMethod.PUT, entity, responseType);
	}

	public ResponseEntity delete(URI uri, HttpHeaders headers){
		return restTemplate.exchange(uri, HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
	}

	public <T, R> ResponseEntity<R> execute(URI uri, HttpMethod httpMethod, HttpEntity<T> entity, Class<R> responseType){
		return restTemplate.exchange(
				uri, httpMethod, entity, responseType
		);
	}

	public <T, R> ResponseEntity<R> execute(URI uri, HttpMethod httpMethod, HttpEntity<T> entity, ParameterizedTypeReference<R> responseType){
		return restTemplate.exchange(
				uri, httpMethod, entity, responseType
		);
	}

	public static <E> ParameterizedTypeReference<E> typeOf(Class<E> clz){
		return new ParameterizedTypeReference<E>() {};
	}
}
