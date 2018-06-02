package demo.cosmos.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * PsCampaignClient
 * 
 * @ClassName PsCampaignClient
 * @Description PsCampaignClient
 * @author johnjiang
 * @date May 1, 2018
 * @version V1.0
 */
public interface PsCampaignTargetClient {

	/**
	 * findByCampaignIdAndAgentCodeIn
	 * 
	 * @Description findByCampaignIdAndAgentCodeIn
	 * @param campaignId
	 * @param agentCodeList
	 * @return
	 * @return List<CampaignClient>
	 */
	List<CampaignClient> findByCampaignIdAndAgentCodeIn(String campaignId, List<String> agentCodeList, int type);

	/**
	 * findByCampaignId
	 * 
	 * @Description findByCampaignId
	 * @param campaignId
	 * @return
	 * @return List<CampaignClient>
	 */
	// List<CampaignClient> findByCampaignId(String campaignId);

}
