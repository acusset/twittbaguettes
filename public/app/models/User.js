/**
 * Created by Wafae on 20/06/2016.
 */

var User = Backbone.Model.extend({

    initialize: function(attributes) {
        this.on("change", function (route, params) {
            if (_.has(attributes, "createdAt")) {
                this.createdAt = new moment(attributes.createdAt);
                this.joinDate = this.createdAt.format("DD MMMM YYYY");
            }
        });
    },

    defaults: {
        id: "",
        joinDate: "",
        createdAt: "",
        email: "",
        enabled: "",
        apiKey: "",
        roles: ""
    },

    idAttribute: "id",

    urlRoot: "/user"
});
