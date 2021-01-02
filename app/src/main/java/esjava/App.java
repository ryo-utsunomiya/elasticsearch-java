package esjava;

import java.io.IOException;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class App {

  public static void main(String[] args) {
    try (var client = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200, "http")))) {

      new Search(client, "my_index").execute();

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
