package org.springframework.social.microsoft.azure.http.client;

import static java.util.Collections.singletonList;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.social.microsoft.azure.http.AzureHttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

public class HttpRequestBuilder {
	private AzureHttpHeaders headers;
	private RestResource restResource;
	private UriComponentsBuilder uriBuilder;

	HttpRequestBuilder(RestResource restResource, String resourceBaseUri, String msRequestId, String msCorrelationId){
		this.headers = new AzureHttpHeaders();
		this.restResource = restResource;
		this.uriBuilder = UriComponentsBuilder.fromUriString(resourceBaseUri);
	}

	HttpRequestBuilder(RestResource restResource, String resourceBaseUri){
		this.headers = new AzureHttpHeaders();
		this.restResource = restResource;
		this.uriBuilder = UriComponentsBuilder.fromUriString(resourceBaseUri);
	}

	public HttpRequestBuilder header(String key, List<String> value){
		headers.put(key, value);
		return this;
	}

	public HttpRequestBuilder header(String key, String value){
		headers.put(key, singletonList(value));
		return this;
	}

	public HttpRequestBuilder pathSegment(String ... pathSegments){
		uriBuilder.pathSegment(pathSegments);
		return this;
	}

	public HttpRequestBuilder queryParam(String name, Object... values){
		uriBuilder.queryParam(name, values);
		return this;
	}

	public HttpRequestBuilder path(String path){
		uriBuilder.path(path);
		return this;
	}

	public <T, R> ResponseEntity<R> put(T payload, Class<R> aClass){
		HttpEntity<T> tHttpEntity = new HttpEntity<>(payload, headers);
		return restResource.put(uriBuilder.build().toUri(), tHttpEntity, aClass);
	}

	public <T, R> ResponseEntity<R> post(T payload, Class<R> aClass){
		HttpEntity<T> tHttpEntity = new HttpEntity<>(payload, headers);
		return restResource.post(uriBuilder.build().toUri(), tHttpEntity, aClass);
	}

	public <T, R> ResponseEntity<R> patch(T payload, Class<R> aClass){
		HttpEntity<T> tHttpEntity = new HttpEntity<>(payload, headers);
		return restResource.patch(uriBuilder.build().toUri(), tHttpEntity, aClass);
	}

	public <T> ResponseEntity<T> get(Class<T> aClass){
		return restResource.get(uriBuilder.build().toUri(), aClass, headers);
	}

	public <T> ResponseEntity<T> get(){
		return restResource.get(uriBuilder.build().toUri(), new ParameterizedTypeReference<T>() {}, headers);
	}

	public ResponseEntity delete(){
		return restResource.delete(uriBuilder.build().toUri(), headers);
	}

}
