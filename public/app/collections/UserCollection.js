var UsersCollection = Backbone.Collection.extend({

    initialize: function (parameters) {
        if (_.isUndefined(parameters)) {
        } else {
            this.page = parameters.page;
            this.perPage = parameters.perPage;
        }
    },

    model: Message,

    url: function () {
        if(! _.isNull(this.page) && _.isNull(this.perPage)) {
            return "/users/" + this.page;
        } else if (! _.isNull(this.page) && ! _.isNull(this.perPage)) {
            return "/users/" + this.page + "/" + this.perPage;
        } else {
            return "/users";
        }
    },

    comparator: function (a, b) {
        a = a.get(this.createdAt);
        b = b.get(this.createdAt);
        return a > b ? -1
            : a < b ? 1
            : 0;
    },

    parse: function (response) {
        return _.map(response.content, function (model) {
            return new User(model);
        });
    }
});