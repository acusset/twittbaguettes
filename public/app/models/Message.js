/**
 * Created by Hugo on 21/06/2016.
 */

var Message = Backbone.Model.extend({

    initialize: function () {
        
    },

    defaults: {
        id: "",
        content: "Default message",
        createdAt: "",
        img: "",
        url: "",
        author: ""
    },

    idAttribute: "id",

    urlRoot: "/message",

    url: function () {
        return this.urlRoot + "?id=" + this.id
    }
});