package twitter.extract;

import org.apache.log4j.Level;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;
import org.apache.log4j.Logger;

import twitter4j.Status;

public class ExtractTweets {

	
		

public static void main(String[] args) {
		final String consumerKey = "MTpb00qCVBxgFeP6mGSk5tCIA";
		final String consumerSecret = "3hqomtt1E76S2j48aOylAyFXnPs5FrkDYFH7mIvdcQg35jAfDs";
		final String accessToken = "1037671455373357056-AeBD23QcLsFVsMvqjJOSDzGxs0ModT";
		final String accessTokenSecret = "5Y1W17kF5ZWn4Q1DUzkmh9pimgjPLcc987szriQ8nglPF";
		
		Logger.getLogger("org.apache").setLevel(Level.WARN);
		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("SparkTwitterHelloWorldExample");
        JavaStreamingContext jssc = new JavaStreamingContext(conf, new Duration(30000));
        System.setProperty("twitter4j.oauth.consumerKey", consumerKey);
        System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret);
        System.setProperty("twitter4j.oauth.accessToken", accessToken);
        System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret);

        JavaReceiverInputDStream<Status> twitterStream = TwitterUtils.createStream(jssc);
        
        JavaDStream<String> statuses = twitterStream.map(
                new Function<Status, String>() {
                    public String call(Status status) { return status.getText(); }
                }
        );
        statuses.print();
        jssc.start();
	}

}
