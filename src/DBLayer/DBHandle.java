/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBLayer;

import com.mongodb.MongoClient;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import model.Job;

/**
 *
 * @author Ebad Ali
 */
public class DBHandle {

    private DB db = null;

    static DBHandle handle;

    private DBHandle() {
        try {

            // To connect to mongodb server
            MongoClient mongoClient = new MongoClient();

            // Now connect to your databases
            db = mongoClient.getDB("test");
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

    public void AddJob(Job job) {
        try {
            BasicDBObject doc = job.toBasicDBObject();
            DBCollection coll = db.getCollection(job.getClass().getName());
            coll.insert(doc);

            System.out.println("Document inserted successfully");

        } catch (Exception ex) {
        }
    }

    public void Test(String job) {
        try {
            BasicDBObject doc = new BasicDBObject("title", job);
            DBCollection coll = db.getCollection("mycol");
            coll.insert(doc);

            System.out.println("Document inserted successfully");

        } catch (Exception ex) {
        }
    }

    public static DBHandle GetConn() {
        if (handle == null) {
            handle = new DBHandle();
        }

        return handle;

    }
}
