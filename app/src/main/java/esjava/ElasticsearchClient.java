package esjava;

import org.elasticsearch.client.RestHighLevelClient;

public abstract class ElasticsearchClient {

  protected final RestHighLevelClient client;
  protected final String index;

  public ElasticsearchClient(RestHighLevelClient client, String index) {
    this.client = client;
    this.index = index;
  }
}
