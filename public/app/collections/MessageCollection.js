/**
 * Created by Antoine on 29/06/2016.
 */

window.MessagesCollection = Backbone.Collection.extend({

    model: Message,
    url: "/messages",
    parse : function(response){
        //api returns objects in the content attribute of response, need to override parse and cast
        return _.map(response.content, function(model, id) {
            return new Message(model);
        });
    }
});
