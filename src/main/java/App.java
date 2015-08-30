import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import static java.lang.System.out;
import java.lang.*;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App{
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/",  (request, reponse) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/clients.vtl");

      model.put("clients", Client.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients/new", (request, reponse) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/client-form.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/clients", (request, reponse) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String client = request.queryParams("client");
      Client newClient = new Client(client);
      newClient.save();

      model.put("client", newClient);
      model.put("template", "templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients/:id/stylists/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/stylist-form.vtl");

      model.put("client", Client.find(Integer.parseInt(request.params(":id"))));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/stylist.vtl");

      String name = request.queryParams("name");
      String hours = request.queryParams("hours");
      String contact_info = request.queryParams("contact_info");
      int rating = Integer.parseInt(request.queryParams("rating"));

      Stylist newStylist = new Stylist(name, hours, contact_info, rating);
      newStylist.save();

      model.put("client", Client.find(Integer.parseInt(request.queryParams("client_id"))));
      model.put("stylist", newStylist);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients/:id/stylists", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();


      model.put("client", Client.find(Integer.parseInt(request.params(":id"))));
      model.put("Stylists", Stylist.allByClient(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/stylists.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients/:client_id/stylists/:stylist_id/info", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/info.vtl");

      model.put("client", Client.find(Integer.parseInt(request.params(":client_id"))));
      model.put("stylist", Stylist.find(Integer.parseInt(request.params(":stylist_id"))));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients/:client_id/stylists/:stylist_id/update", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/update.vtl");

      model.put("client", Client.find(Integer.parseInt(request.params(":client_id"))));
      model.put("stylist", Stylist.find(Integer.parseInt(request.params(":stylist_id"))));
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/info", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String name = request.queryParams("name");
      String hours = request.queryParams("hours");
      String contact_info = request.queryParams("contact_info");
      int rating = Integer.parseInt(request.queryParams("rating"));

      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylist_id")));

      stylist.updateName(name);
      stylist.updateHours(hours);
      stylist.updateContact_info(contact_info);
      stylist.updateRating(rating);

      model.put("stylist", Stylist.find(Integer.parseInt(request.queryParams("stylist_id"))));
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients/:client_id/stylists/:stylist_id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Client client = Client.find(Integer.parseInt(request.params(":client_id")));
      model.put("client", client);
      String cuisine_id = request.params(":cuisine_id");

      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylist_id")));
      stylist.delete();
      // model.put("template", "templates/delete.vtl");
      response.redirect("/clients/" + client_id + "/stylists");
      return null;
    });

    get("/clients/:client_id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Client client = Client.find(Integer.parseInt(request.params(":client_id")));
      client.delete();

      response.redirect("/clients" );
      return null;
    });



  }



}
