/**
 * Created by Wafae on 20/06/2016.
 */

var User = Backbone.Model.extend({

    initialize: function(attributes) {
        if(_.has(attributes, "createdAt")) {
            this.createdAt = new moment(attributes.createdAt);
            this.set("LastLogin", this.createdAt.fromNow());
        }
    },

    defaults: {
        id: "",
        username: "",
        createdAt: new moment(),
        email: "",
        enabled: "",
        apiKey: "",
        roles: ""
    },

    idAttribute: "id",

    url: "/user"
});
