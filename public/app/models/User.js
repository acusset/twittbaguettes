/**
 * Created by Wafae on 20/06/2016.
 */

var User = Backbone.Model.extend({

    initialize: function() {
        console.log("Initialisation d'un user");
    },

    defaults: {
        id: "",
        username: "",
        createdAt: "",
        email: "",
        enabled: "",
        apiKey: "",
        roles: ""
    },

    idAttribute: "id",

    urlRoot: "/user",

    url: function () {
        return this.urlRoot + "?id=" + this.id
    }
});
