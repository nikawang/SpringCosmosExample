package demo.cosmos.model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sun.org.apache.xml.internal.utils.MutableAttrListImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MultiThreadQuery{

	 private final static String TABLE_NAME = "t_campaign";
	@Autowired
	private PsCampaignTargetClientImpl psCampaignTargetClient;

	public void runTask()
	{
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			Runnable worker = new WorkThread(i +"");
			executor.execute(worker);
		}
//		executor.shutdown();
		while (!executor.isTerminated()) {
		}
	}

	class WorkThread implements Runnable{
		private String command;

		WorkThread(String s){
			this.command=s;
		}

		@Override
		public void run() {
			System.out.print(command+"command Start");
			List<String> agentCodeist = new ArrayList<String>();
			for(int i=0;i<100;i++){
				agentCodeist.add(""+i);
			}
			psCampaignTargetClient.findByCampaignIdAndAgentCodeIn("20",agentCodeist,2);
			System.out.println(command+"command End");
		}
	}


}
