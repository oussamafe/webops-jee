package services;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.google.gson.Gson;

import entities.Company;
import entities.Packs;
import interfaces.CompanyInterfaceRemote;

@Stateless
@LocalBean
public class CompanyServiceImpl implements CompanyInterfaceRemote {

	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;

	@Override
	public Company registerCompany(Company company) {
		
		try {
			em.persist(company);
		} catch (PersistenceException e) {
			return null;
		}
		return company;
	}

	@Override
	public int removeComany(int idC) {

		Company company = em.find(Company.class, idC);
		em.remove(company);
		return 1;
	}

	@Override
	public List<Company> getAllCompanies() {

		Settings settings = Settings.builder().put("client.transport.sniff", true).put("cluster.name", "elasticsearch").build();
		TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(new InetSocketAddress("127.0.0.1", 9300)));
		List<Company> list = em.createQuery("from Company", Company.class).getResultList();
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		for (Company company : list) {
			bulkRequest.add(client.prepareIndex("companies", "_doc", String.valueOf(company.getId()))
					.setSource(new Gson().toJson(company), XContentType.JSON));
		}
		BulkResponse bulkResponse = bulkRequest.get();
		SearchResponse response = client.prepareSearch("companies").setQuery(QueryBuilders.multiMatchQuery("Textile", "field")).execute().actionGet();
		List<SearchHit> searchHits = Arrays.asList(response.getHits().getHits());
		List<Company> results = new ArrayList<Company>();
		searchHits.forEach(hit -> results.add(new Gson().fromJson(hit.getSourceAsString(), Company.class)));
		client.close();
		return results;
	}

}
