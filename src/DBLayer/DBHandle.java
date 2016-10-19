/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBLayer;

import com.mongodb.MongoClient;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Inventory;
import model.InventoryTransaction;
import model.Job;
import org.bson.Document;

/**
 *
 * @author Ebad Ali
 */
public class DBHandle {

    private MongoDatabase db = null;
    MongoCollection<Document> job, inventory, inventoryTransaction;
    static DBHandle handle;

    final String INVENTORY = "inventory", JOB = "job", INVENTORYSTORE = "inventoryTransaction";

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
        return listOfJobs;
    }

    public void SetJob(Job job, Job old) {
        try {
//            Document doc = new Document().
//                    append("$set", job.toBasicDBObject());

            System.out.println(old.toBasicDBObject());
            System.out.println(job.toBasicDBObject());

            BasicDBObject query = new BasicDBObject().append("jobWorkOrder", old.getJobWorkOrder());

            UpdateResult res = db.getCollection(JOB).replaceOne(query, job.toBasicDBObject());
            System.out.println("Document Replaced successfully with " + res.toString());
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
            BasicDBObject query = new BasicDBObject().append("inventoryMaterial", oldinvent.getInventoryMaterial());

            UpdateResult res = db.getCollection(INVENTORY).replaceOne(query, newinvent.toDocument());
            System.out.println("Inventory Updated successfully with " + res.toString());
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
    }

    public void RemoveJob(Job old) {
        try {

            System.out.println(old.toBasicDBObject());
            BasicDBObject query = new BasicDBObject().append("jobWorkOrder", old.getJobWorkOrder());
            DeleteResult res = db.getCollection(JOB).deleteOne(query);
            System.out.println("Document Removed successfully with " + res.toString());
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.err.println(e.toString());

        }
    }

    public List<Job> GetJobsOfDate(LocalDate someDate) {
        final List<Job> listOfJobs = new ArrayList<>();

        FindIterable<Document> iterable = db.getCollection(JOB).find(
                new Document("Job.jobTemplateDate", new Document("$lt", Job.dateToString(someDate))));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                listOfJobs.add(new Job(document));
//                System.out.println(document);
            }
        });

        return listOfJobs;

    }

    public List<Inventory> LoadInventories() {
        final List<Inventory> listOfJobs = new ArrayList<>();

//        FindIterable<Document> iterable = job.find();
        FindIterable<Document> iterable = db.getCollection(INVENTORY).find().limit(50);
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                listOfJobs.add(new Inventory(document));
            }
        });
        return listOfJobs;
    }

    public List<InventoryTransaction> LoadInventoryTransaction() {
        final List<InventoryTransaction> listOfJobs = new ArrayList<>();
        FindIterable<Document> iterable = db.getCollection(INVENTORYSTORE).find().limit(50);
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                listOfJobs.add(new InventoryTransaction(document));
            }
        });
        return listOfJobs;
    }
    
    public void AddInventoryTransaction(InventoryTransaction transaction)
    {
                try {
            db.getCollection(INVENTORYSTORE).insertOne(transaction.toDocument());
            System.out.println("InventoryTransaction inserted successfully");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }

    }
}
