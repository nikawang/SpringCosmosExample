package demo.cosmos.model;

import java.util.List;


/**
 * PsCMSContentRecord
 * 
 * @ClassName PsCMSContentRecord
 * @Description PsCMSContentRecord
 * @author johnjiang
 * @date Apr 23, 2018
 * @version V1.0
 */
public interface PsCMSContentRecord {

	List<ContentRecordSummer> findByContentIdAndUserIdInAndStartTimeAndEndTime(String contentId,
                                                                               List<String> userIdList, long startTime, long endTime);

	void save(ContentRecord contentRecord);
}
