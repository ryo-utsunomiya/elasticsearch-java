package esjava;

import java.io.IOException;
import java.util.Arrays;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class Search extends ElasticsearchClient {

  public Search(RestHighLevelClient client, String index) {
    super(client, index);
  }

  public void execute() throws IOException {
    var request = new SearchRequest("my_index");
    request.source(
        SearchSourceBuilder.searchSource()
            .query(
                QueryBuilders.boolQuery()
                    .must(QueryBuilders.termQuery("favorite_genres", "rock"))
                    .should(QueryBuilders.termQuery("favorite_genres", "r&b"))
                    .should(QueryBuilders.termQuery("favorite_genres", "pop"))
                    .mustNot(QueryBuilders.termQuery("favorite_genres", "techno"))
                    .filter(QueryBuilders.rangeQuery("age").gte(50))
            )
            .fetchSource(new String[]{"name", "age", "favorite_genres"}, null)
    );
    var response = client.search(request, RequestOptions.DEFAULT);
    Arrays.stream(response.getHits().getHits())
        .forEach(h -> System.out.println(h.getSourceAsString()));
  }
}
