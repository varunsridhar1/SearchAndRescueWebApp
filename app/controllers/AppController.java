package controllers;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import play.mvc.*;

import views.html.*;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoDatabase;
import java.util.*;
import org.bson.Document;
import com.mongodb.client.MongoCollection;

import java.util.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class AppController extends Controller {
    //private static MongoClient mongoClient = new MongoClient(new MongoClientURI("cluster0-shard-00-00-etur1.mongodb.net:27017"));

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result list() {
        //MongoCredential credential = MongoCredential.createCredential("seniordesign", "myDb", "infotheory".toCharArray());
        MongoClientURI uri = new MongoClientURI(
                "mongodb://seniordesign:infotheory@cluster0-shard-00-00-etur1.mongodb.net:27017,cluster0-shard-00-01-etur1.mongodb.net:27017,cluster0-shard-00-02-etur1.mongodb.net:27017/admin?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("mydb");
        String s = database.getName();
        System.out.println(s);
        s= "Adi Miller";
        MongoCollection<Document> collection = database.getCollection("survivor_info");
        System.out.println(collection.count());
        MongoCursor<Document> survivors = collection.find().iterator();
        List<String> ids = new ArrayList<String>();
        List<String> longitudes = new ArrayList<String>();
        List<String> latitudes = new ArrayList<String>();
        List<String> images = new ArrayList<String>();
        Document row;
        while(survivors.hasNext()){
            row = survivors.next();
            ids.add(String.valueOf(row.get("_id")));
            longitudes.add(String.valueOf(row.get("long")));
            latitudes.add(String.valueOf(row.get("lat")));
            images.add(String.valueOf(row.get("image")));
        }
        Document user = collection.find().first();
        System.out.println(user.keySet());
        System.out.println(images.get(0));
        return ok(list.render(ids, longitudes, latitudes, images));
    }
    public Result maps() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb://seniordesign:infotheory@cluster0-shard-00-00-etur1.mongodb.net:27017,cluster0-shard-00-01-etur1.mongodb.net:27017,cluster0-shard-00-02-etur1.mongodb.net:27017/admin?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("mydb");
        String s = database.getName();
        System.out.println(s);
        s= "Adi Miller";
        MongoCollection<Document> collection = database.getCollection("survivor_info");
        System.out.println(collection.count());
        MongoCursor<Document> survivors = collection.find().iterator();
        List<String> ids = new ArrayList<String>();
        List<String> longitudes = new ArrayList<String>();
        List<String> latitudes = new ArrayList<String>();
        List<String> images = new ArrayList<String>();
        Document row;
        while(survivors.hasNext()){
            row = survivors.next();
            ids.add(String.valueOf(row.get("_id")));
            longitudes.add(String.valueOf(row.get("long")));
            latitudes.add(String.valueOf(row.get("lat")));
            images.add(String.valueOf(row.get("image")));
        }
        Document user = collection.find().first();
        System.out.println(user.keySet());
        System.out.println(images.get(0));
        return ok(maps.render(ids, longitudes, latitudes, images));

    }

}