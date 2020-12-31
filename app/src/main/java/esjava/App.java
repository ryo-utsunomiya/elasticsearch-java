package esjava;

import java.io.IOException;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class App {

  public static void main(String[] args) {
    try (var client = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200, "http")))) {

      var crud = new CRUD(client, "my_index");
      String id = crud.create("created");
      System.out.println(crud.read(id));
      crud.update(id, "updated");
      System.out.println(crud.read(id));
      crud.delete(id);

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
