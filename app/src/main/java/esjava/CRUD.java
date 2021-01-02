package esjava;

import java.io.IOException;
import java.util.Map;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.GetSourceRequest;

public class CRUD extends ElasticsearchClient {

  public CRUD(RestHighLevelClient client, String index) {
    super(client, index);
  }

  public String create(String message) throws IOException {
    var request = new IndexRequest(index);
    request.source(Map.of("message", message));
    var response = client.index(request, RequestOptions.DEFAULT);
    return response.getId();
  }

  public String read(String id) throws IOException {
    var request = new GetSourceRequest(index, id);
    var response = client.getSource(request, RequestOptions.DEFAULT);
    return response.getSource().toString();
  }

  public void update(String id, String message) throws IOException {
    var request = new UpdateRequest(index, id);
    request.doc(Map.of("message", message));
    client.update(request, RequestOptions.DEFAULT);
  }

  public void delete(String id) throws IOException {
    var request = new DeleteRequest(index, id);
    client.delete(request, RequestOptions.DEFAULT);
  }
}
