package demo.cosmos.core;

import com.sun.org.apache.xpath.internal.operations.Mult;
import demo.cosmos.model.ContentRecordSummer;
import demo.cosmos.model.MultiThreadQuery;
import demo.cosmos.model.PsCMSContentRecordImpl;
import demo.cosmos.model.PsCampaignTargetClientImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.springframework.context.support.GenericXmlApplicationContext;

public class Reproducer {
	public static void testContent(ApplicationContext ctx)
	{
		PsCMSContentRecordImpl psCMSContentRecord = (PsCMSContentRecordImpl) ctx.getBean(PsCMSContentRecordImpl.class);

		List<String> userList = new ArrayList<String>();
		String contentId = "99999";

		for(int i=0; i<1000; i++)
		{
			userList.add(i+"");
		}

		psCMSContentRecord.findByContentIdAndUserIdInAndStartTimeAndEndTime(contentId, userList,0 ,9);
	}

	public static void testCampaign(ApplicationContext ctx)
	{
		PsCampaignTargetClientImpl psCampaignTargetClient = (PsCampaignTargetClientImpl) ctx.getBean(PsCampaignTargetClientImpl.class);
		List<String> agentCodeist = new ArrayList<String>();
		for(int i=0;i<100;i++){
			agentCodeist.add(""+i);
		}

		long start = new Date().getTime();
		psCampaignTargetClient.findByCampaignIdAndAgentCodeIn("20",agentCodeist,2);
		System.out.println(new Date().getTime()-start);
	}

	public static void testMultiThread(ApplicationContext ctx)
	{
		MultiThreadQuery multiThreadQuery = (MultiThreadQuery) ctx.getBean(MultiThreadQuery.class);
		multiThreadQuery.runTask();
	}

	public static void main(String[] args) {

		// For XML
		ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");
//		testCampaign(ctx);
		testMultiThread(ctx);
		// For Annotation
//		 ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");


	}

}