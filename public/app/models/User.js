var User = Backbone.Model.extend({

    initialize: function(attributes) {
        this.createdAt = new moment(attributes.createdAt);
        this.set("LastLogin", this.createdAt.fromNow());
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

    urlRoot: "/user"
});