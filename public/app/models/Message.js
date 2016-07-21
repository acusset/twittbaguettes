var Message = Backbone.Model.extend({

    initialize: function (data) {
        this.createdAt = new moment(data.createdAt);
        this.set("fromNow", this.createdAt.fromNow());
    },

    defaults: {
        content: "Default message",
        createdAt: "",
        updatedAt: "",
        img: "",
        url: "",
        author: "",
        fromNow: ""
    },

    idAttribute: "id",

    urlRoot: "/message"
});