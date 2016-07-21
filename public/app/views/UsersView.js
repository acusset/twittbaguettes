var UsersView = Backbone.View.extend({

    model: UsersCollection,

    el: "div#container",

    render: function () {

        this.$el.html(""); // lets render this view

        for (var i = 0; i < this.model.length; ++i) {
            var userView = new UserView({model: this.model.at(i)});

            this.$el.append(userView.$el);
            userView.render();
        }
        return this;
    }

});