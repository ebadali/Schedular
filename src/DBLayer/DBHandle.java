/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBLayer;

import com.mongodb.BasicDBList;
import com.mongodb.MongoClient;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Inventory;
import model.InventoryTransaction;
import model.Job;
import org.apache.commons.beanutils.BeanMap;
import org.bson.Document;

/**
 *
 * @author Ebad Ali
 */
public class DBHandle {

    private MongoDatabase db = null;
    MongoCollection<Document> job, inventory, inventoryTransaction;
    static DBHandle handle;
    
    final String INVENTORY = "inventory", JOB ="job" , INVENTORYSTORE = "inventoryTransaction";

    private DBHandle() {
        try {

            // To connect to mongodb server
            // Now connect to your databases
//            db = mongoClient.getDB("test");
            MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));

            db = mongoClient.getDatabase("schedule");
            job = db.getCollection(JOB);           
            inventory = db.getCollection(INVENTORY);

            inventoryTransaction = db.getCollection(INVENTORYSTORE);

            System.out.println("Connect to database successfully");

//         boolean auth = db.authenticate(myUserName, myPassword);
//         System.out.println("Authentication: "+auth);         
//            DBCollection coll = db.getCollection("mycol");
//            System.out.println("Collection mycol selected successfully");
//
//            BasicDBObject doc = new BasicDBObject("title", "MongoDB").
//                    append("description", "database").
//                    append("likes", 100).
//                    append("url", "http://www.tutorialspoint.com/mongodb/").
//                    append("by", "tutorials point");
//
//            coll.insert(doc);
//            System.out.println("Document inserted successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void Test(String job) {
        try {
            BasicDBObject doc = new BasicDBObject("title", job);
//            DBCollection coll = db.getCollection("mycol");
//            coll.insert(doc);

            System.out.println("Document inserted successfully");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
    }

    public static DBHandle GetConn() {
        if (handle == null) {
            handle = new DBHandle();
        }
        return handle;

    }

    public void AddJob(Job jobObj) {

        try {
            Document d = jobObj.toBasicDBObject();
            System.out.println("Gonna Insert here");

            db.getCollection(JOB).insertOne(d);
            System.out.println("Document inserted successfully");
            System.out.println(jobObj.toBasicDBObject());

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
    }

    public List<Job> GetJobs() {
        final List<Job> listOfJobs = new ArrayList<>();

//        FindIterable<Document> iterable = job.find();
        FindIterable<Document> iterable = db.getCollection(JOB).find().limit(50);
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                listOfJobs.add(new Job(document));
//                System.out.println(document);
            }
        });

//        DBCursor cursor = this.job.find();
// 
//        while (cursor.hasNext()) {
//            DBObject theObj = cursor.next();
//            //How to get the DBObject value to ArrayList of Java Object?
////            if( theObj.get("Job") ! )
//            System.out.println(theObj);
////            BasicDBList studentsList = (BasicDBList) theObj.get("Job");
////            for (Object studentsList1 : studentsList) {
////                BasicDBObject obj = (BasicDBObject) studentsList1;
////                listOfJobs.add(new Job(obj));
////            }      
//        }
        return listOfJobs;
    }

    public void SetJob(Job job, Job old) {
        try {
//            Document doc = new Document().
//                    append("$set", job.toBasicDBObject());

            System.out.println(old.toBasicDBObject());
            System.out.println(job.toBasicDBObject());
//            db.getCollection(JOB).updateOne(eq("name", "frank"), new Document("$set", new Document("age", 33)));

            UpdateResult res = db.getCollection(JOB).replaceOne(old.toBasicDBObject(), job.toBasicDBObject());
            System.out.println("Document inserted successfully with " + res.toString());
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.err.println(e.toString());

        }
    }

    public void AddInventory(Inventory newinvent) {

        try {
            db.getCollection(INVENTORY).insertOne(newinvent.toDocument());
            System.out.println("Inventory inserted successfully");


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
    }

    public void UpdateInventory(Inventory newinvent, Inventory oldinvent) {
        
        try {
            UpdateResult res = db.getCollection(INVENTORY).replaceOne(oldinvent.toDocument(), newinvent.toDocument());
            System.out.println("Document inserted successfully with " + res.toString());
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            

        }
    }

}
