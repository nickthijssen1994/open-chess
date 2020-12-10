package nl.nickthijssen.restclient;

public class RestClient {

	public static void main(String[] args) {
		AuthenticationRestClient authenticationRestClient = new AuthenticationRestClient(
				"http://localhost:8090");
	}
}
