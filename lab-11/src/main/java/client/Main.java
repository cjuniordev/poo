package client;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.result.*;
import org.bson.*;
import org.bson.types.*;

public class Main {

    private static final String uri = "mongodb+srv://poo:poo@cluster0.bdnfi4u.mongodb.net/?retryWrites=true&w=majority";
    private static final String databaseName = "ceunes_personal";

    public static void main(String[] args) {

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> collection = database.getCollection("alunos");

            try {
                InsertOneResult result = collection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append("nome", "Carlos Roberto Rogério Junior")
                        .append("matricula", 2021101100));

                System.out.println("Inserido com sucesso: " + result.getInsertedId());
            } catch (MongoException e) {
                System.err.println("erro ao inserir: " + e);
            }
        }

    }
}
