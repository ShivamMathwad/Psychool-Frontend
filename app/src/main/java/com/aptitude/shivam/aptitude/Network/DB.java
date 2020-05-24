package com.aptitude.shivam.aptitude.Network;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Aggregates.sort;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.ascending;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bson.Document;

public class DB {

    public static List<String> getQuestionsFromDB(){

        MongoClientURI uri = new MongoClientURI(
                "mongodb://shivammad:<password>@cluster0-shard-00-00-5zsao.mongodb.net:27017,cluster0-shard-00-01-5zsao.mongodb.net:27017,cluster0-shard-00-02-5zsao.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&w=majority");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("psychool");
        MongoCollection<Document> collection = database.getCollection("personality");

        List<String> questionsList = new ArrayList<>();
        Document document = new Document();
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(sort(ascending("id")), project(fields(include("question"), excludeId()))));

        MongoCursor<Document> cursor = result.iterator();
        if (cursor.hasNext()) {
            document = cursor.next();
            questionsList.add(document.getString("question"));
        }

        Log.d("result",questionsList.toString());

       /* for (Document doc: result) {
            questionsList.add(doc.getString("question"));
        }
        */
        return questionsList;
    }
}
