package demo.cosmos.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Component
public class PsCampaignTargetClientImpl implements PsCampaignTargetClient {

	 private final static String TABLE_NAME = "t_campaign";
	@Autowired
	private MongoTemplate mongoTemplate;

	public void save(CampaignClient campaignClient) {
		mongoTemplate.save(campaignClient);
	}

	public List<CampaignClient> findByCampaignIdAndAgentCodeIn(String campaignId, List<String> agentCodeList,
			int type) {
		 Criteria criteria = Criteria.where("campaign_id").is(campaignId);

		 if (agentCodeList != null && agentCodeList.size() > 0) {
		 criteria.andOperator(Criteria.where("agent_code").in(agentCodeList));
		 }

		DBObject query1 = new BasicDBObject(); // setup the query criteria 设置查询条件
		query1.put("campaign_id", campaignId);
		if (type == 1) {
			query1.put("agent_code", (new BasicDBObject("$in", agentCodeList)));
		}
		DBCursor dbCursor = mongoTemplate.getCollection(TABLE_NAME).find(query1);
		dbCursor.batchSize(100000);
		List<CampaignClient> list = new ArrayList<>();
		while (dbCursor.hasNext()) {
			DBObject object = dbCursor.next();
			CampaignClient campaignClient = new CampaignClient();
			campaignClient.setAgentCode(object.get("agent_code").toString());
			campaignClient.setCampaignId(object.get("campaign_id").toString());
			campaignClient.setClientNum(object.get("client_num").toString());
			campaignClient.setClientName(object.get("client_name").toString());
			campaignClient.setPhoneNum(object.get("mobile_phone_num").toString());
			// te.setTime((Date) object.get("time"));
			list.add(campaignClient);
		}

		return list;

		// return mongoTemplate.find(new Query(criteria),
		// CampaignClient.class,"campaign.target");
	}
}
