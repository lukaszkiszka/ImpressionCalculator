package pl.lkiszka.core.algorithm;

import static org.junit.Assert.assertEquals;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pl.lkiszka.core.algorithm.impl.MaximizationImpressionProfit;
import pl.lkiszka.core.model.InputImpression;
import pl.lkiszka.core.model.InputImpressionData;
import pl.lkiszka.core.model.OutputImpression;

/**
 * Created by lukas on 06.01.2016.
 */
public class MaximizationImpressionProfitTest {

	@Test
	public void shouldPassExample1() throws Exception {

		InputImpression inputImpression = new InputImpression();
		inputImpression.setMounthImpressionQuantity(32356000);

		List<InputImpressionData> inputImpressionDatas = new ArrayList<InputImpressionData>();

		InputImpressionData client1 = new InputImpressionData();
		client1.setClientName("Test1");
		client1.setImpressionInCampaignQuantity(2000000);
		client1.setCampaignPrice(200);
		inputImpressionDatas.add(client1);

		InputImpressionData client2 = new InputImpressionData();
		client2.setClientName("Test2");
		client2.setImpressionInCampaignQuantity(3500000);
		client2.setCampaignPrice(400);
		inputImpressionDatas.add(client2);

		InputImpressionData client3 = new InputImpressionData();
		client3.setClientName("Test3");
		client3.setImpressionInCampaignQuantity(2300000);
		client3.setCampaignPrice(210);
		inputImpressionDatas.add(client3);

		InputImpressionData client4 = new InputImpressionData();
		client4.setClientName("Test4");
		client4.setImpressionInCampaignQuantity(8000000);
		client4.setCampaignPrice(730);
		inputImpressionDatas.add(client4);

		InputImpressionData client5 = new InputImpressionData();
		client5.setClientName("Test5");
		client5.setImpressionInCampaignQuantity(10000000);
		client5.setCampaignPrice(1000);
		inputImpressionDatas.add(client5);

		InputImpressionData client6 = new InputImpressionData();
		client6.setClientName("Test6");
		client6.setImpressionInCampaignQuantity(1500000);
		client6.setCampaignPrice(160);
		inputImpressionDatas.add(client6);

		InputImpressionData client7 = new InputImpressionData();
		client7.setClientName("Test7");
		client7.setImpressionInCampaignQuantity(1000000);
		client7.setCampaignPrice(100);
		inputImpressionDatas.add(client7);

		inputImpression.setInputImpressionDatas(inputImpressionDatas);

		MaximizationImpressionProfit maximizationImpressionProfit = new MaximizationImpressionProfit();

		OutputImpression outputImpression = maximizationImpressionProfit.run(inputImpression);

		assertEquals(32000000L, outputImpression.getImpressionSummary().longValue());
		assertEquals(3620L, outputImpression.getMonthlyTotalProfit().longValue());
	}

	@Test
	public void shouldPassExample2() throws Exception {

		InputImpression inputImpression = new InputImpression();
		inputImpression.setMounthImpressionQuantity(50000000);

		List<InputImpressionData> inputImpressionDatas = new ArrayList<InputImpressionData>();

		InputImpressionData client1 = new InputImpressionData();
		client1.setClientName("Test1");
		client1.setImpressionInCampaignQuantity(1);
		client1.setCampaignPrice(0);
		inputImpressionDatas.add(client1);

		InputImpressionData client2 = new InputImpressionData();
		client2.setClientName("Test2");
		client2.setImpressionInCampaignQuantity(2);
		client2.setCampaignPrice(2);
		inputImpressionDatas.add(client2);

		InputImpressionData client3 = new InputImpressionData();
		client3.setClientName("Test3");
		client3.setImpressionInCampaignQuantity(3);
		client3.setCampaignPrice(2);
		inputImpressionDatas.add(client3);

		InputImpressionData client4 = new InputImpressionData();
		client4.setClientName("Test4");
		client4.setImpressionInCampaignQuantity(70000);
		client4.setCampaignPrice(71000);
		inputImpressionDatas.add(client4);

		InputImpressionData client5 = new InputImpressionData();
		client5.setClientName("Test5");
		client5.setImpressionInCampaignQuantity(49000000);
		client5.setCampaignPrice(50000000);
		inputImpressionDatas.add(client5);

		inputImpression.setInputImpressionDatas(inputImpressionDatas);

		MaximizationImpressionProfit maximizationImpressionProfit = new MaximizationImpressionProfit();
		OutputImpression outputImpression = maximizationImpressionProfit.run(inputImpression);

		assertEquals(50000000L, outputImpression.getImpressionSummary().longValue());
		assertEquals(51014000L, outputImpression.getMonthlyTotalProfit().longValue());

	}

	@Test
	public void shouldPassExample3() throws Exception {

		InputImpression inputImpression = new InputImpression();
		inputImpression.setMounthImpressionQuantity(2000000000);

		List<InputImpressionData> inputImpressionDatas = new ArrayList<InputImpressionData>();

		InputImpressionData client1 = new InputImpressionData();
		client1.setClientName("Test1");
		client1.setImpressionInCampaignQuantity(1000000);
		client1.setCampaignPrice(5000);
		inputImpressionDatas.add(client1);

		InputImpressionData client2 = new InputImpressionData();
		client2.setClientName("Test2");
		client2.setImpressionInCampaignQuantity(2000000);
		client2.setCampaignPrice(9000);
		inputImpressionDatas.add(client2);

		InputImpressionData client3 = new InputImpressionData();
		client3.setClientName("Test3");
		client3.setImpressionInCampaignQuantity(3000000);
		client3.setCampaignPrice(20000);
		inputImpressionDatas.add(client3);

		inputImpression.setInputImpressionDatas(inputImpressionDatas);

		MaximizationImpressionProfit maximizationImpressionProfit = new MaximizationImpressionProfit();
		OutputImpression outputImpression = maximizationImpressionProfit.run(inputImpression);

		assertEquals(2000000000L, outputImpression.getImpressionSummary().longValue());
		assertEquals(13330000L, outputImpression.getMonthlyTotalProfit().longValue());

	}

}